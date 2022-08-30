import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] belt;
    static boolean[] robot;
    static int up;
    static int down;
    static int Len;
    static int zeroCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Len = 2 * N;
        belt = new int[Len];    // 내구도
        robot = new boolean[Len]; // 로봇의 존재 유무
        up = 0;
        down = N - 1;
        zeroCnt = 0;    // 내구도 0 갯수
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Len; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(RunBelt());
    }

    private static int RunBelt() {
        int level = 0;
        while(zeroCnt < K){
            level++;
            moveBelt();     // Phase 1
            moveRobot();    // Phase 2
            putRobot();     // Phase 3
        }
        return level;
    }

    private static void putRobot() {
        if(!robot[up] && belt[up] > 0){
            robot[up] = true;
            belt[up]--;
            if(belt[up] == 0)    zeroCnt++;
        }
    }

    private static void moveRobot() {
        int idx = down;
        while(idx != up){
            int front = (idx+1)%Len;
            if(robot[idx] && !robot[front] && belt[front] > 0){
                // 현재 칸에 로봇이 있고, 바로 앞의 칸에는 로봇이 없으며 해당 칸의 내구도가 0 이상일 경우
                robot[idx] = false;
                robot[front] = true;
                belt[front]--;
                if(belt[front] == 0)    zeroCnt++;
            }
            idx = (idx - 1 + Len) % Len;
        }
        if(robot[down]) robot[down] = false;    //down에 도달하면 하차
    }

    private static void moveBelt(){
        up = (up - 1 + Len) % Len;
        down = (down - 1 + Len) % Len;
        if(robot[down])  robot[down] = false;
    }
}