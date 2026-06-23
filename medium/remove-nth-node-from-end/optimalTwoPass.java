/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        int length = 0;

        while(current != null){
            length++;
            current = current.next;
        }

        int positionToRemove = length - n + 1;

        if(positionToRemove == 1) return head.next;

        current = head;
        
        for(int i = 1; i < positionToRemove - 1; i++){
            current = current.next;
        }

        current.next = current.next.next;

        return head;

    }
}
