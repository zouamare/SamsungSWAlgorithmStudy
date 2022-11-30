import java.io.*;
import java.util.*;

public class Main {
    static int k;
    static Set<String> set = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            for (int i = 0; i < k; i++) {
                String tmp = st.nextToken();
                sb.append("*" + tmp);
                set.add(sb.toString());
            }
            sb.setLength(0);
        }

        Iterator<String> iter = set.iterator();
        while(iter.hasNext()) {
            String tmp = iter.next();
            int cnt = tmp.length() - tmp.replace(String.valueOf('*'), "").length();
            int pos = tmp.lastIndexOf("*");
            String word = tmp.substring(pos + 1);
            for (int i = 0; i < cnt - 1; i++) {
                sb.append("--");
            }
            sb.append(word + "\n");
        }

        System.out.print(sb);
    }
}