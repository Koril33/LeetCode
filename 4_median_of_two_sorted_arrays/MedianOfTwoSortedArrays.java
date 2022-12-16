import java.util.Arrays;

public class MedianOfTwoSortedArrays {

    /**
     * 判断给定数字是否为奇数
     * @param i
     * @return
     */
    private static boolean isOdd(int i) {
        return (i & 1) != 0;
    }

    /**
     * 返回给定数组中间的值
     * 奇数长度返回单个值，偶数长度返回中间两值的平均数
     * @param nums
     * @return
     */
    private static double getMidNumInArray(int[] nums) {
        int len = nums.length;
        if (isOdd(len)) {
            return nums[len / 2];
        }
        return (nums[len / 2] + nums[len / 2 - 1]) / 2.0;
    }

    /**
     * 有序合并两数组
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] mergeTwoSortedArray(int[] nums1, int[] nums2) {
        // nums1, nums2 的长度
        int m = nums1.length, n = nums2.length;
        // 将 nums1 和 nums2 有序放进 tmp 数组中
        int[] tmp = new int[m + n];

        int i = 0;
        // p1 指向 nums1 的值，p2 指向 nums2 的值
        int p1 = 0, p2 = 0;
        // 循环，合并两数组
        while (i < (m + n)) {
            // p1 和 p2 都没有到各自数组的末尾
            if (p1 < m && p2 < n) {
                tmp[i] = Math.min(nums1[p1], nums2[p2]);
                if (nums1[p1] < nums2[p2]) {
                    p1++;
                }
                else {
                    p2++;
                }
            }
            // p1 到达 nums1 的末尾，则将 nums2 剩余的值放进 tmp 的后续部分
            else if (p1 == m && p2 < n) {
                tmp[i] = nums2[p2];
                p2++;
            }
            // p2 到达 nums2 的末尾，则将 nums1 剩余的值放进 tmp 的后续部分
            else if (p2 ==n && p1 < m) {
                tmp[i] = nums1[p1];
                p1++;
            }
            else {
                break;
            }
            i++;
        }
        return tmp;
    }

    static double solution(int[] nums1, int[] nums2) {
        // 特殊情况判断，都为 null
        if (nums1 == null && nums2 == null) {
            return 0.0;
        }
        // 特殊情况判断，nums2 为 null 或者长度为 0
        // 则取 nums1 的中间值
        if (nums2 == null || nums2.length == 0) {
            return getMidNumInArray(nums1);
        }
        // 特殊情况判断，nums1 为 null 或者长度为 0
        // 则取 nums2 的中间值
        if (nums1 == null || nums1.length == 0) {
            return getMidNumInArray(nums2);
        }

        return getMidNumInArray(mergeTwoSortedArray(nums1, nums2));
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-1, 3};
        System.out.println(solution(nums1, nums2));
    }
}
