class LRUCache {

    static class Node{
       int key;
       int value;

       Node prev;
       Node next;

        Node(int key , int value){
            this.key = key;
            this.value = value;
       }  
    }

    // variables(global)
    private final int capacity;
    private final HashMap<Integer , Node> cache;
    private final Node left;
    private final Node right;


    public LRUCache(int capacity) {
        // first this is an constructor of class so , initalize the paramter
        this.capacity = capacity;

        cache = new HashMap<>();
        // now intilaize the dummy nodes;
        left = new Node(0,0); 
        right = new Node(0,0); 

        // now connect these both nodes
        left.next = right;
        right.prev = left;
    }

    // now very very important helper methods
    public void insert(Node head){
        // we r inserting the values new ones 
        // so the new values should come to the right side dummy node because this node was recenlty used node thus
        Node prevNode = right.prev;
        Node NextNode = right;

        // now make the required attacked by placing the new inseted node right betwwen right dummy node and before prev node of the right dummy node
        prevNode.next = head;
        NextNode.prev = head;

        //now for the attachments of the inseting node
        head.prev = prevNode;
        head.next = NextNode;
    }

    public void remove(Node head){
        Node prevNode = head.prev;
        Node nextNode = head.next;

        // now attack the prev node and nextnode so we can remove the node
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
    
    public int get(int key) {
        // first check if the node is avaibale aldready in the ll ( we are using hashmap to c it is present or not)
        if(cache.containsKey(key)){
            // if it is avaibale first we remove the node 
            // and we again insert it ( because it is now become a recently used one so insert at right)
            Node node = cache.get(key);
            remove(node);
            insert(node);
            // now we return its value
            return node.value;
        }
        // if the key is not present then
        return -1; 
    }
    
    public void put(int key, int value) {
        // first check if the node is avaibale aldready in the ll ( we are using hashmap to c it is present or not)
        if(cache.containsKey(key)){
            // if the key is present first we remove it
            remove(cache.get(key));
        }

        // why we r removing is because we r inserting the updated value of the key so
        Node node = new Node(key,value);
        // first we update the newy key and node value in the hashmap
        cache.put( key, node);
        // now we inser the node
        insert(node); 
        // very very important step after inserting if th size of the hashmap have exceeded the capacity then we delete the lest recenlty used chache 
        if (cache.size() > capacity) {
            Node lru = left.next; 
            remove(lru);
            cache.remove(lru.key); 
        } 
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */