package coding_test.Algo2022년_10월.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 어떻게 어항 모양을 변경할 수 있을까?
* 1. 일반 배열을 가지고 이용하는 방법 -> 선택
* 2. stack을 이용하는 방법. N이 최대 100개 이므로 나쁘지 않을 것 같다! -> 나중에 주변에 인접한 어항에 대해 처리를 해줘야 하는데 이게 어렵다.
*
* */
public class BOJ_23291_어항정리 {
    static int N, K, half;
    static int[][] map;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        half = (N - 1) / 2;

        map = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[N-1][i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        while(!isSatisfied()){  // 만족할 때 반복문을 종료
            rollTheMap();
            foldTheMap();
            cnt++;
        }
        System.out.println(cnt);
    }

    private static void rollTheMap() {
        // 가장 물고기 수가 적은 어항에 +1
        addNewFish();
        // roll
        doRoll(0,1, 1);
        // 인접한 어항에 대해 d = (물고기 차이 / 5 > 0) 이면, 많은 곳에서는 - d, 적은 곳에서는 + d. 이는 동시에 일어난다.
        moveFishes();
        // 어항을 다시 바닥에 둔다.
        putDownTheFishbowl();
    }

    private static void foldTheMap() {
        // fold
        doFold(0, 0, half);
        // 인접한 어항에 대해 d = (물고기 차이 / 5 > 0) 이면, 많은 곳에서는 - d, 적은 곳에서는 + d. 이는 동시에 일어난다.
        moveFishes();
        // 어항을 다시 바닥에 둔다.
        putDownTheFishbowl();
    }

    private static void addNewFish() {
        int min = Integer.MAX_VALUE;
        for(int val : map[N-1]){
            if(val < min)  min = val;
        }
        for (int i = 0; i < N; i++) {
            if(map[N-1][i] == min)  map[N-1][i]++;
        }
    }

    private static void doRoll(int basis, int w, int h) {
        if(basis + h >= N)  return;
        int r = N - 2, c = 0;
        for(int i = basis; i >= 0; i--){
            if(map[N-1][i] == 0)  break;
            for (int j = N-1; j >= 0; j--) {
                if(map[j][i] == 0)  break;
                map[r][i + N - j + c] = map[j][i];
                map[j][i] = 0;
            }
            r--;
            c++;
        }
        if(w == h)  doRoll(basis + w, w, h + 1);
        else doRoll(basis + w + 1, w + 1, h);
    }

    private static void doFold(int cnt, int start, int end) {
        if(cnt == 2)    return;
        int r = N - 2 - cnt;
        for(int i = 0; i < N; i++){
            if(map[i][start] == 0)  continue;
            int c = 1;
            for (int j = end; j >= start; j--) {
               map[r][end + c] = map[i][j];
               map[i][j] = 0;
                c++;
            }
            r--;
        }

        doFold(cnt + 1, end + 1, (end + (end + 1 - start)/2));
    }

    private static void moveFishes() {  // 인접한 어항에 대해 d = (물고기 차이 / 5 > 0) 이면, 많은 곳에서는 - d, 적은 곳에서는 + d. 이는 동시에 일어난다.
        int[][] adder = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0){
                    for (int d = 0; d < 2; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(isVaild(nx, ny) && map[nx][ny] > 0){
                            int dif = Math.abs(map[i][j] - map[nx][ny]) / 5;
                            if(dif > 0){
                                if(map[i][j] > map[nx][ny]){
                                    adder[i][j] -= dif;
                                    adder[nx][ny] += dif;
                                }
                                else if (map[i][j] < map[nx][ny]){
                                    adder[i][j] += dif;
                                    adder[nx][ny] -= dif;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += adder[i][j];
            }
        }

    }

    private static void putDownTheFishbowl() {
        int idx = 0;
        for (int i = 0; i < N; i++) {
            int r = N - 1;
            while(idx < N && map[r][i] != 0){
                map[N-1][idx++] = map[r][i];
                if(N-1 != r || idx - 1 != i) map[r][i] = 0;
                r--;
            }
        }
    }

    static boolean isSatisfied(){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int val : map[N-1]){
            if(val > max)  max = val;
            if(val < min)  min = val;
        }
        if(max - min <= K)  return true;
        return false;
    }

    private static boolean isVaild(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
