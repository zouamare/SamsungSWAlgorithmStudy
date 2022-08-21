import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, totalVal, minDifference;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        init();
        permXY(new int[4]);
        printminDif();
    }

    private static void printminDif() {
        System.out.println(minDifference);
    }

    private static void permXY(int[] output) {
        // X, Y
        for(int x = 0; x < N; x++){
            output[0] = x;
            for(int y = 0; y < N; y++){
                output[1] = y;
                permD1D2(output);
            }
        }
    }

    private static void permD1D2(int[] output){
        // d1, d2
        int x = output[0], y = output[1];
        for(int d1 = 1; d1 < N; d1++){
            for(int d2 = 1; d2 < N; d2++){
                if(x+d1+d2 < N && 1 <=y-d1 && y+d2 < N){
                    output[2] = d1;
                    output[3] = d2;
                    makeAConstituency(output);
                }
            }
        }
    }

    private static void makeAConstituency(int[] output) {
        // 나누어진 x, y, d1, d2를 가지고 선거구를 계산한다.
        int x = output[0], y = output[1], d1 = output[2], d2 = output[3];
        int val = 0, total = totalVal;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0, t = 0; i < x+d1; i++){
            if(i >= x) t++;
            for(int j = 0; j <= y - t; j++){
                // 1번 선거구
                val += map[i][j];
            }
        }
        min = Math.min(min, val);
        max = Math.max(max, val);
        total -= val;
        val = 0;
        for(int i = 0, t = 1; i <= x+d2; i++){
            if(i > x) t++;
            for(int j = y + t; j < N; j++){
                // 2번 선거구
                val += map[i][j];
            }
        }
        min = Math.min(min, val);
        max = Math.max(max, val);
        total -= val;
        val = 0;
        for(int i = x + d1, t = -1; i < N; i++){
            if(t < d2) t++;
            for(int j = 0; j < y-d1+t; j++){
                // 3번 선거구
                val += map[i][j];
            }
        }
        min = Math.min(min, val);
        max = Math.max(max, val);
        total -= val;
        val = 0;
        for(int i = x+d2+1, t = 0; i < N; i++){
            if(t <= d1)  t++;
            for(int j = y+d2-t+1; j < N; j++){
                // 4번 선거구
                val += map[i][j];
            }
        }
        min = Math.min(min, val);
        max = Math.max(max, val);
        total -= val;
        // 5번 선거구
        val = total;
        min = Math.min(min, val);
        max = Math.max(max, val);
        minDifference = Math.min(minDifference, max - min);
    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        totalVal = 0;
        minDifference = Integer.MAX_VALUE;
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                totalVal += map[i][j];
            }
        }
    }
}