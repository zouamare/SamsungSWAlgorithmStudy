package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N21608 {
	static int N,ans;
	static int map[][];
	static int like[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int score[] = {0,1,10,100,1000};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		like = new int[N*N+1][4];
		
		for(int i=0;i<N*N;i++) {
			st= new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j=0;j<4;j++) {
				like[num][j] = Integer.parseInt(st.nextToken());
			}
			
			int X =0;
			int Y =0;
			int likeMax=Integer.MIN_VALUE;
			int emptyMax = Integer.MIN_VALUE;
			
			for(int j=1;j<=N;j++) {
				for(int k=1;k<=N;k++) {
					if(map[j][k]==0) {
						int likeCnt=0;
						int emptyCnt=0;
						for(int d=0;d<4;d++) {
							int nx = j+dx[d];
							int ny = k+dy[d];
							if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
								if(Like(nx,ny,num)) likeCnt++;
								if(map[nx][ny]==0) emptyCnt++;
							}
						}
						
						if(likeMax<likeCnt) {
							likeMax = likeCnt;
							emptyMax = emptyCnt;
							X=j;
							Y=k;
						}else if(likeMax==likeCnt) {
							if(emptyMax<emptyCnt) {
								emptyMax = emptyCnt;
								X=j;
								Y=k;
							}else if(emptyMax==emptyCnt) {
								if(X>j) {
									X=j;
									Y=k;
								}else if(X==j) {
									if(Y>k) {
										Y=k;
									}
								}
							}
						}
					}
				}
			}
			map[X][Y]=num;
		}
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				ans+= getScore(i,j,map[i][j]);
			}
		}
		System.out.println(ans);
	}
	
	public static int getScore(int x, int y, int num) {
		int cnt=0;
		for(int d=0;d<4;d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(Like(nx,ny,num)) cnt++;
			}
		}
		return score[cnt];
	}
	
	public static boolean Like(int x, int y, int num) {
		for(int i: like[num]) {
			if(i==map[x][y]) return true;
		}
		return false;
	}
}
