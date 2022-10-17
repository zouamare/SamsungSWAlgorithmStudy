package coding_test.Algo2022년_10월.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 와.. 이차원 배열로 만들면 아예 메모리 초과가 뜨기도 하는구나..
* 최대 크기가 10000이라서 당연히 될거라고 생각했는데 10000 * 10000 = 1_0000_0000 1억개의 크기는 배열로 만들기 힘든것 같다.
* */

public class BOJ_10282_해킹 {
    static final int MAX_VALUE = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken());   // 의존성 개수
            int c = Integer.parseInt(st.nextToken());   // 해킹당한 컴퓨터의 번호

            ArrayList<Data>[] graph = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[to].add(new Data(from, cost));
            }

            int[] minDist = new int[n + 1];
            PriorityQueue<Data> priorityQueue = new PriorityQueue<>(Comparator.comparing(o -> o.value));
            Arrays.fill(minDist, MAX_VALUE);
            boolean[] visited = new boolean[n + 1];
            priorityQueue.offer(new Data(c, 0));
            minDist[c] = 0;
            int cnt = 0;
            int time = 0;

            while(!priorityQueue.isEmpty()){
                Data v = priorityQueue.poll();

                if(minDist[v.vertex] < v.value) continue;
                visited[v.vertex] = true;
                time = v.value;
                cnt++;

                for (Data data : graph[v.vertex]) {
                    if(!visited[data.vertex] &&  minDist[data.vertex] > minDist[v.vertex] + data.value){
                        minDist[data.vertex] = minDist[v.vertex] + data.value;
                        priorityQueue.add(new Data(data.vertex, minDist[data.vertex]));
                    }
                }
            }


            sb.append(cnt).append(" ").append(time).append("\n");
        }
        System.out.println(sb);
    }

    static class Data{
        int vertex, value;

        public Data(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }
    }
}
