package coding_test.Algo20220901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 최단 거리를 구해야 하므로 BFS로 접근합니다.
* 중복여부를 체크하기 위해 F 크기의 배열 visited를 만듭니다.
*/
public class BOJ_5014_스타트링크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[F+1];
        Queue<Now> nowQueue = new ArrayDeque<>();
        visited[S] = true;
        nowQueue.add(new Now(S, 0));
        while (!nowQueue.isEmpty()){
            Now now = nowQueue.poll();
            if(now.location == G){
                System.out.println(now.cnt);
                return;
            }
            int up = now.location + U, down = now.location - D;
            if(up <= F && !visited[up]){
                visited[up] = true;
                nowQueue.add(new Now(up, now.cnt + 1));
            }
            if(down > 0 && !visited[down]){
                visited[down] = true;
                nowQueue.add(new Now(down, now.cnt + 1));
            }
        }
        System.out.println("use the stairs");
    }

    static class Now{
        int location, cnt;

        public Now(int location, int cnt) {
            this.location = location;
            this.cnt = cnt;
        }
    }
}
