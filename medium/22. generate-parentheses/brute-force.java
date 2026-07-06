class Solution {

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack(n, new StringBuilder(), result);

        return result;
    }

    private void backtrack(int n,
                           StringBuilder current,
                           List<String> result) {

        if(current.length() == 2*n){

            if(isValid(current)){
                result.add(current.toString());
            }

            return;
        }

        current.append('(');
        backtrack(n,current,result);
        current.deleteCharAt(current.length()-1);

        current.append(')');
        backtrack(n,current,result);
        current.deleteCharAt(current.length()-1);
    }

    private boolean isValid(StringBuilder current){

        int balance = 0;

        for(int i=0;i<current.length();i++){

            if(current.charAt(i)=='(')
                balance++;
            else
                balance--;

            if(balance<0)
                return false;
        }

        return balance==0;
    }
}
