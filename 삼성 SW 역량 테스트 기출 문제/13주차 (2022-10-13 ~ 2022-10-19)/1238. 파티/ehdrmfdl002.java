package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_1238_파티 {
	static class Node implements Comparable<Node>{
		int index, dist;

		public Node(int index, int dist) {
			super();
			this.index = index;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	static int n, m, x;
	static int[] arr1, arr2;
	static ArrayList<Node>[] list1, list2;
	static boolean[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		x = sc.nextInt();
		
		// x에서 각 마을까지의 최단거리를 구하기 위한 리스트
		list1 = new ArrayList[n + 1];
		// 마을에서 x까지의 거리를 구하기 위한 리스트
		list2 = new ArrayList[n + 1];
		
		arr1 = new int[n + 1];
		arr2 = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			list1[i] = new ArrayList<>();
			list2[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int time = sc.nextInt();
			
			list1[start].add(new Node(end, time));
			list2[end].add(new Node(start, time));
			
		}
		
		dijkstra(x, list1, arr1);
		dijkstra(x, list2, arr2);
		
		
		int ans = 0;
		
		for(int i = 1; i <= n; i++) {
			ans = Math.max(ans, arr1[i] + arr2[i]);
		}
		
		System.out.println(ans);
	}
	
	private static void dijkstra(int start, ArrayList<Node>[] list, int[] arr) {
		check = new boolean[n + 1];
		Arrays.fill(arr, Integer.MAX_VALUE);
		
		arr[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			int current = pq.poll().index;

			if(check[current]) continue;
			check[current] = true;

			for(Node next : list[current]) {
				if(arr[next.index] > arr[current] + next.dist) {
					arr[next.index] = arr[current] + next.dist;
					pq.offer(new Node(next.index, arr[next.index]));
				}
			}
		}
	}
	
}
