class Solution {
    public int[] applyOperations(int[] nums) {

        for(int i = 0; i < nums.length - 1; i++){

            if(nums[i] == nums[i + 1]){

                nums[i] = nums[i] * 2;
                nums[i + 1] = 0;
            }
        }

        int slow = 0;

        for(int fast = 0; fast < nums.length; fast++){

            if(nums[fast] != 0){

                swap(nums, slow, fast);
                slow++;
            }
        }

        return nums;
        
    }

    private void swap(int[] arr, int slow, int fast){

        int temp = arr[slow];
        arr[slow] = arr[fast];
        arr[fast] = temp;
    }
}
