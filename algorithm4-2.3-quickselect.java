class Solution {
    public int findKthLargest(int[] nums, int k) {
        // k-th largest, or N-k+1 smallest
        int K = nums.length - k + 1;
        // quick select
        return findK(nums, 0, nums.length - 1, K);
    }
    
    // find the K-th smallest element in the range of [lo, hi]
    private int findK(int[] nums, int lo, int hi, int K) {
        int x = new Random().nextInt(hi - lo + 1);
        // performance: use a random element in the range as pivot
        swap(nums, lo + x, lo);

        int pivot = nums[lo];
        int i = lo + 1;
        int j = hi;
        while (i <= j) {
            while (i <= hi && nums[i] < pivot) i++;
            while (j >= lo + 1 && nums[j] > pivot) j--;
            
            if (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            } else if (i == j) {
                break;
            }
        }
        // afterwards, [lo+1, j] will be elements smaller than or equal to pivot
        // [i, hi] will be elements larger than or equal to pivot
        
        swap(nums, lo, j);
        // after that, [lo, j-1] smaller than or equal to pivot, [j] is pivot
        // num[j] is the (j - lo + 1)-th element in range [lo, hi]
        
        int rank = j - lo + 1;
        if (rank == K)
            return nums[j];
        else if (rank > K)
            return findK(nums, lo, j-1, K);
        return findK(nums, j+1, hi, K-rank);
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}