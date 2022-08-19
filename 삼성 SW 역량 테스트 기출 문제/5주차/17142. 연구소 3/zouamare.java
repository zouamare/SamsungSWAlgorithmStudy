import java.io.*;
import java.util.*;

public class Main {
    static int N, M, virusCnt, minSecond, blankCnt;
    static boolean flag = false;
    static int[][] map;
    static ArrayList<Virus> virusList;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virusList = new ArrayList<>();
        blankCnt = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    blankCnt++;
                }
                else if(map[i][j] == 2){
                    virusList.add(new Virus(i, j));
                }
            }
        }

        virusCnt = virusList.size();
        minSecond = Integer.MAX_VALUE;
        // 조합으로 퍼트릴 바이러스를 고른다.
        selectVirusToSpread(new int[M], new boolean[virusCnt], 0, 0);
        System.out.println((flag?minSecond:-1));
    }

    private static void selectVirusToSpread(int[] output, boolean[] visited, int start, int depth) {
        if(depth == M){
            // 바이러스를 모두 골랐으므로 바이러스를 퍼트려본다.
            spreadVirus(output);
            return;
        }
        for(int i = start; i < virusCnt; i++){
            if(visited[i])  continue;
            visited[i] = true;
            output[depth] = i;
            selectVirusToSpread(output, visited, i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private static void spreadVirus(int[] output) {
        int[][] visited = new int[N][N];
        int leftBlanks = blankCnt;  // 0의 개수
        Queue<Virus> virusQ = new LinkedList<>();
        for(int i = 0; i < M; i++){
            Virus virus = virusList.get(output[i]);
            virusQ.add(virus);
            visited[virus.x][virus.y] = 1;
        }
        int completeTime = 0;
        while (!virusQ.isEmpty()){  // 바이러스를 퍼트리는 과정 (BFS)
            Virus virus = virusQ.poll();
            for(int d = 0; d < 4; d++){
                int nx = virus.x + dx[d];
                int ny = virus.y + dy[d];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] != 0 || map[nx][ny] == 1)   continue;
                visited[nx][ny] = visited[virus.x][virus.y] + 1;
                // 비활성 바이러스 처리 주의!! (비활성 바이러스가 바이러스 확산 경로를 막을 수 있다.)
                if(map[nx][ny] == 0){
                    if(visited[nx][ny] > completeTime)  completeTime = visited[nx][ny] - 1;
                    leftBlanks--;
                }
                virusQ.add(new Virus(nx, ny));
            }
        }
        if(leftBlanks == 0){    // 바이러스가 모두 퍼졌을 경우
            flag = true;
            minSecond = Math.min(minSecond, completeTime);
        }
    }


    static class Virus{
        int x;
        int y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}