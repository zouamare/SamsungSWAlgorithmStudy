import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int L = 0;
        int R = n - 1;
        int minSum = 2000000001;
        int ans1 = minSum, ans2 = minSum;

        while (L < R) {
            int sum = arr[L] + arr[R];

            if (minSum > Math.abs(sum)) {
                minSum = Math.abs(sum);
                ans1 = arr[L];
                ans2 = arr[R];

                if (sum == 0)
                    break;
            }

            if (sum < 0)
                L++;
            else
                R--;
        }

        System.out.println(ans1 + " " + ans2);
    }
}