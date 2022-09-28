package coding_test.Algo20220927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 블로그 https://lotuslee.tistory.com/113 참고
public class BOJ_2293_동전1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] values = new int[n];

        for(int i = 0; i < n; i++){
            values[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];

        dp[0] = 1;

        for(int i = 0; i < n; i++){
            for (int j = 1; j <= k; j++) {
                if(values[i] <= j)
                    dp[j] += dp[j - values[i]];
            }
        }

        System.out.println(dp[k]);
    }

}
