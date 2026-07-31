// heap sort , time = O(nlogn) and space = O(1)

class Solution {
    public int[] sortArray(int[] nums) {

        int n = nums.length;

        for(int i = n/2 - 1; i >= 0; i--){

            heapify(nums, i, n);
        }

        for(int i = n - 1; i > 0; i--){

            swap(nums, i, 0);

            heapify(nums, 0, i);
        }

        return nums;
        
    }

    private void heapify(int[] nums, int i, int n){

        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left < n && nums[left] > nums[largest]) largest = left;

        if(right < n && nums[right] > nums[largest]) largest = right;

        if(largest != i){

            swap(nums, i, largest);

            heapify(nums, largest, n);
        }
    }

    private void swap(int nums[], int i, int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
