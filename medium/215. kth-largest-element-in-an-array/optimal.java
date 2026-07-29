class Solution {
    public int findKthLargest(int[] nums, int k) {
        
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private int quickSelect(int[] nums, int low, int high, int target){

        if(low == high) return nums[low];

        int pivotIndex = partition(nums, low, high);

        if(pivotIndex == target) return nums[pivotIndex];

        if(target < pivotIndex) return quickSelect(nums, low, pivotIndex - 1, target);

        return quickSelect(nums, pivotIndex + 1, high, target);
    }

    private int partition(int[] nums, int low, int high){

        int i = low;
        int pivot = nums[high];

        for(int j = low; j < high; j++){

            if(nums[j] < pivot){

                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, high);
        return i;
    }

    private void swap(int[] nums, int i, int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
