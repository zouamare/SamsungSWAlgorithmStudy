import java.io.*;
import java.util.*;

public class Main {
    static Gear gear1;
    static Gear gear2;
    static Gear gear3;
    static Gear gear4;
    public static void main(String[] args) throws IOException {
        /*
        * 시간이 좀 걸린 이유
        * left랑 right를 변경해줬으면 그 상태에 맞는
        * top 위치도 변경이 되게 끔 코드를 짰어야 했는데,
        * 고정적으로 0번 째 인덱스에 접근하게 짰어서 오류가 발생했다.
        * 그 외의 부분은 객체로 푼다는 아이디어를 생각하고 나서
        * 따로 문제될 건 없었다.
        * */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gear1 = new Gear(br.readLine().split(""));
        gear2 = new Gear(br.readLine().split(""));
        gear3 = new Gear(br.readLine().split(""));
        gear4 = new Gear(br.readLine().split(""));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()); // 1: 시계방향, -1: 반시계 방향
            // move(돌아가는 애, 방향, visited)
            boolean[] visited = new boolean[5];
            move(num, dir, visited);
        }
        int score = 0;
        score += (gear1.getTop().equals("1")?1:0);
        score += (gear2.getTop().equals("1")?2:0);
        score += (gear3.getTop().equals("1")?4:0);
        score += (gear4.getTop().equals("1")?8:0);
        System.out.println(score);
    }

    private static void move(int num, int dir, boolean[] visited) {
        if(visited[num])    return;
        visited[num] = true;
        String left;
        String right;
        switch (num){
            case 1:
                right = gear1.getRight();
                gear1.move(dir);
                if(!right.equals(gear2.getLeft())){
                    move(2, -dir, visited);
                }
                break;
            case 2:
                left = gear2.getLeft();
                right = gear2.getRight();
                gear2.move(dir);
                if(!left.equals(gear1.getRight())){
                    move(1, -dir, visited);
                }
                if(!right.equals(gear3.getLeft())){
                    move(3, -dir, visited);
                }
                break;
            case 3:
                left = gear3.getLeft();
                right = gear3.getRight();
                gear3.move(dir);
                if(!left.equals(gear2.getRight())){
                    move(2, -dir, visited);
                }
                if(!right.equals(gear4.getLeft())){
                    move(4, -dir, visited);
                }
                break;
            case 4:
                left = gear4.getLeft();
                gear4.move(dir);
                if(!left.equals(gear3.getRight())){
                    move(3, -dir, visited);
                }
                break;
        }
    }

    static class Gear{
        String[] wheel;
        int left;
        int right;

        Gear(String[] wheel){
            this.wheel = wheel;
            left = 6;
            right = 2;
        }

        public void move(int dir){
            if(dir == 1)
                moveClockwise();
            else
                moveCounterClockwise();
        }

        public void moveClockwise(){
            left = (left - 1 + 8) % 8;
            right = (right - 1 + 8) % 8;
        }

        public void moveCounterClockwise(){
            left = (left + 1) % 8;
            right = (right + 1) % 8;
        }

        public String getTop(){
            return wheel[(right - 2 + 8) % 8];
        }

        public String getLeft(){
            return wheel[left];
        }

        public String getRight(){
            return wheel[right];
        }
    }
}