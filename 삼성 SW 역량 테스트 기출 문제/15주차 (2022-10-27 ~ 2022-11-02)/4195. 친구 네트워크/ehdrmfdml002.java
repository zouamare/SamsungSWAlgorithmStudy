package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_4195_친구네트워크 {

	static int[] parents, rank;
	static int size = 100_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Map<String, Integer> map = new HashMap<>();
		
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());

			parents = new int[2 * size];
			rank = new int[2 * size];
			for (int i = 1; i < 2 * size; i++) {
				parents[i] = i;
				rank[i] = 1;
			}

			int idx = 1;
			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				st = new StringTokenizer(line);
				for (int j = 0; j < 2; j++) {
					String name = st.nextToken();
					if (!map.containsKey(name)) {
						map.put(name, idx++);
					}
				}

				String[] network = line.split(" ");
				int to = map.get(network[0]);
				int from = map.get(network[1]);
				int num = union(to, from);
				System.out.println(num);
			}
		}
	}

	static int find(int x) {
		if (parents[x] == x)
			return x;
		int rx = find(parents[x]);
		return rx;
	}

	static int union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if (x > y) { // y가 부모
				parents[x] = y;
				rank[y] += rank[x];
				return rank[y];
			} else { // x가 부모
				parents[y] = x;
				rank[x] += rank[y];
			}
		}
		return rank[x];
	}
}