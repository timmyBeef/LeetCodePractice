package leetcode.array.java;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/continuous-subarray-sum/
/*
leetcode 523
數學題
本題可以理解成這樣 （subarray sum % k = 0) =>  aj+1 + ... + an = |n1-n2|k

所以可以變成這樣, 餘數一樣的時候, 能符合這題（subarray sum % k = 0)

ai + .... aj           = n1k + q
ai + .... aj + .... an = n2k + q
=>
aj+1 + ... + an = |n1-n2|k

use hashmap

init: map.put(0, -1);// presum%k :0, index: -1

caculate the presum, but store into map <presum%k and index>
check if in the later,
the sum%k exists in map, it means we have mutilple-k sum in this array
return true

-----------------------------
一樣先計算 presum

map.put(0, -1);// presum:0, index: -1

跑迴圈
更新 sum = sum%k  // 取得 % 後的 key
如果之後遇到 map.containsKey(sum) // 如果之後又遇到, 代表你和上一個發生 這個 % 後的 key, 相差了 k倍 !
if (i - map.get(sum) > 1) // 所以看一下 index 和上次發生的 index 是不是 比 1 大, 代表中間有隔一個數, 所以是連續的
return true;
} else {
    // 紀錄 % 後的 key
}
其他都false

Now, assume that the given sum value at the i-th
  index be equal to rem. Now, if any subarray follows the i-th
  element, which has a sum equal to the integer multiple of k, say extending upto the j-th
  index, the sum value to be stored in the HashMap for the j-th index
  will be: (rem+n∗k), where n is some integer > 0.
  We can observe that  rem(rem+n∗k), which is the same value as stored corresponding
  to the i-th index..

  Time complexity : O(n).

  Space complexity : O(n)

corner case
[0,0] k=0

so
if (k != 0) {//!!!!
                sum = sum % k; // 取得 % 後的 key
}
and

// map 有就不會更新!!! 因為有遇到符合的,應該就會回傳了...除非是[0,0] k=0
if (map.containsKey(sum)) {
        if (i - map.get(sum) > 1)
                   return true;

} else { //!!!
    map.put(sum, i);// 紀錄 % 後的 key
}
*/
public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // for [0,0] k=0, return true
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {//!!!! moder != 0
                sum = sum % k; // 取得 % 後的 key
            }
            // map 有就不會更新!!! 因為有遇到符合的 應該就會回傳了...除非是[0,0] k=0
            if (map.containsKey(sum)) { // 如果之後又遇到, 代表你和上一個發生 這個% 後的 key, 相差了 k倍 !
                if (i - map.get(sum) > 1) // 所以看一下 index 和上次發生的 index 是不是 比 1 大, 代表中間有隔一個數, 所以是連續的
                    return true;
            } else { //!!!
                map.put(sum, i);// 紀錄 % 後的 key
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        System.out.println(new ContinuousSubarraySum().checkSubarraySum(nums, k));
    }
}