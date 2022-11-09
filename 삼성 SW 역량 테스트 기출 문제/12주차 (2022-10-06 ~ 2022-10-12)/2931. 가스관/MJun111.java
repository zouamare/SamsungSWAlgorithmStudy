package com.ssafy.day1012.problem;

import java.io.*;
import java.util.*;

// 출력 행 열 + 1 씩
public class BOJ_2931 {
    static class Point {
        int r, c, dir;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    static int r, c, er, ec, epipe, cnt1, cnt2;   // dir : 0, 1, 2, 3 -> 동, 서, 남, 북
    static char[][] map;
    static Point M, Z;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static boolean flag;
    static char[] pipes = {'|', '1', '2', '3', '4', '-', '+'};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
            	if (map[i][j] != '.') cnt1++;
            	if (map[i][j] == '+') cnt1++;
                if (map[i][j] == 'M') {
                    M = new Point(i, j);
                }
                if (map[i][j] == 'Z') {
                    Z = new Point(i, j);
                }
            }
        }
        
        findSP();

    }

    // 모스크바 사방에서 탐색
    static void findSP() {
        for (int i = 0; i < 4; i++) {
            int nr = M.r + dr[i];
            int nc = M.c + dc[i];
            
            if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue;
            // 파이프가 있다면
            if (map[nr][nc] != '.') {
                int dir = checkDir(i, map[nr][nc]);
                if (dir == -1) continue;
                bfs(new Point(nr, nc, i));
            }
        }

    }

    static void bfs(Point sp) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(sp);

        while (!q.isEmpty()) {
        	cnt2++;
            Point cur = q.poll();
            int dir = cur.dir;
            
            int nextDir = checkDir(dir, map[cur.r][cur.c]);
            if (nextDir == -1) return;
            
            int nr = cur.r + dr[nextDir];
            int nc = cur.c + dc[nextDir];
        	
            if (nr < 0 || nc < 0 || nr >= r || nc >= c) return;
            
            // 가스가 갈 수 없음
            if (map[nr][nc] == '.') {
            	er = nr + 1;
            	ec = nc + 1;
            	for (int i = 0; i < 7; i++) {
            		map[nr][nc] = pipes[i];
            		epipe = i;
            		
            		if (pipes[i] == '+')
            			dfs(nr, nc, nextDir, cnt2);
            		else
            			dfs(nr, nc, nextDir, cnt2 + 1);
            		map[nr][nc] = '.';
            	}
            }
            
            q.add(new Point(nr, nc, nextDir));
        }

    }
    
    static void dfs(int y, int x, int dir, int cnt) {
    	if (map[y][x] == 'Z') {
			if (cnt1 == cnt) {
				System.out.println(er + " " + ec + " " + pipes[epipe]);
				System.exit(0);
			}
    		
    		return;
    	}
    	int nextDir = checkDir(dir, map[y][x]);
    	if (nextDir == -1) return;

    	int nr = y + dr[nextDir];
    	int nc = x + dc[nextDir];

    	if (nr < 0 || nc < 0 || nr >= r || nc >= c) return;
    	
    	dfs(nr, nc, nextDir, cnt + 1);
    }
    
    // dir : 0, 1, 2, 3 -> 동, 서, 남, 북
    static int checkDir(int dir, char pipe) {
        switch(pipe) {
            case '|': if (dir == 2 || dir == 3) return dir; break;
            case '-': if (dir == 0 || dir == 1) return dir; break;
            case '+': return dir;
            case '1': if (dir == 1) return 2; else if (dir == 3) return 0; break;
            case '2': if (dir == 1) return 3; else if (dir == 2) return 0; break;
            case '3': if (dir == 0) return 3; else if (dir == 2) return 1; break;
            case '4': if (dir == 0) return 2; else if (dir == 3) return 1; break;
            default: break;
        }
        return -1;
    }

}