class Solution {
    public boolean isHappy(int n) {
        
        int slow = n;
        int fast = n;

        do{

            slow = nextNum(slow);
            fast = nextNum(nextNum(fast));

        } while(slow != fast);

        return slow == 1;
    }

    private int nextNum(int num){

        int sum = 0;

        while(num > 0){

            int digit = num % 10;
            sum += digit * digit;
            num = num / 10;
        }

        return sum;
    }
}
