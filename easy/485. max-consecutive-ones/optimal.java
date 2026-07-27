class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        
        int left = 0;
        int maxLen = 0;

        for(int right = 0; right < nums.length; right++){

            while(nums[right] == 0){

                left = right + 1;
                break;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }

        return maxLen;

    }
}
