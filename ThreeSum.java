
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //对nums进行排序
        Arrays.sort(nums);
        //遍历nums数组
        for (int i = 0; i < nums.length - 2; i++) {
            //如果数组中当前元素大于0，则三数之和一定大于0，所以结束循环
            //如果数组中元素的个数小于3，则不可能组成三元组，也结束循环
            if (nums[i] > 0 || nums.length < 3) {
                break;
            }
            //如果当前元素和前一个元素相同，则跳过，避免重复解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //定义左右指针
            int left = i + 1;
            int right = nums.length - 1;
            //当左指针小于右指针时，进行循环
            while (left < right) {
                //计算三数之和
                int sum = nums[i] + nums[left] + nums[right];
                //如果三数之和等于0，则将三元组加入结果集
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //跳过重复元素，避免重复解
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    //移动左右指针
                    left++;
                    right--;
                } //如果三数之和小于0，则左指针右移
                else if (sum < 0) {
                    left++;
                } //如果三数之和大于0，则右指针左移
                else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();

        check("基础用例",
                solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}),
                Arrays.asList(
                        Arrays.asList(-1, -1, 2),
                        Arrays.asList(-1, 0, 1)
                ));

        check("全 0 去重",
                solution.threeSum(new int[]{0, 0, 0, 0}),
                Arrays.asList(
                        Arrays.asList(0, 0, 0)
                ));

        check("无答案",
                solution.threeSum(new int[]{1, 2, -2, -1}),
                new ArrayList<>());

        check("长度不足",
                solution.threeSum(new int[]{0, 1}),
                new ArrayList<>());

        System.out.println("所有测试通过！");
    }

    private static void check(String description, List<List<Integer>> actual, List<List<Integer>> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(description + "：期望 " + expected + "，实际 " + actual);
        }
        System.out.println("通过：" + description + "，结果 = " + actual);
    }
}
