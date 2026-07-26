class Solution {
    public double findMaxAverage(int[] nums, int k) {
        
        int left = 0;
        double sum = 0;
        double maxAvg = -Double.MAX_VALUE;

        for(int right = 0; right < nums.length; right++){

            sum += nums[right];

            if(right - left + 1 > k){
                sum -= nums[left];
                left++;
            }

            if(right - left + 1 == k){
                
                double avg = sum / k;
                maxAvg = Math.max(maxAvg, avg);

            }
        }

        return maxAvg;
    }
}
