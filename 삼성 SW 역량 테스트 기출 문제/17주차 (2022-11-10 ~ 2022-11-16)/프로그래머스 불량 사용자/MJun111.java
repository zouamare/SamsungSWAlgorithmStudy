import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

        System.out.println(solution(user_id, banned_id));
    }

    static int bSize;
    static Set<String> tSet = new TreeSet<>();
    static Set<String> hSet = new HashSet<>();
    static List<String>[] list;
    static StringBuilder sb = new StringBuilder();
    static public int solution(String[] user_id, String[] banned_id) {
        bSize = banned_id.length;
        list = new List[bSize];

        for (int i = 0; i < banned_id.length; i++) {
            list[i] = new ArrayList<>();

            for (int j = 0; j < user_id.length; j++) {
                if (user_id[j].length() != banned_id[i].length()) continue;

                boolean flag = true;
                for (int k = 0; k < banned_id[i].length(); k++) {
                    if (banned_id[i].charAt(k) == '*') continue;

                    if (banned_id[i].charAt(k) != user_id[j].charAt(k)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    list[i].add(user_id[j]);
                }
            }
        }

        combi(0);

        return hSet.size();
    }

    static public void combi(int depth) {
        if (depth == bSize) {
            if (tSet.size() != bSize) return;

            Iterator<String> iter = tSet.iterator();
            while (iter.hasNext()) {
                sb.append(iter.next() + " ");
            }
            hSet.add(sb.toString());
            sb.setLength(0);

            return;
        }

        for (int i = 0; i < list[depth].size(); i++) {
            String cur = list[depth].get(i);
            int size = tSet.size();

            tSet.add(cur);
            combi(depth + 1);

            // 이번 시행에 추가했었다면 지우기
            if (size < tSet.size())
                tSet.remove(cur);
        }

    }

}