package BOJ;

import java.util.Scanner;

public class BOJ_1701_Cubeditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int ans = 0;

        for (int i = 0, size = s.length(); i < size; i++) {
            int match = 0;
            String str = s.substring(i, size);

            int[] pi = new int[str.length()];

            for (int j = 1, size2 = str.length(); j < size2; j++) {
                while (match > 0 && str.charAt(match) != str.charAt(j)) {
                    match = pi[match - 1];
                }

                if(str.charAt(match) == str.charAt(j)) {
                    match += 1;
                }

                pi[j] = match;
                ans = Math.max(ans, pi[j]);
            }
        }
        System.out.println(ans);
    }
}
