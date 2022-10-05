import java.io.*;
import java.util.*;

public class Main {
    static class Fish implements Cloneable {
        int x;
        int y;
        int d;

        Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
        
        @Override
        protected Fish clone() throws CloneNotSupportedException {
            return (Fish) super.clone();
        }
    }

    static int sx, sy, ans, m, s;
    static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; 
    static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] sdx = { 0, -1, 0, 1, 0 };
    static int[] sdy = { 0, 0, -1, 0, 1 };

    static int tmpPath[] = new int[3];
    static int path[] = new int[3];

    static ArrayList<Fish>[][] map;
    static ArrayList<Fish> fish;
    static int[][] smell;
    static int maxCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        smell = new int[5][5];
        map = new ArrayList[5][5];

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                map[i][j] = new ArrayList<Fish>();
            }
        }
        fish = new ArrayList<Fish>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fish.add(new Fish(r, c, d));
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        simulation();
        System.out.println(ans);
    }

    static void simulation() throws CloneNotSupportedException {
        while(s-- > 0) {
            // 1. 물고기 복제
            ArrayList<Fish> copy = copy(fish);

            // 2. 물고기 이동
            moveFishes();
            setMap();

            // 3. 상어 이동
            maxCnt = -1;
            sharkPath(0);
            sharkMove();

            // 4. 물고기 냄새 지움
            removeSmell();

            // 5. 복제마법 실행
            copyMagic(copy);

            // 6. map에 있는 내용 list에 담기(물고기 개수도 세기)
            reset();
        }
    }

    static void moveFishes() {
        for (int i = 0; i < fish.size(); i++) {
            Fish cur = fish.get(i);
            cur = moveFish(cur);    // cur 값이 바뀌면 fish 리스트 안에 값도 바뀜 -> 객체
        }
    }

    static Fish moveFish(Fish cur) {
        int nx = 0, ny = 0;
        int cnt = 0;

        while (true) {
            nx = cur.x + dx[cur.d];
            ny = cur.y + dy[cur.d];

            // 이동 방향으로 갈 수 있으면 이동
            if (nx > 0 && ny > 0 && nx <= 4 && ny <= 4 && smell[nx][ny] == 0 && !(nx == sx && ny == sy))
                break;

            // 회전방향 반시계 45도
            cur.d = cur.d - 1;
            if (cur.d <= 0)
                cur.d = 8;

            // 8방향 모두 돌았을 경우 이동불가
            if (++cnt == 8)
                break;
        }
        
        // 이동이 있었을 경우 좌표 갱신
        if (cnt < 8) {
            cur.x = nx;
            cur.y = ny;
        }

        return cur;
    }

    static void setMap() {
        for (int i = 0; i < fish.size(); i++) {
            Fish cur = fish.get(i);
            map[cur.x][cur.y].add(cur);
        }
    }

    // 중복 순열
    static void sharkPath(int idx) { 
        if (idx == 3) {
            int cnt = checkFish(); 
            
            // 못 가는 경로일 경우 리턴
            if(cnt == -1) 
                return;

            // 기존보다 물고기를 많이 먹을 경우 갱신
            if (maxCnt < cnt) {
                maxCnt = cnt;
                for (int i = 0; i < 3; i++) {
                    path[i] = tmpPath[i];
                }
            }
            return;
        }

        for (int i = 1; i <= 4; i++) { 
            tmpPath[idx] = i;
            sharkPath(idx + 1);
        }
    }

    static int checkFish() {
        boolean visited[][] = new boolean[5][5];
        int cnt = 0;
        int nx = sx, ny = sy;
        for (int i = 0; i < 3; i++) {
            nx += sdx[tmpPath[i]];
            ny += sdy[tmpPath[i]];

            if (nx <= 0 || ny <= 0 || nx > 4 || ny > 4) return -1;
            if (visited[nx][ny]) continue;
            
            cnt += map[nx][ny].size();
            visited[nx][ny] = true;
        }
        return cnt;
    }

    static void sharkMove() {
        for (int i = 0; i < 3; i++) {
            sx += sdx[path[i]];
            sy += sdy[path[i]];

            // 물고기가 있을 경우 지워주고 흔적 남김
            if (map[sx][sy].size() > 0) {
                smell[sx][sy] = 3;
                map[sx][sy].clear();
            }
        }
    }

    static void removeSmell() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (smell[i][j] > 0)
                    smell[i][j]--;
            }
        }
    }

    static void copyMagic(ArrayList<Fish> copy) {
        for (int i = 0; i < copy.size(); i++) {
            Fish cur = copy.get(i);
            map[cur.x][cur.y].add(cur);
        }
    }

    // 맵 초기화 및 fish 갱신
    static void reset() {
        fish.clear();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 0; k < map[i][j].size(); k++) {
                    Fish cur = map[i][j].get(k);
                    fish.add(cur);
                }
                map[i][j].clear();
            }
        }
        ans = fish.size();
    }

    static ArrayList<Fish> copy(ArrayList<Fish> list) throws CloneNotSupportedException {
        ArrayList<Fish> tmp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Fish cur = list.get(i);
            tmp.add(cur.clone());
        }
        return tmp;
    }
}