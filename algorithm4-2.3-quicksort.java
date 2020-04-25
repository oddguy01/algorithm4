class Solution {
    public int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length-1);
        return nums;
    }

    private void quicksort(int[] nums, int lo, int hi) {
        if (lo >= hi)
            return; // assure that lo < hi
        int j = partition(nums, lo, hi);
        quicksort(nums, lo, j-1);
        quicksort(nums, j+1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo + 1; // it's guaranteed that lo < hi
        int j = hi;
        while (i <= j) {
            // we stop at items which equal to the pivot during scanning, and swap.
            // This is because, if the original array contains almost all identical
            // items equal to the pivot, skipping equal items will result in
            // 2 un-balanced subarrays (fails to reduce the scale of problem by half)
            // and quadratic running time.
            while (i <= hi && nums[i] < pivot) i++;
            while (j >= lo + 1 && nums[j] > pivot) j--;

            if (i < j) {
                swap(nums, i, j); i++; j--;
            } else if (i == j) {
                // j may be equal to i so we need to break, to avoid infinite loop
                break;
            }
        }
        // afterwards, [lo+1, j] smaller or equal to pivot, [i, hi] larger or equal

        swap(nums, lo, j);
        // after swap, [lo, j-1] smaller or equal to pivot

        return j;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}