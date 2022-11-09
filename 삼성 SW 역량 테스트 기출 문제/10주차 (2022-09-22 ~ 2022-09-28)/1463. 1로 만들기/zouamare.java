package coding_test.Algo20220922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 조건은 다음과 같다.
* 1. 3으로 나누어 떨어지면 3으로 나눈다.
* 2. 2로 나누어 떨어지면 2로 나눈다.
* 3. 1을 뺀다.
*
* 위의 조건으로 1을 만들어야 한다.
*
* 10 -> 5 -> 4 -> 2 -> 1
* 10 -> 9 -> 3 -> 1
*
* */
public class BOJ_1463_1로만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        dp[N] = 1;
        for(int i = N; i > 0; i--){
            if(dp[i] > 0){
                if(i%3 == 0 && (dp[i/3] == 0 || dp[i/3] > dp[i] + 1))    dp[i/3] = dp[i] + 1;
                if(i%2 == 0 && (dp[i/2] == 0 || dp[i/2] > dp[i] + 1))    dp[i/2] = dp[i] + 1;
                if(i - 1 > 0 && (dp[i-1] == 0 || dp[i-1] > dp[i] + 1)) dp[i-1] = dp[i] + 1;
            }
        }
        System.out.println(dp[1]-1);
    }
}
