import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] visited;
    static int fishCnt;
    static BabyShark babyShark;
    static int time;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        mapInit();
        letsEatFishes();
        printTime();
    }

    private static void printTime() {
        System.out.println(time);
    }

    private static void mapInit() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    // 아기 상어일 경우
                    babyShark = new BabyShark(i, j);
                    map[i][j] = 0;  // 상어 위치만 확인되면 계산에 지장없도록 삭제
                }else if(map[i][j] > 0){
                    // 물고기인 경우
                    fishCnt++;
                }
            }
        }
    }

    private static void letsEatFishes() {
        while(true){
            if(fishCnt == 0)
                return;
            if(!findFishAndGo())    // 먹을 수 있는 물고기가 없다.
                return;
        }
    }

    private static boolean findFishAndGo() {
        // 현재 상어 주변의 '먹을 수 있는' 물고기를 찾아서 우선순위를 따져 물고기를 반환한다.
        // 주변을 탐색할 때 상어의 크기보다 큰 물고기가 있는 구역은 지나가지 못한다.
        // 상어가 한칸을 이동할 때 드는 시간은 1이다.
        Position p = findFish();
        if(p == null)   return false;
        time += visited[p.x][p.y];
        babyShark.changePosition(p.x, p.y);
        babyShark.eatFish();
        map[p.x][p.y] = 0;
        fishCnt--;
        return true;
    }

    private static Position findFish() {
        // BFS 로  찾는다. 시작 지점은 상어.
        Position fish = null;
        int fishDistance = Integer.MAX_VALUE;
        visited = new int[N][N];
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(babyShark.x, babyShark.y));
        visited[babyShark.x][babyShark.y] = 0;
        int nx, ny;
        while(!queue.isEmpty()){
            Position p = queue.poll();
            for(int i = 0; i < 4; i++){
                nx = p.x + dx[i];
                ny = p.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N)  continue;
                if(visited[nx][ny] > 0) continue;   // 이미 방문한 지역
                if(map[nx][ny] > babyShark.size)    continue;   // 상어보다 큰 경우
                queue.add(new Position(nx,ny));
                visited[nx][ny] = visited[p.x][p.y] + 1;
                if(0 < map[nx][ny] && map[nx][ny] < babyShark.size){
                    // 먹을 수 있는 물고기
                    // 현재 fish에 저장된 물고기랑 비교하자.
                    if(fish == null || fishDistance > visited[nx][ny]){
                        // 처음이거나 거리가 더 가까운 물고기거나
                        fish = new Position(nx, ny);
                        fishDistance = visited[nx][ny];
                    } else if(fishDistance == visited[nx][ny] && (nx < fish.x || (nx == fish.x && ny < fish.y))){
                        // 거리가 같은 물고기
                        // 더 위에 있거나 같은 row에 있다면, 더 왼쪽일 경우 갱신
                        fish = new Position(nx, ny);
                        fishDistance = visited[nx][ny];
                    }
                }
            }
        }
        return fish;
    }

    static class Position{
        int x;
        int y;

        Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class BabyShark{
        int x;
        int y;
        int size;
        int fishCnt;

        BabyShark(int x, int y){
            this.x = x;
            this.y = y;
            this.size = 2;
            this.fishCnt = 0;
        }

        public void eatFish(){
            fishCnt++;
            if(fishCnt == size){
                size++;
                fishCnt = 0;
            }
        }

        public void changePosition(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}