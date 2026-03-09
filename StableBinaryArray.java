class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1000000007;
        
        long[][][] dp = new long[zero + 1][one + 1][2];
        for (int z = 1; z <= Math.min(limit, zero); z++) {
            dp[z][0][0] = 1;
        }
        for (int o = 1; o <= Math.min(limit, one); o++) {
            dp[0][o][1] = 1;
        }
        
        for (int z = 0; z <= zero; z++) {
            for (int o = 0; o <= one; o++) {

                if (dp[z][o][0] > 0) {
                    for (int k = 1; k <= limit && o + k <= one; k++) {
                        dp[z][o + k][1] = (dp[z][o + k][1] + dp[z][o][0]) % MOD;
                    }
                }
                if (dp[z][o][1] > 0) {
                    for (int k = 1; k <= limit && z + k <= zero; k++) {
                        dp[z + k][o][0] = (dp[z + k][o][0] + dp[z][o][1]) % MOD;
                    }
                }
            }
        }
        long result = (dp[zero][one][0] + dp[zero][one][1]) % MOD;
        return (int) result;
    }
}
