// one pointer for 0s 
// another for non zero nums

class Solution {
    public void moveZeroes(int[] nums) {
        
        int slow = 0;

        for(int fast = 0; fast < nums.length; fast++){

            if(nums[fast] != 0){

                swap(slow, fast, nums);
                slow++;
            }
        }

    }

    private void swap(int slow, int fast, int[] nums){

        int temp = nums[slow];
        nums[slow] = nums[fast];
        nums[fast] = temp;
    }
}
