package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3079_입국심사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] data = new int[N];

        int max = 0;

        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, data[i]);
        }

        long left = 0L;
        long right = (max) * 1000000000L;

        long ans = 0L;

        while(left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for(int i = 0; i < N; i++) {
                cnt += (mid / data[i]);
            }

            if(cnt >= M) {
                ans = mid;
                right = mid-1;
            } else if (cnt < M) {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }


}
