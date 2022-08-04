import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+2][2];    // [i][0] == 일 수, [i][1] = 얻을 수 있는 이익
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+2];
        for(int i = N; i > 0; i--){
            int next = i + arr[i][0];
            if(next > N + 1){
                // 일할 수 있는 날짜를 넘어섬
                dp[i] = dp[i + 1];
            }else{
                // 일할 수 있는 날짜
                // 일하지 않았을 때(dp[i+1])와
                // 일했을 때 벌 수 있는 금액(dp[next] + P[i])을 비교
                dp[i] = Math.max(dp[i+1], dp[next] + arr[i][1]);
            }
        }

        System.out.println(dp[1]);
    }
}