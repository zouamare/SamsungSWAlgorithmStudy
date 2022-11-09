import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        long totalCnt = N;   // 총 감독관 수를 미리 더한다.
        for(int i = 0; i < N; i++){
            // 총감독관은 무조건 있어야 함. 단 1명.
            // 부감독관은 있어도 되고 없어도 된다. 하지만 맡지 못하는 학생이 없으면 안된다.
            // 따라서 A[i] - B > 0 일 때 C로 나누자. 나머지가 0이 아니면 + 1 처리
            int tmp = A[i] - B;
            if(tmp > 0)
                totalCnt += (tmp%C == 0 ? tmp/C : (tmp/C)+1);
        }
        System.out.println(totalCnt);
    }
}