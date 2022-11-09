package solo.study;

import java.util.*;
import java.io.*;

public class BOJ_19237_어른상어 {
	static class Shark {
		int x, y, no;

		public Shark(int x, int y, int no) {
			this.x = x;
			this.y = y;
			this.no = no;
		}
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int N, M, K;
	static int sharkMap[][]; //상어의 번호를 저장하는 2차원배열
	static int smellMap[][]; //상어의 냄새를 적어주는 2차원배열 ( 0~K 까지 )
	static int sharkD[];   //현재 상어의 방향을 저장하는 1차원배열
	static int sharkPriorityD[][][]; //상어의 우선순위 방향을 저장 
	
	static PriorityQueue<Shark> sharkList = new PriorityQueue<>((o1, o2) -> (o2.no - o1.no)); 
	//상어의 위치, 번호 저장, 내림차순 
	static HashMap<String, Integer> smellList = new HashMap<>(); 
	//상어의 흔적 (좌표, 번호) 저장 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sharkMap = new int[N][N];
		smellMap = new int[N][N];
		sharkD = new int[M + 1]; // 1번부터
		sharkPriorityD = new int[M + 1][4][4];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sharkMap[i][j] = Integer.parseInt(st.nextToken());
				if (sharkMap[i][j] != 0) {
					sharkList.add(new Shark(i, j, sharkMap[i][j])); // 상어 pq삽입
					smellMap[i][j] = K;
					smellList.put(Integer.toString(i) +","+ Integer.toString(j), sharkMap[i][j]);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= M; i++)
			sharkD[i] = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					sharkPriorityD[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}

		System.out.println(solve());
	}

	private static int solve() {
		boolean flag = false;
		int time = -1;
		while (time<1001) {
			time++;
			int size = sharkList.size();
			if(size == 1) return time;
			for (int i = 0; i < size; i++) {
				Shark shark = sharkList.poll();
				int x = shark.x;
				int y = shark.y;
				
				flag = false; //아무냄새 없는 방향으로 갔는지 체크 
				// 아무 냄새 없는 방향
				for (int d = 0; d < 4; d++) {
					//현재 방향 정해서 X,Y좌표 바꾸기
					int nx = x + dx[sharkPriorityD[shark.no][sharkD[shark.no]][d]];
					int ny = y + dy[sharkPriorityD[shark.no][sharkD[shark.no]][d]];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue; // 범위 벗어나면
					if (smellMap[nx][ny] != 0)
						continue; // 다른 상어냄새 있으면

					// 갈 수 있으니까 sharkMap, shrakList바꿔주기
					sharkMap[x][y] = 0;
					sharkMap[nx][ny] = shark.no;
					sharkD[shark.no]=sharkPriorityD[shark.no][sharkD[shark.no]][d]; //상어방향 바꿔주기
					flag=true;
					break;
				}

				// 내 냄새로 가기 
				if(!flag) {
					for (int d = 0; d < 4; d++) {
						int nx = x + dx[sharkPriorityD[shark.no][sharkD[shark.no]][d]];
						int ny = y + dy[sharkPriorityD[shark.no][sharkD[shark.no]][d]];
						
						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue; // 범위 벗어나면
						if (smellList.get(Integer.toString(nx)+","+Integer.toString(ny)) != shark.no)
							continue; // 내 냄새 아니라면
						
						// 갈 수 있으니까 sharkMap, shrakList바꿔주기
						sharkMap[x][y] = 0;
						sharkMap[nx][ny] = shark.no;
						sharkD[shark.no]=sharkPriorityD[shark.no][sharkD[shark.no]][d]; //상어방향 바꿔주기
						break;
					}
				}
				
			}
			// 이동 다하고 겹친거 처리해주기(함수로) + 냄새 뿌리기 
			inputSharkAndSmell();

		}
		return -1;
	}


	private static void inputSharkAndSmell() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(smellMap[i][j]!=0) { //냄새가 있다면  빼주기 
					if(--smellMap[i][j]==0) { //만약 1 뺏을 때  0 이라면 
						smellList.remove(Integer.toString(i)+","+Integer.toString(j)); //삭제 
					}
				}
				
				if(sharkMap[i][j]!=0) { //상어가 있다면  pq에 상어넣고 냄새 추가하기 
					sharkList.add(new Shark(i,j,sharkMap[i][j]));
					smellList.put(Integer.toString(i)+","+Integer.toString(j), sharkMap[i][j]);
					smellMap[i][j]=K;
				}
			}
		}
		
	}

}

