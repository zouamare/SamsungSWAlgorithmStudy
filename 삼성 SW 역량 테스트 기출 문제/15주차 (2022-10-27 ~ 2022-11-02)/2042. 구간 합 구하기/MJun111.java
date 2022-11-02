import java.io.*;
import java.util.*;

public class Main {
    static long[] arr;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new long[n];
        tree = new long[4 * n + 1];

        for (int i = 0; i < n; i++)
            arr[i] = Long.parseLong(br.readLine());

        init(0, n - 1, 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(0, n - 1, 1, b - 1, c - arr[b - 1]);
                arr[b - 1] = c;
            }
            else
                sb.append(sum(0, n - 1, 1, b - 1, c - 1)).append("\n");
        }
        System.out.print(sb);
    }

    // start : 시작 인덱스, end : 끝 인덱스
    static long init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // start : 시작 인덱스, end : 끝 인덱스
    // left, right : 구간 합을 구하고자 하는 범위
    static long sum(int start, int end, int node, int left, long right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // index : 구간 합을 수정하고자 하는 노드
    // dif : 원래 인덱스에 있는 값과의 차이만큼 수정 ex ) 5 -> 7 이라면 dif = 2
    static void update(int start, int end, int node, int index, long dif) {
        // 범위 밖인 경우 리턴
        if (index < start || index > end) return;
        // 범위 안에 있는 경우 내려가며 다른 원소도 갱신
        tree[node] += dif;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}