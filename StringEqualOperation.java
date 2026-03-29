class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char a1 = s1.charAt(0), b1 = s1.charAt(2);
        char a2 = s2.charAt(0), b2 = s2.charAt(2);

        if (!((a1 == a2 && b1 == b2) || (a1 == b2 && b1 == a2))) {
            return false;
        }
        char c1 = s1.charAt(1), d1 = s1.charAt(3);
        char c2 = s2.charAt(1), d2 = s2.charAt(3);

        if (!((c1 == c2 && d1 == d2) || (c1 == d2 && d1 == c2))) {
            return false;
        }

        return true;
    }
}
