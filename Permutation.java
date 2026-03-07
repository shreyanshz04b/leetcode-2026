import java.util.*;

class Solution {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        makePermutation(nums, 0, result);

        return result;
    }

    public void makePermutation(int[] numbers, int startIndex, List<List<Integer>> result) {

        if (startIndex == numbers.length) {

            List<Integer> onePermutation = new ArrayList<>();

            for (int num : numbers) {
                onePermutation.add(num);
            }

            result.add(onePermutation);
            return;
        }

        for (int i = startIndex; i < numbers.length; i++) {

            int temp = numbers[startIndex];
            numbers[startIndex] = numbers[i];
            numbers[i] = temp;
            makePermutation(numbers, startIndex + 1, result);
            temp = numbers[startIndex];
            numbers[startIndex] = numbers[i];
            numbers[i] = temp;
        }
    }
}
