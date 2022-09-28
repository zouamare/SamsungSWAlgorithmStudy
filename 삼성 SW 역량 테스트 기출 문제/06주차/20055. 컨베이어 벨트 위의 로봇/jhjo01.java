package BOJ;

import java.util.Scanner;

public class BOJ_20055_컨베이어벨트위의로봇
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 컨베이어 길이 / 2
        int K = sc.nextInt(); // 종료 조건
        int[][] data = new int[N*2][2]; // 컨베이어

        // 컨베이어 내구도 입력
        for(int i = 0; i < N*2; i++)
        {
            // [0] 내구도 [1] 로봇 유무
            data[i][0] = sc.nextInt();
        }

        int cnt = 0; // 몇번째 단계인지 Cnt
        int stopCnt = 0;
        while (stopCnt < K)
        {
            // 벨트 및 로봇 회전
            // 제일 뒤부터 스왑하는 식으로 구현
            int temp0 = data[N*2-1][0];
            int temp1 = data[N*2-1][1];
            for(int i = N*2-1; i > 0; i--)
            {
                data[i][0] = data[i-1][0];
                data[i][1] = data[i-1][1];
            }
            data[0][0] = temp0;
            data[0][1] = temp1;
//
//            for(int i = 0; i < N*2; i++)
//            {
//                System.out.print(data[i][0] + " ");
//            }
//            System.out.println();

                // 로봇이 내릴 위치에 있는지 검사
            if(data[N-1][1] == 1)
            {
                // 로봇 내리기
                data[N-1][1] = 0;
            }

            // 먼저 올라간 로봇부터 한칸 이동
            for(int i = N-2; i >= 0; i--)
            {
                // 로봇 다음 위치가 비었고 내구도 0이 아니면 이동
                if(data[i][1] == 1 && data[i+1][1] == 0 && data[i+1][0] != 0)
                {
                    data[i][1] = 0;
                    data[i + 1][1] = 1;
                    data[i + 1][0]--; // 이동하고 내구도 -1
                }
            }

//            for(int i = 0; i < N*2; i++)
//            {
//                System.out.print(data[i][0] + " ");
//            }
//            System.out.println();

                // 로봇이 내릴 위치에 있는지 검사
            if(data[N-1][1] == 1)
            {
                // 로봇 내리기
                data[N-1][1] = 0;
            }

            // 로봇 올리기
            // 처음 칸의 내구도가 0이 아니면 로봇 올림
            if(data[0][0] != 0)
            {
                data[0][1] = 1;
                data[0][0]--;
            }

//            for(int i = 0; i < N*2; i++)
//            {
//                System.out.print(data[i][0] + " ");
//            }
//            System.out.println();

            // 내구도 0인칸 검사
            int sum = 0;
            for(int i = 0; i < N*2; i++)
            {
                if(data[i][0] == 0) sum++;
            }
            stopCnt = Math.max(sum, stopCnt);
            cnt++;
//            for(int i = 0; i < N*2; i++)
//            {
//                System.out.print(data[i][0] + " ");
//            }
//            System.out.println();
        }

        System.out.println(cnt);
    }
}
