import java.util.ArrayList;
import java.util.Random;



class FindKthLargest {
    //快速选择算法
     private int quickSelect(ArrayList<Integer> nums, int k) {
        //随机选一个pivot
        Random random=new Random();
        int pivotIndex=random.nextInt(nums.size());
        int pivot=nums.get(pivotIndex);
        //分成三部分
        ArrayList<Integer> big=new ArrayList<>();
        ArrayList<Integer> mid=new ArrayList<>();
        ArrayList<Integer> small=new ArrayList<>();
        for(int i=0;i<nums.size();i++){
            if(nums.get(i)>pivot){
                big.add(nums.get(i));
            }else if(nums.get(i)<pivot){
                small.add(nums.get(i));
            }else{
                mid.add(nums.get(i));
            }
        }
        //判断k在哪一部分
        //k在big中——>递归在big中找第k大
        //k在mid中——>返回pivot
        //k在small中——>递归在small中找第k-big.size()-mid.size()大
        if(k<=big.size()){
            return quickSelect(big,k);
        }else if(k>big.size()+mid.size()){
            return quickSelect(small,k-big.size()-mid.size());
        }else{
            return pivot;
        }
    }

    public int findKthLargest(int[] nums, int k) {
        //nums变成arraylist
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            list.add(nums[i]);
        }
        return quickSelect(list,k);
    }

    public static void main(String[] args) {
        FindKthLargest solution = new FindKthLargest();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        int result = solution.findKthLargest(nums, k);
        System.out.println("第" + k + "大的元素是: " + result);
    }
}