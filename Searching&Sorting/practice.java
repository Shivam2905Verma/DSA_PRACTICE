import java.util.ArrayList;
import java.util.Arrays;

public class practice {

    // 704. Binary Search
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                ans = mid;
                break;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    // 35. Search Insert Position ( Leetcode ) ( Lag )
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    // 34. Find First and Last Position of Element in Sorted Array ( Leetcode ) (
    // Repeat )
    public int[] searchRange(int[] nums, int target) {
        int left = findBound(nums, target, true);
        if (left == -1) {
            return new int[] { -1, -1 };
        }
        int right = findBound(nums, target, false);
        return new int[] { left, right };
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int start = 0;
        int end = nums.length - 1;
        int bound = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return bound;
    }

    // 33. Search in Rotated Sorted Array ( Leetcode ) ( Lag ) ( Lag )
    public int searchInRotated(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    // 215 Kth Largest Element in an Array ( Leetcode ) ( Quick select method ) (
    // There is also a heap method )
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length;
        int target = k - 1;

        while (left <= right) {

            int pivotIndex = givePivotIndex(nums, left, right);

            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex > target) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }

        return -1;
    }

    public static int givePivotIndex(int nums[], int left, int right) {
        int pivot = nums[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] > pivot) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
            }
        }

        int temp = nums[i];
        nums[i] = nums[right];
        nums[right] = temp;

        return i;

    }

    /*
     * This is something different method In this approach, we create a frequency
     * array to store how many times each number appears in the original array. We
     * use the index of each number to store its frequency, adjusting the index
     * using the minimum value.
     * This helps us represent the numbers in sorted order without actually sorting
     * the array. Finally, we traverse the frequency array from the largest value to
     * the smallest, subtracting each number's frequency from k. When k becomes less
     * than or equal to 0, we have found the kth largest element, so we return that
     * number.
     */
    public int findKthLargest2(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            } else if (num < min) {
                min = num;
            }
        }
        int freq[] = new int[max - min + 1];
        for (int num : nums) {
            freq[num - min]++;
        }

        for (int i = max - min; i >= 0; i--) {
            k -= freq[i];
            if (k <= 0)
                return i + min;
        }
        return -1;
    }

    // 435. Non-overlapping Intervals ( Leetcode )
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int max = intervals[0][1];

        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (max == intervals[i][0]) {
                max = Math.max(max, intervals[i][1]);
            } else if (max > intervals[i][0]) {
                count++;
                max = Math.min(max, intervals[i][1]);
            }
        }

        return count;
    }

    // 55. Jump Game ( Leetcode )
    public boolean canJump(int[] nums) {
        int target = nums.length - 1;
        int fartestPoint = 0;
        for (int i = 0; i < nums.length; i++) {
            if (fartestPoint >= target) {
                return true;
            }
            if (nums[i] == 0) {
                if (fartestPoint > i) {
                    continue;
                } else {
                    return false;
                }
            }
            fartestPoint = Math.max(i + nums[i], fartestPoint);

        }
        return false;
    }

    // 134. Gas Station ( Leetcode ) ( Lag )
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int currentGas = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];

            totalGas += diff;
            currentGas += diff;

            if (currentGas < 0) {
                start = i + 1;
                currentGas = 0;
            }
        }
        return totalGas < 0 ? -1 : start;
    }

    // 881. Boats to Save People ( Leetcode ) ( Lag )
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int boats = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }

            right--;
            boats++;
        }

        return boats;
    }

    // 2089. Find Target Indices After Sorting Array ( Leetcode )
    public ArrayList<Integer> targetIndices(int[] nums, int target) {
        ArrayList<Integer> ans = new ArrayList<>();

        int start = 0;
        int end = nums.length - 1;

        int si = 0;
        int ei = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                si = findLeft(nums, target, mid);
                ei = findRight(nums, target, mid);
                break;
            }

            if (nums[mid] > target) {
                end = mid - 1;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            }
        }

        for (int i = si; i < ei; i++) {
            ans.add(i);
        }

        return ans;
    }

    public int findLeft(int[] nums, int target, int e) {
        int start = 0;
        int end = e;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            }
            if (nums[mid] >= target) {
                end = mid - 1;
            }
        }

        return start;
    }

    public int findRight(int[] nums, int target, int s) {
        int start = s;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            }
            if (nums[mid] > target) {
                end = mid - 1;
            }
        }

        return start;
    }

    public static void main(String[] args) {

    }
}
