class Solution {
    public String foreignDictionary(String[] words) {
        // explanation given in notes
        // adj lisy
        Map<Character , Set<Character>> adjList = new HashMap<>();
        // in degree 
        Map<Character , Integer> in_degree = new HashMap<>();

        // initialzation
        for(String word : words){
            for(char c : word.toCharArray()){
                adjList.putIfAbsent(c , new HashSet<>());
                in_degree.putIfAbsent(c , 0);
            }
        }

        // nnow graph formation
        for(int i = 0; i < words.length - 1; i++){
            String w1 = words[i];
            String w2 = words[i + 1];

            // edge case
            if(w1.length() > w2.length() && w1.startsWith(w2)){
                return "";
            }

            int minSize = Math.min(w1.length() , w2.length());
            for(int j =0; j < minSize; j++){
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                // first miss match only forms rules
                if( c1 != c2){
                    if(!adjList.get(c1).contains(c2)){
                       adjList.get(c1).add(c2);
                       // in degree for c2 
                       in_degree.put(c2 , in_degree.get(c2) + 1); 
                    }
                    // after first mistmatch we break
                    break;
                }
            }
        }

        // now the imporant toposort bfs part
        Queue<Character> q = new LinkedList<>();
        // first add 0 in degree char nodes
        for( char c : in_degree.keySet()){
            if(in_degree.get(c) == 0){
                q.add(c);
            }
        }

        // now it starts
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            char curr = q.poll();
            sb.append(curr);

            for(char neighbour : adjList.get(curr)){
                // first reduce in degre of neighbour
                in_degree.put(neighbour , in_degree.get(neighbour) - 1);
                // after reducing if it reaches 0 then
                if(in_degree.get(neighbour) == 0){
                    q.add(neighbour);
                }
            }
        }
        // imp edge case
        if(sb.length() < adjList.size()){
            return "";
        }
        return sb.toString();
    }
}

