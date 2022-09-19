package coding_test.Algo20220914;

/*
* 백트래킹 문제.
* 비어있는 자리에 들어갈 숫자를 적는다.
* 만약 끝까지 도달하지 못했는데 더 이상 삽입할 숫자가 없다면, 백트래킹
*
* 가로 줄
* 세로 줄
* 3 x 3 구역 모두 확인해야 함
*
* 오래 걸린 이유: 오타 ^_^
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2580_스도쿠 {
    final static int SIZE = 9;
    static int[][] map;
    static ArrayList<Point> seq;
    static int seqSize;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[SIZE][SIZE];
        seq = new ArrayList<>();
        // 스도쿠 입력
        for(int i = 0; i < SIZE; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < SIZE; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)seq.add(new Point(i, j));
            }
        }
        seqSize = seq.size();
        fillMap(0);
    }

    private static void fillMap(int idx) {
        if(idx == seqSize){
            printMap();
            System.exit(0);
        }
        boolean[] visited = getPossibleArr(idx);
        for(int i = 1; i <= SIZE; i++){
            if(!visited[i]){
                map[seq.get(idx).x][seq.get(idx).y] = i;
                fillMap(idx+1);
                map[seq.get(idx).x][seq.get(idx).y] = 0;
            }
        }
    }

    private static boolean[] getPossibleArr(int idx) {  // 가능한 숫자 visited를 반환하는 메소드
        Point p = seq.get(idx);
        boolean[] visited = new boolean[SIZE+1];
        // 가로 찾기
        for(int i = 0; i < SIZE; i++){
            visited[map[p.x][i]] = true;
        }
        // 세로 찾기
        for(int i = 0; i < SIZE; i++){
            visited[map[i][p.y]] = true;
        }
        // 블록 찾기
        int x = ((p.x / 3) * 3);
        int y = ((p.y / 3) * 3);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                visited[map[x+i][y+j]] = true;
            }
        }
        return visited;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                sb.append(map[i][j]);
                if(j != SIZE - 1)   sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
