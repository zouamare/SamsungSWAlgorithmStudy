package BOJ;

import java.util.Scanner;

public class BOJ_17413_단어뒤집기2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int idx = 0;
        int lastIdx = s.length();

        StringBuilder sb = new StringBuilder();

        StringBuilder temp = new StringBuilder();

        while ( idx < lastIdx) {
            if(s.charAt(idx) == '<') {
                sb.append(temp.reverse());
                temp = new StringBuilder();
                while ( s.charAt(idx) != '>') {
                    sb.append(s.charAt(idx));
                    idx++;
                }
                sb.append(s.charAt(idx));
                idx++;
                continue;
            }

            if(s.charAt(idx) == ' ') {
                sb.append(temp.reverse().append(' '));
                temp = new StringBuilder();
                idx++;
                continue;
            }

            temp.append(s.charAt(idx));
            idx++;

        }

        sb.append(temp.reverse());

        System.out.println(sb);
    }
}
