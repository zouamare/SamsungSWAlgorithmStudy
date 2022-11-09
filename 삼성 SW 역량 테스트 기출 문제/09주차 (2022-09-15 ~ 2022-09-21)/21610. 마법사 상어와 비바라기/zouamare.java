package coding_test.Algo20220914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 격자를 벗어나면 반대쪽으로 나오는 구조.
*
* [알고리즘]
*
* 1. 처음에 배열을 확인하여 2이상인 것 파악 (N^2) --> 꼭 처음에 안해도 될 것 같다.
* 2. 구름 이동
* 3. 해당 위치 물++, 얘네 위치 저장
* 4. 물이 증가한 칸에 물복사 버그 실시
* 5. 다시 구름이 생김 (직전에 생겼던 곳에서는 다시 안생김)
*
* */

public class BOJ_21610_마법사상어와비바라기 {
    static int N;
    static int[][] map;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int pad = 0;
        while (pad <= 50)   pad += N;

        Queue<Point> queue = new ArrayDeque<>();
        // 초기 세팅
        queue.add(new Point(N-1, 0));
        queue.add(new Point(N-1, 1));
        queue.add(new Point(N-2, 0));
        queue.add(new Point(N-2, 1));

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            boolean[][] clouds = new boolean[N][N];
            // 구름 이동
            for (int j = 0, size = queue.size(); j < size; j++){
                Point point = queue.poll();

                int nx = (point.x + dx[dir] * len + pad) % N;
                int ny = (point.y + dy[dir] * len + pad) % N;
                map[nx][ny]++;
                clouds[nx][ny] = true;
                queue.add(new Point(nx, ny));   // 이후 물복사 버그를 위해 다시 넣어줌.
            }
            // 물 복사 버그 진행
            while(!queue.isEmpty()){
                Point point = queue.poll();
                for(int d = 2; d <= 8; d += 2){
                    int nx = point.x + dx[d];
                    int ny = point.y + dy[d];
                    if(isValid(nx, ny) && map[nx][ny] > 0){
                        map[point.x][point.y]++;
                    }
                }
            }

            // 전체를 확인하여 Queue에 넣는다. clouds가 true이면 넣을 수 없다.
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(map[j][k] >= 2 && !clouds[j][k]){
                        map[j][k] -= 2;
                        queue.add(new Point(j, k));
                    }
                }
            }
        }

        int sumOfWater = 0;
        for(int j = 0; j < N; j++){
            for(int k = 0; k < N; k++){
                sumOfWater += map[j][k];
            }
        }

        System.out.println(sumOfWater);
    }


    private static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
