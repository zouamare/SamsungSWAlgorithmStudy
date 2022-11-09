package coding_test.Algo2022년_10월.day26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 안뒤집는게 포인트~!
* */
public class BOJ_5430_AC {
    static char[] methods;
    static int N;
    static Deque<Integer> deque;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        outer: for (int t = 0; t < T; t++) {
            methods = br.readLine().toCharArray();
            N = Integer.parseInt(br.readLine());
            deque = new ArrayDeque<>();
            flag = true;    // true면 앞, false면 뒤를 의미
            StringTokenizer st = new StringTokenizer(br.readLine().replace("[","").replace("]",""), ",");
            for (int i = 0; i < N; i++) {
                deque.offer(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < methods.length; i++) {
                if(methods[i] == 'R') { // Reverse
                    flag = !flag;
                }
                else {  // Delete
                    if(deque.isEmpty()){
                        sb.append("error").append("\n");
                        continue outer;
                    }
                    if(flag)    deque.pollFirst();
                    else deque.pollLast();
                }
            }
            sb.append(print()).append("\n");
        }
        System.out.println(sb);
    }

    private static String print() {
        StringBuilder result = new StringBuilder("[");
        if(flag)    while (!deque.isEmpty())    result.append(deque.pollFirst()).append(",");
        else    while (!deque.isEmpty())    result.append(deque.pollLast()).append(",");
        if(result.length() > 1) result.deleteCharAt(result.length() - 1);
        return result.append("]").toString();
    }
}
