import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int L;
    static int[][] map;
    static boolean[][] visited;
    static int validRoadCnt;
    static boolean flagValid;
    static boolean flagStart;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        validRoadCnt = 0;
        visited = new boolean[N][N];
        leftToRight();
        visited = new boolean[N][N];
        topToBot();
        System.out.println(validRoadCnt);
    }

    private static void leftToRight() {
        for(int i = 0; i < N; i++){
            // 처음 상태로 초기화, 유효하며, 시작하지 않은 상태고 0번째 index의 cnt를 세줌
            flagValid = true;
            flagStart = false;
            cnt = 1;
            for(int j = 1; j < N; j++){
                if(map[i][j-1] == map[i][j]){
                    // 값이 동일할 경우
                    cnt++;
                }else{
                    // 값이 다를 경우
                    // 값이 다른데 차이가 1보다 큼
                    if(Math.abs(map[i][j-1] - map[i][j]) > 1){
                        flagValid = false;
                        break;
                    }
                    // 값이 다른데 차이는 1
                    if(map[i][j-1] > map[i][j]){
                        // 큰 값 - 작은 값
                        if(flagStart){
                            flagValid = false;
                            break;
                        }
                        flagStart = true;
                        cnt = 1;
                    }else{
                        // 작은 값 - 큰 값
                         if(cnt >= L){  // 지금 까지 쌓아온 cnt 가 L보다 같거나 클 때
                             int t = 1;
                             while(t <= L){ // 작은 값 - 큰 값은 현재의 값은 미포함
                                 if(visited[i][j-t]){
                                     flagValid = false;
                                     break;
                                 }
                                 visited[i][j-t] = true;
                                 t++;
                             }
                             cnt = 1;
                         }else{ // L보다 cnt가 작으므로 탈락
                             flagValid = false;
                             break;
                         }
                    }
                }
                if(flagStart && cnt == L){
                    int t = 0;
                    while(t < L){   // start - L 까지 진행되었으면 현재 인덱스 까지 포함
                        if(visited[i][j-t]){
                            flagValid = false;
                            break;
                        }
                        visited[i][j-t] = true;
                        t++;
                    }
                    cnt = 0;
                    flagStart = false;
                }
            }
            if(flagStart && cnt < L){   // 만약 flagStart가 true 인 경우 : 아직 경사로를 쌓아야 하는 상태인데 L보다 cnt가 작으면 경사로를 쌓지 못함
                flagValid = false;
            }
            if(flagValid)
                validRoadCnt++;
        }
    }

    private static void topToBot() {
        for(int i = 0; i < N; i++){
            // 처음 상태로 초기화, 유효하며, 시작하지 않은 상태고 0번째 index의 cnt를 세줌
            flagValid = true;
            flagStart = false;
            cnt = 1;
            for(int j = 1; j < N; j++){
                if(map[j-1][i] == map[j][i]){
                    // 값이 동일할 경우
                    cnt++;
                }else{
                    // 값이 다를 경우
                    if(Math.abs(map[j-1][i] - map[j][i]) > 1){
                        flagValid = false;
                        break;
                    }
                    // 값이 다른데 차이는 1
                    if(map[j-1][i] > map[j][i]){
                        // 큰 값 - 작은 값
                        if(flagStart){
                            flagValid = false;
                            break;
                        }
                        flagStart = true;
                        cnt = 1;
                    }else{
                        // 작은 값 - 큰 값
                        if(cnt >= L){   // 지금 까지 쌓아온 cnt 가 L보다 같거나 클 때
                            int t = 1;
                            while(t <= L){  // 작은 값 - 큰 값은 현재의 값은 미포함
                                if(visited[j-t][i]){
                                    flagValid = false;
                                    break;
                                }
                                visited[j-t][i] = true;
                                t++;
                            }
                            cnt = 1;
                        }else{  // L보다 cnt가 작으므로 탈락
                            flagValid = false;
                            break;
                        }
                    }
                }
                if(flagStart && cnt == L){
                    int t = 0;
                    while(t < L){   // start - L 까지 진행되었으면 현재 인덱스 까지 포함
                        if(visited[j-t][i]){
                            flagValid = false;
                            break;
                        }
                        visited[j-t][i] = true;
                        t++;
                    }
                    cnt = 0;
                    flagStart = false;
                }
            }
            if(flagStart && cnt < L){   // 만약 flagStart가 true 인 경우 : 아직 경사로를 쌓아야 하는 상태인데 L보다 cnt가 작으면 경사로를 쌓지 못함
                flagValid = false;
            }
            if(flagValid)
                validRoadCnt++;
        }
    }
}