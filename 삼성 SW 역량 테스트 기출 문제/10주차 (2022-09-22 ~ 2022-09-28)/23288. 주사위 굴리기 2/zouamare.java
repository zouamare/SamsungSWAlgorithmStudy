package coding_test.Algo20220926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
*   1. 구르기
*   2. 점수 획득
*   3. 방향 변경
*
*   위의 조건에 따라 진행하는 문제.
*
*   -> 시간 소요됐던 이유 : BFS로 했어야 했는데 DFS로 진행했다.
*
*/
public class BOJ_23288_주사위굴리기2 {
    static int N, M, cnt;
    static int[][] diceMap;
    static int[][] countMap;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(playDice());
    }

    private static int playDice() {
        int x = 0, y = 0, dir = 0, score = 0;
        Dice dice = new Dice();
        for (int i = 0; i < cnt; i++) {
            // 1. 한 칸 굴러간다.
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(!isValid(nx, ny)){
                dir = (dir + 2) % 4;    // 반대 방향으로 변경
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            x = nx;
            y = ny;
            dice.moveDice(dir);
            // 2. 점수를 얻는다.
            score += diceMap[x][y] * countMap[x][y];
            // 3. 이동 방향을 결정한다.
            if(dice.getBot() > diceMap[x][y]){  // 시계 방향으로 이동
                dir = (dir + 1) % 4;
            }else if(dice.getBot() < diceMap[x][y]){    // 반시계 방향으로 이동
                dir = (dir + 3) % 4;
            }
        }
        return score;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());
        diceMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                diceMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        countMap = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j])   continue;
                BFS(i, j, visited);
            }
        }
    }

    private static void BFS(int x, int y, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        Queue<int[]> addQ = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int count = 0;
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            count++;
            for (int d = 0; d < 4; d++) {
                int nx = point[0] + dx[d];
                int ny = point[1] + dy[d];
                if(isValid(nx, ny) && !visited[nx][ny] && diceMap[nx][ny] == diceMap[x][y]){
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
            addQ.offer(point);
        }

        while(!addQ.isEmpty()){
            int[] point = addQ.poll();
            countMap[point[0]][point[1]] = count;
        }
    }

    private static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    
    
    static class Dice{
        int top, bot, left, right, front, back;
        
        public Dice(){
            this.top = 1;
            this.bot = 6;
            this.front = 2;
            this.back = 5;
            this.left = 4;
            this.right = 3;
        }
        
        public int getBot(){
            return this.bot;
        }
        
        public void moveDice(int dir){
            switch (dir){
                case 0:
                    moveToEast();
                    break;
                case 1:
                    moveToSouth();
                    break;
                case 2:
                    moveToWest();
                    break;
                case 3:
                    moveToNorth();
                    break;
            }
        }

        private void moveToEast() {
            int tmp = this.bot;
            this.bot = this.right;
            this.right = this.top;
            this.top = this.left;
            this.left = tmp;
        }

        private void moveToSouth() {
            int tmp = this.back;
            this.back = this.top;
            this.top = this.front;
            this.front = this.bot;
            this.bot = tmp;
        }

        private void moveToWest() {
            int tmp = this.left;
            this.left = this.top;
            this.top = this.right;
            this.right = this.bot;
            this.bot = tmp;
        }

        private void moveToNorth() {
            int tmp = this.front;
            this.front = this.top;
            this.top = this.back;
            this.back = this.bot;
            this.bot = tmp;
        }
    }
}
