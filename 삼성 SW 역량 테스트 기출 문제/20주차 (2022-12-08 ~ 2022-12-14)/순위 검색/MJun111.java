import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        int[] ans = solution(info, query);

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + ", ");
        }
    }

    static Map<String, ArrayList<Integer>> map = new HashMap<>();
    static ArrayList<Integer> list;
    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // info로 될 수 있는 모든 경우의 수, 점수 맵에 저장
        for (int i = 0; i < info.length; i++) {
            subset("", 0, info[i].split(" "));
        }

        // 맵에 저장된 리스트들 오름차순 정렬
        for (String key : map.keySet()) {
            ArrayList<Integer> score = map.get(key);
            Collections.sort(score);
        }

        // 쿼리 별 이분 탐색
        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and", "");
            String[] str = query[i].split(" ");
            int score = Integer.parseInt(str[4]);

            answer[i] = binarySearch(str[0] + str[1] + str[2] + str[3], score);
        }

        return answer;
    }

    static void subset(String str, int idx, String[] infoData) {
        if (idx == 4) {
            if (!map.containsKey(str)) {
                list = new ArrayList<>();
                list.add(Integer.parseInt(infoData[4]));
                map.put(str, list);
            } else {
                map.get(str).add(Integer.parseInt(infoData[4]));
            }
            return;
        }

        subset(str + "-", idx + 1, infoData);
        subset(str + infoData[idx], idx + 1, infoData);
    }

    static int binarySearch(String str, int score) {
        if (!map.containsKey(str)) return 0;

        ArrayList<Integer> list = map.get(str);
        int L = 0, R = list.size() - 1;

        while (L <= R) {
            int M = (L + R) / 2;
            
            if (list.get(M) < score) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }

        return list.size() - L;
    }

}