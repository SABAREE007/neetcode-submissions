class MyCircularQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    // Initialize the queue with a fixed size k
    public MyCircularQueue(int k) {
        queue = new int[k];
        capacity = k;
        front = 0;
        rear = -1; // Starts at -1 so the first element lands at index 0
        size = 0;
    }
    
    // Insert an element into the circular queue
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        // 🔄 The Circular Jump: Move rear forward, wrapping around to 0 if it hits the end
        rear = (rear + 1) % capacity;
        queue[rear] = value;
        size++;
        return true;
    }
    
    // Delete an element from the circular queue
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        // 🔄 The Circular Jump: Move front forward, wrapping around if needed
        front = (front + 1) % capacity;
        size--;
        return true;
    }
    
    // Get the front item from the queue
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[front];
    }
    
    // Get the last item from the queue
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[rear];
    }
    
    // Check whether the circular queue is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Check whether the circular queue is full
    public boolean isFull() {
        return size == capacity;
    }
}


/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */