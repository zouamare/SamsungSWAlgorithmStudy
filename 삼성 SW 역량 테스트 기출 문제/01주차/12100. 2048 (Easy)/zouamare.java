import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int maxNumOfBlock;
    final static int MOVE_CNT = 5;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());    // Board의 크기
        int[][] matrix = new int[N][N];
        StringTokenizer st;
        // fill matrix
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //DFS
        maxNumOfBlock = Integer.MIN_VALUE;
        DFS(matrix,0);
        System.out.println(maxNumOfBlock);
    }

    private static void DFS(int[][] matrix, int depth) {
        maxNumOfBlock = Math.max(maxNumOfBlock, findMaxNumOfBlock(matrix));
        if(depth == MOVE_CNT){
            return;
        }
        for(int i = 0; i < 4; i++){
            int[][] newM = new int[N][N];
            for(int j = 0; j < N; j++){
                System.arraycopy(matrix[j], 0, newM[j], 0, matrix[j].length);
            }
            moveMatrix(newM, i);
            DFS(newM, depth+1);
        }
    }

    private static void moveMatrix(int[][] matrix, int direction) {
        switch (direction){
            case 0: //상
                for(int i = 0; i < N; i++){
                    int index = 0;
                    for(int j = 1; j < N; j++){
                        if(matrix[j][i] == 0) continue;
                        if(matrix[index][i] == 0) {   // 이동만 할 때 - 0일 때
                            matrix[index][i] = matrix[j][i];
                            matrix[j][i] = 0;
                        }
                        else if(matrix[j][i] == matrix[index][i]) { // 합칠 때
                            matrix[index++][i] *= 2;
                            matrix[j][i] = 0;
                        }
                        else{   // 이동만 할 때 - 0이 아닐 때
                            if(j - index++ > 1) {
                                matrix[index][i] = matrix[j][i];
                                matrix[j][i] = 0;
                            }
                        }
                    }
                }
                break;
            case 1: //하
                for(int i = 0; i < N; i++){
                    int index = N - 1;
                    for(int j = N - 2; j >= 0; j--){
                        if(matrix[j][i] == 0) continue;
                        if(matrix[index][i] == 0) {   // 이동만 할 때 - 0일 때
                            matrix[index][i] = matrix[j][i];
                            matrix[j][i] = 0;
                        }
                        else if(matrix[j][i] == matrix[index][i]) {
                            matrix[index--][i] *= 2;
                            matrix[j][i] = 0;
                        }
                        else{
                            if(index-- - j > 1) {
                                matrix[index][i] = matrix[j][i];
                                matrix[j][i] = 0;
                            }
                        }
                    }
                }
                break;
            case 2: //좌
                for(int i = 0; i < N; i++){
                    int index = 0;
                    for(int j = 1; j < N; j++){
                        if(matrix[i][j] == 0) continue;
                        if(matrix[i][index] == 0) {   // 이동만 할 때 - 0일 때
                            matrix[i][index] = matrix[i][j];
                            matrix[i][j] = 0;
                        }
                        else if(matrix[i][j] == matrix[i][index]) {
                            matrix[i][index++] *= 2;
                            matrix[i][j] = 0;
                        }
                        else{
                            if(j - index++ > 1) {
                                matrix[i][index] = matrix[i][j];
                                matrix[i][j] = 0;
                            }
                        }
                    }
                }
                break;
            case 3: //우
                for(int i = 0; i < N; i++){
                    int index = N - 1;
                    for(int j = N - 2; j >= 0; j--){
                        if(matrix[i][j] == 0) continue;
                        if(matrix[i][index] == 0) {   // 이동만 할 때 - 0일 때
                            matrix[i][index] = matrix[i][j];
                            matrix[i][j] = 0;
                        }
                        else if(matrix[i][j] == matrix[i][index]) { // 합칠 때
                            matrix[i][index--] *= 2;
                            matrix[i][j] = 0;
                        }
                        else{   // 이동만 할 때 - 0이 아닐 때
                            if(index-- - j > 1) {
                                matrix[i][index] = matrix[i][j];
                                matrix[i][j] = 0;
                            }
                        }
                    }
                }
                break;
        }
    }

    private static int findMaxNumOfBlock(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] > max)
                    max = matrix[i][j];
            }
        }
        return max;
    }
}