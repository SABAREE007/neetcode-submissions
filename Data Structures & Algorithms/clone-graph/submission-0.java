/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {

    // in any graph question , it is very veyr important to have a visited track of nodes so that u dont visit a node which is aldready visited

    Map<Node , Node> visited = new HashMap<>(); 


    public Node cloneGraph(Node node) {
        // this is an easy question and brillaint question
        // we have given the constructors and starting node 
        // which makes this easy

        // this will act as a recursive fucntion

        // edge case and base case:
        if(node == null){
            return null;
        }

        // veyr very imporant case; if we viiste a node which is aldready visted then we return it
        if(visited.containsKey(node)){
            return visited.get(node);   
        }
        
        Node clone = new Node(node.val);

        // Mark as visited by saving the clone 
        visited.put(node , clone);

        // now we have created the node , now lets connect the adjacent node to the copy node
        for(Node neighbour : node.neighbors){
            clone.neighbors.add( cloneGraph(neighbour) ); // so now for each node's clone we attack its neighbours by recursily calling them
        }

        return clone;
    }
}