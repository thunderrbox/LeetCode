import java.util.*;

class Solution {

    /*
     * ============================================================
     * APPROACH 1: SLIDING WINDOW
     * ============================================================
     *
     * This approach handles:
     *     a-z
     *     A-Z
     *     digits
     *     special characters
     *
     * Example:
     * s = "aAbBc"
     *
     * 'a' and 'A' are considered DIFFERENT characters.
     *
     * ------------------------------------------------------------
     * Idea:
     *
     * We maintain a window from 'left' to 'right'.
     *
     * The window must always contain unique characters.
     *
     * 1. Move right forward and add the character.
     * 2. If the character becomes duplicate:
     *      move left forward
     *      until the duplicate is removed.
     * 3. Calculate the current window length.
     * 4. Store the maximum length.
     *
     * ------------------------------------------------------------
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */

    public int lengthOfLongestSubstring(String s) {

        // ASCII characters range from 0 to 127.
        // This handles lowercase + uppercase + digits + symbols.
        int[] freq = new int[128];

        // Left boundary of sliding window
        int left = 0;

        // Stores the longest valid substring length
        int maxLength = 0;

        // Right boundary of sliding window
        for (int right = 0; right < s.length(); right++) {

            // Get current character
            char c = s.charAt(right);

            // Add current character to the window
            freq[c]++;

            /*
             * If frequency becomes greater than 1,
             * the current character is repeated.
             *
             * Shrink the window from the left.
             */
            while (freq[c] > 1) {

                // Character at the left side
                char c1 = s.charAt(left);

                // Remove it from the window
                freq[c1]--;

                // Move left forward
                left++;
            }

            /*
             * Now the window contains only unique characters.
             *
             * Length = right - left + 1
             */
            maxLength = Math.max(
                maxLength,
                right - left + 1
            );
        }

        return maxLength;
    }


    /*
     * ============================================================
     * APPROACH 2: BRUTE FORCE
     * ============================================================
     *
     * Try every possible starting position.
     *
     * For every starting position:
     *     expand the substring to the right.
     *
     * If a character is repeated:
     *     stop that substring.
     *
     * Otherwise:
     *     update maximum length.
     *
     * ------------------------------------------------------------
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */

    // public int lengthOfLongestSubstringBruteForce(String s) {

    //     int maxLength = 0;

    //     // Try every starting position
    //     for (int i = 0; i < s.length(); i++) {

    //         // Frequency array for current substring
    //         int[] freq = new int[128];

    //         // Expand substring from i
    //         for (int j = i; j < s.length(); j++) {

    //             char c = s.charAt(j);

    //             /*
    //              * If character already exists,
    //              * current substring is invalid.
    //              */
    //             if (freq[c] == 1) {
    //                 break;
    //             }

    //             // Mark character as seen
    //             freq[c]++;

    //             // Current substring length
    //             int currentLength = j - i + 1;

    //             // Update maximum length
    //             maxLength = Math.max(
    //                 maxLength,
    //                 currentLength
    //             );
    //         }
    //     }

    //     return maxLength;
    // }
}