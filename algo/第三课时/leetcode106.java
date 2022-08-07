package com.sean.module3;

import com.sean.tree.TreeNode;

/**
 * @ClassName leetcode106
 * @Description: 从中序与后序遍历序列构造二叉树
 * @Author a9705
 * @Date 2022/8/7
 * @Version V1.0
 **/
public class leetcode106 {

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //后续的头节点在结尾
        int size = postorder.length;
        TreeNode root = dobuild(postorder, 0, size - 1, inorder, 0, size - 1);
        return root;
    }

    private TreeNode dobuild(int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend) {

        if (pstart > pend) {
            return null;
        }

        //可以换成hashmap来查找
        TreeNode root = new TreeNode(preorder[pend]);
        int mid = istart;
        while (inorder[mid] != root.val) {
            mid++;
        }
        //后续遍历要注意后续队列的截取，左段截取的是 当前节点 pstart + 中序遍历移动点数（mid - istart） - 再减去闭区间 1 [0,1)
        root.left = dobuild(preorder, pstart, pstart + (mid - istart) - 1, inorder, istart, mid - 1);
        root.right = dobuild(preorder, pstart + (mid - istart), pend - 1, inorder, mid + 1, iend);
        return root;
    }
}
