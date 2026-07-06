class Solution {

    private Boolean dp[][];

    public boolean isMatch(String s, String p) {
        
        dp = new Boolean[s.length()+1][p.length()+1];

        return dfs(0, 0, s, p);
    }

    private boolean dfs(int i, int j, String s, String p){

        if(dp[i][j] != null){
            return dp[i][j];
        }

        if(i >= s.length() && j >= p.length()) return true;

        if(j >= p.length()) return false;

        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        if(j+1 < p.length() && p.charAt(j+1) == '*'){

            dp[i][j] = dfs(i, j+2, s, p) || match && dfs(i+1, j, s, p);
            return dp[i][j];
        }

        if(match){

            dp[i][j] = dfs(i+1, j+1, s, p);
            return dp[i][j];
        }

        dp[i][j] = false;

        return false;
    }
}
