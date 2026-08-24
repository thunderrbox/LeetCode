class Solution {
    public int firstUniqChar(String s) {

        /*
         * ============================================================
         * APPROACH:
         * ============================================================
         *
         * We need to find the FIRST character in the string
         * that occurs exactly once.
         *
         * Example:
         *
         * Input:  "swiss"
         *
         * Frequencies:
         * s -> 3
         * w -> 1
         * i -> 1
         *
         * The unique characters are 'w' and 'i'.
         *
         * But the question asks for the FIRST unique character.
         * In "swiss", 'w' comes first.
         *
         * Index:
         * s  w  i  s  s
         * 0  1  2  3  4
         *
         * Therefore answer = 1.
         *
         *
         * We use TWO PASSES:
         *
         * PASS 1:
         * Count the frequency of every character.
         *
         * PASS 2:
         * Traverse the original string from left to right
         * and find the first character whose frequency is 1.
         *
         *
         * IMPORTANT:
         * We must traverse the ORIGINAL STRING in the second pass.
         *
         * We cannot simply traverse the frequency array from
         * 'a' to 'z' because that would give alphabetical order,
         * not the order in which characters appear in the string.
         */


        /*
         * ============================================================
         * STEP 1: CREATE FREQUENCY ARRAY
         * ============================================================
         *
         * The problem contains lowercase English letters.
         *
         * There are 26 lowercase English letters:
         *
         * a -> index 0
         * b -> index 1
         * c -> index 2
         * ...
         * z -> index 25
         *
         * Therefore, an array of size 26 is enough.
         */
        int[] freq = new int[26];


        /*
         * ============================================================
         * STEP 2: COUNT FREQUENCY OF EACH CHARACTER
         * ============================================================
         *
         * Example:
         *
         * s = "swiss"
         *
         * When c = 's':
         *
         * 's' - 'a' = 18
         *
         * Therefore:
         *
         * freq[18]++;
         *
         * When c = 'w':
         *
         * 'w' - 'a' = 22
         *
         * Therefore:
         *
         * freq[22]++;
         *
         *
         * This converts a character into an array index.
         */
        for (char c : s.toCharArray()) {

            // Increase the frequency of the current character.
            freq[c - 'a']++;
        }


        /*
         * ============================================================
         * STEP 3: TRAVERSE THE ORIGINAL STRING
         * ============================================================
         *
         * We now go from left to right.
         *
         * Why?
         *
         * Because we need the FIRST non-repeating character.
         *
         * Example:
         *
         * "swiss"
         *
         * i = 0 -> 's' -> frequency = 3 -> skip
         * i = 1 -> 'w' -> frequency = 1 -> ANSWER
         *
         * Therefore, return i.
         */
        for (int i = 0; i < s.length(); i++) {

            // Get the character at the current index.
            char c = s.charAt(i);


            /*
             * Check whether this character occurs exactly once.
             *
             * freq[c - 'a'] == 1
             *
             * means:
             * "The current character appears only one time."
             */
            if (freq[c - 'a'] == 1) {

                /*
                 * Return the INDEX, not the character.
                 *
                 * Example:
                 *
                 * s = "swiss"
                 *
                 * current character = 'w'
                 * current index = 1
                 *
                 * return 1;
                 */
                return i;
            }
        }


        /*
         * ============================================================
         * STEP 4: NO UNIQUE CHARACTER FOUND
         * ============================================================
         *
         * If the loop finishes, it means every character occurs
         * more than once.
         *
         * Therefore, no unique character exists.
         *
         * Return -1.
         */
        return -1;
    }
}


