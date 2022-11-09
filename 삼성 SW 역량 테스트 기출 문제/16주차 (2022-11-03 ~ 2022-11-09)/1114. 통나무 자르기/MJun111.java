import java.io.*;
import java.util.*;

public class Main {
    static long l;
    static int k, c;
    static long[] cut;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Long.parseLong(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cut = new long[k + 2];
        cut[0] = 0;
        cut[1] = l;

        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < k + 2; i++) {
            cut[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(cut);

        long L = 0, R = l;
        long ansWood = l, ansPos = 0;
        while (L <= R) {
            long mid = (L + R) / 2;
            int cnt = 0;
            long cutPos = 0;
            long len = 0;
            // 자르는 구간별로 길이를 더해감
            for (int i = k; i >= 0; i--) {
                len += cut[i + 1] - cut[i];

                // 나무조각의 길이가 제한길이보다 더 길어지면 컷
                if (len > mid) {
                    len = cut[i + 1] - cut[i];
                    cnt++;

                    // 컷한 길이가 제한 길이보다도 길 경우 break
                    if (len > mid) {
                        cnt = c + 1;
                        break;
                    }
                }
            }

            // 자를 수 있는 횟수가 남아있을 경우 가장 작은 자르는 위치를 가져감
            if (cnt <= c) {
                cutPos = cut[1];

                // 자르를 수 있는 횟수 없으면 현재 위치 가져감
                if(cnt == c)
                    cutPos = len;

                ansWood = Math.min(mid, ansWood);
                ansPos = cutPos;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        System.out.println(ansWood + " " + ansPos);
    }
}