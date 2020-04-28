**[33] 搜索旋转排序数组
Medium**

- https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
- 做一次二分，分析应该搜索左边还是右边。每次二分有三种情况：
1. nums[mid] = target，则可以返回mid
2. nums[mid] < nums[right]，说明在[mid, right]区间是右边递增的区间，然后判断target是否在这个区间内
    - 如果nums[mid] < target <= nums[right]，说明target在右边区间里，则left = mid + 1;
    - 否则在左边区间里，搜索左边区间，right = mid - 1;
3. nums[mid] >= nums[right]，说明[elft, mid]区间是在左边的递增区间，然后判断target是否在这个左边区间里
    - 如果nums[left] <= target < nums[mid]，说明target在这个区间里，则使right = mid - 1;
    - 否则说明target在[mid, right]的不规则区间里，搜索右边区间，则使left = mid + 1;


**49  Group Anagrams，字母异位词分组
Medium**

- https://leetcode-cn.com/problems/group-anagrams/description/
1. 每个单词 sorted 作为 key 放入 HashMap
2. 每个单词每个字母映射成字母表 int 数组，组成唯一 String key (E.g. 1a1e1t) 作为 key 


**[64] 最小路径和
Medium**

- https://leetcode-cn.com/problems/minimum-path-sum/description/
- 用`dp`数组记录当前行走到当前点的最小路径值，`dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1])`
- `dp`第一行只能从左走，第一列只能从上走，提前初始化


**92. Reverse Linked List II
Medium**

- https://leetcode.com/problems/reverse-linked-list-ii/
- 翻转开始前一个节点
- 翻转完成后一个节点
- 中间翻转
- 转完连接
  

**[200] 岛屿数量
Medium**

- https://leetcode-cn.com/problems/number-of-islands/description/
- == 1 则DFS遍历每个点的上下左右，把 1 变为 0，遍历结束count += 1


**[201] 数字范围按位与
Medium**
- https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/description/
1. Solution 1 
   - 最后的数是该数字范围内所有的数的左边共同的部分
   - 只要找到左边公共的部分
   - 直接平移m和n，每次向右移一位，直到m和n相等
   - 记录下所有平移的次数i，然后再把m左移i位即为最终结果
2. Solution 2
   - 从后往前每次 n = (n & (n-1)), 直到n <= m, 然后返回n,
   - 逐渐将最右边不同的置为0, 最后只剩下最左边m 和 n相同的位置.
   - 举例：110与上(110-1)，得到100，相当于去掉最低位的1，
   - n就这样每次去掉最低位的1，如果小于等于m了，返回此时的n


**[238] 除自身以外数组的乘积
Medium**

- https://leetcode-cn.com/problems/product-of-array-except-self/description/
- 从左到右遍历保存`nums[i]`左边所有数的乘积`res[i] = left; left *= nums[i]`
再从右往左遍历，右边的所有数乘积乘上左边的` res[i] *= right; right *= nums[i];  `


**[525] 连续数组
Medium**

- https://leetcode-cn.com/problems/contiguous-array/description/
- 测试用例只包含0和1，遇0减1、遇1加1，存`sum`值到HashMap中，若`sum`之前存在，则说明`sum + 0 = sum`，即中间子序列和为0，即0和1个数相
- HashMap中需要先`put(0, -1)`来使比如在`[0, 1]`上遍历`sum = 0`时`index = 1`，此时应该更新`res = 2`，而非记录为`index=1, res = 0`


**[560] 和为K的子数组
Medium**

- https://leetcode-cn.com/problems/subarray-sum-equals-k/description/
-  prefixSum array 记录从0到i的前缀sum
-  subarray(i, j) = prefixSum(j) - prefixSum(i - 1)
-  找到 prefixSum(j)- prefixSum(i) == k == subarray(i, j)
-  HashMap<Integer, Integer>: <prefixSum, prefixSum出现的次数>
-  Time:O(n), Space:O(n)


**[678] 有效的括号字符串
Medium**

