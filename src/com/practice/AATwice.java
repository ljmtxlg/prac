package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AATwice {
    private class Node {
        int index;
        int data;
        
        public Node(int index, int data) {
            this.index = index;
            this.data = data;
        }
    }
    
    public int[] twoSum(int[] numbers, int target) {
        List<Node> nodes = new ArrayList<Node>(numbers.length);
        for(int i = 0; i < numbers.length; i++) {
            nodes.add(new Node(i+1, numbers[i]));
        }
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.data - o2.data;
            }
        });
        
        int i = 0, j = numbers.length - 1;
        Node first = null, second = null;
        while(i < j) {
            first = nodes.get(i);
            second = nodes.get(j);
            
            if((first.data + second.data) == target) {
                if(first.index < second.index) {
                    return new int[] {first.index, second.index};
                } else {
                    return new int[] {second.index, first.index};
                }
            } else if((first.data + second.data) > target) {
                j--;
            } else {
                i++;
            }
        }
        
        return null;
    }
    
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        if(strs.length > 0) {
            prefix = strs[0];
            int mark = prefix.length();
            for(int i = 1; i < strs.length; i++) {
                int j = 0;
                for(; j < mark && j < strs[i].length(); j++) {
                    if(prefix.charAt(j) != strs[i].charAt(j)) {
                        break;
                    }
                }
                mark = j;
            }
            
            prefix = prefix.substring(0, mark);
        }
        
        return prefix;
    }
    
    //only one appear once, others twice.
    public int singleNumber(int[] A) {
        if(A.length > 0) {
            for(int i = 1; i < A.length; i++) {
                A[0] ^= A[i];
            }
            
            return A[0];
        }
        return 0;
    }
    
    //"this is blue sky"->"sky blue is this"
    public String reverseWords(String s) {
        String[] splits = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = splits.length-1; i >= 0; i--) {
            splits[i] = splits[i].trim();
            if(splits[i].length() > 0) {
                sb.append(splits[i]).append(" ");
            }
        }
        
        return sb.toString().trim();
    }
    
    public int evalRPN(String[] tokens) {
        Stack<Integer> ops = new Stack<>();
        int eval = 0, op1 = 0, op2 = 0;
        for(String token : tokens) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                op2 = ops.pop();
                op1 = ops.pop();
                switch(token) {
                    case "+":
                        eval = op1 + op2;
                        break;
                    case "-":
                        eval = op1 - op2;
                        break;
                    case "*":
                        eval = op1 * op2;
                        break;
                    case "/":
                        eval = op1 / op2;
                        break;
                }
                ops.push(eval);
            } else {
                ops.push(Integer.valueOf(token));
            }
        }
        
        return ops.pop();
    }
    
    /**
     * Definition for a point.
     */
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    
    //find max count that points in a line
    public int maxPoints(Point[] points) {
        int max = 0;
        
        Map<Float, Integer> map = new HashMap<>();
        Float slope = null;
        Integer value = null;
        int sameCount = 0, currentMax = 0;
        for(int i = 0; i < points.length; i++) {
            map.clear();
            sameCount = 0;
            currentMax = 0;
            for(int j = 0; j < points.length; j++) {
                if(j != i) {
                    slope = null;
                    if((points[j].x == points[i].x) && (points[j].y == points[i].y)) {
                        sameCount++;
                    } else if(points[j].x == points[i].x) {
                        slope = Float.MAX_VALUE;
                    } else {
                        slope = (points[j].y - points[i].y) / (float) (points[j].x - points[i].x);
                    }
                    
                    if(slope != null) {
                        value = map.get(slope);
                        if(value == null) {
                            map.put(slope, 1);
                        } else {
                            map.put(slope, value + 1);
                        }
                    }
                }
            }
            
            for(Float key : map.keySet()) {
                value = map.get(key);
                if(value > currentMax) {
                    currentMax = value;
                }
            }
            currentMax += sameCount;
            
            if(currentMax > max) {
                max = currentMax;
            }
        }
        
        return points.length > 0 ? max+1 : 0;
    }
    
    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public ListNode sortList(ListNode head) {
        int length = 0;
        ListNode node = head;
        while(node != null) {
            length++;
            node = node.next;
        }
        return length > 0 ? mergeSort(head, length) : head;
    }
    
    private ListNode mergeSort(ListNode head, int length) {
        if(length <= 1) {
            if(head != null) {
                head.next = null;
            }
            return head;
        } else {
            int half = length / 2;
            ListNode node = head;
            int i = half;
            while(node != null && i > 0) {
                i--;
                node = node.next;
            }
            
            head = mergeSort(head, half);
            node = mergeSort(node, length - half);
            
            head = merge(head, half, node, length - half);
            
            return head;
        }
    }
    
    private ListNode merge(ListNode head1, int length1, ListNode head2, int length2) {
        ListNode newHead = head1.val < head2.val ? head1 : head2;
        ListNode newNext = newHead;
        int i = 0, j = 0;
        if(newHead == head1) {
            i++;
            head1 = head1.next;
        } else {
            j++;
            head2 = head2.next;
        }
        while(i < length1 && j < length2) {
            if(head1.val <= head2.val) {
                newNext.next = head1;
                i++;
                head1 = head1.next;
            } else {
                newNext.next = head2;
                j++;
                head2 = head2.next;
            }
            newNext = newNext.next;
        }
        if(i < length1) {
            newNext.next = head1;
        } else {
            newNext.next = head2;
        }
        
        return newHead;
    }
    
    public ListNode insertionSortList(ListNode head) {
        if(head != null && head.next != null) {
            int length = 0;
            ListNode node = head;
            while(node != null) {
                length++;
                node = node.next;
            }
            
            ListNode sortedPre = null, sortedCurrent, unsortedCurrent, unsortedNext = null;
            sortedCurrent = head;
            unsortedCurrent = head.next;
            int sortedCount = 1, iterCount = 0;
            while(sortedCount < length) {
                iterCount = 0;
                while(unsortedCurrent != null && iterCount < sortedCount) {
                    iterCount++;
                    if(unsortedCurrent.val >= sortedCurrent.val) {
                        sortedPre = sortedCurrent;
                        sortedCurrent = sortedCurrent.next;
                    } else {
                        unsortedNext = unsortedCurrent.next;
                        if(sortedPre != null) {
                            sortedPre.next = unsortedCurrent;
                        } else {
                            head = unsortedCurrent;
                        }
                        unsortedCurrent.next = sortedCurrent;
                        sortedCount++;
                        
                        break;
                    }
                }
                if(iterCount == sortedCount) {
                    unsortedNext = unsortedCurrent.next;
                    sortedPre.next = unsortedCurrent;
                    sortedCurrent = unsortedCurrent;
                    sortedCount++;
                }
                
                iterCount++;
                while(iterCount < sortedCount) {
                    iterCount++;
                    sortedCurrent = sortedCurrent.next;
                }
                sortedCurrent.next = null;

                sortedPre = null;
                sortedCurrent = head;
                unsortedCurrent = unsortedNext;
            }
        }
        
        return head;
    }

    public static void main(String[] args) {
//        String s = "   a   b ";
//        System.out.println(s.trim());
//        String[] splits = s.trim().split(" ");
//        
//        System.out.println(splits.length);
//        for(String t : splits) {
//            if(!t.equals(" "))
//                System.out.println(t.length());
//        }
        AATwice a = new AATwice();
        ListNode head = a.new ListNode(3);
        ListNode node1 = a.new ListNode(2);
        ListNode node2 = a.new ListNode(4);
        head.next = node1;
        node1.next = node2;
        
        ListNode newHead = a.insertionSortList(head);
        while(newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
