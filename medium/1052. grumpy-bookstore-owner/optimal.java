class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        
        int normal = 0;

        for(int i = 0; i < customers.length; i++){

            if(grumpy[i] == 0){

                normal += customers[i];

            }
        }

        int left = 0;
        int extra = 0;
        int maxExtra = 0;

        for(int right = 0; right < customers.length; right++){

            if(grumpy[right] == 1){

                extra += customers[right];

            }

            if(right - left + 1 > minutes){
                
                if(grumpy[left] == 1){
                    
                    extra -= customers[left];
                }

                left++;

            }

            if(right - left + 1 == minutes){

                maxExtra = Math.max(maxExtra, extra);

            }
        }

        return maxExtra + normal;

    }
}
