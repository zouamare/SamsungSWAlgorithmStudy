package coding_test.Algo2022년_10월.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 내가 생각하는 이 문제의 포인트는 다음과 같다.
* 1. 중복을 어떻게 해결할 것인가
* 2. 최소 동전의 개수를 어떻게 구할 것인가
*
* 다음은 해결방안이다.
* 1번) stream의 distinct 메소드를 이용하여 해결했다. 애초에 동전 개수에는 제한이 없으므로 중복만 제거하면 된다고 생각했다.
* 2번) dp를 이용하여 풀이했다. 금액 X에서 가치만큼을 뺀 위치에 존재하는 최소 동전 개수 값에 + 1을 하면 결론이 도출된다고 생각했다. 기존에 풀었던 문제여서 쉽게 풀었다.
*
* */
public class BOJ_2294_동전2 {
    static final int MAX_VAL = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 동전의 개수
        int K = Integer.parseInt(st.nextToken());   // 가치의 합

        int[] values = new int[N + 1];
        for (int i = 1; i <= N ; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        values = Arrays.stream(values).distinct().toArray();
        N = values.length;

        int[] dp = new int[K + 1];
        Arrays.fill(dp, MAX_VAL);
        dp[0] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = values[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - values[i]] + 1);
            }
        }

        System.out.println((dp[K] == MAX_VAL ? -1 : dp[K]));
    }
}
