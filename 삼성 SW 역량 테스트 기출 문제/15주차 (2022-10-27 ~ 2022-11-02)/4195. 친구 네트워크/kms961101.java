package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_4195_친구네트워크 {
	static int N;
	static int[] cnt, parent;
	static HashMap<String, Integer> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			parent = new int[N * 2];
			cnt = new int[N * 2];
			Arrays.fill(cnt, 1);
			int idx = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String from = st.nextToken();
				String to = st.nextToken();
				if(!map.containsKey(from)) {
					parent[idx] = idx;
					map.put(from, idx++);
				}
				if(!map.containsKey(to)) {
					parent[idx] = idx;
					map.put(to, idx++);
				}
				union(map.get(from), map.get(to));
//				System.out.println(cnt[find(map.get(to))]);
				sb.append(cnt[find(map.get(to))]).append("\n");
			}
			System.out.println(sb);
		}
	}
	
	private static void union(int from, int to) {
		from = find(from);
		to = find(to);
		if(from == to) return;
		parent[to] = from;
		cnt[from] += cnt[to];
	}

	private static int find(int from) {
		if(parent[from] == from) return from;
		return parent[from] = find(parent[from]);
	}
}
