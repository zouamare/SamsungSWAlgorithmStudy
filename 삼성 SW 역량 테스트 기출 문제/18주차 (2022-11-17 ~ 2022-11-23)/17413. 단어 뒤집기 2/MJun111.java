import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();

        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            if (flag) {
                if (str.charAt(i) != '>') {
                    sb.append(str.charAt(i));
                }
                else {
                    sb2.append(sb + ">");
                    sb.setLength(0);
                    flag = false;
                }
            }
            else {
                if (str.charAt(i) == '<') {
                    sb2.append(sb.reverse());
                    sb.setLength(0);
                    sb.append('<');
                    flag = true;
                }
                else if (str.charAt(i) == ' ') {
                    sb2.append(sb.reverse() + " ");
                    sb.setLength(0);
                } else {
                    sb.append(str.charAt(i));
                }
            }
        }
        sb2.append(sb.reverse());
        System.out.println(sb2);
    }
}