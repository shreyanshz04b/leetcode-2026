import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] health, String dir) {

        int n = positions.length;

        Integer[] sortedIdx = new Integer[n];
        for (int i = 0; i < n; i++) sortedIdx[i] = i;

        Arrays.sort(sortedIdx, Comparator.comparingInt(i -> positions[i]));

        boolean[] isAlive = new boolean[n];
        Arrays.fill(isAlive, true);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int curr : sortedIdx) {

            char direction = dir.charAt(curr);

            if (direction == 'R') {
                stack.push(curr);
                continue;
            }

            // current robot moving left
            while (!stack.isEmpty()) {

                int prev = stack.peek();

                if (health[prev] < health[curr]) {
                    isAlive[prev] = false;
                    stack.pop();
                    health[curr]--;
                    continue;
                }

                if (health[prev] > health[curr]) {
                    isAlive[curr] = false;
                    health[prev]--;
                    break;
                }

                // equal health
                isAlive[prev] = false;
                isAlive[curr] = false;
                stack.pop();
                break;
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isAlive[i]) {
                answer.add(health[i]);
            }
        }

        return answer;
    }
}
