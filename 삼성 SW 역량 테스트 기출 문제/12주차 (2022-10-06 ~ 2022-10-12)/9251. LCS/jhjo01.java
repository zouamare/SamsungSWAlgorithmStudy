package BOJ;

import java.util.Scanner;

public class BOJ_9251_LCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();
        String b = sc.nextLine();

        int lenA = a.length();
        int lenB = b.length();

        char[] strA = new char[lenA];
        char[] strB = new char[lenB];

        for (int i = 0; i < lenA; i++) {
            strA[i] = a.charAt(i);
        }

        for (int i = 0; i < lenB; i++) {
            strB[i] = b.charAt(i);
        }

        int[][] dp = new int[lenA+1][lenB+1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
               if(strA[i-1] == strB[j-1]) {
                   dp[i][j] = dp[i-1][j-1] + 1;
               } else {
                   dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
               }
            }
        }


        System.out.println(dp[lenA][lenB]);


    }
}
