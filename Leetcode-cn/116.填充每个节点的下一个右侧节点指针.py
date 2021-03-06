#
# @lc app=leetcode.cn id=116 lang=python3
#
# [116] 填充每个节点的下一个右侧节点指针
#
# https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/description/
#
# algorithms
# Medium (69.67%)
# Likes:    474
# Dislikes: 0
# Total Accepted:    124.6K
# Total Submissions: 178.7K
# Testcase Example:  '[1,2,3,4,5,6,7]'
#
# 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
# 
# 
# struct Node {
# ⁠ int val;
# ⁠ Node *left;
# ⁠ Node *right;
# ⁠ Node *next;
# }
# 
# 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
# 
# 初始状态下，所有 next 指针都被设置为 NULL。
# 
# 
# 
# 进阶：
# 
# 
# 你只能使用常量级额外空间。
# 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
# 
# 
# 
# 
# 示例：
# 
# 
# 
# 
# 输入：root = [1,2,3,4,5,6,7]
# 输出：[1,#,2,3,#,4,5,6,7,#]
# 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由
# next 指针连接，'#' 标志着每一层的结束。
# 
# 
# 
# 
# 提示：
# 
# 
# 树中节点的数量少于 4096
# -1000 
# 
# 
#

# @lc code=start
"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""


# 1. Solution1, 使用next指针层次遍历，Time: O(n), Space: O(1), Runtime: 86%
#   - 每一层抽象成单链表，从左到右连接
#   - 使用上一层已经完成的连接去连接下一层
#   1. 情况1: left和right在一个父结点下，head.left.next = head.right
#   2. 情况2: left和right不在一个父结点下，但上一层已经连接，可以通过上一层的next, head.right.next = head.next.left
# class Solution:
#     def connect(self, root: 'Node') -> 'Node':
#         if not root:
#             return root
#         leftmost = root
#         while leftmost.left:
#             head = leftmost
#             while head:
#                 # 情况1
#                 head.left.next = head.right
#                 # 情况2
#                 if head.next:
#                     head.right.next = head.next.left
#                 # 向右移动一次
#                 head = head.next
#             # 向下移动一层
#             leftmost = leftmost.left
#         return root  


# 2. Solution2, 递归, Time: O(n), Space: O(1), Runtime: 86%
class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return root
        left = root.left
        right = root.right
        while left:
            left.next = right
            left = left.right
            right = right.left
        self.connect(root.left)
        self.connect(root.right)
        return root   
# @lc code=end