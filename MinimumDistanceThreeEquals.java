class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

        int[] last1 = new int[n + 1];
        int[] last2 = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            last1[i] = -1;
            last2[i] = -1;
        }

        int res = -1;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            if (last2[x] != -1) {
                int d = (i - last2[x]) * 2;
                if (res == -1 || d < res) res = d;
            }

            last2[x] = last1[x];
            last1[x] = i;
        }

        return res;
    }
}
