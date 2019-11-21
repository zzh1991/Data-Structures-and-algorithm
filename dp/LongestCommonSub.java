/**
 * @author zhihao zhang
 * @date 11/18/19
 */

public class LongestCommonSub {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ecd";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        if (text1.length() > text2.length()) {
            String tmp = text1;
            text1 = text2;
            text2 = tmp;
        }

        int[] dp = new int[text1.length() + 1];
        for (int i = 1; i <= text2.length(); i++) {
            int tmp = dp[0];
            for (int j = 1; j <= text1.length(); j++) {
                int pre = tmp;
                tmp = dp[j];
                dp[j] = Math.max(dp[j - 1], dp[j]);
                if (text2.charAt(i - 1) == text1.charAt(j - 1)) {
                    dp[j] = Math.max(pre + 1, dp[j]);
                }
            }
        }
        return dp[text1.length()];
    }
}
