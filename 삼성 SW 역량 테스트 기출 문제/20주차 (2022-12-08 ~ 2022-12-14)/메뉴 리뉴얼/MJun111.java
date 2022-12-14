import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 5};

        String[] ans = solution(orders, course);
       for (String str : ans) {
           System.out.println(str);
       }
    }

    static Map<String, Integer> map;
    static StringBuilder sb;
    static ArrayList<String> ans = new ArrayList<>();
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        // 입력 문자열 정렬
        for (int i = 0; i < orders.length; i++) {
//            orders[i] = Stream.of(orders[i].split("")).sorted().collect(Collectors.joining());
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = new String(chars);
        }

        // 원하는 코스 길이 별 단품 메뉴 조합 판별
        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();

            for (int j = 0; j < orders.length; j++) {
                // 주문한 음식 길이 < 코스 길이
                if (orders[j].length() < course[i]) continue;
                sb = new StringBuilder();

                // 코스 길이 별 음식 조합 세기
                combi(orders[j], course[i], 0, 0);
            }

            // 가장 많이 나온 음식 조합 채택
            ArrayList<String> list = new ArrayList<>();
            int max = 2;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int cnt = entry.getValue();

                // 갱신하면 기존 리스트 비워주고 추가
                if (cnt > max) {
                    max = cnt;
                    list.clear();
                    list.add(key);
                } else if (cnt == max) {    // 기존과 같다면 리스트에 추가
                    list.add(key);
                }
            }

            // 리스트에 있는 조합 최종 ans 리스트에 추가
            for (String str : list) {
                ans.add(str);
            }
        }

        // 사전 순 정렬
        Collections.sort(ans);

        // 테스트 출력
        System.out.println(ans);

        answer = ans.toArray(new String[ans.size()]);
        return answer;
    }

    public static void combi(String word, int c, int cnt, int start) {
        if (cnt == c) {
            String str = sb.toString();

            // 맵에 존재하면 value + 1, 없으면 1 추가
            map.put(str, map.containsKey(str) ? map.get(str) + 1 : 1);
            return;
        }

        for (int i = start; i < word.length(); i++) {
            sb.append(word.charAt(i));
            combi(word, c, cnt + 1, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}