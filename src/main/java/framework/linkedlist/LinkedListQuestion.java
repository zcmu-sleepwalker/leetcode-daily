package framework.linkedlist;

/**
 * 链表问题
 *
 * @Author: LCH
 * @Date: 2021/3/30 5:50 PM
 */
public class LinkedListQuestion {

    // 单链表
    public static class SingleListNode {
        int val;
        SingleListNode next;

        SingleListNode(int x) {
            val = x;
        }
    }

    /**
     * 翻转单链表（递归实现）
     *
     * @param head
     * @return
     */
    SingleListNode reverse(SingleListNode head) {
        if (head.next == null) {
            return head;
        }
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6
        // 1 <- 2 <- 3 <- 4 <- 5 <- 6
        // 找到最后一个节点 也就是翻转后的头结点
        SingleListNode lastNode = reverse(head.next);
        // 将 1 -> 2 变成了 1 <- 2 并且 1 指向null
        head.next.next = head;
        head.next = null;

        return lastNode;
    }

    /**
     * 翻转单链表
     *
     * @param head
     * @return
     */
    SingleListNode reverse_01(SingleListNode head) {
        if (head == null) {
            return null;
        }
        // 为什么从null开始，因为null相当于是一个尾结点
        SingleListNode p0 = null;
        SingleListNode p1 = head;
        // p2只是为了记录下一个节点，以为当前节点的next会改变，所以需要记录下个节点
        SingleListNode p2 = head.next;
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
        // (1) p0 = null p1 = 1 p2 = 2
        // null <- 1 -> 2 -> 3 -> 4 -> 5 -> 6 (1)
        // (2) p0 = 1 p1 = 2 p2 = 3
        // null <- 1 <- 2 -> 3 -> 4 -> 5 -> 6 (1)
        // 最终p1是要往后移的，p0会走到p1的位置，此时p1 == null，终止循环，返回p0即可。
        while (p1 != null) {
            p1.next = p0;

            p0 = p1;
            p1 = p2;
            // p2无关紧要 有就后移 没有就算了
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        return p0;
    }


    /**
     * 反转链表前n个节点（递归实现）
     *  这里递归的本质是会返回最后一个翻转节点，将他变成头结点。
     *  并且要记录他的后继节点，因为最后要连接。
     * @param head
     * @param n
     * @return
     */
    static SingleListNode successor = null;
    public static SingleListNode reverse(SingleListNode head, int n) {

        // 如果n > head.length 那么取最小的
        int index = 0;
        SingleListNode tmp = head;
        while (tmp != null){
            tmp = tmp.next;
            index++;
        }
        n = Math.min(n,index);

        if (n == 1) {
            // 记录第n个的节点的后继节点
            successor = head.next;
            // 返回最后节点 变成头结点
            return head;
        }
        // 递归去反转链表
        SingleListNode lastNode = reverse(head.next, n - 1);

        head.next.next = head;
        // 将head的指向后继节点
        head.next = successor;

        return lastNode;
    }

    /**
     * 反转链表前n个节点
     *
     * @param head
     * @param n
     * @return
     */

    SingleListNode reverse_01(SingleListNode head, int n) {
        if (head == null) {
            return head;
        }
        // 将首节点存下来，后续直接使用。
        SingleListNode tmp = head;
        SingleListNode p0 = null;
        SingleListNode p1 = head;
        SingleListNode p2 = head.next;

        for (int i = 0; i < n; i++) {
            p1.next = p0;
            p0 = p1;
            p1 = p2;
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        tmp.next = p1;

        return head;
    }

    /**
     * 反转链表的一部分
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    SingleListNode reverseBetween(SingleListNode head, int m, int n) {
        if (m == 1) {
            // 相当于反转链表
            return reverse(head, n);
        }
        // 前进到反转的起始点
        // head.next可以将首尾连接
        // 对于 head.next 来说，就是反转区间 [m - 1, n - 1]，也就是不停的向前压缩，压缩到头了就反转链表前n个节点
        head.next = reverseBetween(head, m - 1, n - 1);

        return head;
    }


    /**
     * k个一组反转链表
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> null
     * @param head
     * @param k
     * @return
     */
    public static SingleListNode reverseKGroup(SingleListNode head, int k) {
        if (head == null){
            return head;
        }
        // 进行判断 如果n 大于head的长度就直接返回head
        SingleListNode a,b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        SingleListNode lastNode = reverse(head, k);

        a.next = reverseKGroup(b, k);

        return lastNode;
    }


    /**
     * k个一组反转链表（自己的解法）
     * @param head
     * @param k
     * @return
     */
    public static SingleListNode reverseKGroup_my(SingleListNode head, int k) {
        return reverseKGroup(head,k,0);
    }

    public static SingleListNode reverseKGroup(SingleListNode head, int k, int n) {
        if (head == null){
            return null;
        }
        head.next = reverseKGroup(head.next, k, n + 1);
        if (n % k == 0) {
            return reverse(head, k);
        }
        return head;
    }


    public static SingleListNode reverse_group(SingleListNode head, int n) {
        int index = 0;
        SingleListNode tmp = head;
        while (tmp != null){
            tmp = tmp.next;
            index++;
        }
        n = Math.min(n,index);
        if (n == 1) {
            // 记录第n个的节点的后继节点
            successor = head.next;
            // 返回最后节点 变成头结点
            return head;
        }
        // 递归去反转链表
        SingleListNode lastNode = reverse_group(head.next, n - 1);

        head.next.next = head;
        // 将head的指向后继节点
        head.next = successor;

        return lastNode;
    }

    /**
     * k个一组反转链表（迭代）
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> null
     * @param head
     * @param k
     * @return
     */
    public static SingleListNode reverseKGroup_01(SingleListNode head, int k) {
        if (head == null){
            return head;
        }
        SingleListNode leftNode = head;
        SingleListNode rightNode = head;
        for (int i = 0; i < k; i++) {
            // 代表不需要反转
            if (rightNode == null){
                return head;
            }
            rightNode = rightNode.next;
        }
        SingleListNode lastNode = reverse(leftNode,rightNode);
        leftNode.next = reverseKGroup_01(rightNode,k);
        return lastNode;
    }

    public static SingleListNode reverse(SingleListNode leftNode, SingleListNode rightNode){
        SingleListNode p0 = null;
        SingleListNode p1 = leftNode;
        SingleListNode p2 = leftNode.next;

        while (p1 != rightNode){
            p1.next = p0;

            p0 = p1;
            p1 = p2;
            if (p2 != null){
                p2 = p2.next;
            }
        }
        return p0;
    }


    public static void main(String[] args) {
        SingleListNode node1 = new SingleListNode(1);
        SingleListNode node2 = new SingleListNode(2);
        SingleListNode node3 = new SingleListNode(3);
        SingleListNode node4 = new SingleListNode(4);
        SingleListNode node5 = new SingleListNode(5);
        SingleListNode node6 = new SingleListNode(6);
        SingleListNode node7 = new SingleListNode(7);
        SingleListNode node8 = new SingleListNode(8);
        SingleListNode node9 = new SingleListNode(9);
        SingleListNode node10 = new SingleListNode(10);
        SingleListNode node11 = new SingleListNode(11);
        SingleListNode node12 = new SingleListNode(12);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;
        node11.next = node12;
        node12.next = null;

        node1 = reverseKGroup_01(node1,5);
        printNode(node1);

    }

    public static void printNode(SingleListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

}
