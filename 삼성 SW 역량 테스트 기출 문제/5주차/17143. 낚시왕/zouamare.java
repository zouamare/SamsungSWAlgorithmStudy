import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static Shark[][] map;
    static Fisherman fisherman;
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        init();
        letsFishing();
        printTotalFishSize();
    }

    private static void printTotalFishSize() {
        System.out.println(fisherman.getTotalSize());
    }

    private static void letsFishing() {
        fisherman = new Fisherman();
        while(true){
            // 낚시왕이 오른쪽으로 한 칸 이동한다.
            fisherman.move();
            if(fisherman.isFinish())    break;
            // 낚시왕이 상어를 잡는다.
            fisherman.catchShark(findShark());
            // 상어가 이동한다.
            sharkMove();
        }
    }

    private static int findShark() {
        int size = 0;
        for(int row = 1, col = fisherman.getCol(); row <= R; row++){
            if(map[row][col] != null){
                size = map[row][col].getZ();
                map[row][col] = null;
                break;
            }
        }
        return size;
    }

    private static void sharkMove() {
        Shark[][] newMap = new Shark[R+1][C+1];
        for(int row = 1; row <= R; row++){
            for(int col = 1; col <= C; col++){
                if(map[row][col] != null){
                    // moving
                    Shark shark = map[row][col];
                    int tr = shark.getR(), tc = shark.getC(), speed = shark.getS(), d = shark.getD();
                    switch (d){
                        case 1: case 2:
                            tr = shark.getR();   // R만 바뀜
                            if(speed > (R-1)*2) speed %= (R-1)*2;
                            while(speed-- > 0){
                                if(tr == R) d = 1;
                                else if(tr == 1) d = 2;
                                tr = tr + dr[d];
                            }
                            break;
                        case 3: case 4:
                            tc = shark.getC();   // C만 바뀜
                            if(speed > (C-1)*2) speed %= (C-1)*2;
                            while(speed-- > 0){
                                if(tc == C) d = 4;
                                else if(tc == 1) d = 3;
                                tc = tc + dc[d];
                            }
                            break;
                    }
                    if(newMap[tr][tc] == null || newMap[tr][tc].getZ() < shark.getZ())
                        newMap[tr][tc] = new Shark(tr, tc, shark.getS(), d, shark.getZ());
                }
            }
        }
        // 변경된 내용을 재할당
        map = newMap;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new Shark[R+1][C+1];
        int r, c, s, d, z;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            map[r][c] = new Shark(r, c, s, d, z);
        }
    }


    static class Shark{
        private int r, c, s, d, z;

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public int getS() {
            return s;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getZ() {
            return z;
        }

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static class Fisherman{
        private int col;
        private int totalSize;

        public Fisherman() {
            this.col = 0;
            this.totalSize = 0;
        }

        public int getCol() {
            return col;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void move(){
            this.col++;
        }

        public boolean isFinish(){
            return col == C + 1;
        }

        public void catchShark(int size){
            this.totalSize += size;
        }
    }
}