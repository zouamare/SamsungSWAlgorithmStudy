package coding_test.Algo2022년_10월.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* MAX_VALUE를 10000으로 정해줬더니 통과가 안됐다..
*
* 10000 -> 10000000으로 변경해주니 통과가 됐다.
*
* 그런데 시간이 너무 오래걸린다.. (2644ms) 다른 사람들 풀이를 보니까 2번의 시행만으로 정답이 나오던데 이해가 안된다.
*
* 변천 과정: 플로이드 와샬처럼 다익스트라를 N만큼 반복 -> 총 2회로 단축
*
* [수정 1회]
* 1. 일단 X -> 다른 정점까지의 최단거리는 한번만 구하면 될 것 같아서 그렇게 구해봤다. => (1524ms)
*
* [수정 2회]
* 2. 거꾸로 Dijstra를 돌려봐서 한번만 돌려도 되도록 해보자. => (400ms)
*
* [수정 3회]
* 3. 2차원 배열을 배열 리스트로 변경해보자. => (472ms)
*
*
* */
public class BOJ_1238_파티 {
    static final int MAX_VALUE = 10000000;
    static int N, M, X, map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        int[] minDistCome = DijkstraC(X);
        int[] minDistGo = DijkstraG(X);

        int max = 0;
        for (int start = 1; start <= N; start++) {
            if(start == X)  continue;
            max = Math.max(max, minDistGo[start] + minDistCome[start]);
        }

        System.out.println(max);
    }

    private static int[] DijkstraC(int end) {
        int[] minDist = new int[N + 1];
        PriorityQueue<Data> queue = new PriorityQueue<Data>(Comparator.comparing(o -> o.cost));
        boolean[] visited = new boolean[N + 1];
        queue.offer(new Data(end, 0));
        Arrays.fill(minDist, MAX_VALUE);
        minDist[end] = 0;

        while (!queue.isEmpty()){
            Data data = queue.poll();

            visited[data.vertex] = true;

            for (int i = 1; i <= N ; i++) {
                if(map[i][data.vertex] > 0 && !visited[i] && minDist[i] > minDist[data.vertex] + map[i][data.vertex]){
                    minDist[i] = minDist[data.vertex] + map[i][data.vertex];
                    queue.offer(new Data(i, minDist[i]));
                }
            }
        }

        return minDist;
    }

    private static int[] DijkstraG(int start) {
        int[] minDist = new int[N + 1];
        PriorityQueue<Data> queue = new PriorityQueue<Data>(Comparator.comparing(o -> o.cost));
        boolean[] visited = new boolean[N + 1];
        queue.offer(new Data(start, 0));
        Arrays.fill(minDist, MAX_VALUE);
        minDist[start] = 0;

        while (!queue.isEmpty()){
            Data data = queue.poll();

            visited[data.vertex] = true;

            for (int i = 1; i <= N ; i++) {
                if(map[data.vertex][i] > 0 && !visited[i] && minDist[i] > minDist[data.vertex] + map[data.vertex][i]){
                    minDist[i] = minDist[data.vertex] + map[data.vertex][i];
                    queue.offer(new Data(i, minDist[i]));
                }
            }
        }

        return minDist;
    }

    static class Data{
        int vertex, cost;

        public Data(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
