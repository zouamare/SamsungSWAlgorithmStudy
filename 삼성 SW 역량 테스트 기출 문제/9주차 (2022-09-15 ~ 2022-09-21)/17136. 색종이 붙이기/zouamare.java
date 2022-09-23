package coding_test.Algo20220919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* [내 접근 방법]
* 처음에는 그리디 방식으로 단순하게 생각하여 구현했다.
* 그러나 그리디 방식으로는 답이 나올 수 없는 반례가 존재했다.
*
* 따라서 백트래킹 방법으로 접근하는 것에 대해서는 이해가 됐지만 이를 어떻게 구현을 할 지는 모르겠어서 블로그를 참고했다.
*
* 내 구현 애로사항은 다음과 같다.
* 1.  x, y 좌표에 따라서 어떻게 적용할 것인가?
*   - 이를 이중 for문을 이용하여 메소드를 호출하도록 구현하려고 했으나 이의 경우 백트래킹이 성립되지 않았다.
* 2. 모든 1을 다 지우지 못하는 경우는 어떻게 적용할 것인가?
*   - 1의 개수를 카운트 해서 지우는 방법을 생각했다. 그러나 이 부분을 수행하기 이전에 위의 문제에 막혀서 해결하지 못했다.
*
* [블로그에 대해 이해한 내용]
* 블로그에서는 백트래킹 방식을 이용하기 위해 DFS 방식으로 구현했다.
* 따라서 매개변수는 x 좌표, y 좌표, 색종이의 갯수 cnt로 구성했다.
*
* map을 서치할 때 1을 발견하게 되고 for문을 돌아서 5x5 색종이 부터 내림차순으로 해당 위치에 맞는 색종이를 붙인다. (해당 크기의 색종이 수 - 1)
* 이후 만약에 호출한 메소드가 종료된다면 다시 색종이를 떼고 줄였던 색종이 수를 + 1 한다.
*
* 색종이를 붙일 수 없는 경우는 for문에 if()로 조건을 둬서 만약에 for문을 모두 돌았는데 진행이 안된다면 이전의 메소드로 돌아가게 된다.
*
* x 좌표는 MAX_LEN - 1이고 y 좌표가 MAX_LEN이면, cnt의 결과값을 ans에 저장하여 출력한다.
* */
// 블로그 https://steady-coding.tistory.com/43 참고
public class BOJ_17136_색종이붙이기 {
    static final int MAX_LEN = 10;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int ans = Integer.MAX_VALUE;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[MAX_LEN][MAX_LEN];
        for(int i = 0; i < MAX_LEN; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < MAX_LEN; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        DFS(0, 0, 0);

        System.out.println((ans == Integer.MAX_VALUE ? -1 : ans));
    }

    private static void DFS(int x, int y, int cnt) {
        if(x == MAX_LEN - 1 && y >= MAX_LEN){
            ans = Math.min(ans, cnt);
            return;
        }

        if(ans <= cnt)  return;

        if(y > 9){
            DFS(x+1, 0, cnt);
            return;
        }

        if(map[x][y] == 1){
            for(int i = 5; i > 0; i--){
                if(paper[i] > 0 && isPossible(x, y, i)){
                    setPaper(x, y, i, 0);   // 색종이로 씌우기
                    paper[i]--;
                    DFS(x, y+1, cnt+1);
                    setPaper(x, y, i, 1);   // 다시 벗기기
                    paper[i]++;
                }
            }
        }else{
            DFS(x, y+1, cnt);
        }
    }

    private static void setPaper(int x, int y, int d, int value) {
        for(int i = 0; i < d; i++){
            for (int j = 0; j < d; j++) {
                map[x + i][y + j] = value;
            }
        }
    }

    private static boolean isPossible(int x, int y, int d) {
        for(int i = 0; i < d; i++){
            for (int j = 0; j < d; j++) {
                if(x + i >= MAX_LEN || y + j >= MAX_LEN || map[x + i][y + j] == 0)  return false;
            }
        }
        return true;
    }
}
