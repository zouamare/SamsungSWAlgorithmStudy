package BOJ;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
/*
    for 루프에 if문 여러개 써서
    3으로 나누기 -> -1하면 3으로 나누기 가능하면 -1 -> 2로 나누기 -> -1 우선순위로 풀었는데
    3으로 나누는거보다 2로 나누는게 더 빠른 경우도 있고
    -1해서 3으로 나누는거보다 2로 나누는게 더 빨라서 포기

    BFS로 풀었음

    DP -> 같은 일 반복



 */
public class BOJ_1463_1로만들기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        boolean[] visited = new boolean[N];
        int cnt = 0;

        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] {N, 0});
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cur = temp[0];
            cnt = temp[1];

            if(cur == 1) break;

            int d3 = cur / 3;
            int d2 = cur / 2;
            int m1 = cur - 1;

            if( cur % 3 == 0 && !visited[cur/3]) {
                q.offer(new int[] {d3, cnt+1});
                visited[d3] = true;
            }
            if( cur % 2 == 0 && !visited[cur/2]) {
                q.offer(new int[] {d2, cnt+1});
                visited[d2] = true;
            }
            if(!visited[cur-1]) {
                q.offer(new int[] {m1, cnt+1});
                visited[m1] = true;
            }






        }

        System.out.println(cnt);


    }
}