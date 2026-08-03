class Solution {
    public void duplicateZeros(int[] arr) {

        int zeros = countZeros(arr);

        int i = arr.length - 1;
        int j = arr.length + zeros - 1;

        while(i >= 0){

            if(j < arr.length){

                arr[j] = arr[i];
            }

            if(arr[i] == 0){

                j--;

                if(j < arr.length){

                    arr[j] = 0;
                }
            }

            j--;
            i--;
        }
        
    }

    private int countZeros(int[] arr){

        int zeros = 0;

        for(int nums : arr){

            if(nums == 0){

                zeros++;
            }
        }

        return zeros;
    }
}
