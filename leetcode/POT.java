package com.qianxiang.leetcode;

import java.util.ArrayList;
import java.util.Stack;
/*
Given a binary tree, return the postorder traversal of its nodes' values.
Recursive solution is trivial, could you do it iteratively?

解法：
先用栈做辅助空间，先从根往左一直入栈，直到为空，然后判断栈顶元素的右孩子，如果不为空且未被访问过，
则从它开始重复左孩子入栈的过程；否则说明此时栈顶为要访问的节点（因为左右孩子都是要么为空要么已访问过了），
出栈然后访问即可，接下来再判断栈顶元素的右孩子...直到栈空。
 */
public class POT {
    // 非递归实现
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        TreeNode p = root, r = null; // p用来记录当前节点，r用来记录访问过的节点
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> al = new ArrayList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            }
            else
            {
                p = stack.peek();
                p = p.right;
                if (p != null && p != r) {
                    stack.push(p);
                    p = p.left;
                }
                else {
                    p = stack.pop();
                    al.add(p.val);
                    r = p;
                    p = null;
                }
            }
        }
        return al;
    }

    // 递归实现
    public ArrayList<Integer> postorderTraversal1(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return list;
        }
        postRec(root,list);
        return list;
    }

    public void postRec(TreeNode node,ArrayList<Integer> list){
        if(node.left != null){
            postRec(node.left,list);
        }
        if(node.right != null){
            postRec(node.right,list);
        }
        list.add(node.val);
    }
}
