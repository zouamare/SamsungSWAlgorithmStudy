package coding_test.Algo2022년_11월.day29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11057_오르막수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[10][N];
        for(int i = 0; i < 10; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i < N; i++) {   // 1 ~ N 까지의 경우의 수를 구함
            for (int j = 0; j < 10; j++) {  // 0 ~ 9 까지의 각 상황에 따른 오르막 수 경우의 수
                for (int k = 0; k <= j; k++) {  // 오르막 경우의 수를 수에 따라 차등 기록
                    dp[j][i] += dp[k][i - 1];
                }
                dp[j][i] %= 10007;
            }
        }

        int result = 0;
        for(int i = 0; i < 10; i++){
            result += dp[i][N-1];
            result %= 10007;
        }
        System.out.println(result);
    }
}
