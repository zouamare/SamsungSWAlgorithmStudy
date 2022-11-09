import java.io.*;
import java.util.*;

public class Main {
    static int f;
    static String str1, str2;
    static int[] parent = new int[200001];
    static int[] join = new int[200001];
    static Map<String, Integer> match;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            match = new HashMap<>();
            int idx = 1;

            f = Integer.parseInt(br.readLine());
            for (int i = 0; i < f; i++) {
                st = new StringTokenizer(br.readLine());
                str1 = st.nextToken();
                str2 = st.nextToken();

                if (!match.containsKey(str1)) {
                    parent[idx] = idx;
                    join[idx] = 1;
                    match.put(str1, idx++);
                }
                if (!match.containsKey(str2)) {
                    parent[idx] = idx;
                    join[idx] = 1;
                    match.put(str2, idx++);
                }

                union(match.get(str1), match.get(str2));

                sb.append(join[find(match.get(str1))] + "\n");
            }
        }
        System.out.println(sb);
    }

    static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
            join[x] += join[y];
        }
    }
}