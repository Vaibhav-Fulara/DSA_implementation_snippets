package com.company;

// Tree Implementation
import java.util.*;
public class Tree {
    public static void main(String[] args){
        Integer[]arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = createTree(arr);
        display(root);
    }
    public static void display(Node node){
        if (node == null) return;
        String str = " <- " + node.val + " -> ";
        String left = (node.left == null) ? "." :  "" + node.left.val;
        String right = (node.right == null) ? "." : "" + node.right.val;
        str = left + str + right;
        System.out.println(str);
        display(node.left);
        display(node.right);
    }
    public static Node createTree(Integer[]arr){
        Stack<Pair>st = new Stack<>();
        Node node = new Node(arr[0]);
        Pair ini = new Pair(node,1);
        st.push(ini);
        int idx = 1;
        while(st.size()!=0){
            Pair top = st.peek();
            if(top.state==1){
                if(arr[idx]!=null){
                    Node child = new Node(arr[idx]);
                    top.node.left = child;
                    Pair lc = new Pair(child, 1);
                    st.push(lc);
                }
                idx++;
            }
            else if(top.state==2){
                if(arr[idx]!=null){
                    Node child = new Node(arr[idx]);
                    top.node.right = child;
                    Pair rc = new Pair(child, 1);
                    st.push(rc);
                }
                idx++;
            }
            else st.pop();
            top.state++;
        }
        return node;
    }
    public static class Node{
        int val;
        Node left;
        Node right;
        Node(int val){
            this.val = val;
        }
    }
    public static class Pair {
        Node node;
        int state;
        Pair(Node node, int state){
            this.node = node;
            this.state = state;
        }
    }
}
