package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282_해킹{
	public static class Node implements Comparable<Node>{
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
	static int MaxV = Integer.MAX_VALUE;
	static int n, d, c;
	static int[][] map;
	static List<Node> graph[];
	static int[] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken()) - 1;
			graph = new ArrayList[n];
			for (int j = 0; j < n; j++) {
				graph[j] = new ArrayList<Node>();
			}
			for (int j = 0; j < d; j++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken()) - 1;
				int from = Integer.parseInt(st.nextToken()) - 1;
				int w = Integer.parseInt(st.nextToken());
				graph[from].add(new Node(w, to));
			}
			dijkstra();
			int cnt = 0;
			int ans = 0;
			for (int j = 0; j < n; j++) {
				if(distance[j] != MaxV) cnt++;
				if(distance[j] != MaxV && ans < distance[j]) ans = distance[j];
			}
			System.out.println(cnt + " " + ans);
		}
	}
	
	private static void dijkstra() {
		distance = new int[n];
		Arrays.fill(distance, MaxV);
		distance[c] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, c));
		
		while(!pq.isEmpty()) {
			int now_min = MaxV;
			int now_idx = -1;
			
			Node node = pq.poll();
			now_min = node.weight;
			now_idx = node.index;
			
			for (int i = 0; i < graph[now_idx].size(); i++) {
				Node next = graph[now_idx].get(i);
				if(next.weight + now_min < distance[next.index]) {
					distance[next.index] = next.weight + now_min;
					pq.add(new Node(distance[next.index], next.index));
				}
			}
		}
	}
}
