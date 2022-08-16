import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int K;
    static int[][] A;
    static int[][] map;
    static Deque<Tree> trees;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        /*
        * 이 문제는 ArrayList로 풀면 안되는 문제였다.
        * ArrayList의 add와 remove에서 Deque의 poll과 add, addFirst로 변경해주니 통과했다.
        * ArrayList는 가끔만 remove 해주고 Deque는 계속 poll하는 데도 왜 속도 차이가 이렇게 나는 걸까?
        * 내가 예상한 차이는 다음과 같다.
        * 일단 자료구조를 처음부터 끝까지 읽어야한다. 이는 List와 deque 모두 드는 시간이 O(1)을 N번 실행, 즉 O(N)으로 동일하다.
        * 그런데 Deque에서는 죽은 나무를 다시 안더하면 끝이지만, ArrayList에서는 remove로 지워줘야 한다.
        * ArrayList의 remove() 메소드는 시간 복잡도가 O(N)이다. 따라서 지워야 하는 나무가 많을 경우 시간은 더욱 늘어날 것이다.
        *
        * <결론>
        * 삭제 연산이 빈번하게 발생하는 문제는 더 나은 시간복잡도를 위해 LinkedList를 이용하자.
        * */
        init();
        for(int year = 0; year < K; year++){
            summer(spring());
            fall();
            winter();
        }
        printTreeCnt();
    }

    private static void printTreeCnt() {
        System.out.println(M);
    }

    private static int[][] spring() {
        // 나이만큼 양분을 얻는다.
        // 자신의 나이만큼 양분을 못먹으면 삭제.
        int[][] deleted = new int[N][N];
        for(int i = 0, size = trees.size(); i < size; i++){
            Tree tree = trees.poll();
            if(map[tree.x][tree.y] < tree.age){
                // 나이 만큼의 양분이 없다. -> 나무는 죽는다.
                deleted[tree.x][tree.y] += tree.age/2;
                M--;
            }else{
                // 양분이 있다.
                map[tree.x][tree.y] -= tree.age++;
                trees.add(tree);
            }
        }
        return deleted;
    }

    private static void summer(int[][] deleted) {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] += deleted[i][j];
            }
        }
    }

    private static void fall() {
        Queue<Tree> temp = new LinkedList<>();
        for(Tree tree : trees){
            if(tree.age % 5 == 0){
                temp.add(tree);
            }
        }
        while(!temp.isEmpty()){
            Tree tree = temp.poll();
            int nx, ny;
            for(int j = 0, d = 8; j < d; j++){
                nx = tree.x + dx[j];
                ny = tree.y + dy[j];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N)  continue;
                trees.addFirst(new Tree(nx, ny, 1));
                M++;
            }
        }
    }

    private static void winter() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] += A[i][j];
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = 5;
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        trees = new LinkedList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            trees.add(new Tree(Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken()) - 1,Integer.parseInt(st.nextToken())));
        }
    }

    static class Tree{
        int x;
        int y;
        int age;

        Tree(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}