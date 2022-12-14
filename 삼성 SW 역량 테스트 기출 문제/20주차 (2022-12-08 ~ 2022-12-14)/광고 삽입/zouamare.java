package coding_test.Algo2022년_12월.day14;

import java.util.Arrays;

public class PG_광고삽입 {
    /*
    * 가닥은 잡았으나.. 세부적인 부분에서 헤맸다.
    *
    * 처음에는 n^2의 시간복잡도로 문제를 풀이하고자 했으나 그러면 시간 초과가 발생했다.
    * 이후 누적합 방식이 생각이 나서 해당 방식으로 풀이했으나 일부 테스트케이스를 통과하지 못했다.
    *
    * 그래서 https://dev-note-97.tistory.com/156 이 블로그를 참고하여 해결했다.
    *
    *
    * */
    public static void main(String[] args){
        System.out.println(solution("02:03:55"	, "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
    }

    final static int HOUR_DIV_VAL = 3600;
    final static int MINUTE_DIV_VAL = 60;
    final static int MAX_VAL = 360_000;
    public static String solution(String play_time, String adv_time, String[] logs) {
        int logLength = logs.length;
        long[] dp = new long[MAX_VAL + 1];

        // log 시간 int로 변경
        for (int i = 0; i < logLength; i++) {
            String[] startEnd = logs[i].split("-");
            dp[timeToInt(startEnd[0])]++;
            dp[timeToInt(startEnd[1])]--;
        }

        for (int i = 1; i <= MAX_VAL; i++) {
            dp[i] = dp[i] + dp[i-1];
        }

        for (int i = 1; i <= MAX_VAL; i++) {
            dp[i] = dp[i] + dp[i-1];
        }

        // play_time과 adv_time int로 변경
        int playTime = timeToInt(play_time);
        int advTime = timeToInt(adv_time);

        // 광고 시작 시간과, 시청자 누적 재생시간을 저장
        int adStartTime = 0;
        long maxTotalUserTime = 0;

        for (int i = advTime - 1; i < playTime; i++) {
            if(i >= advTime){
                long value = dp[i] - dp[i - advTime];
                if(maxTotalUserTime < value){
                    adStartTime = i - advTime + 1;
                    maxTotalUserTime = value;
                }
            }
            else{
                if(maxTotalUserTime < dp[i]){
                    adStartTime = i - advTime + 1;
                    maxTotalUserTime = dp[i];
                }
            }

        }

        return intToTime(adStartTime);
    }

    public static int timeToInt(String time){
        String[] timeArr = time.split(":");

        int result = 0;

        result += Integer.parseInt(timeArr[0]) * 60 * 60;
        result += Integer.parseInt(timeArr[1]) * 60;
        result += Integer.parseInt(timeArr[2]);

        return result;
    }

    public static String intToTime(int time){
        StringBuilder sb = new StringBuilder();
        sb.append(paddingZero(time / HOUR_DIV_VAL));
        sb.append(":");
        time %= HOUR_DIV_VAL;
        sb.append(paddingZero(time / MINUTE_DIV_VAL));
        sb.append(":");
        time %= MINUTE_DIV_VAL;
        sb.append(paddingZero(time));
        return sb.toString();
    }

    private static String paddingZero(int value){
        String valueString = Integer.toString(value);
        if(valueString.length() < 2){
            valueString = "0" + valueString;
        }
        return valueString;
    }

    public static class Time{
        int start, end;

        public Time (int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
