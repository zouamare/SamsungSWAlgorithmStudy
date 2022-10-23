package coding_test.Algo2022년_10월.day23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1701_Cubeditor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int max = 0, len = s.length();

        for (int i = 0; i < len; i++) {
            max = Math.max(max, makePi(s.substring(i, len)));
        }

        System.out.println(max);
    }

    private static int makePi(String s) {
        int[] pi = new int[s.length()];

        int j = 0, max = 0;
        for (int i = 1; i < s.length(); i++) {
            while(j > 0 && s.charAt(i) != s.charAt(j))  j = pi[j-1];
            if(s.charAt(i) == s.charAt(j)){
                pi[i] = ++j;
                max = Math.max(max, pi[i]);
            }
        }

        return max;
    }
}
