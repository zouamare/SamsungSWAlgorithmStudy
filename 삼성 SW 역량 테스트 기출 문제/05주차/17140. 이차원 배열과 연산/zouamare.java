import java.io.*;
import java.util.*;

public class Main {
    static int r;
    static int c;
    static int k;
    static int[][] map;
    static int R;
    static int C;
    static int[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        map = new int[100][100];
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }
        R = 3;
        C = 3;
        System.out.println(arrCalculation());
    }
    private static int arrCalculation() {
        int time = 0;
        while(true) {
            if(time > 100) {
                time = -1;
                break;
            }
            if(map[r][c] == k)	break;
            if(R >= C)	calR();
            else	calC();
            time++;
        }
        return time;
    }
    private static void calC() {
        int maxR = 0;
        for(int i = 0; i < C; i++) {
            check = new int[101];
            for(int j = 0; j < R; j++) {
                if(map[j][i] != 0)	check[map[j][i]]++;
            }
            ArrayList<Info> list = new ArrayList<>();
            for(int j = 1; j <= 100; j++) {
                if(check[j] != 0)	list.add(new Info(j, check[j]));
            }
            maxR = Math.max(maxR, list.size() * 2);
            // 등장 횟수가 커지는 순으로 정렬
            // 등장 횟수가 같다면 수가 커지는 순으로 정렬
            Collections.sort(list);
            // 해당 값을  map에 복사하기
            int index = 0;
            int j = 0;
            int size = list.size();
            while(index < 100) {
                if(j < size) {
                    map[index++][i] = list.get(j).val;
                    map[index++][i] = list.get(j).cnt;
                    j++;
                }else {
                    map[index++][i] = 0;
                    map[index++][i] = 0;
                }
            }
        }
        R = maxR;
    }
    private static void calR() {
        int maxC = 0;
        for(int i = 0; i < R; i++) {
            check = new int[101];
            for(int j = 0; j < C; j++) {
                if(map[i][j] != 0)	check[map[i][j]]++;
            }
            ArrayList<Info> list = new ArrayList<>();
            for(int j = 1; j <= 100; j++) {
                if(check[j] != 0)	list.add(new Info(j, check[j]));
            }
            maxC = Math.max(maxC, list.size() * 2);
            // 등장 횟수가 커지는 순으로 정렬
            // 등장 횟수가 같다면 수가 커지는 순으로 정렬
            Collections.sort(list);
            // 해당 값을  map에 복사하기
            int index = 0;
            int j = 0;
            int size = list.size();
            while(index < 100) {
                if(j < size) {
                    map[i][index++] = list.get(j).val;
                    map[i][index++] = list.get(j).cnt;
                    j++;
                }else {
                    map[i][index++] = 0;
                    map[i][index++] = 0;
                }
            }
        }
        C = maxC;
    }

    static class Info implements Comparable<Info>{
        int val;
        int cnt;

        public Info(int val, int cnt) {
            super();
            this.val = val;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info o) {
            if(this.cnt == o.cnt) {
                return this.val - o.val;
            }
            return this.cnt - o.cnt;
        }

    }
}