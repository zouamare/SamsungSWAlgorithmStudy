import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int L;
    static int R;
    static int[] dx = {-1, 1, 0, 0};    // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    static int maxUnionNum;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        while(true){
            int[][] union = new int[N][N];
            int[][] copyMap = deepCopy(map);
            maxUnionNum = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(union[i][j] != 0) continue;
                    maxUnionNum++;
                    findUnion(map, union, i, j);
                }
            }
            if(maxUnionNum == N*N)
                break;
            peopleLeaving(map, union, maxUnionNum);
            if(isUnchanged(map,copyMap))
                break;
            cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean isUnchanged(int[][] map, int[][] copyMap) {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] != copyMap[i][j])
                    return false;
            }
        }
        return true;
    }

    private static int[][] deepCopy(int[][] map) {
        int[][] newMap = new int[N][N];
        for(int i = 0; i < N; i++)
            newMap[i] = map[i].clone();
        return newMap;
    }

    private static void peopleLeaving(int[][] map, int[][] union, int maxUnionNum) {
        ArrayList<int[]>[] list = new ArrayList[maxUnionNum+1];
        int[] newVal = new int[maxUnionNum+1];
        for(int i = 1; i < list.length; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                list[union[i][j]].add(new int[]{i,j});
            }
        }

        for(int i = 1; i < list.length; i++){
            int blockCnt = list[i].size();
            int peopleSum = list[i].stream().mapToInt(a -> map[a[0]][a[1]]).sum();
            newVal[i] = peopleSum / blockCnt;
        }

        for(int i = 1; i < list.length; i++){
            for(int[] j : list[i]){
                map[j[0]][j[1]] = newVal[i];
            }
        }
    }

    private static void findUnion(int[][] map, int[][] union, int i, int j) {
        // 각 Union을 구별하는 메소드
        Queue<int[]> queue = new LinkedList<>();
        union[i][j] = maxUnionNum;
        queue.add(new int[]{i,j});
        while(!queue.isEmpty()){
            int[] deleted = queue.poll();
            int x = deleted[0];
            int y = deleted[1];
            for(int k = 0; k < 4; k++){
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if(union[nx][ny] != 0) continue;
                    int valDif = Math.abs(map[x][y] - map[nx][ny]);
                    if(valDif >= L && valDif <= R){
                        union[nx][ny] = maxUnionNum;
                        queue.add(new int[]{nx,ny});
                    }
                }
        }
    }
}