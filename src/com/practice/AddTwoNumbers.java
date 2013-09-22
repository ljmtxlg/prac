package com.practice;


public class AddTwoNumbers {
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = null, next = null, current = null;
        while(l1 != null || l2 != null) {
            next = new ListNode(getVal(l1) + getVal(l2));
        	
        	if(current != null)
        		current.next = next;
        	current = next;
        	
        	if(l3 == null) {
        		l3 = next;
        	}
        	
        	if(l1 != null) l1 = l1.next;
        	if(l2 != null) l2 = l2.next;
        }
        
        next = l3; 
        int bit = 0;
        while(next != null) {
        	next.val += bit;
            if(next.val < 10)
                bit = 0;
            else {
            	next.val -= 10;
                bit = 1;
            }
            current = next;
            next = next.next;
        }
        if(bit == 1) {
        	current.next = new ListNode(1);
        }
        
        return l3;
    }
    
    public int getVal(ListNode ln) {
    	if(ln == null)
    		return 0;
    	else 
    		return ln.val;
    }
    
    public ListNode reverse(ListNode ln) {
    	ListNode pre = null, tmp = null;
    	while(ln != null) {
    		tmp = ln.next;
    		ln.next = pre;
    		pre = ln;
    		ln = tmp;
    	}
    	
    	return pre;
    }
    
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
    
    public static void main(String[] args) {
    	ListNode l1 = new ListNode(1);
    	ListNode l12 = new ListNode(8);
//    	ListNode l13 = new ListNode(3);
    	l1.next = l12;
//    	l12.next = l13;
    	ListNode l2 = new ListNode(0);
//    	ListNode l22 = new ListNode(6);
//    	ListNode l23 = new ListNode(4);
//    	l2.next = l22;
//    	l22.next = l23;
    	
    	ListNode l3 = new AddTwoNumbers().addTwoNumbers(l1, l2);
    	while(l3 != null) {
    		System.out.println(l3.val);
    		
    		l3 = l3.next;
    	}
    }
    
}
