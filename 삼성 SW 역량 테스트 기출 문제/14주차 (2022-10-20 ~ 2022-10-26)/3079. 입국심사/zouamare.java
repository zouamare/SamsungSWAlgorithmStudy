package coding_test.Algo2022년_10월.day25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 틀렸던 이유: 값이 엄청 크게 나올 수도 있는데 아무 생각 없이 min 초기값을 Integer.MAX_VALUE로 잡아버렸다.. Long으로 해야됐는데!!
public class BOJ_3079_입국심사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long max = 0;
        int[] T = new int[N];

        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(br.readLine());
            if(max < T[i]) max = T[i];
        }

        long min = max * M;

        long start = 0;
        long end = max * M;

        while(start <= end){
            long mid = (start + end) / 2;  // 이진탐색 mid 만들기
            long sum = 0;

            for (int val : T){
                sum += mid / val;   // 통과할 수 있는 사람의 수 파악하여 더함

                if(sum >= M)    break;  // M 이상이면 굳이 계산할 이유가 없음
            }

            if(sum >= M){
                min = Math.min(min, mid);   // min 갱신
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        System.out.println(min);
    }
}
