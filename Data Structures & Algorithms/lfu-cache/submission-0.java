// very very important question -> before this complete lru cache
class LFUCache {

    static class Node{
        int key , value , frequency;
        Node next , prev;
        // constructor
        Node(int key , int value){
            this.key = key;
            this.value = value;
            this.frequency = 1; // for every node when creating -> starting frequency is 1
        }
    }
    //Doubly Linked List to handle LRU sub-ties inside a frequency lane
    static class DoublyLinkedList{
        Node left , right;
        //constructor
        DoublyLinkedList(){
            // very time u call this constructor -> it has aldready ;eft and right dummy nodes like how we used in lru cache
            left = new Node(0,0);
            right = new Node(0,0);
            // connections of left and right dummy
            left.next = right;
            right.prev = left;
        }
        // now methods we use when using this dll class
        // first is remove
        public void removeNode(Node node){
            Node prevNode = node.prev;
            Node nextNode = node.next;
            // remove the node by cpnnection prev and next
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        // this method is the insert the node on the most recently used side(right dummy node side)
        public void insertAtMRU(Node node){
            Node prevNode = right.prev;
            Node nextNode = right;
            // now insert the respective node at the end of right side(before the right dummy)
            prevNode.next = node;
            nextNode.prev = node;
            // important
            node.prev = prevNode;
            node.next = nextNode;
        }
        // to check if empty or not
        boolean isEmpty(){
            return left.next == right;  // return true if empty or ese false
        }
    }
    // now public important variables
    private final int capacity;
    private int minfrequency;  // tracks the absolute lowest frewncy 
    private final HashMap<Integer , Node> cacheMap;  // this map is to c how mnay nodes
    private final HashMap<Integer , DoublyLinkedList> freqMap;  // this map stores the respective dll for the respective frequency

    public LFUCache(int capacity) {
        // very very important
        this.capacity = capacity;
        minfrequency = 0;
        cacheMap = new HashMap<>();
        freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        // if the key  value pair is not found then return -1
        if(!cacheMap.containsKey(key)){
            return -1;
        }
        // if it is there then -> get that node and  updates its frequcny
        Node node = cacheMap.get(key);
        updateFrequency(node);  // trigger the updation sequence( where it goes to new frequency dll)
        return node.value;  // return the value of that node

    }
    
    public void put(int key, int value) {
        // this functios is used to put the key,vlaue pair insdie lfu cache
        // if capacity is 0 then no use
        if(capacity == 0) {return;}
        // if the pair is present
        if(cacheMap.containsKey(key)){
            // if it exits then update the value of the node and update its frenqwuncy and go to its level
            Node node = cacheMap.get(key);
            node.value = value;  // update the new value given by user
            updateFrequency(node);  // update the frequncy of node and go to next higher level
        }
        else{
            // in this case -> there are 2 possibilies if they capcity is full then we have to eliminate the lru one (or) add the new node
            if(cacheMap.size() == capacity){
                DoublyLinkedList minFreqList = freqMap.get(minfrequency);
                // get the victim node from the current frequenct levrl
                Node victim = minFreqList.left.next;
                // now remove the node from the cache map and freqmap
                freqMap.get(minfrequency).removeNode(victim);
                // remove from the cache map
                cacheMap.remove(victim.key);
            }
            // now add the new node to the cache map
            Node newNode = new Node(key , value);
            cacheMap.put(key , newNode);
            // since new node so
            minfrequency = 1;

            // now if the frequecny level is not created create a new one
            freqMap.putIfAbsent(minfrequency , new DoublyLinkedList());
            // now to that freqncy level , addd that node on the right most freqent one
            freqMap.get(minfrequency).insertAtMRU(newNode);
        }
    }

    // very very very important helper function used in the above fucntions
    public void updateFrequency(Node node){
        int oldFreq = node.frequency;
        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.removeNode(node); // Unhook from current frequency lane

        // Critical Check: If the minimum frequency lane becomes completely dry, 
        // and it was the current global minimum, push the min Frequency marker up by 1 level
        if (oldFreq == minfrequency && oldList.isEmpty()) {
            minfrequency++;
        }

        // Upgrade node internal counter metrics
        node.frequency++;
        int newFreq = node.frequency;
        
        // Fly into the higher frequency list lane mapping
        freqMap.putIfAbsent(newFreq, new DoublyLinkedList());
        freqMap.get(newFreq).insertAtMRU(node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */