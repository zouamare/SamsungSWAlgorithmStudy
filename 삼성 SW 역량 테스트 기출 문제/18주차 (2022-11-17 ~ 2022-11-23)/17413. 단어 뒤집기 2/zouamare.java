package coding_test.Algo2022년_11월.day29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17413_단어뒤집기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder ans = new StringBuilder();
        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = input.length(); i < size; i++) {
            if(input.charAt(i) == '<'){
                flag = false;
                ans.append(sb.reverse());
                sb = new StringBuilder();
            }
            if(input.charAt(i) == ' '){
                ans.append(sb.reverse());
                sb = new StringBuilder();
                ans.append(" ");
            }
            else{
                if(flag){
                    sb.append(input.charAt(i));
                }
                else{
                    ans.append(input.charAt(i));
                }
            }
            if(input.charAt(i) == '>'){
                flag = true;
            }
        }
        ans.append(sb.reverse());
        System.out.println(ans);
    }
}
