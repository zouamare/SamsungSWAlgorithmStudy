package coding_test.Algo2022년_11월.day29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2193_이친수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[2][N];
        dp[1][0] = 1;   // 첫 값은 무조건 1만 올 수 있다.

        for (int i = 1; i < N; i++) {
            dp[0][i] = dp[0][i - 1] + dp[1][i - 1]; // 0은 이전의 값이 1이 올 수도, 0이 올 수도 있다.
            dp[1][i] = dp[0][i - 1];    // 1이라면 무조건 이전의 값은 0이다.
        }

        System.out.println(dp[0][N - 1] +  dp[1][N - 1]);   // 0의 경우의 수 + 1의 경우의 수
    }
}
