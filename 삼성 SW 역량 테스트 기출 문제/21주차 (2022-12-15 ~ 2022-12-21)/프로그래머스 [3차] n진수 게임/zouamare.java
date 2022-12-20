package coding_test.Algo2022년_12월.day20;

public class PG_n진수게임 {
    public static void main(String[] args) {
        System.out.println(solution(2,4,2,1));
    }

    public static String solution(int n, int t, int m, int p) {
        StringBuilder ans = new StringBuilder();
        /*
        * 나눠야 할 숫자 = i
        * 현재까지 추가한 숫자의 수 = cnt
        * 현재까지 숫자 길이의 총 합 = len
        * 선택해야 할 숫자의 위치 = iLen
        * */
        int i = 0;
        int cnt = 0;
        int len = 0;
        int iLen = p;
        while(cnt < t){
            String num = Integer.toString(i++, n);
            len += num.length();
           while(len >= iLen && cnt < t){
               char c = num.charAt(iLen - (len - num.length()) - 1);
               ans.append((Character.isLowerCase(c) ? Character.toUpperCase(c) : c));
               iLen += m;
               cnt++;
           }
        }

        return ans.toString();
    }
}
