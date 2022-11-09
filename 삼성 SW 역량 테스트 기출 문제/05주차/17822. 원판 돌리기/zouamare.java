import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T;
    static int[][] circle;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // 이차원 배열 초기화 시작
        circle = new int[N+1][M]; // 원판의 배수의 인덱스를 구해야 하기 때문에
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 이차원 배열 초기화 종료
        
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            turnCircles(x, d, k);
            findNearSameNumber();
        }

        System.out.println(getTotalSum());
    }

    private static int getTotalSum() {
        int sum = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < M; j++){
                sum += circle[i][j];
            }
        }
        return sum;
    }

    private static int getTotalCnt() {
        int cnt = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < M; j++){
                if(circle[i][j] != 0)   cnt++;
            }
        }
        return cnt;
    }
    private static void findNearSameNumber() {  // 확인해보니 연속하는 건 다 지워야 한다...
        boolean flag = false;   // 변경상황을 확인할 flag 변수
        for(int i = 1; i <= N; i++){    // 동일 circle 내 숫자 찾기
            for(int j = 0; j < M; j++){
                if(circle[i][j] == 0)   continue;
                if(BFS(i, j))   flag = true;
            }
        }
        if(!flag){
            // 변경이 없다는 의미
            // 모든 수의 합을 구해서 남은 개수로 나눠서 값이 크면 +1
            double ave = (double) getTotalSum() / getTotalCnt();
            for(int i = 1; i <= N; i++){
                for(int j = 0; j < M; j++){
                    if(circle[i][j] == 0)   continue;
                    if(circle[i][j] > ave)  circle[i][j]--;
                    else if(circle[i][j] < ave)circle[i][j]++;
                }
            }
        }
    }

    private static boolean BFS(int x, int y) {
        boolean[][] visited = new boolean[N+1][M];
        ArrayList<int[]> same = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        while (!queue.isEmpty()){
            int[] deleted = queue.poll();
            for(int d = 0; d < 4; d++){
                int nx = deleted[0] + dx[d];
                int ny = (deleted[1] + dy[d] + M) % M;
                if(nx < 0 || nx > N || visited[nx][ny] || circle[nx][ny] != circle[x][y]) continue;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
            same.add(deleted);
        }

        if(same.size() == 1)    return false;
        for(int i = 0, size = same.size(); i < size; i++){
            int[] deleted = same.get(i);
            circle[deleted[0]][deleted[1]] = 0;
        }
        return true;
    }

    private static void turnCircles(int x, int d, int k) {
        // 번호가 x의 배수인 원판을 d 방향으로 k칸 회전
        for(int i = x; i <= N; i+=x){
            // x의 배수인 원판에 접근.
            int[] newCircle = new int[M];
            if(d == 0){ // 시계 방향
                for(int j = 0; j < M; j++) newCircle[(j+k)%M] = circle[i][j];
            }else{  // 반시계 방향
                for(int j = 0; j < M; j++) newCircle[(j-k+M)%M] = circle[i][j];
            }
            circle[i] = newCircle;
        }
    }
}