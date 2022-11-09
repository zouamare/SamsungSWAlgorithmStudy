package coding_test.Algo2022년_10월.day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 재귀를 이용하여 풀이
public class BOJ_17609_회문 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String s = br.readLine();
            sb.append(GetResult(0, s.length() - 1, 0, s)).append("\n");
        }
        System.out.println(sb);
    }

    private static int GetResult(int left, int right, int cnt, String s) {
        if(cnt > 1)   return 2; // 다른 부분이 1 이상이라면 종료
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){  // 다를 경우
                // left + 1 == right 이고 left == right - 1 일 때는 둘 의 경우를 모두 고려하여 최선의 값 (최솟값) 을 도출하여 반환한다.
                if(s.charAt(left + 1) == s.charAt(right) && s.charAt(left) == s.charAt(right - 1))  return Math.min(GetResult(left + 1, right, cnt + 1, s), GetResult(left, right - 1, cnt + 1, s));
                // left != right - 1 인 경우는 left + 1 == right 인 경우와 left + 1 != right 인 경우 모두 올 수 있는데 어떠한 경우가 오더라도 결과는 정상적이게 도출되어 반환된다.
                // 1) left + 1 == right 인 경우, 이를 계산한 값을 반환하면 된다.
                // 2) left + 1 != right 인 경우, 이후의 값도 달라지게 되므로 결과적으로 값이 항상 2가 도출된다. 따라서 이러한 과정을 거쳐도 문제가 없음을 알 수 있다.
                else if(s.charAt(left) != s.charAt(right - 1)) return GetResult(left + 1, right, cnt + 1, s);
                // left + 1 != right 인 경우는 left == right - 1 인 경우만 오게 되므로 이를 계산한 값을 반환한다.
                else if(s.charAt(left + 1) != s.charAt(right)) return GetResult(left, right - 1, cnt + 1, s);
                break;
            }
            // 값이 같을 경우에는 left는 1 증가하고 right는 1 감소한다.
            left++;
            right--;
        }
        return cnt;
    }
}
