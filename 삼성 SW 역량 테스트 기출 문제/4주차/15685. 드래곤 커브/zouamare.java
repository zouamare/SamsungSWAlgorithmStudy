package coding_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};
        // 0 ~ 100 까지의 위치의 map
        boolean[][] map = new boolean[101][101];
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            ArrayList<Integer> dList = new ArrayList<>();
            map[x][y] = true;
            // 0세대 제작
            x = x + dx[d];
            y = y + dy[d];
            dList.add(d);
            map[x][y] = true;

            for(int i = 0; i < g; i++){
                for(int j = dList.size() - 1; j >= 0; j--){
                    int beforeD = dList.get(j);
                    int nowD = (beforeD + 1)%4;
                    x = x + dx[nowD];
                    y = y + dy[nowD];
                    dList.add(nowD);
                    map[x][y] = true;
                }
            }
        }
        int cnt = 0;
        // 네 꼭짓점이 모두 드래곤 커브의 일부 == 근처 네개의 칸이 모두 true일 때
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
                    cnt++;
            }
        }

        System.out.println(cnt);
    }
}
