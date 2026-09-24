import java.util.Random;

class SortArray {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    private void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            //随机选一个pivot
            Random random=new Random();
            int pivotIndex=random.nextInt(right - left + 1) + left;
            int pivot=nums[pivotIndex];
            int i=left, j=right;

            while (i <= j) {
                while (nums[i] < pivot) {
                    i++;
                }
                while (nums[j] > pivot) {
                    j--;
                }
                if (i <= j) {
                    //交换nums[i]和nums[j]
                    int temp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                    i++;
                    j--;
                }
            }
            //递归排序左右两部分
            quickSort(nums, left, j);
            quickSort(nums, i, right);
        }
    }

}