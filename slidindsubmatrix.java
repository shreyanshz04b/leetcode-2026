import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] ans = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {

                Set<Integer> set = new HashSet<>();

                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        set.add(grid[x][y]);
                    }
                }

                List<Integer> list = new ArrayList<>(set);
                Collections.sort(list);

                int minDiff = Integer.MAX_VALUE;

                if (list.size() <= 1) {
                    ans[i][j] = 0;
                    continue;
                }

                for (int t = 1; t < list.size(); t++) {
                    int diff = list.get(t) - list.get(t - 1);
                    minDiff = Math.min(minDiff, diff);
                }

                ans[i][j] = minDiff;
            }
        }

        return ans;
    }
}
