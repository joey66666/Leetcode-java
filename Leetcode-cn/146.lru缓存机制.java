/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU缓存机制
 *
 * https://leetcode-cn.com/problems/lru-cache/description/
 *
 * algorithms
 * Medium (46.89%)
 * Likes:    518
 * Dislikes: 0
 * Total Accepted:    48.8K
 * Total Submissions: 103.4K
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) -
 * 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2  //缓存容量  );
        *
        * cache.put(1, 1);
        * cache.put(2, 2);
        * cache.get(1);       // 返回  1
        * cache.put(3, 3);    // 该操作会使得密钥 2 作废
        * cache.get(2);       // 返回 -1 (未找到)
        * cache.put(4, 4);    // 该操作会使得密钥 1 作废
        * cache.get(1);       // 返回 -1 (未找到)
        * cache.get(3);       // 返回  3
        * cache.get(4);       // 返回  4
        *
        *
        */

// @lc code=start

import java.util.HashMap;

class DoubleLinkedNode {
    Object value;
    Object key;
    DoubleLinkedNode prev;
    DoubleLinkedNode next;

    DoubleLinkedNode(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}

class DoubleLinkedList {
    DoubleLinkedNode centinel = new DoubleLinkedNode(null, null);
    DoubleLinkedNode tail = centinel;

    public void addLast(DoubleLinkedNode node) {
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    public DoubleLinkedNode getHead() {
        return centinel.next;
    }

    public void removeNode(DoubleLinkedNode node) {
        node.prev.next = node.next;
        if (node.next == null) tail = node.prev;
        else node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    public void moveToTail(DoubleLinkedNode node) {
        removeNode(node);
        addLast(node);
    }

}

class LRUCache {
    int capacity = 0;
    HashMap<Integer, DoubleLinkedNode> hm = new HashMap<>();
    DoubleLinkedList list = new DoubleLinkedList();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (hm.containsKey(key)) {
            DoubleLinkedNode node = hm.get(key);
            list.moveToTail(node);
            return (int) node.value;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        if (hm.containsKey(key)) {
            DoubleLinkedNode node = hm.get(key);
            node.value = value;
            hm.put(key, node);
            list.moveToTail(node);
        } else {
            if (hm.size() >= capacity) {
                DoubleLinkedNode node = list.getHead();
                hm.remove(node.key);
                list.removeNode(node);
                node = new DoubleLinkedNode(key, value);
                hm.put(key, node);
                list.addLast(node);
            } else {
                DoubleLinkedNode node = new DoubleLinkedNode(key, value);
                hm.put(key, node);
                list.addLast(node);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

