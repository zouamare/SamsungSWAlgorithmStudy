package coding_test.Algo20220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
*   '물고기가 없는 곳에는 갈 수 없다.' 라는 말을
*   '물고기가 없다면 탐색을 할 수 없다.' 라고 이해해서 그 부분의 로직 구현에서 문제가 있었다.
*   문제를 잘 읽자!!!!
* */


public class BOJ_19236_청소년상어 {
    static int maxVal;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};  // 상 상좌 좌 하좌 하 하우 우 상우
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 4 X 4 크기의 공간이 주어지고 그 안에 상어들이 주어 짐.
        Fish[][] map = new Fish[4][4];
        int[][] fishes = new int[17][2];    // 물고기 위치 저장
        // 입력을 받아서 공간을 채운다.
        for(int i = 0; i < 4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = new Fish(num, dir);
                fishes[num][0] = i;
                fishes[num][1] = j;
            }
        }
        maxVal = Integer.MIN_VALUE;

        // 사냥 시작
        // 초기 세팅
        // 상어 설정
        Shark teenagerShark = new Shark(0,0,0,0);
        teenagerShark.eatFish(map, fishes, 0,0);
        startHunting(map, teenagerShark, fishes);
        System.out.println(maxVal);
    }

    private static void startHunting(Fish[][] map, Shark teenagerShark, int[][] fishes) {
        if(teenagerShark.num > maxVal)  maxVal = teenagerShark.num;
        // 물고기가 차례로 이동한다.
        for(int i = 1; i <= 16; i++){
            if(fishes[i][0] == -1 && fishes[i][1] == -1)    continue;   // 상어가 죽은 경우, 좌표는 (-1, -1)
            int x = fishes[i][0], y = fishes[i][1];
            Fish fish = map[x][y];
            boolean flag = false;   // 물고기가 움직일 수 있는지
            for(int j = 0; j < 8; j++){ // 8방 탐색
                int nx = x + dx[fish.dir];
                int ny = y + dy[fish.dir];
                if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (nx == teenagerShark.x && ny == teenagerShark.y)) {
                    // 공간을 넘어서거나, 상어의 자리인 경우
                    fish.dir = (fish.dir+1) % 8;
                    continue;
                }
                // 옮길 수 있는 자리!
                flag = true;
                if(map[nx][ny] == null){    // 옮기는 위치가 null인 경우
                    fishes[i][0] = nx;
                    fishes[i][1] = ny;
                    map[nx][ny] = map[x][y];
                    map[x][y] = null;
                }else{  // 옮기는 위치에 또 다른 fish가 있는 경우!
                    // 해당 자리에 존재하는 물고기를 먼저 옮긴다.
                    Fish anotherFish = map[nx][ny];
                    fishes[anotherFish.num][0] = x;
                    fishes[anotherFish.num][1] = y;
                    map[x][y] = anotherFish;
                    // 물고기를 옮겨야할 곳에 옮긴다.
                    fishes[i][0] = nx;
                    fishes[i][1] = ny;
                    map[nx][ny] = fish;
                }
                break;
            }
            if(!flag)   fish.dir = (fish.dir+1) % 8;    // 원상태 방향으로 돌아옴.
        }
        // 그 다음 상어가 이동한다. 각 경우가 몇개인지에 따라서.. 호출을 여러번 하자.
        int nx = teenagerShark.x + dx[teenagerShark.dir];
        int ny = teenagerShark.y + dy[teenagerShark.dir];
        while(!(nx < 0 || nx >= 4 || ny < 0 || ny >= 4)){    // 상어가 이동할 칸이 없으면 집으로 간다.
            if(map[nx][ny] != null){
                // 갈 수 있다!
                // 그럼 물고기를 먹어야지.
                // 물고기를 먹는다.
                Fish[][] newMap = deepMapCopy(map);    // map 복사
                Shark newTeenagerShark = new Shark(teenagerShark.x, teenagerShark.y, teenagerShark.num, teenagerShark.dir); // shark 복사
                int[][] newFishes = deepArrCopy(fishes);    // fishes 복사
                // 복사한 거에서 먹는 것을 진행한다. 먹을 위치는 nx, ny!!
                newTeenagerShark.eatFish(newMap, newFishes, nx, ny);
                startHunting(newMap, newTeenagerShark, newFishes);
            }

            nx = nx + dx[teenagerShark.dir];    // 더 가본다.
            ny = ny + dy[teenagerShark.dir];    // 더 가본다.
        }

    }

    private static int[][] deepArrCopy(int[][] fishes) {
        int[][] newFishes = new int[17][2];
        for(int i = 1; i <= 16; i++){
            newFishes[i][0] = fishes[i][0];
            newFishes[i][1] = fishes[i][1];
        }
        return newFishes;
    }

    private static Fish[][] deepMapCopy(Fish[][] map) {
        Fish[][] newMap = new Fish[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(map[i][j] == null) continue;
                Fish fish = map[i][j];
                newMap[i][j] = new Fish(fish.num, fish.dir);
            }
        }
        return newMap;
    }

    static class Fish{
        int num, dir;

        public Fish(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }

    static class Shark extends Fish{
        int x, y;

        public Shark(int x, int y, int num, int dir) {
            super(num, dir);
            this.x = x;
            this.y = y;
        }

        public void eatFish(Fish[][] map, int[][] fishes, int x, int y){
            int val = map[x][y].num;
            this.x = x;
            this.y = y;
            this.num += val;
            this.dir = map[x][y].dir;
            fishes[val][0] = -1;    // 상어는 이제 죽었다.
            fishes[val][1] = -1;    // 상어는 이제 죽었다.
            map[x][y] = null;   // 상어 먹음.
        }
    }
}
