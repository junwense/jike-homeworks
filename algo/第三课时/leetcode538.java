package com.sean.module3;

import com.sean.tree.TreeNode;

/**
 * @ClassName leetcode538
 * @Description: 把二叉搜索树转换为累加树
 * @Author a9705
 * @Date 2022/8/7
 * @Version V1.0
 **/
public class leetcode538 {

    int sum=0;

    public TreeNode convertBST(TreeNode root) {
        //中序遍历
        //然后从右边往左遍历，所以是右子树开始
        //累加树为每个节点的值修改为原来的节点值加上所有大于它的节点值之和
        if(root!=null){
            convertBST(root.right);
            sum+=root.val;
            root.val=sum;
            convertBST(root.left);
        }
        return root;
    }
}
