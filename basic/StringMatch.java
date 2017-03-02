package basic;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Created by zzh1991 on 2016/3/22.
 */
// () {} [] 三种括号的匹配问题
public class StringMatch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String string = in.nextLine();
            char[] chars = string.toCharArray();
            System.out.println(peipi(chars));
        }

    }

    public static boolean peipi(char[] chars) {
        if (chars == null || chars.length == 0) return false;
        if (chars.length % 2 == 1) return false;
        if (chars[0] == '}' || chars[0] == ']' || chars[0] == ')')
            return false;

        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '{' || chars[i] == '[') {
                deque.push(chars[i]);
            }
            else if (chars[i] == ')') {
                if (deque.pop() != '(') {
                    return false;
                }
            }
            else if (chars[i] == ']') {
                if (deque.pop() != '[') return false;
            }
            else {
                if (deque.pop() != '{') return false;
            }
        }
        if (deque.isEmpty()) {
            return true;
        }
        else return false;
    }
}
