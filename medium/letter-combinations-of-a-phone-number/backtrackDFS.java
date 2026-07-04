class Solution {
    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<>();

        if(digits.length() == 0) return result;
    
        backtrack(0, digits, new StringBuilder(), result);

        return result;

    }

    private static final String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    private void backtrack(int index, String digits, StringBuilder current, List<String> result){

        if(digits.length() == index){
            result.add(current.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for(char ch : letters.toCharArray()){

            current.append(ch);

            backtrack(index + 1, digits, current, result);

            current.deleteCharAt(current.length() - 1);
        }
    }



}
