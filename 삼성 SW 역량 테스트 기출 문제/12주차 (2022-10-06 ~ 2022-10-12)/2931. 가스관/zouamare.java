package day20221012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2931_가스관 {
    static int R, C, tr, tc;
    static char[][] map;
    static char targetPipe;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        int nr = -1, nc = -1;
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                if(map[r][c] == 'M'){
                    nr = r;
                    nc = c;
                }
            }
        }
        
        for (int d = 0; d < 4; d++) {
            int r = nr + dr[d];
            int c = nc + dc[d];
            if(r < 0 || r >= R || c < 0 || c >= C || map[r][c] == '.' || map[r][c] == 'Z')    continue;
            DFS(r, c, d, new int[R][C]);
            break;
        }

        System.out.println(tr + " " + tc + " " + targetPipe);
    }

    private static void DFS(int r, int c, int dir, int[][] visited) {
        visited[r][c]++;
        if(map[r][c] == '.'){	// 해커가 없앤 위치를 찾았다!
            tr = r + 1;
            tc = c + 1;
            // 어떤 도로가 들어가야 하는지 체크
            boolean[] connectingLoc = new boolean[4];
            visited[r - dr[dir]][c - dc[dir]]--;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '.' || (map[nr][nc] == '+' && visited[nr][nc] > 1) || (map[nr][nc] != '+' && visited[nr][nc] > 0))    continue;
                if(d == 0 && (map[nr][nc] == '-' || map[nr][nc] == '2' || map[nr][nc] == '3'))	continue;
                if(d == 1 && (map[nr][nc] == '|' || map[nr][nc] == '1' || map[nr][nc] == '2'))	continue;
                if(d == 2 && (map[nr][nc] == '-' || map[nr][nc] == '1' || map[nr][nc] == '4'))	continue;
                if(d == 3 && (map[nr][nc] == '|' || map[nr][nc] == '3' || map[nr][nc] == '4'))	continue;
                connectingLoc[d] = true;
            }

            if(connectingLoc[0] && connectingLoc[1] && connectingLoc[2] && connectingLoc[3]) targetPipe = '+';
            else if(connectingLoc[0] && connectingLoc[2])   targetPipe = '|';
            else if(connectingLoc[1] && connectingLoc[3])   targetPipe = '-';
            else if(connectingLoc[1] && connectingLoc[2])   targetPipe = '1';
            else if(connectingLoc[0] && connectingLoc[1])   targetPipe = '2';
            else if(connectingLoc[0] && connectingLoc[3])   targetPipe = '3';
            else if(connectingLoc[2] && connectingLoc[3])   targetPipe = '4';
            
            return;
        }
        else{
            int newDir = -1;
            switch (map[r][c]){
                case '|': case '-': case '+':
                    newDir = dir;
                    break;
                case '1':
                    if(dir == 3)  newDir = 2;
                    else newDir = 1;
                    break;
                case '2':
                    if(dir == 2)  newDir = 1;
                    else newDir = 0;
                    break;
                case '3':
                    if(dir == 1)  newDir = 0;
                    else newDir = 3;
                    break;
                case '4':
                    if(dir == 1) newDir = 2;
                    else newDir = 3;
                    break;
            }
            DFS(r + dr[newDir], c + dc[newDir], newDir, visited);
        }
    }
}