- https://leetcode-cn.com/problems/valid-parenthesis-string/description/
1. Solution 1 
    - 两个栈（ * 和 left ），遍历，存入index，遇到' ) '先用 left ，再用 * 抵消，都为空则false
    - 遍历完若两栈都不空，if（ index(left) > index(*) ) 则为" *( "形式，无法抵消，false；若可抵消则都pop
    - 若 pop 完 left 空，* 不空，* 可视为空字符串，true
    - 若 pop 完 left 不空，* 空，false
2. Solution 2
    - 先从左往右遍历，所有 * 视为 ( ，* 和 ( 则 + 1，否则 - 1
    - 若中间有 < 0，则表示所有 * 不够抵消 ) ，return false
    - 若遍历完 = 0，表示 * 和 ( 正好抵消 )，return true
    - 若 > 0 ，可能有 * 补充了没用完，进行下一步
    - 下一步从右往左遍历， 所有 * 视为 ) ，* 和 ) 则 + 1，否则 - 1
    - 若过程中 < 0 ，( 数量大于 ), return false
    - 若遍历完 = 0，* 正好抵消，return true
    - 若遍历完 > 0，之前一轮遍历中 * 变的 数量 ( < 这一轮数量 ) ，表示部分 * 可变为 ( 或 ) 抵消，部分为空字符串, return true


**[1008] 先序遍历构造二叉树
Medium**

- https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
- 第一个是根，往后遍历，<=根为左子树，剩下为右子树
- 递归遍历 


**[146] LRU缓存机制
Medium**
- https://leetcode-cn.com/problems/lru-cache/description/
- Time: O(1), Size: O(n)
- 双向链表中存储`Key`和`Value`, `HashMap`中存储`Key`和`Node`
- 链表从头到尾按以旧到新，新插入放到尾，最近get放到尾，`HashMap.size() >= capcity`删除头，`put`新`Node`
- ![image](https://user-images.githubusercontent.com/25404074/80353709-4fa02500-88a8-11ea-90ee-770fb2ad2277.png)


**[55] 跳跃游戏
Medium**
- https://leetcode-cn.com/problems/jump-game/description/
- `dp`数组中 `dp[i]` 表示达到`i`位置时剩余的跳力，若到达某个位置时跳力为负了，说明无法到达该位置。
- 到达当前位置的剩余跳力跟上一个位置的剩余跳力（`dp` 值）和上一个位置新的跳力（`nums` 数组中的值）有关。所以当前位置的剩余跳力（`dp` 值）和当前位置新的跳力中的较大那个数决定了当前能到的最远距离，而下一个位置的剩余跳力（`dp` 值）就等于当前的这个较大值减去1，因为需要花一个跳力到达下一个位置
- 所以就有状态转移方程了：`dp[i] = max(dp[i - 1], nums[i - 1]) - 1`，如果当某一个时刻 `dp` 数组的值为负了，说明无法抵达当前位置，则直接返回 `false`，最后循环结束后直接返回 `true ` 即可


**[1143] 最长公共子序列
Medium**
- https://leetcode-cn.com/problems/longest-common-subsequence/description/
- dp[text1.length() + 1][text2.length() + 1]
- dp[i][j]表示两个字符串在i, j之前位置（不包含i, j）的最大公共子序列长度
- if(text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1
- else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
- ![image](https://user-images.githubusercontent.com/25404074/80450199-96972480-8953-11ea-8641-656efe8beffe.png)


**[221] 最大正方形
Medium**
- https://leetcode-cn.com/problems/maximal-square/description/
- dp[i][j]数组用来保存以matrix[i][j]为右下角的最大正方形边长
```java
if(matrix[i][j] == 1){ 
     dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - 1],dp[i - 1][j - 1]) + 1
}
```
- return dp 数组中最大值的 ^2 
- ![image](https://user-images.githubusercontent.com/25404074/80496729-2eb8fc00-899c-11ea-973d-8c0b39fde046.png)