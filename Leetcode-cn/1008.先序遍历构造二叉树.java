/*
 * @lc app=leetcode.cn id=1008 lang=java
 *
 * [1008] 先序遍历构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
 *
 * algorithms
 * Medium (71.92%)
 * Likes:    53
 * Dislikes: 0
 * Total Accepted:    5.6K
 * Total Submissions: 7.9K
 * Testcase Example:  '[8,5,1,7,10,12]'
 *
 * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 * 
 * (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而
 * node.right 的任何后代，值总 > node.val。此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历
 * node.right。）
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：[8,5,1,7,10,12]
 * 输出：[8,5,10,1,7,null,12]
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= preorder.length <= 100
 * 先序 preorder 中的值是不同的。
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        return traverse(preorder, 0, preorder.length);
    }

    public TreeNode traverse(int[] preorder, int start, int end) {
        if (start >= end) return null;
        TreeNode root = new TreeNode(preorder[start]);
        int splitnum = start;
        while (splitnum < end && preorder[splitnum] <= preorder[start])
            splitnum += 1;
        root.left = traverse(preorder, start + 1, splitnum);
        root.right = traverse(preorder, splitnum, end);
        return root;
    }
}
// @lc code=end