/*
 * ====================================================================
 * UNDERSTANDING
 * ====================================================================
 *
 * The main idea is:
 *
 *       COUNT -> TRAVERSE -> CHECK -> RETURN
 *
 *
 * 1. COUNT
 *    Count how many times each character occurs.
 *
 * 2. TRAVERSE
 *    Go through the original string from left to right.
 *
 * 3. CHECK
 *    Check whether the frequency of the current character is 1.
 *
 * 4. RETURN
 *    Return its index immediately.
 *
 *
 *
 * ====================================================================
 * WHY DO WE NEED TWO LOOPS?
 * ====================================================================
 *
 * First loop:
 *
 *     for (char c : s.toCharArray())
 *
 * tells us:
 *
 *     "How many times does each character occur?"
 *
 *
 * Second loop:
 *
 *     for (int i = 0; i < s.length(); i++)
 *
 * tells us:
 *
 *     "Which unique character appears FIRST?"
 *
 *
 * Frequency alone cannot tell us which unique character appears first.
 *
 *
 *
 * ====================================================================
 * WHY CAN'T WE LOOP THROUGH 26?
 * ====================================================================
 *
 * Incorrect idea:
 *
 *     for (int i = 0; i < 26; i++)
 *
 * This checks:
 *
 *     a, b, c, d, ..., z
 *
 * That is alphabetical order.
 *
 * But we need the order in the ORIGINAL STRING.
 *
 * Example:
 *
 *     s = "leetcode"
 *
 * Unique characters include:
 *
 *     l, t, c, o, d
 *
 * The first unique character is 'l'.
 *
 * If we search alphabetically, 'c' would be found first.
 *
 * Therefore, searching the frequency array from 0 to 25
 * is not the correct way to solve this problem.
 *
 *
 *
 * ====================================================================
 * WHY DO WE USE c - 'a'?
 * ====================================================================
 *
 * A character cannot directly represent the desired array index.
 *
 * We convert it into an index.
 *
 * Example:
 *
 *     'a' - 'a' = 0
 *     'b' - 'a' = 1
 *     'c' - 'a' = 2
 *     'd' - 'a' = 3
 *     ...
 *     'z' - 'a' = 25
 *
 * Therefore:
 *
 *     freq[c - 'a']++;
 *
 * is used to count the character.
 *
 *
 *
 * ====================================================================
 * TIME COMPLEXITY
 * ====================================================================
 *
 * Let n = length of the string.
 *
 * First loop:
 *
 *     O(n)
 *
 * because we visit every character once.
 *
 * Second loop:
 *
 *     O(n)
 *
 * because in the worst case we may visit every character.
 *
 * Total:
 *
 *     O(n) + O(n)
 *
 *     = O(2n)
 *
 *     = O(n)
 *
 * Therefore:
 *
 *     TIME COMPLEXITY = O(n)
 *
 *
 *
 * ====================================================================
 * SPACE COMPLEXITY
 * ====================================================================
 *
 * We create:
 *
 *     int[] freq = new int[26];
 *
 * The array always contains exactly 26 elements.
 *
 * 26 is a constant.
 *
 * Therefore:
 *
 *     O(26) = O(1)
 *
 * So:
 *
 *     SPACE COMPLEXITY = O(1)
 *
 *
 *
 * ====================================================================
 * FINAL COMPLEXITY
 * ====================================================================
 *
 * Time Complexity:
 *     O(n)
 *
 * Space Complexity:
 *     O(1)
 *
 *
 *
 * ====================================================================
 * MISTAKES IN YOUR ORIGINAL CODE
 * ====================================================================
 *
 *
 * ---------------------- MISTAKE 1 ----------------------
 *
 * You wrote:
 *
 *     char c;
 *     for(c : s.toCharArray())
 *
 * This is invalid Java syntax.
 *
 * Correct:
 *
 *     for (char c : s.toCharArray())
 *
 * The variable type must be declared inside the enhanced
 * for-loop.
 *
 *
 *
 * ---------------------- MISTAKE 2 ----------------------
 *
 * You wrote:
 *
 *     for(int i = 0; i < 26; i++){
 *         if(freq[c - 'a'] == 1){
 *             return c;
 *         }
 *     }
 *
 * Problem:
 *
 * You are using c instead of i.
 *
 * Also, looping from 0 to 25 checks the alphabet rather than
 * the original string order.
 *
 * The correct second loop is:
 *
 *     for(int i = 0; i < s.length(); i++)
 *
 *
 *
 * ---------------------- MISTAKE 3 ----------------------
 *
 * You wrote:
 *
 *     return c;
 *
 * But the method returns:
 *
 *     public int firstUniqChar(String s)
 *
 * The question asks for the INDEX of the first unique character.
 *
 * Therefore we return:
 *
 *     return i;
 *
 * Example:
 *
 *     "swiss"
 *
 *     'w' is the answer character
 *     1 is the answer index
 *
 * The expected answer is:
 *
 *     1
 *
 * not:
 *
 *     'w'
 *
 *
 *
 * ---------------------- MISTAKE 4 ----------------------
 *
 * You wrote:
 *
 *     return "/0";
 *
 * This is wrong because "/0" is a String.
 *
 * The method must return an int.
 *
 * Correct:
 *
 *     return -1;
 *
 * -1 means that no unique character was found.
 *
 *
 *
 * ---------------------- MISTAKE 5 ----------------------
 *
 * You also had:
 *
 *     for(auto c : s)
 *
 * `auto` is not used this way in Java.
 *
 * Correct Java syntax:
 *
 *     for(char c : s.toCharArray())
 *
 *
 *
 * ---------------------- MISTAKE 6 ----------------------
 *
 * You had:
 *
 *     freq[c]++;
 *
 * This is incorrect for a character-frequency array of size 26.
 *
 * Correct:
 *
 *     freq[c - 'a']++;
 *
 * because:
 *
 *     'a' -> 0
 *     'b' -> 1
 *     ...
 *     'z' -> 25
 *
 *
 *
 * ---------------------- MISTAKE 7 ----------------------
 *
 * You had:
 *
 *     if(freq[i] == 0)
 *
 * But we need a character that occurs exactly ONCE.
 *
 * Therefore:
 *
 *     if(freq[i] == 1)
 *
 * However, even this alone is not enough because we still need
 * to preserve the original string order.
 *
 *
 *
 * ====================================================================
 * PAPER CODING MEMORY TRICK
 * ====================================================================
 *
 * Whenever you see:
 *
 * "Find the first non-repeating character"
 *
 * Think:
 *
 *     1. Frequency array
 *     2. Count everything
 *     3. Traverse original string
 *     4. frequency == 1
 *     5. return index
 *
 *
 * Short form:
 *
 *     COUNT -> CHECK ORIGINAL ORDER -> RETURN INDEX
 *
 *
 *
 * ====================================================================
 * INTERVIEW EXPLANATION
 * ====================================================================
 *
 * "I will use a frequency array of size 26 because the input
 * contains lowercase English letters. First, I count the frequency
 * of every character. Then I traverse the original string from
 * left to right and check the frequency of each character. The
 * first character whose frequency is one is the first non-repeating
 * character, so I return its index. If no such character exists,
 * I return -1."
 *
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */