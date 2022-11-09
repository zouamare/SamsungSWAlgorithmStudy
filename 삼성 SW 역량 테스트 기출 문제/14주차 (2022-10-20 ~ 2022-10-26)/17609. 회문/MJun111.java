import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String input = br.readLine();
            sb.append(isPalindrome(input, 0, input.length() - 1, false) + "\n");
        }
        System.out.print(sb);
    }

    static int isPalindrome(String str, int L, int R, boolean flag) {
        while (L < R) {
            if (str.charAt(L) == str.charAt(R)) {
                L++;
                R--;
            } else {
                if (!flag)
                    return Math.min(isPalindrome(str, L + 1, R, true), isPalindrome(str, L, R - 1, true));
                else
                    return 2;
            }
        }

        if (flag)
            return 1;

        return 0;
    }
}