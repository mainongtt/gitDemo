public class Solution {
    public static int findKthLargest(int[] nums, int k) {
        int left = 0;
        int n = nums.length;
        int right = nums.length -1;
        QuickSort(nums,left,right);
        return nums[n-k];
    }

    public static void QuickSort(int[] nums, int left,int right){
        if(left < right){
            int p = partition(nums,left,right);
            QuickSort(nums,left,p-1);
            QuickSort(nums,p+1,right);
        }
    }
    public static int partition(int[] nums, int left, int right){
        int lo = left;
        int ro = right + 1;
        int paviot = nums[left];
        while(true){
            while(nums[--ro] >= paviot){
                if(ro <= left){
                    break;
                }
            }
            while(nums[++lo] <= paviot){
                if(lo >= right){
                    break;
                }
            }
            if(lo >= ro){
                break;
            }
            swap(nums,lo,ro);
        }
        swap(nums,left,ro);
        return ro;

    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int largest = findKthLargest(nums, 4);
        System.out.println(largest);
    }
}
