import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 보드의 크기
        int K = Integer.parseInt(br.readLine());    // 사과의 수
        StringTokenizer st;
        int[][] board = new int[N][N];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
        }
        int L = Integer.parseInt(br.readLine());    // 뱀의 방향 변환 정보
        String[][] moves = new String[L][];
        for(int i = 0; i < L; i++){
            moves[i] = br.readLine().split(" ");
        }

        int[] head = {0,0};
        int[] tail = {0,0};
        int index = 0;
        int time = 0;
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        int d = 0;  //direction
        board[0][0] = d + 1;
        while(true){
            time++;
            // 뱀이 몸길이를 늘려 머리를 다음 칸에 위치시킨다. 다음칸 정보: headToX, headToY
            int headToX = head[0] + dx[d];
            int headToY = head[1] + dy[d];
            if(headToX < 0 || headToX >= N || headToY < 0 || headToY >= N)  break;  //벽에 닿으면, 종료
            if(board[headToX][headToY] == 0){
                board[head[0]][head[1]] = d + 1;
                board[headToX][headToY] = d + 1;

                head[0] = headToX;
                head[1] = headToY;

                // 사과가 없으면 뒤 꼬리를 줄인다.
                int tmpD = board[tail[0]][tail[1]] - 1;
                board[tail[0]][tail[1]] = 0;
                tail[0] = tail[0] + dx[tmpD];
                tail[1] = tail[1] + dy[tmpD];
            }
            else if(board[headToX][headToY] == -1){
                // 만약 이동한 칸에 사과가 있다면(0보다 큰 값이 아니라면), 사과가 없어지고 꼬리는 움직이지 않는다.
                board[head[0]][head[1]] = d + 1;
                board[headToX][headToY] = d + 1;

                head[0] = headToX;
                head[1] = headToY;

            }else{  // 몸에 닿으면, 종료
                break;
            }

            if(index < moves.length && Integer.parseInt(moves[index][0]) == time){  // 방향 전환
                switch (moves[index][1]){
                    case "L":
                        if(d == 0)
                            d = 4;
                        d = d-1;
                        break;
                    case "D":
                        d = (d+1)%4;
                        break;
                }
                index++;
            }
        }
        System.out.println(time);
    }
}