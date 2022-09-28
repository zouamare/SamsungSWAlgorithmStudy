import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static final int MAX_CNT = 10;
    static int minCnt = MAX_CNT + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String[][] map = new String[N][];
        for(int i = 0; i < N; i++){
            map[i] = br.readLine().split("");
        }

        ballEscape(map);
        printResult();
    }

    private static void printResult() {
        System.out.println((minCnt==MAX_CNT+1? -1 : minCnt));
    }

    private static void ballEscape(String[][] map) {
        moveLeft(1, deepCopy(map));
        moveRight(1, deepCopy(map));
        moveUp(1, deepCopy(map));
        moveDown(1, deepCopy(map));
    }
    private static String[][] deepCopy(String[][] map) {
        String[][] newMap = new String[N][];
        for(int i = 0; i < N; i++){
            newMap[i] = map[i].clone();
        }
        return newMap;
    }

    private static void moveLeft(int cnt, String[][] map) {
        if(cnt > MAX_CNT){
            return;
        }
        // 왼쪽으로 기울인다.
        boolean flagR = false;
        boolean changedR = false;
        boolean flagB = false;
        boolean changedB = false;
        for(int i = 1; i < N - 1; i++){
            for(int j = 1; j < M - 1; j++){
                if(map[i][j].equals("R")){
                    int x = i, y = j;
                    while(!map[x][y-1].equals("#") && !map[x][y-1].equals("B")){
                        if(map[x][y-1].equals("O")) flagR = true;
                        y--;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagR){
                            map[x][y] = "R";
                        }
                        changedR = true;
                    }
                }else if(map[i][j].equals("B")){
                    int x = i, y = j;
                    while(!map[x][y-1].equals("#") && !map[x][y-1].equals("R")){
                        if(map[x][y-1].equals("O")) flagB = true;
                        y--;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagB){
                            map[x][y] = "B";
                        }
                        changedB = true;
                    }
                }
            }
        }
        // 만약에 통과하면 바로 리턴.
        if(flagR && !flagB){
            // 빨간 공은 통과하고 파란 공은 탈출 못할 때
            minCnt = Math.min(minCnt, cnt);
            return;
        }
        else if(flagR || flagB){    // 파랑만 통과하거나 둘 다 통과 했을 때는 실패!
            return;
        }
        if(!changedR && !changedB){ // 변경이 없으면 끝낸다.
            return;
        }
        // 통과하지 않았다면 다시 호출한다.
        moveLeft(cnt+1, deepCopy(map));
        moveRight(cnt+1, deepCopy(map));
        moveUp(cnt+1, deepCopy(map));
        moveDown(cnt+1, deepCopy(map));
    }

    private static void moveRight(int cnt, String[][] map) {
        if(cnt > MAX_CNT){
            return;
        }
        // 오른쪽으로 기울인다.
        boolean flagR = false;
        boolean changedR = false;
        boolean flagB = false;
        boolean changedB = false;
        for(int i = 1; i < N - 1; i++){
            for(int j = M - 2; j > 0; j--){
                if(map[i][j].equals("R")){
                    int x = i, y = j;
                    while(!map[x][y+1].equals("#") && !map[x][y+1].equals("B")){
                        if(map[x][y+1].equals("O")) flagR = true;
                        y++;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagR){
                            map[x][y] = "R";
                        }
                        changedR = true;
                    }
                }else if(map[i][j].equals("B")){
                    int x = i, y = j;
                    while(!map[x][y+1].equals("#") && !map[x][y+1].equals("R")){
                        if(map[x][y+1].equals("O")) flagB = true;
                        y++;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagB){
                            map[x][y] = "B";
                        }
                        changedB = true;
                    }
                }
            }
        }
        // 만약에 통과하면 바로 리턴.
        if(flagR && !flagB){
            // 빨간 공은 통과하고 파란 공은 탈출 못할 때
            minCnt = Math.min(minCnt, cnt);
            return;
        }else if(flagR || flagB){   // 파랑만 통과하거나 둘 다 통과 했을 때는 실패!
            return;
        }
        if(!changedR && !changedB){ // 변경이 없으면 끝낸다.
            return;
        }
        // 만약에 통과하면 바로 리턴.
        // 통과하지 않았다면 다시 호출한다.
        moveLeft(cnt+1, deepCopy(map));
        moveRight(cnt+1, deepCopy(map));
        moveUp(cnt+1, deepCopy(map));
        moveDown(cnt+1, deepCopy(map));
    }

    private static void moveUp(int cnt, String[][] map) {
        if(cnt > MAX_CNT){
            return;
        }
        // 위쪽으로 기울인다.
        boolean flagR = false;
        boolean changedR = false;
        boolean flagB = false;
        boolean changedB = false;
        for(int i = 1; i < N - 1; i++){
            for(int j = 1; j < M - 1; j++){
                if(map[i][j].equals("R")){
                    int x = i, y = j;
                    while(!map[x-1][y].equals("#") && !map[x-1][y].equals("B")){
                        if(map[x-1][y].equals("O")) flagR = true;
                        x--;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagR){
                            map[x][y] = "R";
                        }
                        changedR = true;
                    }
                }else if(map[i][j].equals("B")){
                    int x = i, y = j;
                    while(!map[x-1][y].equals("#") && !map[x-1][y].equals("R")){
                        if(map[x-1][y].equals("O")) flagB = true;
                        x--;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagB){
                            map[x][y] = "B";
                        }
                        changedB = true;
                    }
                }
            }
        }
        // 만약에 통과하면 바로 리턴.
        if(flagR && !flagB){
            // 빨간 공은 통과하고 파란 공은 탈출 못할 때
            minCnt = Math.min(minCnt, cnt);
            return;
        }
        else if(flagR || flagB){    // 파랑만 통과하거나 둘 다 통과 했을 때는 실패!
            return;
        }
        if(!changedR && !changedB){ // 변경이 없으면 끝낸다.
            return;
        }
        // 만약에 통과하면 바로 리턴.
        // 통과하지 않았다면 다시 호출한다.
        moveLeft(cnt+1, deepCopy(map));
        moveRight(cnt+1, deepCopy(map));
        moveUp(cnt+1, deepCopy(map));
        moveDown(cnt+1, deepCopy(map));
    }

    private static void moveDown(int cnt, String[][] map) {
        if(cnt > MAX_CNT){
            return;
        }
        // 아래쪽으로 기울인다.
        boolean flagR = false;
        boolean changedR = false;
        boolean flagB = false;
        boolean changedB = false;
        for(int i = N - 2; i > 0; i--){
            for(int j = 1; j < M - 1; j++){
                if(map[i][j].equals("R")){
                    int x = i, y = j;
                    while(!map[x+1][y].equals("#") && !map[x+1][y].equals("B")){
                        if(map[x+1][y].equals("O")) flagR = true;
                        x++;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagR){
                            map[x][y] = "R";
                        }
                        changedR = true;
                    }
                }else if(map[i][j].equals("B")){
                    int x = i, y = j;
                    while(!map[x+1][y].equals("#") && !map[x+1][y].equals("R")){
                        if(map[x+1][y].equals("O")) flagB = true;
                        x++;
                    }
                    if(i!=x || j!=y){
                        map[i][j] = ".";
                        if(!flagB){
                            map[x][y] = "B";
                        }
                        changedB = true;
                    }
                }
            }
        }
        // 만약에 통과하면 바로 리턴.
        if(flagR && !flagB){
            // 빨간 공은 통과하고 파란 공은 탈출 못할 때
            minCnt = Math.min(minCnt, cnt);
            return;
        }
        else if(flagR || flagB){    // 파랑만 통과하거나 둘 다 통과 했을 때는 실패!
            return;
        }
        if(!changedR && !changedB){ // 변경이 없으면 끝낸다.
            return;
        }
        // 만약에 통과하면 바로 리턴.
        // 통과하지 않았다면 다시 호출한다.
        moveLeft(cnt+1, deepCopy(map));
        moveRight(cnt+1, deepCopy(map));
        moveUp(cnt+1, deepCopy(map));
        moveDown(cnt+1, deepCopy(map));
    }
}