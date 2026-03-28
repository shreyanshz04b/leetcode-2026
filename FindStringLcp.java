import java.util.*;

class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Basic validation
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
            for (int j = 0; j < n; j++) {
                if (lcp[i][j] != lcp[j][i]) return "";
            }
        }

        // Step 2: Construct string
        char[] res = new char[n];
        Arrays.fill(res, '?');

        char ch = 'a';

        for (int i = 0; i < n; i++) {
            if (res[i] == '?') {
                if (ch > 'z') return ""; // more than 26 groups
                res[i] = ch;

                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        res[j] = ch;
                    }
                }
                ch++;
            }
        }

        // Step 3: Validate using DP
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (res[i] == res[j]) {
                    dp[i][j] = 1;
                    if (i + 1 < n && j + 1 < n) {
                        dp[i][j] += dp[i + 1][j + 1];
                    }
                } else {
                    dp[i][j] = 0;
                }

                if (dp[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return new String(res);
    }
}
