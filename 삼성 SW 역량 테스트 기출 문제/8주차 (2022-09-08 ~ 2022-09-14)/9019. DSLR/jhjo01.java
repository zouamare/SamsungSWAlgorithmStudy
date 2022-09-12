package BOJ;

import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
    BFS 사용해서 풀면 될거같음
    String 타입의 5칸짜리 배열을 이용해서 각 칸에 4자리수 집어넣기
    n/1000 (n%1000)/100 (n%100)/10 n%10
    계산시에는 각 자리에 1000x 100x 10x 1 해주면 됨
    배열의 마지막에는 명령어 추가

    시간초과 이유
    방문처리를 큐에 넣은 다음에 해서
    방문처리를 큐에 넣을 때 해서 해결
 */
public class BOJ_9019_DSLR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int now, target;
        boolean[] visited;
        String ans = "";

        for(int test_case = 0; test_case < T; test_case++) {
            now = sc.nextInt();
            target = sc.nextInt();

            char nnnn = (char) (now/1000);      // 1000의 자리
            char nnn = (char) ((now%1000)/100); // 100 의 자리
            char nn = (char) ((now%100)/10);    // 10  의 자리
            char n = (char) (now%10);           // 1   의 자리
            visited = new boolean[10001];

            Queue<String[]> q = new ArrayDeque<>();
            q.offer(new String[]{String.valueOf(nnnn), String.valueOf(nnn), String.valueOf(nn), String.valueOf(n), ""});
            visited[now] = true;
            while (!q.isEmpty()) {
                String[] temp = q.poll();
                nnnn = temp[0].charAt(0);
                nnn = temp[1].charAt(0);
                nn = temp[2].charAt(0);
                n = temp[3].charAt(0);

                // 현재와 target이 일치하는가
                now = (int)nnnn  * 1000 + (int)nnn * 100 + (int)nn * 10 + (int)n;

                if(now == target) {
                    ans = temp[4];
                    break;
                }
                // D  n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
                int D = (now * 2) % 10000;
                if(!visited[D]) {
                    visited[D] = true;
                    q.offer(new String[]{String.valueOf((char) (D / 1000)), String.valueOf((char) ((D % 1000) / 100)), String.valueOf((char) ((D % 100) / 10)), String.valueOf((char) (D % 10)), (temp[4] + "D")});
                }
                // S n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
                int S;
                if(now == 0) S = 9999;
                else S = now - 1;
                if(!visited[S]) {
                    visited[S] = true;
                    q.offer(new String[]{String.valueOf((char) (S / 1000)), String.valueOf((char) ((S % 1000) / 100)), String.valueOf((char) ((S % 100) / 10)), String.valueOf((char) (S % 10)), (temp[4] + "S")});
                }
                // L n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
                int L = (int)nnn  * 1000 + (int)nn * 100 + (int)n * 10 + (int)nnnn;
                if(!visited[L]) {
                    visited[L] = true;
                    q.offer(new String[]{String.valueOf(nnn), String.valueOf(nn), String.valueOf(n), String.valueOf(nnnn), (temp[4] + "L")});
                }
                // R n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
                int R = (int)n  * 1000 + (int)nnnn * 100 + (int)nnn * 10 + (int)nn;
                if(!visited[R]) {
                    visited[R] = true;
                    q.offer(new String[] {String.valueOf(n), String.valueOf(nnnn), String.valueOf(nnn), String.valueOf(nn), (temp[4] + "R")});
                }
            }
            System.out.println(ans);

        }
    }
}
