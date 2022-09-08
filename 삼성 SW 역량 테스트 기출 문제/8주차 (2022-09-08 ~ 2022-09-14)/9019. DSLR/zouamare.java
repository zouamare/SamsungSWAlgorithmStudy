package coding_test.Algo20220908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 이 문제는 BFS로 접근해야 했다.
* 그 근거는 가장 빠르게 숫자가 동일해지는 경우를 찾아야 하기 때문이다.
* 그러나 기존 세팅을 char[]로 하고 연산때에만 int로 해줬더니 visited[]의 위치를 찾을 때에도 이를 변환해야 하는 등 시간이 오래 걸려 시간초과가 발생했다.
* 따라서 기본 값을 int로 하여 shift 연산할때만 char[]로 변경해서 계산했다.
*
* 시간이 진짜 오래 걸렸는데, 출력 부분을 변경했을 때 드라마틱한 변화가 없는 것을 미루어보아
* 시간이 오래 걸리는 부분은 String을 계속 쌓아가는데 소요되는 시간이 많이 들어서 인 것 같다.
*
* 다른 분의 풀이를 보면 배열로 처리하셨던데 이부분을 통해 시간을 더 줄여봐야겠다.
* */

// @aa1aaaa11 님의 L, R 풀이를 보고 참고함
public class BOJ_9019_DSLR {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int goal = Integer.parseInt(st.nextToken());
            sb.append(findGoal(start, goal)).append("\n");
        }
        System.out.println(sb);
    }

    private static String findGoal(int start, int goal) {
        Queue<Register> queue = new ArrayDeque<>();
        queue.add(new Register(start, ""));
        boolean[] visited = new boolean[10000];
        visited[start] = true;
        while(!queue.isEmpty()){
            Register r = queue.poll();

            // goal과 동일하다면 종료
            if(r.val == goal) return r.command;

            // goal을 찾아간다.
            Register D = setD(r), S = setS(r), L = setL(r), R = setR(r);
            // D -- n을 2배로
            if(!visited[D.val]){
                queue.add(D);
                visited[D.val] = true;
            }
            // S -- n - 1
            if(!visited[S.val]){
                queue.add(S);
                visited[S.val] = true;
            }
            // L -- 자릿수 왼편 이동
            if(!visited[L.val]){
                queue.add(L);
                visited[L.val] = true;
            }
            // R -- 자릿수 오른편 이동
            if(!visited[R.val]){
                queue.add(R);
                visited[R.val] = true;
            }

        }
        return "";  // 예외
    }

    private static Register setD(Register r) {
        return new Register((r.val * 2) % 10000, r.command + "D");
    }

    private static Register setS(Register r) {
        if(r.val == 0) return new Register(9999,  r.command + "S");
        return new Register(r.val - 1, r.command + "S");
    }

    private static Register setL(Register r) {
        char[] arr = parseCharArr(r.val);
        char[] register = new char[4];
        for(int i = 1; i <= 3; i++)  register[i-1] = arr[i];
        register[3] = arr[0];
        return new Register(parseInt(register), r.command + "L");
    }

    private static Register setR(Register r) {
        char[] arr = parseCharArr(r.val);
        char[] register = new char[4];
        for(int i = 0; i < 3; i++)  register[i+1] = arr[i];
        register[0] = arr[3];
        return new Register(parseInt(register), r.command + "R");
    }

    private static int parseInt(char[] register) {
        return Integer.parseInt(String.copyValueOf(register));
    }

    private static char[] parseCharArr(int val) {
        String s = String.valueOf(val);
        char[] register = new char[]{'0','0','0','0'};
        int j = 0;
        for(int i = 0; i < 4; i++){
            if(s.length() > 4 - i - 1)  register[i] = s.charAt(j++);
        }
        return register;
    }

    static class Register{
        int val;
        String command;

        public Register(int val, String s) {
            this.val = val;
            command = s;
        }
    }
}
