package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1938_통나무옮기기 {
   static int N, x, y, dir, ans;
   static int garo = 2;
   static int sero = 1;
   static char[][] map;
   static int[] dx = {1, -1, 0, 0};
   static int[] dy = {0, 0, 1, -1};
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      map = new char[N][N];
      for (int i = 0; i < N; i++) {
         map[i] = br.readLine().toCharArray();
      }
      int n = 0;
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if(map[i][j] == 'B' && n == 1) {
               x = i;
               y = j;
               if(x - 1 > -1 && map[x - 1][y] == 'B') dir = sero;
               else dir = garo;
               n++;
            }else if(map[i][j] == 'B') n++;
         }
      }
      bfs();
      System.out.println(ans);
      
   }
   private static void bfs() {
      Queue<int[]> q = new LinkedList<>();
      boolean[][][] visited = new boolean[3][N][N];
      q.add(new int[] {x, y, dir});
      int cnt = 0;
      while(!q.isEmpty()) {
         int size = q.size();
         while(size-- > 0) {
            int[] t = q.poll();
            int nx = t[0];
            int ny = t[1];
            int d = t[2];
            if(d == garo) {
               if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
               if(map[nx][ny] == 'E' && map[nx][ny - 1] == 'E' && map[nx][ny + 1] == 'E') {
                  ans = cnt;
                  return;
               }
            }else {
               if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
               if(map[nx][ny] == 'E' && map[nx - 1][ny] == 'E' && map[nx + 1][ny] == 'E') {
                  ans = cnt;
                  return;
               }
            }
            if(visited[d][nx][ny]) continue;
            visited[d][nx][ny] = true;
            // 가로 방향일때
            if(d == garo) {
               // 위
               if(nx - 1 != -1 && map[nx - 1][ny] != '1' && map[nx - 1][ny - 1] != '1' && map[nx - 1][ny + 1] != '1' && !visited[garo][nx - 1][ny]) {
                  q.add(new int[] {nx - 1, ny, garo});
               }
               // 아래
               if(nx + 1 != N && map[nx + 1][ny] != '1' && map[nx + 1][ny - 1] != '1' && map[nx + 1][ny + 1] != '1' && !visited[garo][nx + 1][ny]) {
                  q.add(new int[] {nx + 1, ny, garo});
               }
               // 오른쪽
               if(ny + 2 != N && map[nx][ny + 2] != '1' && !visited[garo][nx][ny + 1]) {
                  q.add(new int[] {nx, ny + 1, 2});
               }
               // 왼쪽
               if(ny - 2 != -1 && map[nx][ny - 2] != '1' && !visited[garo][nx][ny - 1]) {
                  q.add(new int[] {nx, ny - 1, garo});
               }
               // 회전
               if(nx - 1 > -1 && nx + 1 < N && ny - 1 > -1 && ny + 1 < N 
                     && map[nx - 1][ny] != '1' && map[nx - 1][ny - 1] != '1' && map[nx - 1][ny + 1] != '1' 
                     && map[nx + 1][ny] != '1' && map[nx + 1][ny - 1] != '1' && map[nx + 1][ny + 1] != '1' && !visited[sero][nx][ny]) {
                  q.add(new int[] {nx, ny, sero});
               }
            }else {
               // 위
               if(nx - 2 != -1 && map[nx - 2][ny] != '1' && !visited[sero][nx - 1][ny]) {
                  q.add(new int[] {nx - 1, ny, sero});
               }
               // 아래
               if(nx + 2 != N && map[nx + 2][ny] != '1' && !visited[sero][nx + 1][ny]) {
                  q.add(new int[] {nx + 1, ny, sero});
               }
               // 오른쪽
               if(ny + 1 != N && map[nx][ny + 1] != '1' && map[nx - 1][ny + 1] != '1' && map[nx + 1][ny + 1] != '1' && !visited[sero][nx][ny + 1]) {
                  q.add(new int[] {nx, ny + 1, sero});
               }
               // 왼쪽
               if(ny - 1 != -1 && map[nx][ny - 1] != '1' && map[nx - 1][ny - 1] != '1' && map[nx + 1][ny - 1] != '1' && !visited[sero][nx][ny - 1]) {
                  q.add(new int[] {nx, ny - 1, sero});
               }
               // 회전
               if(nx - 1 > -1 && nx + 1 < N && ny - 1 > -1 && ny + 1 < N 
                     && map[nx][ny + 1] != '1' && map[nx - 1][ny + 1] != '1' && map[nx + 1][ny + 1] != '1' 
                     && map[nx][ny - 1] != '1' && map[nx - 1][ny - 1] != '1' && map[nx + 1][ny - 1] != '1' && !visited[garo][nx][ny]) {
                  q.add(new int[] {nx, ny, garo});
               }
            }
            
         }
         cnt++;
      }
      
   }
}