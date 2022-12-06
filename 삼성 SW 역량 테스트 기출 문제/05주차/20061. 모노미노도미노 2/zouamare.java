package coding_test.Algo2022년_12월.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 세부 조건들 (특별한 구역에 블록이 있을 때 특별한 구역에 있는 블록도 아래로 내려와야 함, 특별한 구역 0번째일때는 2칸이고 1번째일때는 1칸) 부분을 꼼꼼하게 못봐서 시간이 소요됐다.
public class BOJ_20061_모노미노도미노2 {
    static boolean blueZone[][], greenZone[][];
    static int score;
    public static void main(String[] args) throws IOException {
        // 파란색과 초록색 구역을 만듭니다.
        blueZone = new boolean[4][6];
        greenZone = new boolean[6][4];


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 블록을 놓은 횟수 N
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            // t = 1 : 크기가 1x1인 블록을 (x,y)에 놓은 경우
            // t = 2 : 크기가 1x2인 블록을 (x,y), (x,y+1) 에 놓은 경우
            // t = 3 : 크기가 2x1인 블록을 (x,y), (x+1,y) 에 놓은 경우
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 파란색 구역 진행
            putBlockInBlueZone(t, x);
            isFullInBlueZone();
            removeTilesInBlueSpecialZone();

            // 초록색 구역 진행
            putBlockInGreenZone(t, y);
            isFullInGreenZone();
            removeTilesInGreenSpecialZone();
        }
        System.out.println(score);
        System.out.println(getTileCount());
    }

    private static void removeTilesInGreenSpecialZone() {
        int flag = -1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if(greenZone[i][j] && flag == -1) flag = 2 - i;
            }
        }
        if(flag != -1){
            // 값만큼 이동한다.
            for (int i = 5 - flag; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    greenZone[i + flag][j] = greenZone[i][j];
                }
            }
            // 특별한 구역은 비운다.
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    greenZone[i][j] = false;
                }
            }
        }
    }

    private static void removeTilesInBlueSpecialZone() {
        int flag = -1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if(blueZone[j][i] && flag == -1) flag = 2 - i;
            }
        }
        if(flag != -1){
            // 값만큼 이동한다.
            for (int i = 5 - flag; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    blueZone[j][i + flag] = blueZone[j][i];
                }
            }
            // 특별한 구역은 비운다.
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    blueZone[j][i] = false;
                }
            }
        }
    }

    private static void isFullInGreenZone() {
        for(int i = 5; i >= 0; i--){
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                if(greenZone[i][j])    cnt++;
            }
            if(cnt == 4){
                score++;
                for (int j = 0; j < 4; j++) {
                    for (int k = i; k > 0; k--) {
                        greenZone[k][j] = greenZone[k - 1][j];
                    }
                }
                // 제일 위는 비운다.
                for (int j = 0; j < 4; j++) {
                    greenZone[0][j] = false;
                }
                i++;
            }
        }
    }

    private static void isFullInBlueZone() {
        for(int i = 5; i >= 0; i--){
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                if(blueZone[j][i])    cnt++;
            }
            if(cnt == 4){
                score++;
                for (int j = 0; j < 4; j++) {
                    for (int k = i; k > 0; k--) {
                        blueZone[j][k] = blueZone[j][k - 1];
                    }
                }
                // 제일 위는 비운다.
                for (int j = 0; j < 4; j++) {
                    blueZone[j][0] = false;
                }
                i++;
            }
        }
    }

    private static int getTileCount() {
        int count = 0;

        // 파란색 구역의 타일 개수를 확인합니다.
        for(int i = 2; i < 6; i++){
            for (int j = 0; j < 4; j++) {
                if(blueZone[j][i])  count++;
            }
        }

        // 초록색 구역의 타일 개수를 확인합니다.
        for(int i = 2; i < 6; i++){
            for (int j = 0; j < 4; j++) {
                if(greenZone[i][j]) count++;
            }
        }

        return count;
    }

    private static void putBlockInGreenZone(int t, int y) {
        int idx;
        switch (t){
            case 1:
                idx = -1;
                while(idx < 5){
                    if(!greenZone[idx + 1][y])   idx++;
                    else break;
                }
                greenZone[idx][y] = true;    // 해당 위치에 채움
                break;
            case 2:
                idx = -1;
                while(idx < 5){
                    if(!greenZone[idx + 1][y] && !greenZone[idx + 1][y + 1])   idx++;
                    else break;
                }
                greenZone[idx][y] = greenZone[idx][y + 1] = true;
                break;
            case 3:
                idx = 0;
                while(idx < 5){
                    if(!greenZone[idx + 1][y])   idx++;
                    else break;
                }
                greenZone[idx][y] = greenZone[idx - 1][y] = true;
                break;
        }
    }

    private static void putBlockInBlueZone(int t, int x) {
        int idx;
        switch (t){
            case 1:
                idx = -1;
                while(idx < 5){
                    if(!blueZone[x][idx + 1])   idx++;
                    else break;
                }
                blueZone[x][idx] = true;    // 해당 위치에 채움
                break;
            case 2:
                idx = 0;
                while(idx < 5){
                    if(!blueZone[x][idx + 1])   idx++;
                    else break;
                }
                blueZone[x][idx-1] = blueZone[x][idx] = true;
                break;
            case 3:
                idx = -1;
                while(idx < 5){
                    if(!blueZone[x][idx + 1] && !blueZone[x + 1][idx + 1])   idx++;
                    else break;
                }
                blueZone[x][idx] = blueZone[x + 1][idx] = true;
                break;
        }
    }


}
