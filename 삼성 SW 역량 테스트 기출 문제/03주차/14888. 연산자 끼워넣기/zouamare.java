import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] number;
    static char[] operator;
    static int resultMax;
    static int resultMin;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        number = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        operator = new char[N-1];
        int index = 0;
        int plus = Integer.parseInt(st.nextToken());
        for(int i = 0; i < plus; i++){
            operator[index++] = '+';
        }
        int minus = Integer.parseInt(st.nextToken());
        for(int i = 0; i < minus; i++){
            operator[index++] = '-';
        }
        int multi = Integer.parseInt(st.nextToken());
        for(int i = 0; i < multi; i++){
            operator[index++] = '*';
        }
        int divide = Integer.parseInt(st.nextToken());
        for(int i = 0; i < divide; i++){
            operator[index++] = '/';
        }
        resultMax = Integer.MIN_VALUE;
        resultMin = Integer.MAX_VALUE;
        boolean[] visited = new boolean[N-1];
        perm(visited, 0, number[0]);
        System.out.println(resultMax);
        System.out.println(resultMin);
    }

    private static void perm(boolean[] visited, int depth, int value) {
        if(depth == N - 1){
            resultMax = Math.max(resultMax, value);
            resultMin = Math.min(resultMin, value);
            return;
        }
        for(int i = 0; i < N - 1; i++){
            if(visited[i])  continue;
            visited[i] = true;
            switch (operator[i]){
                case '+':
                    perm(visited, depth + 1, value + number[depth+1]);
                    break;
                case '-':
                    perm(visited, depth + 1, value - number[depth+1]);
                    break;
                case '*':
                    perm(visited, depth + 1, value * number[depth+1]);
                    break;
                case '/':
                    if(value < 0){
                        perm(visited, depth + 1, -(Math.abs(value) / number[depth+1]));
                    }else{
                        perm(visited, depth + 1, value / number[depth+1]);
                    }
                    break;
            }
            visited[i] = false;
        }
    }
}