import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int minAbilityGap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int total = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }
        minAbilityGap = Integer.MAX_VALUE;
        // 조합 N/2
        boolean[] visited = new boolean[N];
        comb(visited, 0, N/2, 0, total);
        System.out.println(minAbilityGap);
    }

    private static void comb(boolean[] visited, int start, int r, int sum, int total) {
        if(r == 0){
            minAbilityGap = Math.min(minAbilityGap, Math.abs(total - sum));
            return;
        }
        for(int i = start; i < N; i++){
            if(visited[i])  continue;
            int add = 0;    // true인 곳들의 합
            int sub = 0;    // 총 합
            for(int j = 0; j < visited.length; j++){    // 여기가 제일 중요한 로직!
                sub += map[i][j] + map[j][i];
                if(visited[j])
                    add += map[i][j] + map[j][i];
            }
            visited[i] = true;
            comb(visited, i + 1, r - 1, sum + add, total - sub + add);
            visited[i] = false;
        }
    }
}