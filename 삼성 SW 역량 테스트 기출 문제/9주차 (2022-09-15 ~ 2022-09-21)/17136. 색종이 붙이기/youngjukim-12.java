package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N17136 {
	static int map[][]  = new int[10][10];
	static int colorPaper[]= {0,5,5,5,5,5};
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0;i<10;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<10;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0,0);
		
		if(ans==Integer.MAX_VALUE) ans=-1;
		
		System.out.println(ans);
	}
	
	public static void dfs(int x, int y, int cnt) {
		if(x>=9 && y>9) {
			ans = Math.min(ans,cnt);
			return;
		}
		
		if(ans<=cnt) return;
		
		if(y>9) {
			dfs(x+1,0,cnt);
			return;
		}
		
		if(map[x][y]==1) {
			for(int i=5;i>=1;i--) {
				if(colorPaper[i]>0 && check(x,y,i)) {
					attach(x,y,i,0);
					colorPaper[i]--;
					dfs(x,y+1,cnt+1);
					attach(x,y,i,1);
					colorPaper[i]++;
				}
			}
		}else {
			dfs(x,y+1,cnt);
		}
	}
	public static void attach(int x, int y, int length, int paper) {
		for(int i=x;i<x+length;i++) {
			for(int j=y;j<y+length;j++) {
				map[i][j]=paper;
			}
		}
	}
	public static boolean check(int x, int y, int length) {
		for(int i=x;i<x+length;i++) {
			for(int j=y;j<y+length;j++) {
				if(i<0 || i>=10 || j<0 || j>=10) return false;
				if(map[i][j]!=1) return false;
			}
		}
		return true;
	}
}
