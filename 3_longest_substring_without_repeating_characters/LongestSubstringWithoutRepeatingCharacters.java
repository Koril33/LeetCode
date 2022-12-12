import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	static int solution1(String s) {
		// 判空
		if (s == null || s.length() == 0) {
			return 0;
		}
		// 存储不重复的字符（左右指针范围内的字符串）
		Set<Character> set = new HashSet<>();
		// 字符的双指针
		int left = 0, right = 0;
		// 最长不重复字串的长度
		int max = 1;
		// 遍历字符串
		while (right < s.length()) {
			// 此次循环右指针指向的字符
			char cur = s.charAt(right);

			if (set.contains(cur)) {
				// 如果遇到了重复的字符串，将左指针指向的字符舍弃
				set.remove(s.charAt(left));
				// 并将左指针右移
				left++;
				// 这个 continue 是为了不断右移左指针
				// 直到左右指针范围内的字符串没有重复的字符为止
				// 也可以将这个 if 块改成 while 循环，然后删除此 continue
				continue;
			}
			// 如果不是重复的字符串就添加进
			set.add(cur);
			// 如果此次的大小比之前的纪录要大，则更新结果值
			if (set.size() > max) {
				max = set.size();
			}
			// 右指针不断向右移动
			right++;
		}
		return max;
	}

	static int solution2(String s) {
		// 判空
		if (s == null || s.length() == 0) {
			return 0;
		}
		// 键是字符，值是该字符的字符串下标
		Map<Character, Integer> map = new HashMap<>();
		// 双指针
		int left = 0, right = 0;
		// 最长不重复字串的长度
		int max = 1;
		// 存储每次循环时候子字符串的长度，和 max 作比较
		int len;
		// 遍历字符串
		while (right < s.length()) {
			// 此次循环右指针指向的字符
			char cur = s.charAt(right);
			// 如果 map 包含该字符，说明该字符之前已经出现过
			// left 指针直接跳到之前存储的重复的字符的下一位
			// 比如：abcdxefghxijkl，当右指针移到第二个 x 的时候，发现 x 字符重复（已经保存于 map）
			// left 则直接从 a 字符跳到 e 字符，省去了无效的循环右移（不需要经过 bcdx）
			if (map.containsKey(cur)) {
				// Math.max() 是为了防止 left 指针往回跳
				// 比如 abbbabbb，如果只写成 left = map.get(cur) + 1; 的话
				// 当 right 指针移动到第二个 a 字符的时候，left 此时也在第二个 a 字符
				// 由于 map 存储的是第一个 a 字符的下标，
				// left 就会回跳到第一个 a 字符的下一个字符
				left = Math.max(map.get(cur) + 1, left);
			}
			// 不管遇到的是不是重复的字符，都将将字符和对应下标存入 map
			map.put(cur, right);
			// 当前循环下的子字符串长度
			len = right - left + 1;
			// 如果此次的大小比之前的纪录要大，则更新结果值
			// 也可以不用 len 这个临时变量，直接写成
			// max = Math.max(right - left + 1, max);
			if (len > max) {
				max = len;
			}
			// 右指针不断向右移动
			right++;
		}
		return max;
	}

	/**
	 * 优化的移动窗口，采用数组存储
	 * @param s
	 * @return
	 */
	static int solution3(String s) {
		// 判空
		if (s == null || s.length() == 0) {
			return 0;
		}
		// 最长不重复字串的长度
		int max = 0;
		// 128 表示所有字符的范围
		char[] index = new char[128];
		char c;
		for (int left = 0, right = 0; right < s.length(); right++) {
			c = s.charAt(right);
			left = Math.max(index[c], left);
			max = Math.max(max, right - left + 1);
			index[c] = (char) (right + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		String s = "abbbabbb";
		int res = solution3(s);
		System.out.println(res);
	}
}