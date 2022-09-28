package com.ssafy.recur;

import java.util.*;
import java.io.*;

public class BOJ_3190_뱀 {
	static int N;
	static int K;
	static int L;
	
	static int[][] map;
	
	static Queue<Change> queue;
	static Queue<Point> snake;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		snake = new LinkedList<>();
		queue = new LinkedList<>();
		map[0][0] = 1;
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			String[] temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]) - 1;
			int y = Integer.parseInt(temp[1]) - 1;
			
			map[x][y] = -1;
		}
		
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; i++) {
			String[] temp = br.readLine().split(" ");
			queue.offer(new Change(Integer.parseInt(temp[0]), temp[1]));
		}
		
		
		int x = 0;
		int y = 0;
		int time = 0;
		snake.offer(new Point(x, y));
		
		int rearX = 0;
		int rearY = 0;
		
		int idx = 1;
		while(true) {	
			if(idx == 0) idx = 4;
			else if(idx == 5) idx = 1;
			switch(idx) {
			case 1:
				y++;
				break;
			case 2:
				x++;
				break;
			case 3:
				y--;
				break;
			case 4:
				x--;
				break;
			}
			time++;
			
			if(x < N && y < N && x >=0 && y >= 0) {
				if(map[x][y] == 1) break;
				
				if(map[x][y] == -1) {
					map[x][y] = 1;
					snake.add(new Point(x,y));
				}else {
					map[x][y] = 1;
					map[rearX][rearY] = 0;
					snake.poll();
					snake.offer(new Point(x, y));
					
					if(snake.size() > 1) {
						rearX = snake.peek().x;
						rearY = snake.peek().y;
					}else {
						rearX = x;
						rearY = y;
					}
				}
				if(!queue.isEmpty()) {
					if(queue.peek().sec == time) {
						String move = queue.poll().dir;
						if(move.equals("D")) {
							idx++;
							continue;
						}
						else {
							idx--;
							
							continue;
						}
					}
				}
			}else {
				break;
			}
			
		}
		
		System.out.println(time);
		
	}
	

}

class Point{
	int x;
	int y;
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Change{
	int sec;
	String dir;
	
	Change(int sec, String dir){
		this.sec = sec;
		this.dir = dir;
	}
}