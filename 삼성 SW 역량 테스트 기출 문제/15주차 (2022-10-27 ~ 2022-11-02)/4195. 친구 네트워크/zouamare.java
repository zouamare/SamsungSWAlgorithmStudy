package coding_test.Algo2022년_10월.day27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// map과 union-find를 이용해서 풀었다. 문제 자체는 엄청 어렵지는 않았는데 연결된 애들도 계속 나온다는 걸 명시가 안되어 있어 그 부분을 -1 처리했더니 44%에서 틀렸었다 --;
public class BOJ_4195_친구네트워크 {
    static List<Integer> parent, counts;
    static Map<String, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());    // 친구 관계의 수

            parent = new ArrayList<>();
            counts = new ArrayList<>();

            map = new HashMap<>();
            int totalCnt = 0;

            for (int f = 0; f < F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String left = st.nextToken();
                String right = st.nextToken();

                if(map.get(left) == null){  // left가 없을 경우
                    map.put(left, totalCnt);
                    parent.add(totalCnt++);
                    counts.add(1);
                }

                if(map.get(right) == null){  // right가 없을 경우
                    map.put(right, totalCnt);
                    parent.add(totalCnt++);
                    counts.add(1);
                }

                ans.append(union(map.get(left), map.get(right))).append("\n");

            }

        }

        System.out.println(ans);

    }

    static int union(int leftIdx, int rightIdx){
        leftIdx = find(leftIdx);
        rightIdx = find(rightIdx);

        if(leftIdx == rightIdx) return counts.get(leftIdx);
        int sum = counts.get(leftIdx) + counts.get(rightIdx);
        if(leftIdx < rightIdx){
            parent.set(rightIdx, leftIdx);
            counts.set(leftIdx, sum);
        }
        else{
            parent.set(leftIdx, rightIdx);
            counts.set(rightIdx, sum);
        }
        return sum;
    }

    private static int find(int idx) {
        if(parent.get(idx) == idx)  return idx;
        parent.set(idx, find(parent.get(idx)));
        return parent.get(idx);
    }
}
