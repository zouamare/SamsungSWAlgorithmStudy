package coding_test.Algo20220901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 본 문제는 배열을 돌리면서 모래를 주변에 뿌리는 구현 문제 입니다.
* 도출해야 하는 결과는 `격자의 밖으로 나간 모래의 양을 출력` 입니다.
* 이를 위해 배열을 돌리면서 격자의 밖으로 나간 모래의 값을 카운팅해야 합니다.
* 따라서, 해당 값을 카운팅할 변수 sandCnt 를 static으로 선언해 줍니다.
*
* 모래가 주변에 뿌려지는 로직은 다음과 같습니다.
* 토네이도가 x에서 y로 이동하면, y의 모든 모래가 주변에 뿌려진다. (그림 참고)
* 모래의 양은 y에 있는 모래의 비율만큼이다.
*
* 모래를 뿌릴 때 방향마다 다르게 처리를 해줘야 하는데,
* 좌랑 우와 상과 하가 비슷하므로 이를 비슷하게 처리해준다.
* */
public class BOJ_20057_마법사상어와토네이도 {
    static int N, sandCnt;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int[] tx = {-1, -2, -1, -1, 1, 1, 1, 2, 0, 0};
    static int[] ty = {0, 0, -1, 1, -1, 0, 1, 0, -2, -1};
    static int[] tv = {7, 2, 10, 1, 10, 7, 1, 2, 5, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        startTornado();
    }

    private static void startTornado() {
        int x = N/2, y = N/2, d = 1;
        spreadSand(x, y, 0);
        outer: while(isValid(x, y)){    // 0,0을 지나게 되면 종료
            for(int i = 0; i < d; i++){ // 좌
                x = x + dx[0];
                y = y + dy[0];
                if(x < 0 || y < 0)    break outer;
                spreadSand(x, y, 0);
            }

            for(int i = 0; i < d; i++){ // 하
                x = x + dx[1];
                y = y + dy[1];
                spreadSand(x, y, 1);
            }

            d++;

            for(int i = 0; i < d; i++){ // 우
                x = x + dx[2];
                y = y + dy[2];
                spreadSand(x, y, 2);
            }

            for(int i = 0; i < d; i++){ // 상
                x = x + dx[3];
                y = y + dy[3];
                spreadSand(x, y, 3);
            }
            d++;
        }
        System.out.println(sandCnt);
    }

    static void spreadSand(int x, int y, int dir){
        int sand = map[x][y];
        map[x][y] = 0;
        int removedSand = 0;
        switch (dir){
            case 0:
                for(int t = 0; t < tx.length; t++){
                    int nx = x + tx[t];
                    int ny = y + ty[t];
                    if(t == tx.length - 1){
                        if(isValid(nx,ny)) map[nx][ny] += sand - removedSand;
                        else sandCnt += sand - removedSand;
                    }else{
                        int removed = (sand * tv[t]) / 100;
                        if(isValid(nx, ny)) map[nx][ny] += removed;
                        else sandCnt += removed;
                        removedSand += removed;
                    }
                }
                break;
            case 1:
                for(int t = 0; t < tx.length; t++){
                    int nx = x - ty[t];
                    int ny = y - tx[t];
                    if(t == tx.length - 1){
                        if(isValid(nx,ny)) map[nx][ny] += sand - removedSand;
                        else sandCnt += sand - removedSand;
                    }else{
                        int removed = (sand * tv[t]) / 100;
                        if(isValid(nx, ny)) map[nx][ny] += removed;
                        else sandCnt += removed;
                        removedSand += removed;
                    }
                }
                break;
            case 2:
                for(int t = 0; t < tx.length; t++){
                    int nx = x - tx[t];
                    int ny = y - ty[t];
                    if(t == tx.length - 1){
                        if(isValid(nx,ny)) map[nx][ny] += sand - removedSand;
                        else sandCnt += sand - removedSand;
                    }else{
                        int removed = (sand * tv[t]) / 100;
                        if(isValid(nx, ny)) map[nx][ny] += removed;
                        else sandCnt += removed;
                        removedSand += removed;
                    }
                }
                break;
            case 3: // 상 하
                for(int t = 0; t < tx.length; t++){
                    int nx = x + ty[t];
                    int ny = y + tx[t];
                    if(t == tx.length - 1){
                        if(isValid(nx,ny)) map[nx][ny] += sand - removedSand;
                        else sandCnt += sand - removedSand;
                    }else{
                        int removed = (sand * tv[t]) / 100;
                        if(isValid(nx, ny)) map[nx][ny] += removed;
                        else sandCnt += removed;
                        removedSand += removed;
                    }
                }
                break;
        }
    }

    private static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
