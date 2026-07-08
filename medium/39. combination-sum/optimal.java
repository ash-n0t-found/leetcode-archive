class Solution {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        backtrack(candidates, target, 0, new ArrayList<>());

        return result;
        
    }

    private void backtrack(int[] candidates, int target, int index, List<Integer> current){

        if(target == 0){
            result.add(new ArrayList<>(current));
            return;
        }

        if(target < 0 || index == candidates.length){
            return;
        }

        current.add(candidates[index]);

        backtrack(candidates, target - candidates[index], index, current);

        current.remove(current.size() - 1);

        backtrack(candidates, target, index + 1, current);
    }
}
