class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        int[] even1 = new int[26];
        int[] odd1  = new int[26];
        int[] even2 = new int[26];
        int[] odd2  = new int[26];

        for (int i = 0; i < n; i++) {
            int c1 = s1.charAt(i) - 'a';
            int c2 = s2.charAt(i) - 'a';

            if ((i & 1) == 0) { 
                even1[c1]++;
                even2[c2]++;
            } else {           
                odd1[c1]++;
                odd2[c2]++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (even1[i] != even2[i] || odd1[i] != odd2[i]) {
                return false;
            }
        }

        return true;
    }
}
