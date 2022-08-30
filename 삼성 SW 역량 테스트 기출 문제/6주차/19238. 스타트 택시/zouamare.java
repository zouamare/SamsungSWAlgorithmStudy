import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * [막혔던 이유]
 * 계속 통과하지 못했던 문제가 Guest인지 비교하는 곳에서
 * x 좌표가 같으면 y좌표가 작은 순으로 비교해야 하는데,
 * x 좌표가 같으면 x좌표가 작은 순으로 비교하고 있었다.. (배열의 인덱스를 잘못 지정해주고 있었다.)
 * ㅠㅠ 그래도 찾았으니 어디여
 *
 * [풀이 방법]
 * BFS 이용
 *
 * [이 문제에서 중요하게 생각해야 하는 부분]
 * 승객의 출발지는 모두 다르나 도착지는 겹칠 수 있다. 그리고 출발지와 도착지가 겹칠 수 있다.
 * 	ex) A 승객의 도착지 == B 승객의 도착지  (가능)
 * 		A 승객의 출발지 == B 승객의 도착지  (가능)
 *
 * */

public class BOJ_19238_스타트택시 {
    static int N, M, fuel, guestDist;
    static Point taxi;
    static int[][] map;
    static int[][] guestMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Point[] goals;
    static boolean flag;    // 연료가 바닥났는지 아닌지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        // map 세팅
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        goals = new Point[M+1];
        guestMap = new int[N][N];
        st = new StringTokenizer(br.readLine());
        // 택시의 좌표
        taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            guestMap[startX][startY] = i;
            goals[i] = new Point(endX, endY);
        }

        flag = true;
        for(int i = 0; i < M; i++){
            if(!flag)   break;
            driveTaxi();    // BFS 방식으로 구현
        }
        System.out.println((flag?fuel:-1));
    }

    private static void driveTaxi() { //승객 찾고 목적지까지 도착하는 걸루
        boolean validGuest = false;
        boolean validGoal = false;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{taxi.x, taxi.y, 0}); // 택시 x 좌표, 택시 y 좌표, 거리
        visited[taxi.x][taxi.y] = true;

        Point guest = new Point(N-1, N-1);
        guestDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            if(guestDist < temp[2]) break;  // 찾은 고객보다 멀어지면 종료
            if(isGuest(guest, temp)){  // 승객을 찾았다.
                guest.x = temp[0];
                guest.y = temp[1];
                guestDist = temp[2];
                validGuest = true;
            }
            for(int d = 0; d < 4; d++){ // 사방탐색
                int nx = temp[0] + dx[d];
                int ny = temp[1] + dy[d];
                if(!isVaild(nx,ny) || visited[nx][ny] || map[nx][ny] == 1)  continue;
                queue.add(new int[]{nx, ny, temp[2] + 1});
                visited[nx][ny] = true;
            }
        }
        // 택시가 고객에게 잘 도착한게 맞는지 체크
        if(!validGuest){
            flag = false;
            return;
        }
        // 택시가 고객에게 도착했다! 해당 위치를 넣어주자.
        taxi.x = guest.x;
        taxi.y = guest.y;
        fuel -= guestDist;
        if(fuel < 0){
            flag = false;
            return;
        }
        // val -> 도달해야 할 목적지!
        int val = guestMap[taxi.x][taxi.y];
        guestMap[taxi.x][taxi.y] = 0;

        visited = new boolean[N][N];
        queue = new ArrayDeque<>();
        queue.add(new int[]{taxi.x, taxi.y, 0}); // 택시 x 좌표, 택시 y 좌표, 거리
        visited[taxi.x][taxi.y] = true;

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            if(goals[val].x == temp[0] && goals[val].y == temp[1]){  // 목적지에 도착했다.
                // 목적지에 도착하면 해야할 일
                // 1. 연료를 정산한다. -> 연료가 음수면 종료. 양수면 더해줌
                if(fuel - temp[2] < 0){
                    flag = false;
                    return;
                }
                fuel += temp[2];
                // 2. 택시 위치를 변경해주고 해당 위치를 초기화 시켜준다.
                taxi.x = temp[0];
                taxi.y = temp[1];
                validGoal = true;
                break;
            }
            for(int d = 0; d < 4; d++){ // 사방탐색
                int nx = temp[0] + dx[d];
                int ny = temp[1] + dy[d];
                if(!isVaild(nx,ny) || visited[nx][ny] || map[nx][ny] == 1)  continue;
                queue.add(new int[]{nx, ny, temp[2] + 1});
                visited[nx][ny] = true;
            }
        }
        if(!validGoal){
            flag = false;
            return;
        }
    }

    private static boolean isGuest(Point guest, int[] val) {
        if(guestMap[val[0]][val[1]] != 0){
            if(guestDist > val[2]){ // 더 가까운 위치일 때
                return true;
            }else if(guestDist == val[2]){  // 만약에 거리가 같다면,
                if(val[0] < guest.x || (val[0] == guest.x && val[1] < guest.y)){  // row가 더 작은거거나 row가 같으면 col이 더 작을 때
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isVaild(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}