import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long m, t[], max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        t = new long[n];
        for (int i = 0; i < n; i++) {
            t[i] = Long.parseLong(br.readLine());
            max = Math.max(max, t[i]);
        }
        solution();
    }

    static void solution() {
        long L = 0;
        long R = max * m;

        while (L <= R) {
            long mid = (L + R) / 2;
            long cnt = 0;

            for (long time : t) {
                cnt += mid / time;
            }

            if (cnt >= m)
                R = mid - 1;
            else
                L = mid + 1;
        }
        System.out.println(L);
    }
}