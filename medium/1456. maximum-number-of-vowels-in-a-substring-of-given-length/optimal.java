class Solution {
    public int maxVowels(String s, int k) {
        
        int left = 0;
        int count = 0;
        int maxCount = 0;

        for(int right = 0; right < s.length(); right++){

            if(isVowel(s.charAt(right))){

                count++;
            }

            if(right - left + 1 > k){

                if(isVowel(s.charAt(left))){
                    count--;
                }

                left++;
            }

            if(right - left + 1 == k){

                maxCount = Math.max(maxCount, count);
            }
        }

        return maxCount;
    }

    private boolean isVowel(char c){

        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
