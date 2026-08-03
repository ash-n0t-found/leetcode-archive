// n = array length, m = operations length
// Time: O(n + m) and Space: O(n)

class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){

            map.put(nums[i], i);
        }

        for(int i = 0; i < operations.length; i++){

            if(map.containsKey(operations[i][0])){

                
                int oldValue = operations[i][0];
                int newValue = operations[i][1];

                int index = map.get(oldValue);
                nums[index] = newValue;

                map.remove(oldValue);
                map.put(newValue, index);
            }
        }

        return nums;
        
    }
}
