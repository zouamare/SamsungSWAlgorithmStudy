package coding_test.Algo2022년_12월.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PG_순위검색 {
    public static void main(String[] args) {
        int[] result = solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
        System.out.println(Arrays.toString(result));
    }
    
    public static int[] solution(String[] info, String[] query) {
        int infoLen = info.length;
        
        boolean[] cpp = new boolean[infoLen];
        boolean[] java = new boolean[infoLen];
        boolean[] python = new boolean[infoLen];
        boolean[] backend = new boolean[infoLen];
        boolean[] frontend = new boolean[infoLen];
        boolean[] junior = new boolean[infoLen];
        boolean[] senior = new boolean[infoLen];
        boolean[] chicken = new boolean[infoLen];
        boolean[] pizza = new boolean[infoLen];
        int[] score = new int[infoLen];

        for (int i = 0; i < infoLen; i++) {
            String[] data = info[i].split(" ");

            // 언어
            switch (data[0]){
                case "java":
                    java[i] = true;
                    break;
                case "python":
                    python[i] = true;
                    break;
                case "cpp":
                    cpp[i] = true;
                    break;
            }

            // 직무
            switch (data[1]){
                case "frontend":
                    frontend[i] = true;
                    break;
                case "backend":
                    backend[i] = true;
                    break;
            }
            // 경력
            switch (data[2]){
                case "junior":
                    junior[i] = true;
                    break;
                case "senior":
                    senior[i] = true;
                    break;
            }

            // 음식
            switch (data[3]){
                case "pizza":
                    pizza[i] = true;
                    break;
                case "chicken":
                    chicken[i] = true;
                    break;
            }

            // 성적
            score[i] = Integer.parseInt(data[4]);

        }

        int queryLen = query.length;
        int[] answer = new int[queryLen];
        for (int i = 0; i < queryLen; i++) {
            String[] qData = query[i].split(" ");
            int count = 0;
            // 0 : 언어, 2: 직무, 4: 경력, 6: 음식, 7: 성적
            for (int j = 0; j < infoLen; j++) {
                // 성적
                if(score[j] >= Integer.parseInt(qData[7])){
                    boolean flag = true;
                    // 언어
                    if(!qData[0].equals("-")){
                        switch (qData[0]){
                            case "java":
                                if(!java[j]) flag = false;
                                break;
                            case "python":
                                if(!python[j])  flag = false;
                                break;
                            case "cpp":
                                if(!cpp[j]) flag = false;
                                break;
                        }
                    }

                    if(!flag)   continue;
                    // 직무
                    if(!qData[2].equals("-")){
                        switch (qData[2]){
                            case "frontend":
                                if(!frontend[j]) flag = false;
                                break;
                            case "backend":
                                if(!backend[j]) flag = false;
                                break;
                        }
                    }
                    if(!flag)   continue;
                    // 경력
                    if(!qData[4].equals("-")){
                        switch (qData[4]){
                            case "junior":
                                if(!junior[j]) flag = false;
                                break;
                            case "senior":
                                if(!senior[j]) flag = false;
                                break;
                        }
                    }
                    if(!flag)   continue;
                    // 음식
                    if(!qData[6].equals("-")){
                        switch (qData[6]){
                            case "pizza":
                                if(!pizza[j]) flag = false;
                                break;
                            case "chicken":
                                if(!chicken[j]) flag = false;
                                break;
                        }
                    }
                    if(!flag)   continue;
                    count++;
                }
            }
            answer[i] = count;
        }
        
        return answer;
    }
}
