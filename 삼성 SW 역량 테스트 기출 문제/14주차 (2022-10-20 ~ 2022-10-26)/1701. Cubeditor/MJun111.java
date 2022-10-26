import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int n = input.length(), max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, getMax(input.substring(i, n)));
        }
        System.out.println(max);
    }

    static int getMax(String substr) {
        int n = substr.length();
        int max = 0;
        int pi[] = new int[n];
        
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && substr.charAt(i) != substr.charAt(j)) j = pi[j-1];

            if (substr.charAt(i) == substr.charAt(j)) {
                max = Math.max(max, pi[i] = ++j);
            }
        }

        return max;
    }
}