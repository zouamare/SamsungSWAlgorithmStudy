package BOJ;

import java.util.Scanner;

public class BOJ_17609_회문 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int t = 0;  t < T; t++) {
            String str = sc.next();
            int size = str.length();
            int offsetL = 0;
            int offsetR = 0;
            int ans = 0;
            int cnt = 0;
            int previ = 0;

            boolean check = false;
            boolean check2 = false;

            for(int i = 0; i < size/2; i++) {

                char a = str.charAt(i + offsetL);
                char b = str.charAt(size-i-1 - offsetR);

                if(a != b) {
                    cnt++;
                    char a2 = a;
                    char b2 = str.charAt(size-i-2);

                    if(check && !check2) {
                        offsetR--;
//						if(str.charAt(i+1+offsetL) == b) {
//							offsetL++;
//						}
                        cnt = 0;
                        i = previ - 1;
                        check2 = true;
                        continue;
                    }

                    if(a == str.charAt(size-i-2-offsetR) && !check) {
                        check = true;
                        previ = i;
                        offsetR++;
                    } else if(str.charAt(i+1+offsetL) == b) {
                        offsetL++;
                    }

                    if(cnt >= 2) {
                        ans = 2;
                        break;
                    }
                    i--;
                }
            }
            if(cnt == 1) {
                ans = 1;
            }

            System.out.println(ans);

        }
    }
}
