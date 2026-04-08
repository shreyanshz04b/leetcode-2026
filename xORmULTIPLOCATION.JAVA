import java.util.*;

class Solution {
    public int xorAfterQueries(int[] arr, int[][] qrs) {
        int mod = (int)1e9 + 7;
        int n = arr.length, q = qrs.length;
        int block = (int)Math.sqrt(n);

        ArrayList<int[]>[][] bucket = new ArrayList[block + 1][block + 1];

        for (int i = 0; i < block; i++) {
            for (int j = 0; j < block; j++) {
                bucket[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < q; i++) {
            int l = qrs[i][0], r = qrs[i][1];
            int step = qrs[i][2], val = qrs[i][3];

            if (step >= block) {
                for (int idx = l; idx <= r; idx += step) {
                    arr[idx] = (int)((1L * arr[idx] * val) % mod);
                }
            } else {
                int rem = l % step;
                bucket[step][rem].add(new int[]{l, r, val});
            }
        }

        for (int step = 1; step < block; step++) {
            for (int rem = 0; rem < step; rem++) {
                if (bucket[step][rem].isEmpty()) continue;

                ArrayList<int[]> list = bucket[step][rem];

                int len = (n - rem + step - 1) / step;
                long[] diff = new long[len + 1];
                Arrays.fill(diff, 1L);

                for (int[] e : list) {
                    int l = e[0], r = e[1], v = e[2];

                    int s = (l - rem) / step;
                    int epos = (r - rem) / step;

                    if (s < 0) s = 0;
                    if (epos >= len) epos = len - 1;
                    if (s > epos) continue;

                    diff[s] = (diff[s] * v) % mod;
                    if (epos + 1 < len) {
                        diff[epos + 1] = (diff[epos + 1] * inv(v, mod)) % mod;
                    }
                }

                long cur = 1L;
                for (int i = 0; i < len; i++) {
                    cur = (cur * diff[i]) % mod;
                    int idx = rem + i * step;
                    arr[idx] = (int)((1L * arr[idx] * cur) % mod);
                }
            }
        }

        int ans = 0;
        for (int x : arr) ans ^= x;
        return ans;
    }

    private long inv(long x, int mod) {
        return fastPow(x, mod - 2, mod);
    }

    private long fastPow(long base, long exp, int mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
