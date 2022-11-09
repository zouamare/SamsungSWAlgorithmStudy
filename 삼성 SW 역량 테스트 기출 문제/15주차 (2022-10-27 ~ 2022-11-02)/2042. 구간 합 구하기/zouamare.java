package coding_test.Algo2022년_11월.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042_구간합구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 수의 개수
        int M = Integer.parseInt(st.nextToken());   // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken());   // 구간의 합을 구하는 횟수

        // N * ( M + K ) 의 최대값은 200억 이므로 일반적인 방법으로는 풀이가 불가능하다.
        // 슬라이딩 윈도우 방법을 이용해야 할 것 같다.

        // N 만큼 숫자 입력 받음
        long[] val = new long[N];
        for (int i = 0; i < N; i++) {
            val[i] = Long.parseLong(br.readLine());
        }

        int start = N/2, end = N/2;
        long sum = val[N/2];
        StringBuilder ans = new StringBuilder();
        // M + K 만큼 3자리를 입력 받아 진행
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 1){
                // b 번째 수를 c로 변경
                int b = Integer.parseInt(st.nextToken()) - 1;
                long tmp = val[b];
                val[b] = Long.parseLong(st.nextToken());
                if(b >= start && b <= end){
                    sum = sum - tmp + val[b];
                }
            }else {
                // b ~ c 까지의 합을 출력
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;

                if(b < start){  // b가 start 보다 작으면 b ~ start - 1 까지의 값을 더해준다.
                    for (int j = b; j < start; j++) {
                        sum += val[j];
                    }
                }
                else if(b > start){ // b가 start 보다 크면 start ~ b - 1 까지의 값을 빼준다.
                    for (int j = start; j < b; j++) {
                        sum -= val[j];
                    }
                }

                if(c < end){    // c가 end 보다 작으면 c + 1 ~ end 까지의 값을 빼준다.
                    for (int j = c + 1; j <= end; j++) {
                        sum -= val[j];
                    }
                }
                else if(c > end){   // c가 end 보다 크면 end + 1 ~ c 까지의 값을 더해준다.
                    for (int j = end + 1; j <= c; j++) {
                        sum += val[j];
                    }
                }

                start = b;
                end = c;
                ans.append(sum).append("\n");
            }
        }

        System.out.println(ans);

    }
}
