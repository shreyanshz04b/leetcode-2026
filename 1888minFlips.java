class Solution {

    public int minFlips(String s) {
        int n = s.length();
        String doubled = s + s;
        int fP1 = 0; 
        int fp2 = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < doubled.length(); i++) {
            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';
            if (doubled.charAt(i) != expected1) fP1++;
            if (doubled.charAt(i) != expected2) fp2++;
            if (i >= n) {
                char oldChar = doubled.charAt(i - n);
                char oldExpected1 = ((i - n) % 2 == 0) ? '0' : '1';
                char oldExpected2 = ((i - n) % 2 == 0) ? '1' : '0';
                if (oldChar != oldExpected1) fP1--;
                if (oldChar != oldExpected2) fp2--;
            }
            if (i >= n - 1) {
                ans = Math.min(ans, Math.min(fP1, fp2));
            }
        }

        return ans;
    }
}
