class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        return bud(nums, 0, nums.length - 1);
    }

    public TreeNode bud(int[] nums, int l, int r) {

        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        TreeNode rt = new TreeNode(nums[mid]);

        rt.left = bud(nums, l, mid - 1);
        rt.right = bud(nums, mid + 1, r);

        return rt;
    }
}
