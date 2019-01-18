package com.qianxiang.leetcode;

import com.qianxiang.practice.Linklist;

import java.util.LinkedList;
import java.util.Queue;

/*
question:
Given a binary tree, find its minimum depth.The minimum depth is the number of nodes along the shortest path
from the root node down to the nearest leaf node.

解题思路：这道题与获取树的高度的题类似（采用从叶子节点回溯到根节点，每过一层父辈节点就给深度累计+1），
同理，本题也是如此，获取根节点的最低深度，即是获取它的左孩子与右孩子的最低深度再+1，如此递归调用。
中止条件：碰到null节点，返回0；碰到左孩子或者右孩子没有的节点，返回相反的非null的一支的深度。
 */
class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
public class MinDepthTree {
    // 递归实现（深度遍历）
    public int run(TreeNode root)
    {
        if (root == null)
            return 0;
        if (root.left == null)
            return run(root.right)+1;
        if (root.right == null)
            return run(root.left)+1;
        int leftDepth = run(root.left);
        int rightDepth = run(root.right);
        return (leftDepth < rightDepth)?(leftDepth+1):(rightDepth+1);
    }
    // 非递归实现（广度遍历）
    public int run1(TreeNode root)
    {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        int depth = 0;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        while (!qu.isEmpty()){
            int len = qu.size();
            ++depth;
            for (int i = 0; i < len; ++i)
            {
                TreeNode e = qu.poll();
                if (e.left == null && e.right == null)
                    return depth;
                if (e.left != null)
                    qu.offer(e.left);
                if (e.right != null)
                    qu.offer(e.right);
            }
        }
        return 0;
    }
}
