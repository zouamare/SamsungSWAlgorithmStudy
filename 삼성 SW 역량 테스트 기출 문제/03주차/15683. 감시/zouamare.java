import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int[] dir;
    static ArrayList<CCTV> CCTVs;
    static int CCTVSize;
    static int zeroCnt;
    static int minBlindSpotCnt;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        CCTVs = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    zeroCnt++;
                if(map[i][j] > 0 && map[i][j] < 6){
                    // 감시 카메라 라면,
                    CCTVs.add(new CCTV(i,j,map[i][j]));
                }
            }
        }
        /*
        * dir은 각 감시카메라의 경우의 수를 나열한 것이다.
        * 1 ~
        * */
        dir = new int[]{0,4,2,4,4,1};
        CCTVSize = CCTVs.size();
        minBlindSpotCnt = Integer.MAX_VALUE;
        changeCamerasDirection(new int[CCTVSize], 0);
        System.out.println(minBlindSpotCnt);
    }

    private static void changeCamerasDirection(int[] output, int depth) {
        if(depth == CCTVSize){
            figureOutBlindSpot(output);
            return;
        }
        for(int i = 1; i <= dir[CCTVs.get(depth).value]; i++){
            output[depth] = i;
            changeCamerasDirection(output, depth+1);
        }
    }

    private static void figureOutBlindSpot(int[] output) {
        int cnt = zeroCnt;
        visited = new boolean[N][M];
        for(int i = 0; i < CCTVs.size(); i++){
            CCTV cctv = CCTVs.get(i);
            switch (cctv.value){
                case 1:
                    cnt -= viewCCTVOne(cctv, output[i]);
                    break;
                case 2:
                    cnt -= viewCCTVTwo(cctv, output[i]);
                    break;
                case 3:
                    cnt -= viewCCTVThree(cctv, output[i]);
                    break;
                case 4:
                    cnt -= viewCCTVFour(cctv, output[i]);
                    break;
                case 5:
                    cnt -= viewCCTVFive(cctv);
                    break;
            }
        }
        minBlindSpotCnt = Math.min(minBlindSpotCnt, cnt);
    }

    private static int up(CCTV cctv) {
        int cnt = 0;
        for(int i = cctv.x - 1; i >= 0; i--){
            if(map[i][cctv.y] == 6)
                break;
            if(map[i][cctv.y] == 0 && !visited[i][cctv.y]){
                cnt++;
                visited[i][cctv.y] = true;
            }
        }
        return cnt;
    }

    private static int down(CCTV cctv) {
        int cnt = 0;
        for(int i = cctv.x + 1; i < N; i++){
            if(map[i][cctv.y] == 6)
                break;
            if(map[i][cctv.y] == 0 && !visited[i][cctv.y]){
                cnt++;
                visited[i][cctv.y] = true;
            }
        }
        return cnt;
    }


    private static int left(CCTV cctv) {
        int cnt = 0;
        for(int i = cctv.y - 1; i >= 0; i--){
            if(map[cctv.x][i] == 6)
                break;
            if(map[cctv.x][i] == 0 && !visited[cctv.x][i]){
                cnt++;
                visited[cctv.x][i] = true;
            }
        }
        return cnt;
    }

    private static int right(CCTV cctv) {
        int cnt = 0;
        for(int i = cctv.y + 1; i < M; i++){
            if(map[cctv.x][i] == 6)
                break;
            if(map[cctv.x][i] == 0 && !visited[cctv.x][i]){
                cnt++;
                visited[cctv.x][i] = true;
            }
        }
        return cnt;
    }

    private static int viewCCTVOne(CCTV cctv, int dir) {
        int deletedBlindSpotCnt = 0;
        switch (dir){
            case 1: // 상
                deletedBlindSpotCnt += up(cctv);
                break;
            case 2: // 하
                deletedBlindSpotCnt += down(cctv);
                break;
            case 3: // 좌
                deletedBlindSpotCnt += left(cctv);
                break;
            case 4: // 우
                deletedBlindSpotCnt += right(cctv);
                break;
        }
        return deletedBlindSpotCnt;
    }

    private static int viewCCTVTwo(CCTV cctv, int dir) {
        int deletedBlindSpotCnt = 0;
        switch (dir){
            case 1: // 상 하
                deletedBlindSpotCnt += up(cctv) + down(cctv);
                break;
            case 2: // 좌 우
                deletedBlindSpotCnt += left(cctv) + right(cctv);
                break;
        }
        return deletedBlindSpotCnt;
    }

    private static int viewCCTVThree(CCTV cctv, int dir) {
        int deletedBlindSpotCnt = 0;
        switch (dir){
            case 1: // 상 우
                deletedBlindSpotCnt += up(cctv) + right(cctv);
                break;
            case 2: // 상 좌
                deletedBlindSpotCnt += up(cctv) + left(cctv);
                break;
            case 3: // 좌 하
                deletedBlindSpotCnt += left(cctv) + down(cctv);
                break;
            case 4: // 하 우
                deletedBlindSpotCnt += down(cctv) + right(cctv);
                break;
        }
        return deletedBlindSpotCnt;
    }

    private static int viewCCTVFour(CCTV cctv, int dir) {
        int deletedBlindSpotCnt = 0;
        switch (dir){
            case 1: // 좌 상 우
                deletedBlindSpotCnt += left(cctv) + up(cctv) + right(cctv);
                break;
            case 2: // 하 좌 상
                deletedBlindSpotCnt += down(cctv) + left(cctv) + up(cctv);
                break;
            case 3: // 우 하 좌
                deletedBlindSpotCnt += right(cctv) + down(cctv) + left(cctv);
                break;
            case 4: // 하 우 상
                deletedBlindSpotCnt += down(cctv) + right(cctv) + up(cctv);
                break;
        }
        return deletedBlindSpotCnt;
    }

    private static int viewCCTVFive(CCTV cctv) {
        int deletedBlindSpotCnt = 0;
        // 모든 방향으로 퍼져 나간다.
        deletedBlindSpotCnt += up(cctv) + down(cctv) + left(cctv) + right(cctv);
        return deletedBlindSpotCnt;
    }
    static class CCTV{
        int x;
        int y;
        int value;

        public CCTV(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}