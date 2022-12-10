import java.util.*;

class TwoSum {

	static int[] solution1(int[] nums, int target) {
		int numsSize = nums.length;

		for (int i = 0; i < numsSize; i++) {
			for (int j = i + 1; j < numsSize; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[]{i, j};
				}
			}
		}
		return new int[]{};
	}

	static int[] solution2(int[] nums, int target) {
		int numsSize = nums.length;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < numsSize; i++) {
			int key = target - nums[i];
			if (map.containsKey(key)) {
				return new int[]{map.get(key), i};
			}
			map.put(nums[i], i);
		}
		return new int[]{};
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2, 7, 11, 15};
		int target = 9;

		// int[] res = solution1(nums, target);
		int[] res = solution2(nums, target);
		System.out.println(Arrays.toString(res));
	}
}