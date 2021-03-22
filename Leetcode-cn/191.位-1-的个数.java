/*
 * @lc app=leetcode.cn id=191 lang=java
 *
 * [191] 位1的个数
 *
 * https://leetcode-cn.com/problems/number-of-1-bits/description/
 *
 * algorithms
 * Easy (66.72%)
 * Likes:    161
 * Dislikes: 0
 * Total Accepted:    59.6K
 * Total Submissions: 89.3K
 * Testcase Example:  '00000000000000000000000000001011'
 *
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *
 *
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 *
 *
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 *
 *
 * 提示：
 *
 *
 * 请注意，在某些语言（如
 * Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *
 *
 *
 *
 * 进阶:
 * 如果多次调用这个函数，你将如何优化你的算法？
 *
 */

// @lc code=start
// Solution1，按位与, Time: O(n), Space: O(1), Runtime: 95%
// public class Solution {
//     // you need to treat n as an unsigned value
//     public int hammingWeight(int n) {
//         int count = 0;
//         for(int i = 0; i < 32; i++){
//             if((n & (1 << i)) != 0){
//                 count += 1;
//             }
//         }
//         return count;
//     }
// }

// Solution2, 按位与优化, Time: O(logn), Space: O(1), Runtime: 95%
// - n & (n−1) = 把 n 的二进制位中的最低位的 1 变为 0 之后的结果
// - 如：6 & (6-1) = 4, 6 = (110)2, 4 = (100)2, 运算结果 4 即为把 6 的二进制位中的最低位的 1 变为 0 之后的结果。
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            n &= n - 1;
            count += 1;
        }
        return count;
    }
}
// @lc code=end
