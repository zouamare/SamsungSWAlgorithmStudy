import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            Cube cube = new Cube();
            int N = Integer.parseInt(br.readLine());
            String[] command = br.readLine().split(" ");
            for(int n = 0; n < N; n++){
                // 돌리고 돌리고
                // 1. 어떤 면을 돌릴 것인지
                // 2. 어떤 방향으로 돌릴 것인지
                // 3. 어떤 면의 맞은 편을 제외하고는 다 값을 변경해줘야 한다. (방향 고려해야 함)
                cube.turnTheCube(command[n]);
            }
            cube.printUp();
        }
    }

    static class Cube{
        char[][] up;
        char[][] down;
        char[][] left;
        char[][] right;
        char[][] front;
        char[][] back;

        Cube(){
            up = new char[][]{
                    {'w','w','w'},
                    {'w','w','w'},
                    {'w','w','w'}
            };
            down = new char[][]{
                    {'y','y','y'},
                    {'y','y','y'},
                    {'y','y','y'}
            };
            left = new char[][]{
                    {'g','g','g'},
                    {'g','g','g'},
                    {'g','g','g'}
            };
            right = new char[][]{
                    {'b','b','b'},
                    {'b','b','b'},
                    {'b','b','b'}
            };
            front = new char[][]{
                    {'r','r','r'},
                    {'r','r','r'},
                    {'r','r','r'}
            };
            back = new char[][]{
                    {'o','o','o'},
                    {'o','o','o'},
                    {'o','o','o'}
            };
        }

        public void turnTheCube(String s) {
            char side = s.charAt(0);
            char dir = s.charAt(1);
            switch (side){
                case 'U':   // 윗면
                    turnUp(dir);
                    break;
                case 'D':   // 아랫 면
                    turnDown(dir);
                    break;
                case 'F':   // 앞 면
                    turnFront(dir);
                    break;
                case 'B':   // 뒷 면
                    turnBack(dir);
                    break;
                case 'L':   // 왼쪽 면
                    turnLeft(dir);
                    break;
                case 'R':   // 오른쪽 면
                    turnRight(dir);
                    break;
            }
        }

        private void turnUp(char dir) {
            // up의 내용물을 회전시켜야 함.
            char[][] temp;
            char[] t;
            switch (dir){
                case '+':
                    // 시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[j][2 - i] = up[i][j];
                        }
                    }
                    up = temp;
                    // 위니까 아래를 제외한 앞 뒤 왼 오를 다 시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 앞으로 하자.
                    t = new char[]{front[0][0], front[0][1], front[0][2]};
                    // 오른쪽 면 -> 앞면
                    front[0][0] = right[2][0];
                    front[0][1] = right[1][0];
                    front[0][2] = right[0][0];
                    // 뒷면 -> 오른쪽 면
                    right[0][0] = back[2][0];
                    right[1][0] = back[2][1];
                    right[2][0] = back[2][2];
                    // 왼쪽 면 - > 뒷면
                    back[2][2] = left[0][2];
                    back[2][1] = left[1][2];
                    back[2][0] = left[2][2];
                    // 앞 면 -> 왼쪽 면
                    left[0][2] = t[0];
                    left[1][2] = t[1];
                    left[2][2] = t[2];
                    break;
                case '-':
                    // 반시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[2 - j][i] = up[i][j];
                        }
                    }
                    up = temp;
                    // 위니까 아래를 제외한 앞 뒤 왼 오를 다 반시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 앞으로 하자.
                    t = new char[]{front[0][0], front[0][1], front[0][2]};
                    // 왼쪽 면 -> 앞면
                    front[0][0] = left[0][2];
                    front[0][1] = left[1][2];
                    front[0][2] = left[2][2];
                    // 뒷면 -> 왼쪽 면
                    left[0][2] = back[2][2];
                    left[1][2] = back[2][1];
                    left[2][2] = back[2][0];
                    // 오른쪽 면 - > 뒷면
                    back[2][0] = right[0][0];
                    back[2][1] = right[1][0];
                    back[2][2] = right[2][0];
                    // 앞 면 -> 오른쪽 면
                    right[2][0] = t[0];
                    right[1][0] = t[1];
                    right[0][0] = t[2];
                    break;
            }
        }

        private void turnDown(char dir) {
            // down의 내용물을 회전시켜야 함.
            char[][] temp;
            char[] t;
            switch (dir){
                case '+':
                    // 시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[j][2 - i] = down[i][j];
                        }
                    }
                    down = temp;
                    // 아래니까 위를 제외한 앞 뒤 왼 오를 다 시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 앞으로 하자.
                    t = new char[]{front[2][0], front[2][1], front[2][2]};
                    // 왼쪽 면 -> 앞면
                    front[2][0] = left[0][0];
                    front[2][1] = left[1][0];
                    front[2][2] = left[2][0];
                    // 뒷면 -> 왼쪽 면
                    left[0][0] = back[0][2];
                    left[1][0] = back[0][1];
                    left[2][0] = back[0][0];
                    // 오른쪽 면 - > 뒷면
                    back[0][0] = right[0][2];
                    back[0][1] = right[1][2];
                    back[0][2] = right[2][2];
                    // 앞 면 -> 오른쪽 면
                    right[2][2] = t[0];
                    right[1][2] = t[1];
                    right[0][2] = t[2];
                    break;
                case '-':
                    // 반시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[2 - j][i] = down[i][j];
                        }
                    }
                    down = temp;
                    // 아래니까 위를 제외한 앞 뒤 왼 오를 다 반시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 앞으로 하자.
                    t = new char[]{front[2][0], front[2][1], front[2][2]};
                    // 오른쪽 면 -> 앞면
                    front[2][0] = right[2][2];
                    front[2][1] = right[1][2];
                    front[2][2] = right[0][2];
                    // 뒷면 -> 오른쪽 면
                    right[0][2] = back[0][0];
                    right[1][2] = back[0][1];
                    right[2][2] = back[0][2];
                    // 왼쪽 면 - > 뒷면
                    back[0][0] = left[2][0];
                    back[0][1] = left[1][0];
                    back[0][2] = left[0][0];
                    // 앞 면 -> 왼쪽 면
                    left[0][0] = t[0];
                    left[1][0] = t[1];
                    left[2][0] = t[2];
                    break;
            }
        }

        private void turnFront(char dir) {
            // front 돌리기
            char[][] temp;
            char[] t;
            switch (dir){
                case '+':
                    // 시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[j][2 - i] = front[i][j];
                        }
                    }
                    front = temp;
                    // 앞이니까 뒤를 제외한 상 하 좌 우 를 다 시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[2][0], up[2][1], up[2][2]};
                    // 왼쪽 면 -> 윗면
                    up[2][0] = left[2][0];
                    up[2][1] = left[2][1];
                    up[2][2] = left[2][2];
                    // 아랫 면 -> 왼쪽 면
                    left[2][0] = down[0][2];
                    left[2][1] = down[0][1];
                    left[2][2] = down[0][0];
                    // 오른쪽 면 - > 아랫 면
                    down[0][0] = right[2][2];
                    down[0][1] = right[2][1];
                    down[0][2] = right[2][0];
                    // 윗 면 -> 오른쪽 면
                    right[2][0] = t[0];
                    right[2][1] = t[1];
                    right[2][2] = t[2];
                    break;
                case '-':
                    // 반시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[2 - j][i] = front[i][j];
                        }
                    }
                    front = temp;
                    // 앞이니까 뒤를 제외한 상 하 좌 우 를 다 반시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[2][0], up[2][1], up[2][2]};
                    // 오른쪽 면 -> 윗면
                    up[2][0] = right[2][0];
                    up[2][1] = right[2][1];
                    up[2][2] = right[2][2];
                    // 아랫 면 -> 오른쪽 면
                    right[2][0] = down[0][2];
                    right[2][1] = down[0][1];
                    right[2][2] = down[0][0];
                    // 왼쪽 면 - > 아랫 면
                    down[0][0] = left[2][2];
                    down[0][1] = left[2][1];
                    down[0][2] = left[2][0];
                    // 윗 면 -> 왼쪽 면
                    left[2][0] = t[0];
                    left[2][1] = t[1];
                    left[2][2] = t[2];
                    break;
            }
        }

        private void turnBack(char dir) {
            // back의 내용물을 회전시켜야 함.
            char[][] temp;
            char[] t;
            switch (dir){
                case '+':
                    // 시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[j][2 - i] = back[i][j];
                        }
                    }
                    back = temp;
                    // 뒤니까 앞을 제외한 상 하 좌 우 를 다 시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[0][0], up[0][1], up[0][2]};
                    // 오른쪽 면 -> 윗 면
                    up[0][0] = right[0][0];
                    up[0][1] = right[0][1];
                    up[0][2] = right[0][2];
                    // 아랫면 -> 오른쪽 면
                    right[0][0] = down[2][2];
                    right[0][1] = down[2][1];
                    right[0][2] = down[2][0];
                    // 왼쪽 면 - > 아랫면
                    down[2][0] = left[0][2];
                    down[2][1] = left[0][1];
                    down[2][2] = left[0][0];
                    // 윗 면 -> 왼쪽 면
                    left[0][0] = t[0];
                    left[0][1] = t[1];
                    left[0][2] = t[2];
                    break;
                case '-':
                    // 반시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[2 - j][i] = back[i][j];
                        }
                    }
                    back = temp;
                    // 뒤니까 앞을 제외한 상 하 좌 우 를 다 반시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[0][0], up[0][1], up[0][2]};
                    // 왼쪽 면 -> 윗 면
                    up[0][0] = left[0][0];
                    up[0][1] = left[0][1];
                    up[0][2] = left[0][2];
                    // 아랫 면 -> 왼쪽 면
                    left[0][0] = down[2][2];
                    left[0][1] = down[2][1];
                    left[0][2] = down[2][0];
                    // 오른쪽 면 - > 아랫 면
                    down[2][0] = right[0][2];
                    down[2][1] = right[0][1];
                    down[2][2] = right[0][0];
                    // 윗 면 -> 오른쪽 면
                    right[0][0] = t[0];
                    right[0][1] = t[1];
                    right[0][2] = t[2];
                    break;
            }
        }

        private void turnLeft(char dir) {
            // left의 내용물을 회전시켜야 함.
            char[][] temp;
            char[] t;
            switch (dir){
                case '+':
                    // 시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[j][2 - i] = left[i][j];
                        }
                    }
                    left = temp;
                    // 왼쪽이니까 오른쪽을 제외한 상 하 앞 뒤 를 다 시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[0][0], up[1][0], up[2][0]};
                    // 뒷 면 -> 윗 면
                    up[0][0] = back[0][0];
                    up[1][0] = back[1][0];
                    up[2][0] = back[2][0];
                    // 아랫 면 -> 뒷 면
                    back[0][0] = down[0][0];
                    back[1][0] = down[1][0];
                    back[2][0] = down[2][0];
                    // 앞 면 - > 아랫 면
                    down[0][0] = front[0][0];
                    down[1][0] = front[1][0];
                    down[2][0] = front[2][0];
                    // 윗 면 -> 앞 면
                    front[0][0] = t[0];
                    front[1][0] = t[1];
                    front[2][0] = t[2];
                    break;
                case '-':
                    // 반시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[2 - j][i] = left[i][j];
                        }
                    }
                    left = temp;
                    // 왼쪽이니까 오른쪽을 제외한 상 하 앞 뒤 를 다 반시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[0][0], up[1][0], up[2][0]};
                    // 앞 면 -> 윗 면
                    up[0][0] = front[0][0];
                    up[1][0] = front[1][0];
                    up[2][0] = front[2][0];
                    // 아랫면 -> 앞 면
                    front[0][0] = down[0][0];
                    front[1][0] = down[1][0];
                    front[2][0] = down[2][0];
                    // 뒷 면 - > 아랫면
                    down[0][0] = back[0][0];
                    down[1][0] = back[1][0];
                    down[2][0] = back[2][0];
                    // 윗 면 -> 뒷 면
                    back[0][0] = t[0];
                    back[1][0] = t[1];
                    back[2][0] = t[2];
                    break;
            }
        }

        private void turnRight(char dir) {
            // right의 내용물을 회전시켜야 함.
            char[][] temp;
            char[] t;
            switch (dir){
                case '+':
                    // 시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[j][2 - i] = right[i][j];
                        }
                    }
                    right = temp;
                    // 오른쪽이니까 왼쪽을 제외한 상 하 앞 뒤 를 다 시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[0][2], up[1][2], up[2][2]};
                    // 앞 면 -> 윗 면
                    up[0][2] = front[0][2];
                    up[1][2] = front[1][2];
                    up[2][2] = front[2][2];
                    // 아랫면 -> 앞 면
                    front[0][2] = down[0][2];
                    front[1][2] = down[1][2];
                    front[2][2] = down[2][2];
                    // 뒷 면 - > 아랫면
                    down[0][2] = back[0][2];
                    down[1][2] = back[1][2];
                    down[2][2] = back[2][2];
                    // 윗 면 -> 뒷 면
                    back[0][2] = t[0];
                    back[1][2] = t[1];
                    back[2][2] = t[2];
                    break;
                case '-':
                    // 반시계 방향
                    temp = new char[3][3];
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            temp[2 - j][i] = right[i][j];
                        }
                    }
                    right = temp;
                    // 오른쪽이니까 왼쪽을 제외한 상 하 앞 뒤 를 다 시계 방향으로 전환한다.
                    // 그럼 이제 하나를 임시로 저장. 기준을 상으로 하자.
                    t = new char[]{up[0][2], up[1][2], up[2][2]};
                    // 뒷 면 -> 윗 면
                    up[0][2] = back[0][2];
                    up[1][2] = back[1][2];
                    up[2][2] = back[2][2];
                    // 아랫 면 -> 뒷 면
                    back[0][2] = down[0][2];
                    back[1][2] = down[1][2];
                    back[2][2] = down[2][2];
                    // 앞 면 - > 아랫 면
                    down[0][2] = front[0][2];
                    down[1][2] = front[1][2];
                    down[2][2] = front[2][2];
                    // 윗 면 -> 앞 면
                    front[0][2] = t[0];
                    front[1][2] = t[1];
                    front[2][2] = t[2];
                    break;
            }
        }

        public void printUp() {
            for(int i = 0; i < up.length; i++){
                for(int j = 0; j < up[0].length; j++){
                    System.out.print(up[i][j]);
                }
                System.out.println();
            }
        }
    }
}