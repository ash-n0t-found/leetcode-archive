/* complexity analysis:

time complexity:
>> while(i < m && j < n)
    loop runs at most m + n times because every iteration incements either i or j 
>> while(i < m)
    runs at most m times
>> while(j < n)
    runs at most n times

=> O(m + n) + O(m) + O(n) = O(m + n)

space complexity:
>>  int merged[] = new int[m + n];
extra space of m + n created

=> O(m + n)

time: O(m + n)
space: O(m + n)

*/

class bruteForce {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int i = 0, j = 0, k = 0;

        int merged[] = new int[m + n];
 
        while(i < m && j < n){
            if(nums1[i] <= nums2[j]){
                merged[k++] = nums1[i++];
            }
            else{
                merged[k++] = nums2[j++];
            }
        }

        while(i < m){
            merged[k++] = nums1[i++];
        }
        while(j < n){
            merged[k++] = nums2[j++];
        }

        int total = m + n;

        if(total % 2 == 1){
            return merged[total/2];
        }

        return (merged[total/2 - 1] + merged[total/2])/2.0;
    }
}