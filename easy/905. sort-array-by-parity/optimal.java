class Solution {
    public int[] sortArrayByParity(int[] nums) {
        
        int left = -1;
        int right = nums.length;

        while(true){

            do{

                left++;

            } while(left < nums.length && nums[left] % 2 == 0);

            do{

                right--;

            } while(right >= 0 && nums[right] % 2 == 1);

            if(left >= right){

                return nums;
            }

            swap(nums, left, right);
        }
    }

    private void swap(int[] nums, int left, int right){

        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

    }
}
