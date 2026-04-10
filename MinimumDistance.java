import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        
        // Map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (List<Integer> list : map.values()) {
            int size = list.size();
            
            if (size >= 3) {
                // sliding window of size 3
                for (int i = 0; i <= size - 3; i++) {
                    int left = list.get(i);
                    int right = list.get(i + 2);
                    
                    int dist = 2 * (right - left);
                    ans = Math.min(ans, dist);
                }
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
