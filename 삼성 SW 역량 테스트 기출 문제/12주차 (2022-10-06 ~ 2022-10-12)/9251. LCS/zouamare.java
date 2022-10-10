package coding_test.Algo2022년_10월.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://st-lab.tistory.com/139 블로그 참고
public class BOJ_9251_LCS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] firstSeq = br.readLine().toCharArray();
        char[] secondSeq = br.readLine().toCharArray();
        
        int len_1 = firstSeq.length;
        int len_2 = secondSeq.length;
        int[][] dp = new int[len_1 + 1][len_2 + 1];
        // 모두의 부분 수열이 되는 수열 중 가장 긴 것
        for (int i = 1; i <= len_1; i++) {
            for (int j = 1; j <= len_2; j++) {
                if(firstSeq[i - 1] == secondSeq[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[len_1][len_2]);
    }
}
