package BOJ;

import java.util.Scanner;

public class BOJ_2193_이친수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        long d[] = new long[N+1];

        d[0] = 0;
        d[1] = 1;
        for (int i = 2; i < N+1; i++) {
            d[i] = d[i-1] + d[i-2];
        }

        System.out.println(d[N]);
    }
}
