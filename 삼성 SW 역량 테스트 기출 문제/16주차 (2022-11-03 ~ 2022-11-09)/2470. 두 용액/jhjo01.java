package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2470_두용액 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] data = new int[N];

        for(int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
        }

        int ans = Integer.MAX_VALUE;
        int ansLeft = 0;
        int ansRight = 0;
        int left = 0;
        int right = N-1;

        Arrays.sort(data);


        for(int i = 0; i < N-1; i++) {

            if(ans == 0) break;

            int sum = 0;

            if(data[left] > 0 || data[right] < 0) {
                sum = Math.abs(data[left] + data[right]);
            } else {
                sum = Math.abs(Math.abs(data[left]) - Math.abs(data[right]));
            }

            if(sum < ans) {
                ans = sum;
                ansLeft = data[left];
                ansRight = data[right];
            }

            if(Math.abs(data[left]) > Math.abs(data[right])) left++;
            else right--;

        }

        System.out.println(ansLeft + " " + ansRight);
    }
}
