class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class AddTwoNumbers {

	/**
	 * 功能性代码
	 * 打印链表
	 * @param head 链表头节点
	 */
	static void printList(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.print(cur.val + "->");
			cur = cur.next;
		}
		System.out.println("null");
	}

	/**
	 * 功能性代码
	 * 生成链表
	 * @param nums 给定的数据
	 * @return 链表
	 */
	static ListNode asListNode(int... nums) {
		ListNode head = new ListNode(0);
		ListNode cur = head;

		for (int i : nums) {
			ListNode node = new ListNode(i);
			cur.next = node;
			cur = node;
		}
		cur.next = null;
		return head.next;
	}


	static ListNode solution(ListNode l1, ListNode l2) {
		// 返回结果的头指针
		ListNode res = new ListNode();

		// 当前指针，用于构造结果链表
		ListNode resCur = res;

		// 负责存储前一个结点的进位值
		int carry = 0;
		// 当其中某一个不为空链表时，才进入循环
		while (l1 != null || l2 != null) {
			// 节点总和
			int sum = 0;

			// 将两个链表的节点进行求和
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			// 加上前一个节点的进位值
			sum += carry;
			// 计算当前两节点求和后产生的进位值
			carry = sum / 10;
			// 构造下一个节点
			resCur.next = new ListNode(sum % 10);;
			resCur = resCur.next;

		}
		// 循环结束后，要检测还是否存在进位值
		if (carry != 0) {
			resCur.next = new ListNode(1, null);
		}

		return res.next;
	}


	public static void main(String[] args) {

		ListNode l1 = asListNode(8, 9);
		ListNode l2 = asListNode(5, 6, 4);
		printList(l1);
		printList(l2);
		ListNode res = solution(l1, l2);
		printList(res);
	}
}