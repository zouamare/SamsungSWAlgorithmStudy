import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2294_동전 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] data = new int[N+1];

        for (int i = 1; i <= N; i++) {
            data[i] = sc.nextInt();
        }

        int[] dp = new int[K+1];

        for (int i = 1; i <= K; i++) {
            dp[i] = 9999999;
        }

        for(int i = 1; i <= N; i++) {
            for (int j = data[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - data[i]] + 1);
            }
        }

        System.out.println(dp[K] == 9999999 ? -1 : dp[K]);
    }
}
