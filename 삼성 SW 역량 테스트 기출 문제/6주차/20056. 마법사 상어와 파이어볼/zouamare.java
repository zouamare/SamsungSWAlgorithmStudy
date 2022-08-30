package coding_test.Algo20220830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
* [나의 접근 방법]
* 파이어볼이 여러개가 겹칠 수 있다. 그리고 map의 block 크기가 최대 250이므로, ArrayList를 각각 선언해도 나쁘지 않다고 판단. (사실 이 방법 외에는 떠오르지 않았다..ㅠ)
* 그래서 ArrayList 이차원 배열을 만들고 로직대로 진행했다.
*
* [틀린 이유]
* 속력이 최대 1000임을 간과 했음.
* 속력이 최대 1000이므로 -1 * 1000이 될 수 있기에 이를 위해
* 속력보다 큰 값으로 N의 배수를 구해서 더해주었음.
*
* [더 나은 풀이 방법]
* Queue를 이용하여 더 빠른 풀이가 가능하다.
* */

public class BOJ_20056_마법사상어와파이어볼 {
    static int N, M, K;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<Fireball>[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = mapInit();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new Fireball(m,s,d));
        }
        
        // K만큼 움직이게
        startMoveBall();

        System.out.println(getTotalM());
    }

    private static int getTotalM() {
        int m = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(Fireball fb : map[i][j]){
                    m += fb.m;
                }
            }
        }
        return m;
    }

    private static void startMoveBall() {
        while (K-- > 0){
            ArrayList<Fireball>[][] newMap = mapInit();
            // 순서대로 움직임!
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(Fireball fb : map[i][j]){
                        int x = i, y = j, n = N;
                        while(n < fb.s) n += N;
                        x = ((x + (dx[fb.d] * fb.s)) + n) % N;
                        y = ((y + (dy[fb.d] * fb.s)) + n) % N;
                        newMap[x][y].add(fb);
                    }
                }
            }
            map = newMap;
            // 이제 다 탐색해서 size가 0보다 크면 처리해주기
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j].size() > 1){
                        int m = 0, s = 0, cnt = 0, size = map[i][j].size();
                        for(Fireball fb : map[i][j]){
                            m += fb.m;
                            s += fb.s;
                            if(fb.d % 2 == 0)   cnt++;  // 모두 홀수거나 모두 짝수인가?
                        }
                        // 합을 다했으니 비워줌.
                        map[i][j] = new ArrayList<>();
                        if(m < 5)   continue;
                        m = m / 5;
                        s = s / size;
                        if(cnt == size || cnt == 0) {
                            // 방향이 0, 2, 4, 6
                            map[i][j].add(new Fireball(m, s, 0));
                            map[i][j].add(new Fireball(m, s, 2));
                            map[i][j].add(new Fireball(m, s, 4));
                            map[i][j].add(new Fireball(m, s, 6));
                        }else{
                            // 방향이 1, 3, 5, 7
                            map[i][j].add(new Fireball(m, s, 1));
                            map[i][j].add(new Fireball(m, s, 3));
                            map[i][j].add(new Fireball(m, s, 5));
                            map[i][j].add(new Fireball(m, s, 7));
                        }
                    }
                }
            }
        }
    }

    private static ArrayList<Fireball>[][] mapInit(){
        ArrayList<Fireball>[][] newMap = new ArrayList[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++)  newMap[i][j] = new ArrayList<>();
        }
        return newMap;
    }

    static class Fireball{
        int m, d, s;

        public Fireball( int m, int s, int d) {
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}
