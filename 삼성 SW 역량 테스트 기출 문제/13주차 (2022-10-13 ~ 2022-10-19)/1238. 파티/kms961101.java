package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {
	static public class Node implements Comparable<Node>{
		int weight;
		int index;
		public Node(int weight, int index) {
			super();
			this.weight = weight;
			this.index = index;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	static int INF = Integer.MAX_VALUE;
	static int N, M, X;
	static List<Node> graph[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(weight, to));
		}
		
		System.out.println(dijkstra());
	}
	
	private static int dijkstra() {
		int[] temp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int[] distance = new int[N + 1];
			Arrays.fill(distance, INF);
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, i));
			distance[i] = 0;
			while(!pq.isEmpty()) {
				Node n = pq.poll();
				int now_idx = n.index;
				int now_val = n.weight;
				for (int j = 0; j < graph[now_idx].size(); j++) {
					Node next = graph[now_idx].get(j);
					if(now_val + next.weight < distance[next.index]) {
						distance[next.index] = now_val + next.weight;
						pq.add(new Node(distance[next.index], next.index));
					}
				}
			}
			temp[i] = distance[X];
		}
		
		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, X));
		distance[X] = 0;
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int now_idx = n.index;
			int now_val = n.weight;
			for (int j = 0; j < graph[now_idx].size(); j++) {
				Node next = graph[now_idx].get(j);
				if(now_val + next.weight < distance[next.index]) {
					distance[next.index] = now_val + next.weight;
					pq.add(new Node(distance[next.index], next.index));
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			temp[i] += distance[i];
		}
		Arrays.sort(temp);
		return temp[N];
	}
}
