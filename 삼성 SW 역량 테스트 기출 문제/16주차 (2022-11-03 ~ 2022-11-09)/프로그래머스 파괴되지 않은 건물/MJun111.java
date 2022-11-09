import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board = {{5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4}, {1,2,0,2,3,2}, {2,1,0,3,1,2}, {1,0,1,3,3,1}};
//        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] skill = {{1,1,1,2,2,4}, {1,0,0,1,1,2}, {2,2,0,2,0,100}};

        System.out.println(solution(board, skill));

    }
    static public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length + 1][board[0].length + 1];

        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];

            int count = (int)Math.pow(-1, type) * degree;

            sum[r1][c1] += count;
            sum[r1][c2 + 1] += -1 * count;
            sum[r2 + 1][c1] += -1 * count;
            sum[r2 + 1][c2 + 1] += count;
        }

        for (int i = 1; i < sum.length; i++) {
            for (int j = 0; j < sum[0].length; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        for (int i = 0; i < sum.length; i++) {
            for (int j = 1; j < sum[0].length; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += sum[i][j];
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }

//    static public int solution(int[][] board, int[][] skill) {
//        int answer = 0;
//
//        for (int i = 0; i < skill.length; i++) {
//            int type = skill[i][0];
//            int degree = skill[i][5];
//
//            for (int j = skill[i][1]; j <= skill[i][3]; j++) {
//                for (int k = skill[i][2]; k <= skill[i][4]; k++) {
//                    board[j][k] += (int)Math.pow(-1, type) * degree;
//                }
//            }
//
//        }
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                if (board[i][j] > 0) answer++;
//            }
//        }
//
//        return answer;
//    }
}