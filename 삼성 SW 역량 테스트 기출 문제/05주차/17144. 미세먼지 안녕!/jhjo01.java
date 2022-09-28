import java.util.Scanner;

public class BOJ_17144 {
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1}; // 상하좌우
    static int R, C, T, purifier;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();

        map = new int[R][C];

        boolean find = false;
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                map[i][j] = sc.nextInt();
                if(map[i][j] == -1 && !find)
                {
                    purifier = i;
                    find = true;
                }
            }
        }

        for(int t = 0; t < T; t++)
        {
            // 미세먼지 확산
                // 공기청정기가 있거나 칸이 없으면 확산x
                // map[r][c] / 5
            diff();
            map[purifier][0] = -1;
            map[purifier+1][0] = -1;
            
            
            
            
            // 공기청정기 작동
                // 위쪽 반시계 방향으로 순환
                // 아래쪽 시계방향으로 순환
                // 바람이 불면 방향대로 한칸씩 이동
                // 공기청정기에 들어가면 정화
            run();
            map[purifier][0] = -1;
            map[purifier+1][0] = -1;
            
//            for (int r = 0; r < R; r++)
//            {
//                for (int c = 0; c < C; c++)
//                {
//                	System.out.print(map[r][c] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
        
        
        int ans = 0;
        
        for (int r = 0; r < R; r++)
        {
            for (int c = 0; c < C; c++)
            {
            	if(map[r][c] == -1) continue;
                ans = ans + map[r][c];
            }
        }
        
        System.out.println(ans);
        
    }

    static void diff()
    {
        int[][] nextMap = new int[R][C];
        // 주변 칸이 빈칸 or 공기청정기이면 확산x
        for (int r  = 0; r < R; r++)
        {
            for (int c = 0; c < C; c++)
            {
                // 0 보다 크면
                if(map[r][c] > 0)
                {
                    int cnt = 0;
                    for(int i = 0; i < 4; i++)
                    {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] >= 0)
                        {
                            nextMap[nr][nc] = nextMap[nr][nc] + map[r][c] / 5;
                            cnt++;
                        }
                    }
                    if(map[r][c] < 5)
                	{
                    	nextMap[r][c] = nextMap[r][c] + map[r][c];
                    	continue;
                	}
                    nextMap[r][c] = nextMap[r][c] +  map[r][c] - (map[r][c] / 5) * cnt;
                }
            }
        }
        
        map = nextMap;
    }

    static void run()
    {
    	// 위쪽
    	int cr = purifier;
    	int cc = 0;
    	for(int i = purifier; i > 0; i--)
    	{
    		map[cr][cc] = map[--cr][cc];
    	}
    	for(int i = cc; i < C-1; i++)
    	{
    		map[cr][cc] = map[cr][++cc];
    	}
    	for(int i = cr; i < purifier; i++)
    	{
    		map[cr][cc] = map[++cr][cc];
    	}
    	map[purifier][0] = 0;
    	for(int i = cc; i > 0; i--)
    	{
    		map[cr][cc] = map[cr][--cc];
    	}
    	
    	// 아래쪽
    	cr = purifier+1;
    	cc = 0;
    	for(int i = cr; i < R-1; i++)
    	{
    		map[cr][cc] = map[++cr][cc];
    	}
    	for(int i = cc; i < C-1; i++)
    	{
    		map[cr][cc] = map[cr][++cc];
    	}
    	for(int i = cr; i > purifier+1; i--)
    	{
    		map[cr][cc] = map[--cr][cc];
    	}
    	map[purifier+1][0] = 0;
    	for(int i = cc; i > 0; i--)
    	{
    		map[cr][cc] = map[cr][--cc];
    	}
    	
    	
    	
    	
    	
    }
}