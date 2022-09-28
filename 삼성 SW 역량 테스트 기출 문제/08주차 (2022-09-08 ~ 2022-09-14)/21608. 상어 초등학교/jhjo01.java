package BOJ;

import java.util.PriorityQueue;
import java.util.Scanner;
/*
    접근 방법 및 풀이 로직
        map을 돌면서 각 위치마다 사방탐색 후
        사방에 좋아하는 사람이 많은 자리 > 사방에 빈칸이 많은 자리 > 행이 빠른 자리 > 열이 빠른 자리
        순서로 우선순위를 두고 앉을 자리를 정했음
        처음엔 우선순위 큐를 쓸라고 했는데 생각해보니 안써도 될거같아서 안썼음

         문제 풀이 중 주변에 좋아하는 사람도 없고 빈칸도 없을 상황을 고려를 안해서 문제가 발생했었음
         30분동안 별짓 다하다가 row, column 초기값 max값으로 바꾸니까 해결됨..ㅎㅎ 바꾸기 전엔 0이었음.
*/
public class BOJ_21608_상어초등학교 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] stuLsit = new int[N*N][5]; // 각 학생의 좋아하는 사람 목록
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int satisfaction = 0; // 만족도 취합

        // 각 학생의 좋아하는 사람 목록 입력
        for(int r = 0; r < N*N; r++) {
            for(int c = 0; c < 5; c++) {
                stuLsit[r][c] = sc.nextInt();
            }
        }

        // PriorityQueue 사용시 compare 조건
        PriorityQueue<int[]> pq = new PriorityQueue<>( (o1, o2) -> {
            if(o1[2] == o2[2]) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
            return  o2[2] - o1[2];
        });

        // 학생수만큼 반복
        for(int i = 0; i < N*N; i++) {
            int row = 21;   // 앉을 행
            int column = 21;    // 앉을 열
            int sCnt = 0;   // 좋아하는 사람 카운트
            int eCnt = 0;   // 빈칸 카운트

            // 맵 전체 돌면서 앉을 자리 찾기
            for(int r = 0; r < N; r++) {
                 for(int c = 0; c < N; c++) {
                    if(visited[r][c]) continue;
                    int temp_sCnt = 0; // student Cnt
                    int temp_eCnt = 0; // empty Cnt
                    for(int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                        // 사방에 좋아하는 사람이 있다면 카운트
                        for(int j = 1; j < 5; j++) {
                            if(map[nr][nc] == stuLsit[i][j]) temp_sCnt++;
                        }
                        // 사방에 빈칸이 있다면 카운트
                        if(!visited[nr][nc]) temp_eCnt++;
                    }
                    
                    // 앉을 자리 비교
                    if(temp_sCnt > sCnt) {
                        row = r;
                        column = c;
                        sCnt = temp_sCnt;
                        eCnt = temp_eCnt;
                        continue;
                    }
                    if(temp_sCnt == sCnt) {
                        if(temp_eCnt > eCnt) {
                            row = r;
                            column = c;
                            sCnt = temp_sCnt;
                            eCnt = temp_eCnt;
                            continue;
                        }
                        if(temp_eCnt == eCnt) {
                            if(r < row) {
                                row = r;
                                column = c;
                                sCnt = temp_sCnt;
                                eCnt = temp_eCnt;
                                continue;
                            }
                            if (r == row) {
                                if (c <= column) {
                                    row = r;
                                    column = c;
                                    sCnt = temp_sCnt;
                                    eCnt = temp_eCnt;
                                }
                            }
                        }
                    }
                }
            }
            
            // 앉을 자리에 학생 번호 넣고 방문처리
            map[row][column] = stuLsit[i][0];
            visited[row][column] = true;
        }

        // 만족도 조사
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                int currIdx = 0;
                for(int i = 0; i < N*N; i++) {
                    if(map[r][c] == stuLsit[i][0]) {
                        currIdx = i;
                        break;
                    }
                }
                int cnt = 0;
                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    for(int j = 1; j < 5; j++) {
                        if(stuLsit[currIdx][j] == map[nr][nc]) cnt++;
                    }
                }
                switch (cnt) {
                    case 1:
                        satisfaction += 1;
                        break;
                    case 2:
                        satisfaction += 10;
                        break;
                    case 3:
                        satisfaction += 100;
                        break;
                    case 4:
                        satisfaction += 1000;
                        break;
                }
            }
        }
        System.out.println(satisfaction);
    }
}
