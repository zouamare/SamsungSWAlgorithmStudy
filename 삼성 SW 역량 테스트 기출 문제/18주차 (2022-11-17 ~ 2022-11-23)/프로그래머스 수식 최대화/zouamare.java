package coding_test.Algo2022년_11월.day29;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class PG_수식최대화 {
    public static void main(String[] args) {
        System.out.println(solution("20-10*200"));
    }

    static char[] exp = {'+', '-', '*'};
    static int n = exp.length, r = 0;
    static long max = 0;
    static Queue<String> expQueue = new ArrayDeque<>();
    static boolean[] check = new boolean[n];
    public static long solution(String expression) {
        // 한번 쭉 서치하면서 +, -, * 중 있는 것을 찾는다.
        makeQueue(expression);
        for (int i = 0; i < expression.length(); i++) {
            for (int j = 0; j < n; j++) {
                if(expression.charAt(i) == exp[j] && !check[j]){
                    check[j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if(check[i])    r++;
        }
        // r은 연산자의 종류의 갯수이다.

        perm(0, new char[n], new boolean[n]);

        return max;
    }

    private static void makeQueue(String expression) {
        String s = "";
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)){
                case '+': case '-': case '*':
                    expQueue.offer(s);
                    expQueue.offer(Character.toString(expression.charAt(i)));
                    s = "";
                    break;
                default:
                    s += expression.charAt(i);
                    break;
            }
        }
        if(!s.equals("")){
            expQueue.offer(s);
        }
    }

    private static void perm(int depth, char[] output, boolean[] visited) {
        if(depth == r){
            makeResult(output);
            return;
        }
        for (int i = 0; i < n; i++) {
            if(visited[i] || !check[i]) continue;
            visited[i] = true;
            output[depth] = exp[i];
            perm(depth + 1, output, visited);
            visited[i] = false;
        }
    }

    private static void makeResult(char[] output) {
        Deque<String> copyQueue = new ArrayDeque<>();
        copyQueue.addAll(expQueue);
        for (int i = 0; i < r; i++) {
            for (int j = 0, size = copyQueue.size(); j < size; j++) {
                String val = copyQueue.poll();
                if(val.equals(Character.toString(output[i]))){
                    long left = Long.parseLong(copyQueue.pollLast());
                    long right = Long.parseLong(copyQueue.pollFirst());
                    switch (output[i]){
                        case '+':
                            val = String.valueOf(left + right);
                            break;
                        case '-':
                            val = String.valueOf(left - right);
                            break;
                        case '*':
                            val = String.valueOf(left * right);
                            break;
                    }
                   j++; // 수식이 합쳐지면서 생기는 인덱스 변화 처리
                }
                copyQueue.offerLast(val);
            }
        }
        long val = Math.abs(Long.parseLong(copyQueue.pollFirst()));
        if(max < val) max = val;
    }
}
