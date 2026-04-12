class Solution {

    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int minimumDistance(String word) {
        int n = word.length();
        int total = 0;

        // Step 1: total distance using one finger
        for (int i = 1; i < n; i++) {
            total += dist(word.charAt(i - 1) - 'A', word.charAt(i) - 'A');
        }

        // Step 2: DP to maximize saving
        int[] dp = new int[26]; // dp[b] = max saving with second finger at b

        for (int i = 1; i < n; i++) {
            int a = word.charAt(i - 1) - 'A';
            int c = word.charAt(i) - 'A';

            int[] next = dp.clone();

            for (int b = 0; b < 26; b++) {
                int gain = dist(a, c) - dist(b, c);
                next[a] = Math.max(next[a], dp[b] + gain);
            }

            dp = next;
        }

        int maxSave = 0;
        for (int val : dp) {
            maxSave = Math.max(maxSave, val);
        }

        return total - maxSave;
    }
}
