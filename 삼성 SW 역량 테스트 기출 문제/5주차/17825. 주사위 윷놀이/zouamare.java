import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] map, sequence;
    static int maxSum;
    public static void main(String[] args) throws IOException {
        int[] piece = new int[4];	// 모두의 초기값은 0
        maxSum = Integer.MIN_VALUE;
        init();
        perm(piece, 0, 0);
        System.out.println(maxSum);
    }

    private static void perm(int[] piece, int depth, int sum) {
        // TODO Auto-generated method stub
        if(depth == 10) {
            maxSum = Math.max(maxSum, sum);
            return;
        }
        int[] newPiece = piece.clone();
        for(int i = 0; i < 4; i++) {
            if(map[newPiece[i]] == 99)	continue;
            int nowIdx = newPiece[i];

            if(nowIdx == 5)	nowIdx = 22;	// blue 10
            else if(nowIdx == 10)	nowIdx = 31;	// blue 20
            else if(nowIdx == 15)	nowIdx = 39;	// blue 30

            // 이상태에서 sequence[depth]의 결과가 끝에 도달하는지 확인
            // 끝은 idx 21, 30, 38, 47
            if( nowIdx <= 20 && nowIdx + sequence[depth] > 20)	nowIdx = 21;
            else if(nowIdx <= 29 && nowIdx + sequence[depth] > 29)	nowIdx = 30;
            else if(nowIdx <= 37 && nowIdx + sequence[depth] > 37)	nowIdx = 38;
            else if(nowIdx <= 46 && nowIdx + sequence[depth] > 46)	nowIdx = 47;
            else nowIdx = nowIdx + sequence[depth];

            boolean flag = true;
            // 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다.
            if(map[nowIdx] != 99) {
                for(int j = 0; j < 4; j++) {
					if(i==j)	continue;
                    if(newPiece[j] == nowIdx
                            || (map[nowIdx] == 25 && map[newPiece[j]]== 25) // 25 처리
                            || (nowIdx == 15 && newPiece[j] == 15)  // 30 처리 (30의 경우 여러곳에서 중복되므로 idx로 하나하나 구분함)
                            || (nowIdx == 27 && newPiece[j] == 27)  // 30 처리 (30의 경우 여러곳에서 중복되므로 idx로 하나하나 구분함)
                            || (nowIdx == 35 && newPiece[j] == 35)  // 30 처리 (30의 경우 여러곳에서 중복되므로 idx로 하나하나 구분함)
                            || (nowIdx == 44 && newPiece[j] == 44)  // 30 처리 (30의 경우 여러곳에서 중복되므로 idx로 하나하나 구분함)
                            || (map[nowIdx] == 35 && map[newPiece[j]]== 35) // 35 처리
                            || (map[nowIdx] == 40 && map[newPiece[j]]== 40)) {  // 40 처리
                        flag = false;
                        break;
                    }
                }
            }

            if(flag) {	// 앞으로 이동할 수 있는가?? 내가 진행할 자리에 다른 말이 있는가?
                newPiece[i] = nowIdx;
                if(map[nowIdx] != 99)	perm(newPiece, depth + 1, sum + map[nowIdx]);
                else	perm(newPiece, depth + 1, sum);
                newPiece[i] = piece[i];	// 이전 값으로 되돌리기
            }
        }

    }

    static void init() throws IOException {
        map = new int[]{
                0, 2, 4, 6, 8, 10,	// 0 : 출발점
                12, 14, 16, 18, 20,
                22, 24, 26, 28, 30,
                32, 34, 36, 38, 40, 99,	// 42: 도착점 (idx 21)

                10, 13, 16, 19, 25, // 10이 시작점 (idx 22)
                30, 35, 40, 99,	// 99가 도착점 (idx 30)

                20, 22, 24, 25,	// 20이 시작점 (idx 31)
                30, 35, 40, 99,	// 99가 도착점 (idx 38)

                30, 28, 27, 26,	// 30이 시작점 (idx 39)
                25, 30, 35, 40, 99	// 99가 도착	(idx 47)
        };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sequence = new int[10];
        for(int i = 0; i < 10; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

    }
}