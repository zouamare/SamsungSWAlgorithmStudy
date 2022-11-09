package BOJ;

import java.util.HashMap;
import java.util.Scanner;

public class BOJ_4195_친구네트워크 {

    static int[] p = new int[200002];
    static int[] f = new int[200002];
    static HashMap<String, Integer> h = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int F = sc.nextInt();
            int cnt = 1;
            for (int i = 1; i <= F*2; i++) {
                p[i] = i;
                f[i] = 1;
            }

            for (int i = 0; i < F; i++) {
                String a = sc.next();
                String b = sc.next();

                if(!h.containsKey(a)) {
                    h.put(a, cnt++);
                }
                if(!h.containsKey(b)) {
                    h.put(b, cnt++);
                }

                System.out.println(union(h.get(a), h.get(b)));
            }
        }
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            p[b] = a;
            f[a] += f[b];
            f[b] = 1;
        }

        return f[a];
    }

    static int find(int a) {
        if(p[a] == a) {
            return a;
        } else {
            return p[a] = find(p[a]);
        }
    }
}
