package BOJ;
/*
    그냥 구현 문제
    고려사항 : 상어 방향, 맵 밖으로 나가는 모래
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20057_마법사상어와토네이도  {
    static int[] dr = {0, 1, 0, -1}; // 좌 하 우 상
    static int[] dc = {-1, 0, 1, 0};
    static int[][] map;
    static int N, cd, cr, cc, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cr = N/2; cc = N/2; // 상어 좌표
        ans = 0; // 밖으로 나간 모래

        StringTokenizer st;
        // Map 입력
        for(int r = 0; r < N; r++)
        {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++)
            {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 상어 이동
        for(int i = 0; i < N-1; i++)
        {
            for(int k = 0; k < 2; k++)
            {
                for(int j = 0; j < i+1; j++)
                {
                    cr = cr + dr[cd];
                    cc = cc + dc[cd];
                    // 2. 모래 분산
                    diff();
                }
                cd = (cd + 1) % 4;
            }
            if(i == N-2)
            {
                for(int j = 0; j < i+1; j++)
                {
                    cr = cr + dr[cd];
                    cc = cc + dc[cd];
                    // 2. 모래 분산
                    diff();
                }
            }
        }

        System.out.println(ans);

    }

    static void diff()
    {
        ArrayList<int[]> nMap = new ArrayList<>();
        int pr = cr + dr[(cd + 1) & 3];
        int pc = cc + dc[(cd + 1) & 3];

        int nr, nc;
        if(Math.abs(cd - 3) == 2)
        {
            nr = cr + dr[0];
            nc = cc + dc[0];
        }
        else if(Math.abs(cd - 3) == 0)
        {
            nr = cr + dr[2];
            nc = cc + dc[2];
        }
        else
        {
            nr = cr + dr[Math.abs(cd - 3)];
            nc = cc + dc[Math.abs(cd - 3)];
        }


        int value = map[cr][cc];
        int nValue = value;
        map[cr][cc] = 0;

        // curr
        float c = 0.07f;

        if(pr < 0 || pr > N-1 || pc < 0 || pc > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {pr, pc, (int) (value * c)});
            nValue -= (int) (value * c);
        }
        if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {nr, nc, (int) (value * c)});
            nValue -= (int) (value * c);
        }

        int pr1 = pr + dr[(cd + 1) & 3];
        int pc1 = pc + dc[(cd + 1) & 3];

        int nr1, nc1;
        if(Math.abs(cd - 3) == 2)
        {
            nr1 = nr + dr[0];
            nc1 = nc + dc[0];
        }
        else if(Math.abs(cd - 3) == 0)
        {
            nr1 = nr + dr[2];
            nc1 = nc + dc[2];
        }
        else
        {
            nr1 = nr + dr[Math.abs(cd - 3)];
            nc1 = nc + dc[Math.abs(cd - 3)];
        }

        c = 0.02f;

        if(pr1 < 0 || pr1 > N-1 || pc1 < 0 || pc1 > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {pr1, pc1, (int) (value * c)});
            nValue -= (int) (value * c);
        }
        if(nr1 < 0 || nr1 > N-1 || nc1 < 0 || nc1 > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {nr1, nc1, (int) (value * c)});
            nValue -= (int) (value * c);
        }

        // -1
        int pr2 = pr - dr[cd];
        int pc2 = pc - dc[cd];
        int nr2 = nr - dr[cd];
        int nc2 = nc - dc[cd];
        c = 0.01f;
        if(pr2 < 0 || pr2 > N-1 || pc2 < 0 || pc2 > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {pr2, pc2, (int) (value * c)});
            nValue -= (int) (value * c);
        }
        if(nr2 < 0 || nr2 > N-1 || nc2 < 0 || nc2 > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {nr2, nc2, (int) (value * c)});
            nValue -= (int) (value * c);
        }

        // +1
        int pr3 = pr + dr[cd];
        int pc3 = pc + dc[cd];
        int nr3 = nr + dr[cd];
        int nc3 = nc + dc[cd];
        c = 0.1f;
        if(pr3 < 0 || pr3 > N-1 || pc3 < 0 || pc3 > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {pr3, pc3, (int) (value * c)});
            nValue -= (int) (value * c);
        }
        if(nr3 < 0 || nr3 > N-1 || nc3 < 0 || nc3 > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {nr3, nc3, (int) (value * c)});
            nValue -= (int) (value * c);
        }

        // +2
        pr = cr + dr[cd]*2;
        pc = cc + dc[cd]*2;
        c = 0.05f;
        if(pr < 0 || pr > N-1 || pc < 0 || pc > N-1)
        {
            ans += (int) (value * c);
            nValue -= (int) (value * c);
        }
        else
        {
            nMap.add(new int[] {pr, pc, (int) (value * c)});
            nValue -= (int) (value * c);
        }

        pr = cr + dr[cd];
        pc = cc + dc[cd];

        if(pr < 0 || pr > N-1 || pc < 0 || pc > N-1)
        {
            ans += nValue;
        }
        else
        {
            nMap.add(new int[] {pr, pc, nValue});
        }


        while (!nMap.isEmpty())
        {
            int[] temp = nMap.get(0);
            nMap.remove(0);

            nr = temp[0];
            nc = temp[1];
            nValue = temp[2];

            map[nr][nc] += nValue;
        }
    }
}
