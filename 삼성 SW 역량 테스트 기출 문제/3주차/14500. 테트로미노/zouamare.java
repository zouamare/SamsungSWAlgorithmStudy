package _Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int maxSum = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j])	continue;
                visited[i][j] = true;
                findMaxSum(i, j, map[i][j], 1);
            }
        }
		
        System.out.println(maxSum);
	}
	

    private static void findMaxSum(int x, int y, int sum, int cnt) {
    	if(cnt == 4) {
    		maxSum = Math.max(maxSum, sum);
    		return;
    	}
    	for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])  continue;
            visited[nx][ny] = true;
            if(cnt == 1) {
                int tx = nx, ty = ny;
                // left
            	tx = nx + dx[(d+1)%4];
                ty = ny + dy[(d+1)%4];
                if(!(tx < 0 || tx >= N || ty < 0 || ty >= M) && !visited[tx][ty]) {
                    visited[tx][ty] = true;
                    findMaxSum(nx, ny, sum + map[nx][ny] + map[tx][ty], cnt+2);
                    visited[tx][ty] = false;
                }
                // right
            	tx = nx + dx[(d-1+4)%4];
                ty = ny + dy[(d-1+4)%4];
                if(!(tx < 0 || tx >= N || ty < 0 || ty >= M) && !visited[tx][ty]) {
                    visited[tx][ty] = true;
                    findMaxSum(nx, ny, sum + map[nx][ny] + map[tx][ty], cnt+2);
                    visited[tx][ty] = false;
                }
            	tx = x + dx[(d+1)%4];
                ty = y + dy[(d+1)%4];
                if(!(tx < 0 || tx >= N || ty < 0 || ty >= M) && !visited[tx][ty]) {
                    visited[tx][ty] = true;
                    findMaxSum(nx, ny, sum + map[nx][ny] + map[tx][ty], cnt+2);
                    visited[tx][ty] = false;
                }
                // right
            	tx = x + dx[(d-1+4)%4];
                ty = y + dy[(d-1+4)%4];
                if(!(tx < 0 || tx >= N || ty < 0 || ty >= M) && !visited[tx][ty]) {
                    visited[tx][ty] = true;
                    findMaxSum(nx, ny, sum + map[nx][ny] + map[tx][ty], cnt+2);
                    visited[tx][ty] = false;
                }
            }
            findMaxSum(nx, ny, sum + map[nx][ny], cnt+1);
            visited[nx][ny] = false;
        }
    }
}
