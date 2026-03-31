class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;

        char[] res = new char[L];
        Arrays.fill(res, '?');
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] == '?' || res[i + j] == str2.charAt(j)) {
                        res[i + j] = str2.charAt(j);
                    } else {
                        return ""; 
                    }
                }
            }
        }
        for (int i = 0; i < L; i++) {
            if (res[i] == '?') {
                for (char c = 'a'; c <= 'z'; c++) {
                    res[i] = c;
                    if (isValid(res, str1, str2)) {
                        break;
                    }
                }
            }
        }

        if (!isValid(res, str1, str2)) return "";

        return new String(res);
    }

    private boolean isValid(char[] res, String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        for (int i = 0; i < n; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (res[i + j] != str2.charAt(j)) {
                    match = false;
                    break;
                }
            }

            if (str1.charAt(i) == 'T' && !match) return false;
            if (str1.charAt(i) == 'F' && match) return false;
        }
        return true;
    }
}
