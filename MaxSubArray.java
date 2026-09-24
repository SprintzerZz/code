public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        //presum表示前缀和，max表示最大子数组和
        int presum = 0, max = nums[0];
        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 更新前缀和，如果前缀和为负数则重置为0
            presum = Math.max(presum, 0) + nums[i];
            // 更新最大子数组和
            max = Math.max(max, presum);
        }
        return max;
    }
    
}
