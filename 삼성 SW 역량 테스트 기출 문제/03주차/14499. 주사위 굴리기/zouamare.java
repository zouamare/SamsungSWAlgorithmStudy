import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0,0,0,-1,1};    // 동 서 북 남
        int[] dy = {0,1,-1,0,0};
        Dice dice = new Dice();
        int standard = 1;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int d = Integer.parseInt(st.nextToken());
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M)  continue;
            // 바깥으로 이동되지 않음
            x = nx;
            y = ny;
            int position = map[x][y];
            switch (d){
                case 1:
                    System.out.println(dice.moveEast(position,x,y));
                    break;
                case 2:
                    System.out.println(dice.moveWest(position,x,y));
                    break;
                case 3:
                    System.out.println(dice.moveNorth(position,x,y));
                    break;
                case 4:
                    System.out.println(dice.moveSouth(position,x,y));
            }
        }
    }
    static class Dice{
        int north;
        int west;
        int east;
        int south;
        int top;
        int bot;

        public Dice() { // 초기값은 모두 0
            this.north = 0;
            this.west = 0;
            this.east = 0;
            this.south = 0;
            this.top = 0;
            this.bot = 0;
        }

        public int moveWest(int position, int x, int y){
            int tmp = east;
            // 현재 bot이 east로 이동
            east = bot;
            // 현재 west가 bot으로 이동
            bot = west;
            // 현재 top이 west로 이동
            west = top;
            // 현재 east가 top으로 이동
            top = tmp;
            if(position == 0){
                // position이 0이면
                // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
                map[x][y] = bot;
            }
            else{
                // position이 0이 아니면,
                // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
                bot = map[x][y];
                map[x][y] = 0;
            }
            return top;
        }

        public int moveEast(int position, int x, int y){
            int tmp = west;
            west = bot;
            bot = east;
            east = top;
            top = tmp;
            if(position == 0){
                // position이 0이면
                // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
                map[x][y] = bot;
            }
            else{
                // position이 0이 아니면,
                // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
                bot = map[x][y];
                map[x][y] = 0;
            }
            return top;
        }

        public int moveNorth(int position, int x, int y){
            int tmp = south;
            south = bot;
            bot = north;
            north = top;
            top = tmp;
            if(position == 0){
                // position이 0이면
                // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
                map[x][y] = bot;
            }
            else{
                // position이 0이 아니면,
                // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
                bot = map[x][y];
                map[x][y] = 0;
            }
            return top;
        }

        public int moveSouth(int position, int x, int y){
            int tmp = north;
            north = bot;
            bot = south;
            south = top;
            top = tmp;
            if(position == 0){
                // position이 0이면
                // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
                map[x][y] = bot;
            }
            else{
                // position이 0이 아니면,
                // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
                bot = map[x][y];
                map[x][y] = 0;
            }
            return top;
        }
    }
}