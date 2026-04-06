import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {

        Set<Long> blocked = new HashSet<>();
        for (int[] o : obstacles) {
            long key = encode(o[0], o[1]);
            blocked.add(key);
        }

        int[][] dirs = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };

        int x = 0, y = 0, d = 0;
        int max = 0;

        for (int cmd : commands) {

            if (cmd == -1) {
                d = (d + 1) & 3;
            } else if (cmd == -2) {
                d = (d + 3) & 3;
            } else {

                for (int step = 0; step < cmd; step++) {

                    int nx = x + dirs[d][0];
                    int ny = y + dirs[d][1];

                    if (blocked.contains(encode(nx, ny))) break;

                    x = nx;
                    y = ny;

                    int dist = x * x + y * y;
                    if (dist > max) max = dist;
                }
            }
        }

        return max;
    }

    private long encode(int x, int y) {
        return (((long) x) << 32) | (y & 0xffffffffL);
    }
}
