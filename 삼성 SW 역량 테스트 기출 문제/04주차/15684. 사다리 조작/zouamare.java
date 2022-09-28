package coding_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작 {
    static int N;
    static int M;
    static int H;
    static int[][] ladder;
    static int answer;
    static boolean finish = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로 선의 개수
        M = Integer.parseInt(st.nextToken());   // 가로 선의 개수
        H = Integer.parseInt(st.nextToken());   // 최대로 추가할 수 있는 가로선의 개수가 H

        ladder = new int[H+1][N+1];

        int x, y;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            ladder[x][y] = 1;   // 연결 시작
            ladder[x][y+1] = 2; // 연결 끝
        }

        for(int i = 0; i <= 3; i++){
            answer = i;
            dfs(1, 0);
            if(finish)  break;  // 찾았다면 탈출
        }
        System.out.println((finish) ? answer : -1);
    }

    private static void dfs(int x, int count) {
        if(finish)  return;
        if(answer == count){
            if(check()) finish = true;
            return;
        }
        for(int i = x; i < H + 1; i++){
            for(int j = 1; j < N; j++){
                if(ladder[i][j] == 0 && ladder[i][j+1] == 0){
                    ladder[i][j] = 1;
                    ladder[i][j+1] = 2;
                    dfs(i, count+1);
                    ladder[i][j] = ladder[i][j+1] = 0;
                }
            }
        }
    }

    private static boolean check(){
        for(int i = 1; i <= N; i++){
            int x = 1, y = i;
            for(int j = 0; j < H; j++){
                if(ladder[x][y] == 1)   y++;
                else if(ladder[x][y] == 2)  y--;
                x++;
            }
            if(y != i)  return false;
        }
        return true;
    }
}
