package coding_test.Algo20220913;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class PG_순위 {
    public static int solution(int n, int[][] results){
        int[][] map = new int[n+1][n+1];

        for(int i = 0; i < results.length; i++){
            map[results[i][0]][results[i][1]] = 1;  // 출발지
            map[results[i][1]][results[i][0]] = -1; // 도착지
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(map[k][i] == 1 && map[i][j] == 1){
                        map[k][j] = 1;
                        map[j][k] = -1;
                    }
                    if(map[k][i] == -1 && map[i][j] == -1){
                        map[k][j] = -1;
                        map[j][k] = 1;
                    }
                }
            }
        }

        int cnt = 0;
        for(int i = 1; i <= n; i++){
            boolean flag = true;
            for(int j = 1; j <= n; j++){
                if(i == j)  continue;
                if(map[i][j] == 0){
                    flag = false;
                    break;
                }
            }
            if(flag)    cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        solution(5, new int[][]{{4, 3}, {4, 2},{3, 2}, {1, 2}, {2, 5}});
    }
}
