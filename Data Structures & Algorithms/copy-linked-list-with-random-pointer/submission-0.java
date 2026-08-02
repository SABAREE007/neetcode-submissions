/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // edge case: 
        if(head == null){
            return null;
        }

        // this is an optimal approach -> 3 pass solution
        // pass 1: creation of the copy nodes and attaching it bettwen the cureent node and next node
        Node curr = head;
        while(curr != null){
            Node nextNode = curr.next;  // get the next node before itself 
            Node copy = new Node(curr.val);
            // now we have created the copy , next we innsert it in the middle of the original list
            curr.next = copy;
            copy.next = nextNode;
            // now move to next original node to perform the actions
            curr = nextNode;
        }

        // pass 2: very very important step:
        // now we r assigning the random pointers whihc are pointng to random nodes of original and it should also behave for the copy nodes also
        curr = head;
        while(curr != null){
            if(curr.random != null){
                // very very important formula step: connecting the random of the copy ll based on original
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next; // now move to next original node
        }

        // pass 3: now we have to make the connections for the next node pounter pf the copy list and bringing back the original linked list back to its original form
        curr = head;
        Node dummy = new Node(-1);
        Node copyTail = dummy;
        while(curr != null){
            //very very imporatnt steps follows
            Node nextOriginal = curr.next.next; // get the original current's next node
            Node copyNode = curr.next; // get the copy node's head from the cuur(whihc is only for starting)

            copyTail.next = copyNode;
            copyTail = copyNode;   // this is the formation of the next pointers of the deep copy linked list

            // now return the original list's next pointer and make the connection return
            curr.next = nextOriginal;
            // now move to next original node
            curr = nextOriginal; 
        }
        // return the head of the copy ll's head
        return dummy.next;
    }
}