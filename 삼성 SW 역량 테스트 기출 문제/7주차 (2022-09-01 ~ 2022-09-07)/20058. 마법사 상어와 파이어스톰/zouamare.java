package coding_test.Algo20220905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 이 문제는 구현 문제이다.
* 이 문제를 풀기 위해서 익혀야 하는 구현 방법은 두가지이다.
*
* 1. 배열을 돌릴 수 있는가. (백준 - 배열돌리기 문제 참고)
* 2. 사방탐색을 할 수 있는가. (얼음 감소 로직과 가장큰덩어리(BFS)를 위해 필요함)
*
* 위의 방식을 이용하면 풀린다.
*
* 내가 막혔던 부분은 '얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.' 를 잘못 이해했다.
* BFS로 탐색해서 3개 이상 연결되어 있지 않으면 ~~ 인줄 알았는데. 단순한 사방탐색 문제였다.
*
* 문제를 잘 읽자 ^^! 그리고 배열 돌리기는 가끔씩 다시 풀어보자.
*
* */

public class BOJ_20058_마법사상어와파이어스톰 {
    static int N, Len;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());
        Len = (int) Math.pow(2, N);
        map = new int[Len][Len];

        for(int i = 0; i < Len; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < Len; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int t = 0; t < time; t++){
            int[][] newMap = new int[Len][Len];
            turnTheMap(newMap, (int) Math.pow(2, Integer.parseInt(st.nextToken())), 0, 0, Len - 1, Len - 1);
            for(int i = 0; i < Len; i++){
                for(int j = 0; j < Len; j++){
                    if(newMap[i][j] == 0)  map[i][j] = 0;
                    else    BFS(i, j, newMap);
                }
            }
        }
        printResult();
    }

    private static void printResult(){
        int maxCnt = 0, totalCnt = 0;
        boolean[][] visited = new boolean[Len][Len];
        for(int i = 0; i < Len; i++){
            for(int j = 0; j < Len; j++){
                if(visited[i][j] || map[i][j] == 0)   continue;
                Queue<Point> queue = new ArrayDeque<>();
                queue.add(new Point(i, j));
                int cnt = 1;
                totalCnt += map[i][j];
                visited[i][j] = true;
                while (!queue.isEmpty()){
                    Point tmp = queue.poll();
                    for(int d = 0; d < 4; d++){
                        int nx = tmp.x + dx[d];
                        int ny = tmp.y + dy[d];
                        if(isValid(nx, ny) && map[nx][ny] > 0 && !visited[nx][ny]){
                            queue.add(new Point(nx, ny));
                            totalCnt += map[nx][ny];
                            visited[nx][ny] = true;
                            cnt++;
                        }
                    }
                }
                if(maxCnt < cnt)    maxCnt = cnt;
            }
        }
        System.out.println(totalCnt);
        System.out.println(maxCnt);
    }

    private static void BFS(int x, int y, int[][] newMap) {
        int cnt = 0;
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(isValid(nx, ny) && newMap[nx][ny] > 0){
                cnt++;
            }
        }
        map[x][y] = newMap[x][y];
        if(cnt < 3) map[x][y]--;
    }


    private static void turnTheMap(int[][] newMap, int val, int start_x, int start_y, int end_x, int end_y) {
        if((end_x - start_x + 1) == val){
            for(int i = 0; i < val; i++){
                for(int j = 0; j < val; j++){
                    newMap[start_x+j][end_y-i] = map[i + start_x][j + start_y];
                }
            }
            return;
        }
        int mid_x = (start_x + end_x) / 2;
        int mid_y = (start_y + end_y) / 2;
        turnTheMap(newMap, val, start_x, start_y, mid_x, mid_y);
        turnTheMap(newMap, val, start_x, mid_y+1, mid_x, end_y);
        turnTheMap(newMap, val, mid_x+1, start_y, end_x, mid_y);
        turnTheMap(newMap, val, mid_x+1, mid_y+1, end_x, end_y);
    }

    private static boolean isValid(int x, int y){
        return x >= 0 && x < Len && y >= 0 && y < Len;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
