package coding_test.Algo20220930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int len = Integer.parseInt(br.readLine());
        int[] arr = new int[len];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] DP = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            DP[i] = 1;
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i] && DP[j] + 1 > DP[i])
                    DP[i] = DP[i] + 1;
            }
        }

        Arrays.sort(DP);

        System.out.println(DP[arr.length - 1]);
    }
}
