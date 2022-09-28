package BOJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
    1. 비어있는 자리 찾기
    2. 비어있는 자리 가로 세로 검사
    3. 비어있는 자리 왼쪽 상단 시작부분부터 9칸 검사
    4. 검사해서 후보 선정
    5. 후보 하나 빈칸에 넣기
    6. 1~5 반복 실행
    7. 후보들 중 빈칸에 들어갈 수 있는 후보가 없을경우 이전으로 되돌리기고 1~5 실행
    8. 마지막에 모든 자리 체크해서 문제 없으면 정답 출력

 */
public class BOJ_2580_스도쿠 {
    static boolean end = false;
    static int size;
    static int[][] map;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = 9;
        map = new int[N][N];

        list = new ArrayList<ArrayList<Integer>>();

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                map[r][c] = sc.nextInt();
                if(map[r][c] == 0) {
                    ArrayList<Integer> loc = new ArrayList<Integer>();
                    loc.add(r);
                    loc.add(c);
                    list.add(loc);
                }
            }
        }

        size = list.size();
        for(int i = 0; i < size; i++) {
            int r = list.get(i).get(0);
            int c = list.get(i).get(1);

            // 가로
            int[] data = new int[10];
            for(int j = 0; j < 9; j++) {
                data[map[r][j]]++;
            }
            // 세로
            for(int j = 0; j < 9; j++) {
                data[map[j][c]]++;
            }
            // 정사각형
            int startRow = (r / 3) * 3;
            int startCol = (c / 3) * 3;
            for(int nr = startRow; nr < startRow + 3; nr++) {
                for(int nc = startCol; nc < startCol + 3; nc++) {
                    data[map[nr][nc]]++;
                }
            }

            for(int j = 1; j < 10; j++) {
                if(data[j] == 0) {
                    list.get(i).add(j);
                }
            }
        }

        // allCheck();

        dfs(list, 0);

//        size = list.size();
//        for(int i = 0; i < size; i++) {
//            ArrayList<Integer> loc = list.get(i);
//            for(int b : loc) {
//                System.out.print(b + " ");
//            }
//            System.out.println();
//        }

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }

    }

    public static Boolean dfs(ArrayList<ArrayList<Integer>> list, int idx) {

        if(idx == size) return false;
        boolean last = false;
        if(idx == size-1) last = true;
        ArrayList<Integer> arr = list.get(idx);
        int r = arr.get(0);
        int c = arr.get(1);

        for(int i = 2, size = arr.size(); i < size; i++) {
            map[r][c] = arr.get(i);
            if(!curCheck(r, c)) {
                map[r][c] = 0;
                continue;
            }
            dfs(list, idx+1);
            if(last) end = allCheck();
            if(end) return true;
        }

        map[r][c] = 0;
        return false;
    }

    public static boolean curCheck(int r, int c) {
        int[] data = new int[10];
        for(int j = 0; j < 9; j++) {
            data[map[r][j]]++;
        }
        if(data[map[r][c]] != 1) return false;
        // 세로
        for(int j = 0; j < 9; j++) {
            data[map[j][c]]++;
        }
        if(data[map[r][c]] != 2) return false;
        // 정사각형
        int startRow = (r / 3) * 3;
        int startCol = (c / 3) * 3;
        for(int nr = startRow; nr < startRow + 3; nr++) {
            for(int nc = startCol; nc < startCol + 3; nc++) {
                data[map[nr][nc]]++;
            }
        }
        if(data[map[r][c]] != 3) return false;
        return true;
    }
    public static boolean allCheck() {
        size = list.size();
        boolean state = true;
        for(int i = 0; i < size; i++) {
            int r = list.get(i).get(0);
            int c = list.get(i).get(1);

            // 가로
            int[] data = new int[10];
            for(int j = 0; j < 9; j++) {
                data[map[r][j]]++;
            }
            // 세로
            for(int j = 0; j < 9; j++) {
                data[map[j][c]]++;
            }
            // 정사각형
            int startRow = (r / 3) * 3;
            int startCol = (c / 3) * 3;
            for(int nr = startRow; nr < startRow + 3; nr++) {
                for(int nc = startCol; nc < startCol + 3; nc++) {
                    data[map[nr][nc]]++;
                }
            }

            for(int j = 1; j < 10; j++) {
                if(data[j] == 0) {
                    state = false;
                }
            }
        }

        return state;
    }
}
