import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        // 0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽
        int d = Integer.parseInt(st.nextToken());
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        String[][] map = new String[N][];
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            map[i] = br.readLine().split(" ");
        }
        int cleaned = 0;
        boolean status = true;
        outer: while(true){
            // 1. 현재 위치를 청소한다.
            if(status){
                visited[r][c] = true;
                cleaned++;
                status = false;
            }
            int dec = 1;
            while(dec <= 4){
                // 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
                int left = (d - dec + 4) % 4;
                if(map[r + dx[left]][c + dy[left]].equals("0") && !visited[r + dx[left]][c + dy[left]]){
                    // 2 - 1. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
                    d = left;
                    r = r + dx[d];
                    c = c + dy[d];
                    status = true;
                    continue outer;
                }
                // 2 - 2. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
                dec++;
            }
            if(map[r - dx[d]][c - dy[d]].equals("0")){
                // 2 - 3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
                r = r - dx[d];
                c = c - dy[d];
            }
            else{
                // 2 - 4. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
                break;
            }
        }
        System.out.println(cleaned);
    }
}