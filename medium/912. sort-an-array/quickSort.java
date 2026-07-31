// Randomized quicksort + Hoare partition

class Solution {

    private Random rand = new Random();

    public int[] sortArray(int[] nums) {

        quickSort(nums, 0, nums.length - 1);
        return nums;

    }

    private void quickSort(int[] nums, int low, int high) {

        if(low >= high) return;

        int randomIndex = low + rand.nextInt(high - low + 1);

        int p = hoare(nums, low, high, randomIndex);

        quickSort(nums, low, p);
        quickSort(nums, p+1, high);
    }

    private int hoare(int[] nums, int low, int high, int randomIndex) {

        swap(nums, low, randomIndex);

        int pivot = nums[low];

        int left = low - 1;
        int right = high + 1;

        while (true) {
            do {

                left++;

            } while (nums[left] < pivot);

            do {

                right--;

            } while (nums[right] > pivot);

            if (left >= right) {

                return right;
            }

            swap(nums, left, right);

        }
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
