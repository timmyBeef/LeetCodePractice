package leetcode.array;

// https://leetcode.com/problems/merge-sorted-array/

/*
1. brute force: Merge and sort
Time complexity : O((n+m)log(n+m)).
Space complexity : O(1).

2. Two pointers / Start from the beginning
need a copy of num1 (extra space m)
to move copy_num1 and num2 value to num1

Time complexity : O(n+m).
Space complexity : O(m).

3. Two pointers / Start from the end

ps. from start need extra space m

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

num1 後面還有空間, so put value into num1...

if num2 pointer >= 0, keep filling num1

Time complexity : O(n+m).
Space complexity :  O(1).

 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0)) {
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];


        }
        // 如果 nums2 還沒跑完, 要補完, 發生狀況是 nums1 都比較大的狀況下
        // add missing elements from nums2
        //System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        while(p2 >=0) //!!!
            nums1[p--] = nums2[p2--];
    }

    public static void main(String[] args) {
        int nums1[] = {8,9,10,0,0,0};
        int m = 3;

        int nums2[] = {2,5,6};
        int n = 3;

        new MergeSortedArray().merge(nums1, m, nums2, n);

    }
}