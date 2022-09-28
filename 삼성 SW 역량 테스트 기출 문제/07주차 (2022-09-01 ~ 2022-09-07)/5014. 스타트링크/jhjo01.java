package BOJ;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;
/*
    강의시간에 풀었던 숨바꼭질이랑 비슷하다
    BFS를 이용한 풀이로 결정

    BFS 순회 하면서 방문처리된 칸을 발견한다면 ?
    그 칸은 이미 자신보다 cnt가 낮은 사람?이 거기서 위아래로 탐색을 한거니까 안가도됨

 */
public class BOJ_5014_스타트링크 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int F = sc.nextInt(); // 꼭대기
        int S = sc.nextInt(); // 출발
        int G = sc.nextInt(); // 도착
        int U = sc.nextInt(); // 위로
        int D = sc.nextInt(); // 아래로
        int ans = -1;
        boolean[] visited = new boolean[F + U + 1];

        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[] {S, 0});
        visited[S] = true;

        while (!q.isEmpty())
        {
            int[] temp = q.poll();
            int curr = temp[0];
            int cnt = temp[1];

            if(curr == G)
            {
                ans = cnt;
                break;
            }



            if( (curr+U) < F+1 && !visited[curr+U])
            {
                q.offer(new int[] {curr+U, cnt+1});
                visited[curr+U] = true;
            }
            if( (curr-D) > 0 && !visited[curr-D])
            {
                q.offer(new int[] {curr-D, cnt+1});
                visited[curr-D] = true;
            }

        }

        System.out.println(ans == -1 ? "use the stairs" : ans);

    }
}
