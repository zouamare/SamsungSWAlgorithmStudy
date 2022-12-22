package coding_test.Algo2022년_12월.day20;

import java.util.ArrayList;
import java.util.List;

public class PG_추석트래픽 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"}));
    }
    /*
    * 밀리초로 나누어서 하는 방법으로 하다보니 너무 길어져서 결국 답을 봐버렸다..!!
    * */

    static class Traffic{
        int start, end;

        public Traffic(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static int solution(String[] lines) {

        List<Traffic> list = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] data = lines[i].split(" ");    // 0: 날짜, 1: 시간, 2: 지속 시간
            int[] trafficData = getTimesByLinesData(data[1], data[2]);
            list.add(new Traffic(trafficData[0], trafficData[1]));
        }

        int max = 1;
        int cnt;
        for (int i = 0; i <= list.size(); i++) {
            cnt = 1;
            for (int j = i + 1; j < list.size(); j++) { // 배열은 응답완료시간 S를 기준으로 오름차순 정렬되어 있다.
                if(list.get(i).end + 1000 > list.get(j).start)  cnt++;  // j는 i보다 다음의 인덱스이므로 응답 완료시간이 i보다 클 수 밖에 없다.
            }
            if(cnt > max){
                max = cnt;
            }
        }

        return max;
    }

    private static int[] getTimesByLinesData(String start, String duration) {
        int endTime = timeToInt(start);
        // duration 처리
        duration = duration.replace("s", "");
        int startTime = endTime - (int)(Float.parseFloat(duration) * 1000) + 1;
        return new int[]{startTime, endTime};
    }

    private static int timeToInt(String start) {
        String[] data = start.split(":");
        // 0이 시간
        // 1이 분
        // 2가 초 . 밀리초
        int result = 0;

        result += Integer.parseInt(data[0]) * 1000 * 60 * 60;
        result += Integer.parseInt(data[1]) * 1000 * 60;

        String[] sec = data[2].split("\\.");
        result += Integer.parseInt(sec[0]) * 1000;
        result += Integer.parseInt(sec[1]);

        return result;
    }


}
