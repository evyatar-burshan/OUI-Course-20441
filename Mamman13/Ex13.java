/**
 * This class is dedicated to Mamman 13.
 * 
 * @author ID: 333180354
 * @version 10/06/23
 */
public class Ex13
{
    /**
     * Calcultes the shortest route between two roads with one swap.
     * 
     * Time Complexity: O(n). 3n => n. The function iterates over th loop
     * 3 times when n is the length of the input arrays.
     * 
     * Space Complexity: O(1). The function uses a fixed amount of
     * additional space that does not depend on the input size.
     *
     * @param road1 An array representing the first road
     * @param road2 An array representing the second road
     * @return The shortest time to take in road1 and road2 with one swap
     */
    public static int shortestRoad (int [] road1, int [] road2) {
        int sum1 = 0;
        for(int i=0; i<road1.length;i++){
            sum1+=road1[i];
        }
        int sum2 = sum1;
        int min = sum1;
        
        for(int i=road1.length-1;i>0; i--){
            sum1 = sum1 - road1[i] + road2[i];
            if(sum1<min){
                min = sum1;
            } 
        }
        
        for(int i=0; i<road1.length;i++){
            sum2 = sum2 - road1[i] + road2[i];
            if(sum2<min){
                min = sum2;
            } 
        }

        return min;
    }
    
    /**
     * Calculates the missing value in an arithmetic sequence array
     * 
     * Time Complexity: O(logn). The function uses binary search on the array.
     * 
     * Space Complexity: O(1). The function uses a fixed amount of
     * additional space that does not depend on the input size.
     * 
     * @param arr The arithmetic sequence array
     * @return The missing value
     */
    public static int missingValue (int [] arr) {

        if (arr.length == 2) {
            return (arr[0] + arr[1]) / 2;
        }

        int diff;
        int firstDiff = arr[1] - arr[0];
        int secondDiff = arr[2] - arr[1];
        if (firstDiff != secondDiff) {
            diff = Math.min(firstDiff, secondDiff);
            
            if (firstDiff < secondDiff) {
                return arr[2] - diff;
            }
            
            return arr[1] - diff;
        } 
        
        diff = firstDiff;
        int left = 0;
        int right = arr.length - 1;
        int mid = arr.length / 2;

        while (right - left > 1) {
            
            int correctLeftSum = (int) (((mid - left + 1) / 2.0) * ((2 * arr[left]) + (((mid - left + 1) - 1) * diff)));
            int curLeftSum = (int) (((mid - left + 1) / 2.0) * (arr[left] + arr[mid]));
            int correctRightSum = (int) (((right - mid + 1) / 2.0) * ((2 * arr[mid]) + (((right - mid + 1) - 1) * diff)));
            int curRightSum = (int) (((right - mid + 1) / 2.0) * (arr[mid] + arr[right]));
            
            if (curLeftSum != correctLeftSum) {
                right = mid;
                mid = (right + left) / 2;
            }
            
            if (curRightSum != correctRightSum) {
                left = mid;
                mid = (right + left) / 2;
            } 
        } 
        
        return (arr[right] + arr[left]) / 2;
    }
    
    /**
     * Calculates the length of the longest palindrome subarray within the given array.
     * 
     * @param arr The array to search for the longest palindrome subarray
     * @return The length of the longest palindrome subarray
     */
    public static int longestPalindrome (int[] arr) {
        return longestPalindrome(arr, 0, arr.length-1, 0);
    }
    
    /**
     * Helper method for longestPalindrome (int[] arr) with the same purpose
     * 
     * @param arr The array to search for the longest palindrome subarray
     * @param low The start of the current subarray
     * @param high The end of the current subarray
     * @param longestPal The length of the longest palindrome found so far
     * @return The length of the longest palindrome subarray
     */
    private static int longestPalindrome (int[] arr, int low, int high, int longestPal) {
        
        if (!isPalindrome(arr, low, high)) {
            return longestPalindrome(arr, low, high - 1, longestPal);
        }
            
        if (low > high) {
            return longestPal;
        }

        if (high - low + 1 > longestPal) {
            longestPal = high - low + 1;
        }

        return longestPalindrome(arr, low + 1, arr.length - 1, longestPal);
    }
    
    /**
     * Checks if a subarray is a palindrome
     *
     * @param arr The array to check
     * @param start The start of the subarray
     * @param end The end of the subarray
     * @return Whether the subarray is a palindrome or not
     */
    private static boolean isPalindrome (int[] arr, int start, int end) {
        
        if (start >= end) {
            return true;
        }
        
        if (arr[start] != arr[end]) {
            return false;
        }
        
        return isPalindrome(arr, start + 1, end - 1);
    }
    
    /**
     * Calculates if there is a sequence of elements in a given array
     * that can add up to a given number (num) without 3 consecutive elements
     * 
     * @param a The array to search
     * @param num The target sum number
     * @return Whether there exists a sum of elements that reaches the target number or not
     */
    public static boolean isSum (int[] a, int num) {
        if (num < 0) {
            return false;
        }
        if (num == 0) {
            return true;
        }
        return isSum(a, num, 0, 0, 0);
    }
    
    /**
     * Helper method for isSum (int[] a, int num), with the same purpose
     *
     * @param a The array to search
     * @param num The target sum number
     * @param curSum The current sum of elements
     * @param i The index of the current subarray start
     * @param consecNums Counter for consective elements
     * @return Whether there exists a sum of elements that reaches the target number or not
     */
    private static boolean isSum(int[] a, int num, int curSum, int i, int consecNums) {
        
        if ((i == a.length && curSum != num) || curSum > num) {
            return false;
        }

        if (consecNums == 3) {
            if (curSum == num) {
                return false;
            }
            consecNums = 0;
        } 
        
        if (curSum == num)
            return true;
        
        return isSum(a, num, curSum + a[i], i + 1, consecNums + 1)
                     || isSum(a, num, curSum, i + 1, 0);
    }
}
