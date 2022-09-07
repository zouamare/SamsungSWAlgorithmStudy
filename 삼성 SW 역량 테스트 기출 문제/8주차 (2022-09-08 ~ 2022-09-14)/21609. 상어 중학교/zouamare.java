package coding_test.Algo20220907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 이 문제는 다음의 구현 능력을 요구하는 문제이다.
*
* 1. BFS
* 2. 기준에 따라 배열 배치를 변경하는 능력 (이 문제에서는 '중력'을 의미함)
* 3. 배열 회전을 의미함.
*
* [내가 막힌 부분]
* '무지개 블럭은 얼마나 들어있든 상관이 없다.'라는 말의 이해가 부족했다.
* 단순히 무지개 블럭은 어떤 그룹이든 속할 수 있다. => (중복 X) 의 의미로 받아 들였는데, 그게 아니었다.
* 중복이 허용이 된 것이다. 따라서 문제 해석이 잘못 되었다.
* 구현문제는 문제를 꼼꼼히 읽는 습관을 들여야 한다.
*
* [소요 시간]
* 1시간 반 (질문 검색을 통해 해결하여 질문 검색이 없었을 경우 더 걸렸을 수도 있음)
* */

public class BOJ_21609_상어중학교 {
    static int N, M, score;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        init();
        autoPlay();
        printScore();
    }

    private static void printScore() {
        System.out.println(score);
    }

    private static void autoPlay() {
        while(true){
            Group group = makeGroupQueue();
            if(group == null)   break;
            deleteGroup(group);
            startGravity();
            turnMapCCW();
            startGravity();
        }
    }

    private static void deleteGroup(Group group) {
        score += group.list.size() * group.list.size();
        for(Block block : group.list)   map[block.x][block.y] = -2; // 삭제된 블럭임을 의미함
    }

    private static void startGravity() {
        for(int i = 0; i < N; i++){
            for(int j = N -1; j >= 0 ; j--){
                if(map[j][i] >= 0 && isValid(j+1, i) && map[j+1][i] == -2){
                    int x = j + 1;
                    while(isValid(x+1, i) && map[x+1][i] == -2) x++;
                    map[x][i] = map[j][i];
                    map[j][i] = -2; // 삭제된 블럭임
                }
            }
        }
    }

    private static void turnMapCCW() {
        int[][] newMap = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                newMap[i][j] = map[j][N-1-i];
            }
        }
        map = newMap;
    }

    private static Group makeGroupQueue() {
        PriorityQueue<Group> groups = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j] || map[i][j] <= 0)   continue;
                /*          BFS 시작          */
                ArrayList<Block> blockList = new ArrayList<>();
                ArrayList<Block> rainbowList = new ArrayList<>();
                Queue<Block> blocks = new ArrayDeque<>();
                int blockColor = map[i][j];
                blocks.add(new Block(i, j));
                visited[i][j] = true;
                int size = 0;
                int rainbowBlockCnt = 0;
                while(!blocks.isEmpty()){
                    Block block = blocks.poll();
                    blockList.add(block);
                    if(map[block.x][block.y] == 0){
                        rainbowBlockCnt++;
                        rainbowList.add(block);
                    }
                    size++;
                    for(int d = 0; d < 4; d++){
                        int nx = block.x + dx[d];
                        int ny = block.y + dy[d];
                        if(!isValid(nx, ny) || visited[nx][ny] || !(map[nx][ny] == 0 || map[nx][ny] == blockColor))  continue;
                        blocks.add(new Block(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                for(Block block : rainbowList)  visited[block.x][block.y] = false;
                /*          BFS 종료          */
                if(size > 1){
                    groups.add(new Group(i, j, size, rainbowBlockCnt, blockList)); // groups에 삽입
                }
            }
        }
        return groups.poll();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        score = 0;
    }

    private static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Block{
        int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Group implements Comparable<Group>{
        int main_x, main_y, size, rainbowBlockCnt;
        ArrayList<Block> list;

        public Group(int main_x, int main_y, int size, int rainbowBlockCnt, ArrayList<Block> list) {
            this.main_x = main_x;
            this.main_y = main_y;
            this.size = size;
            this.rainbowBlockCnt = rainbowBlockCnt;
            this.list = list;
        }

        @Override
        public int compareTo(Group o) {
            if(o.size == this.size){
                if(o.rainbowBlockCnt == this.rainbowBlockCnt){
                    if(o.main_x == this.main_x) return o.main_y - this.main_y;  // (우선순위 4) 행이 같으면 열이 큰 순서
                    return o.main_x - this.main_x;  // (우선순위 3) 행이 큰 순서
                }
                return o.rainbowBlockCnt - this.rainbowBlockCnt;    // (우선순위 2) 무지개 블록이 많은 순서
            }
            return o.size - this.size;  // (우선순위 1) 크기가 큰 순서
        }
    }
}
