package coding_test.Algo20220907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* 학생 배치 방법
* 1. 비어 있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정함
* 2. 1.을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
* 3. 2. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
*
* 학생의 만족도 배치 방법
* 좋아하는 학생 수 : 0 - 0, 1 - 1, 2 - 10, 3 - 100, 4 - 1000
*
* 만족도의 총 합을 구해보자.
*
*
* 3 * 3 일 때는 초기 위치가 1 , 1
* 4 * 4 일 때는 초기 위치가 1, 1
* 5 * 5 일 때는 초기 위치가 2, 2 -> 아님!! 무조건 1, 1로 고정됨
*
* [이 문제에서 고려해야할 것]
* 1. 우선순위를 잘 고려했는가?
* 2. 비어있는 칸에만 입력이 들어가는가?
*
* 위의 내용이 주로 많은 사람들이 이 문제에서 실수하는 부분인 것 같다.
* 그래서 내 코드의 오류가 위의 문제라고 생각하고 계속 봤었지만 내 코드는 다른 문제였다.
* N = 3일 때의 예시만 보고 맨 초기값을 중앙에 넣어줬는데, 생각해보니 인접한 빈칸의 수는 4를 초과할 수 없으므로 N의 크기가 3 이상이라면 무조건 (1, 1)에 초기값이 들어갔다.
* 나는 이 부분을 생각하지 못해 많은 시간을 할애했다.
* 어림짐작하면 안되겠다.
*
* 다른 좋은 풀이는 @kimjuc12 님의 큐를 사용하지 않은 풀이이다.
* 시간이 매우 빠름을 알 수 있다.
* */

public class BOJ_21608_상어초등학교 {
    static int N, N2;
    static int[] student;
    static int[][] lover, map, xy;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        N2 = N * N;
        student = new int[N2];
        lover = new int[N2][4];
        xy = new int[N2][2];
        for(int i = 0; i < N2; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            student[i] = Integer.parseInt(st.nextToken());
            lover[i][0] = Integer.parseInt(st.nextToken());
            lover[i][1] = Integer.parseInt(st.nextToken());
            lover[i][2] = Integer.parseInt(st.nextToken());
            lover[i][3] = Integer.parseInt(st.nextToken());
        }
        letsSeatDown();
        figureOutSatisfaction();
    }

    private static void figureOutSatisfaction() {
        int satisfaction = 0;
        for(int i = 0; i < N2; i++){
            int cnt = 0;
            for(int d = 0; d < 4; d++){
                int nx = xy[i][0] + dx[d];
                int ny = xy[i][1] + dy[d];
                if(!isValid(nx,ny) || isNotLover(nx, ny, i))  continue;
                cnt++;
            }
            if(cnt == 1)    satisfaction += 1;
            else if(cnt == 2)   satisfaction += 10;
            else if(cnt == 3)   satisfaction += 100;
            else if(cnt == 4)   satisfaction += 1000;
        }
        System.out.println(satisfaction);
    }

    private static void letsSeatDown() {
        map = new int[N][N];
//        map[(N-1)/2][(N-1)/2] = student[0]; // 맨 중앙 자리에 첫 학생을 앉힌다.
//        xy[0][0] = (N-1)/2;
//        xy[0][1] = (N-1)/2;
        for(int i = 0; i < N2; i++){
            // 앉을 자리를 찾는다.
            PriorityQueue<Seat> seats = new PriorityQueue<>();
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(map[j][k] == 0){
                        Seat seat = new Seat(j,k,0,0);
                        for(int d = 0; d < 4; d++){
                            int nx = j + dx[d];
                            int ny = k + dy[d];
                            if(!isValid(nx,ny))  continue;
                            if(!isNotLover(nx, ny, i)) seat.loverCnt++;
                            else if(map[nx][ny] == 0)   seat.blankCnt++;
                        }
                        seats.add(seat);
                    }
                }
            }
            while (!seats.isEmpty()){
                Seat seat = seats.poll();
                if(map[seat.x][seat.y] == 0){
                    map[seat.x][seat.y] = student[i];
                    xy[i][0] = seat.x;
                    xy[i][1] = seat.y;
                    break;
                }
            }
        }
    }

    private static boolean isNotLover(int x, int y, int idx) {
        return map[x][y] != lover[idx][0] && map[x][y] != lover[idx][1] && map[x][y] != lover[idx][2] && map[x][y] != lover[idx][3];
    }

    private static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Seat implements Comparable<Seat>{
        int x, y, loverCnt, blankCnt;

        public Seat(int x, int y, int loverCnt, int blankCnt) {
            this.x = x;
            this.y = y;
            this.loverCnt = loverCnt;
            this.blankCnt = blankCnt;
        }

        @Override
        public int compareTo(Seat o) {
            if(this.loverCnt == o.loverCnt){
                if(this.blankCnt == o.blankCnt){
                    if(this.x == o.x)   return this.y - o.y;
                    return this.x - o.x;
                }
                return - (this.blankCnt - o.blankCnt);
            }
            return  - (this.loverCnt - o.loverCnt);
        }
    }
}
