package coding_test.Algo2022년_11월.day8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 이진 탐색으로 mid -1, mid 값을 비교하여 분석 ~!!
 * left <= x < mid
 * mid <= x < right
 *
 * 자기자신을 두번 참조하는 경우는 제외해야 함
 *
 * */
public class BOJ_2470_두용액 {
    static int N, arr[], minDiff = Integer.MAX_VALUE, min, max;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());	// 입력 받아오기
        }

        Arrays.sort(arr);	// 이진 탐색을 위한 정렬

        for (int i = 0; i < N; i++) {
            getMinDiff(i, 0, N);
        }

        System.out.println(min + " " + max);
    }

    private static void getMinDiff(int idx, int start, int end) {
        int mid = (start + end) / 2;
        if(start == mid || mid == end) {
            int diff = Math.abs(arr[mid] + arr[idx]);
            if(minDiff > diff && mid != idx) {
                min = Math.min(arr[mid], arr[idx]);
                max = Math.max(arr[mid], arr[idx]);
                minDiff = diff;
            }
            return;
        }

        int left = Math.abs(arr[mid - 1] + arr[idx]);
        int right = Math.abs(arr[mid] + arr[idx]);
        if(left < right) {
            getMinDiff(idx, start, mid);
        }
        else if(left > right) {
            getMinDiff(idx, mid, end);
        }
        else {
            getMinDiff(idx, start, mid);
            getMinDiff(idx, mid, end);
        }
    }
}
