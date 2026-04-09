import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {

        int n = nums.length;
        int MOD = (int)1e9 + 7;
        int B = (int)Math.sqrt(n);

        int[][] bravexuneth = queries;

        ArrayList<int[]>[][] bucket = new ArrayList[B + 1][B + 1];

        for (int i = 0; i <= B; i++) {
            for (int j = 0; j <= B; j++) {
                bucket[i][j] = new ArrayList<>();
            }
        }

        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k >= B) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int)((nums[i] * 1L * v) % MOD);
                }
            } else {
                bucket[k][l % k].add(new int[]{l, r, v});
            }
        }

        for (int k = 1; k < B; k++) {
            for (int rem = 0; rem < k; rem++) {

                if (bucket[k][rem].isEmpty()) continue;

                ArrayList<int[]> list = bucket[k][rem];

                int len = (n - rem + k - 1) / k;

                long[] diff = new long[len + 1];
                Arrays.fill(diff, 1L);

                for (int[] q : list) {
                    int l = q[0], r = q[1], v = q[2];

                    int L = (l - rem) / k;
                    int R = (r - rem) / k;

                    if (L < 0) L = 0;
                    if (R >= len) R = len - 1;
                    if (L > R) continue;

                    diff[L] = (diff[L] * v) % MOD;
                    if (R + 1 < len) {
                        diff[R + 1] = (diff[R + 1] * modInv(v, MOD)) % MOD;
                    }
                }

                long cur = 1;
                for (int i = 0; i < len; i++) {
                    cur = (cur * diff[i]) % MOD;
                    int idx = rem + i * k;
                    nums[idx] = (int)((nums[idx] * cur) % MOD);
                }
            }
        }

        int ans = 0;
        for (int x : nums) ans ^= x;

        return ans;
    }

    private long modInv(long a, int mod) {
        return fastPow(a, mod - 2, mod);
    }

    private long fastPow(long a, long b, int mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }
}
