import java.util.*;

class Solution {

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        // Map robot -> distance
        Map<Integer, Integer> distMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            distMap.put(robots[i], distance[i]);
        }

        Arrays.sort(robots);
        Arrays.sort(walls);

        int[] leftGain = new int[n];
        int[] rightGain = new int[n];
        int[] overlap = new int[n];

        for (int i = 0; i < n; i++) {
            int pos = robots[i];
            int d = distMap.get(pos);

            int wallIdxAtRobot = upperBound(walls, pos);

            // ---------- LEFT ----------
            int leftStart = pos - d;
            if (i > 0) {
                leftStart = Math.max(leftStart, robots[i - 1] + 1);
            }
            int leftWallIdx = lowerBound(walls, leftStart);

            leftGain[i] = wallIdxAtRobot - leftWallIdx;

            // ---------- RIGHT ----------
            int rightEnd = pos + d;
            if (i < n - 1) {
                rightEnd = Math.min(rightEnd, robots[i + 1] - 1);
            }
            int rightWallIdx = upperBound(walls, rightEnd);
            int startIdx = lowerBound(walls, pos);

            rightGain[i] = rightWallIdx - startIdx;

            // ---------- OVERLAP BETWEEN i-1 and i ----------
            if (i > 0) {
                int prevWallIdx = lowerBound(walls, robots[i - 1]);
                overlap[i] = wallIdxAtRobot - prevWallIdx;
            }
        }

        // ---------- DP ----------
        int takeLeft = leftGain[0];
        int takeRight = rightGain[0];

        for (int i = 1; i < n; i++) {

            int newLeft = Math.max(
                takeLeft + leftGain[i],
                takeRight - rightGain[i - 1]
                        + Math.min(leftGain[i] + rightGain[i - 1], overlap[i])
            );

            int newRight = Math.max(
                takeLeft + rightGain[i],
                takeRight + rightGain[i]
            );

            takeLeft = newLeft;
            takeRight = newRight;
        }

        return Math.max(takeLeft, takeRight);
    }

    // first index >= target
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    // first index > target
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}
