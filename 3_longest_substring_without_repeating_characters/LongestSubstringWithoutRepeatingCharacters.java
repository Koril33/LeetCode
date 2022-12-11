import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	static int solution1(String s) {
		// 判空
		if (s.length() == 0) {
			return 0;
		}
		// 存储不重复的字符（左右指针范围内的字符串）
		Set<Character> set = new HashSet<>();
		// 字符的双指针
		int left = 0, right = 0;
		// 最长字符串长度
		int max = 1;

		while (right < s.length()) {
			// 此次循环右指针的字符
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

	public static void main(String[] args) {
		String s = "qrsvbspk";
		int res = solution1(s);
		System.out.println(res);
	}
}