package day20221012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_1938_통나무옮기기 {
	   static int N;
	    static char[][] map;
	    static Point[] targetLoc;
	    static int[] dx = {-1, 1, 0, 0};
	    static int[] dy = {0, 0, -1, 1};
	    public static void main(String[] args) throws IOException {
	        System.out.println(BFS(init()));
	    }

	    private static int BFS(Point[] presentLoc) {
	        boolean[][][] visited = new boolean[N][N][2];
	        Queue<PointData> queue = new ArrayDeque<>();    // 들어가는 Point는 중심점의 좌표
	        int status = 0;
	        if(presentLoc[0].y == presentLoc[1].y)  status = 1;
	        queue.add(new PointData(new Point(presentLoc[1].x, presentLoc[1].y), 0, status));
	        visited[presentLoc[1].x][presentLoc[1].y][status] = true;

	        while(!queue.isEmpty()){
	            PointData loc = queue.poll();

	            if(isArrived(loc.middle, loc.status))  return loc.cnt;

	            // U D L R 처리
	            for (int d = 0; d < 4; d++) {
	                int nx = loc.middle.x + dx[d];
	                int ny = loc.middle.y + dy[d];
	                if(isVaildLog(nx, ny, loc.status) && !visited[nx][ny][loc.status]){
	                    visited[nx][ny][loc.status] = true;
	                    queue.add(new PointData(new Point(nx, ny), loc.cnt + 1, loc.status));
	                }
	            }
	            // turn 처리
	            if(isValidToTurn(loc.middle, loc.status) && !visited[loc.middle.x][loc.middle.y][(loc.status == 0 ? 1 : 0)]){
	                queue.add(new PointData(loc.middle, loc.cnt + 1, (loc.status == 0 ? 1 : 0)));
	            }
	        }

	        return 0;
	    }

	    private static boolean isVaildLog(int x, int y, int status) {
	        if(status == 0) {
	        	for (int i = -1; i <= 1; i++) {
	        		int nx = x;
		            int ny = y + i;
		            if(!isVaild(nx, ny) || map[nx][ny] == '1')    return false;
				}
	        }
	        else {
	        	for (int i = -1; i <= 1; i++) {
	        		int nx = x + i;
		            int ny = y;
		            if(!isVaild(nx, ny) || map[nx][ny] == '1')    return false;
				}
	        }
            
	        return true;
	    }

	    private static boolean isArrived(Point middle, int status) {
	    	if(status == 0 && middle.x == targetLoc[1].x && middle.y == targetLoc[1].y) {
	    		if((targetLoc[0].x == middle.x && targetLoc[0].y == (middle.y - 1)) && (targetLoc[2].x == middle.x && targetLoc[2].y == (middle.y + 1)))	return true;
	    	}
	    	else if(status == 1 && middle.x == targetLoc[1].x && middle.y == targetLoc[1].y){
	    		if((targetLoc[0].x == (middle.x - 1) && targetLoc[0].y == middle.y) && (targetLoc[2].x == (middle.x + 1) && targetLoc[2].y == middle.y))	return true;
	    	}
	        return false;
	    }

	    private static boolean isValidToTurn(Point middle, int status) {
	        if(status == 0){   // 가로로 되어 있다면,
	            for (int i = -1; i <= 1; i++) {
	                for (int j = -1; j <= 1; j++) {
	                	if(i == 0 && j == 0)	continue;
	                	int nx = middle.x - i;
	                	int ny = middle.y - j;
	                    if(!isVaild(nx, ny) || map[nx][ny] == '1')   return false;
	                }
	            }
	        }
	        else{
	            for (int i = -1; i <= 1; i++) {
	                for (int j = -1; j <= 1; j++) {
	                	if(i == 0 && j == 0)	continue;
	                	int nx = middle.x - i;
	                	int ny = middle.y - j;
	                    if(!isVaild(nx, ny) || map[nx][ny] == '1')   return false;
	                }
	            }
	        }

	        return true;
	    }


	    private static Point[] init() throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        N = Integer.parseInt(br.readLine());
	        map = new char[N][N];
	        Point[] presentLoc = new Point[3];
	        targetLoc = new Point[3];
	        int idxP = 0, idxT = 0;
	        for (int i = 0; i < N; i++) {
	            char[] arr = br.readLine().toCharArray();
	            for (int j = 0; j < N; j++) {
	                map[i][j] = arr[j];
	                if(map[i][j] == 'B'){
	                    presentLoc[idxP++] = new Point(i, j);
	                    map[i][j] = '0';
	                }
	                else if(map[i][j] == 'E'){
	                    targetLoc[idxT++] = new Point(i, j);
	                    map[i][j] = '0';
	                }
	            }
	        }
	        return presentLoc;
	    }

	    private static boolean isVaild(int x, int y){
	        return x >= 0 && x < N && y >= 0 && y < N;
	    }

	    static class PointData{
	        Point middle;
	        int cnt, status;    // status 0 : 가로, 1 : 세로

	        public PointData(Point middle, int cnt, int status) {
	            this.middle = middle;
	            this.cnt = cnt;
	            this.status = status;
	        }
	    }

	    static class Point{
	        int x, y;

	        public Point(int x, int y) {
	            this.x = x;
	            this.y = y;
	        }
	    }
}
