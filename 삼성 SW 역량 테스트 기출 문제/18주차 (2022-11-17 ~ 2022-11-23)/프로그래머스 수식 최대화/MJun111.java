import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String expression = "50*6-3*2";
        System.out.println(solution(expression));

    }

    static Map<Integer, Character> map = new HashMap<>();
    static long max = 0;
    public static long solution(String expression) {
        map.put(0, '+');
        map.put(1, '-');
        map.put(2, '*');

        ArrayList<Character> opers = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') opers.add(expression.charAt(i));
        }

        long nums[] = Arrays.stream(expression.split("\\+|-|\\*")).mapToLong(Long::parseLong).toArray();

        calc(1, 0, 0, 0, nums, opers);
        calc(0, 1, 0, 1, nums, opers);
        calc(0, 0, 1, 2, nums, opers);

        return max;
    }

    public static void calc(int o1, int o2, int o3, int idx, long[] nums, ArrayList<Character> opers) {
        long[] tmpNums = nums.clone();
        ArrayList<Character> tmpOpers = (ArrayList) opers.clone();

        while (tmpOpers.indexOf(map.get(idx)) >= 0) {
            int nIdx = tmpOpers.indexOf(map.get(idx));
            tmpOpers.remove(nIdx);

            switch (idx) {
                case 0: tmpNums[nIdx] += tmpNums[nIdx + 1]; break;
                case 1: tmpNums[nIdx] -= tmpNums[nIdx + 1]; break;
                case 2: tmpNums[nIdx] *= tmpNums[nIdx + 1]; break;
            }

            tmpNums = removeIdx(tmpNums, nIdx + 1);
        }

        if (o1 == 1 && o2 == 1 && o3 == 1)
            max = Math.max(max, Math.abs(tmpNums[0]));

        if (o1 == 0) calc(1, o2, o3, 0, tmpNums, tmpOpers);
        if (o2 == 0) calc(o1, 1, o3, 1, tmpNums, tmpOpers);
        if (o3 == 0) calc(o2, o2, 1, 2, tmpNums, tmpOpers);
    }

    public static long[] removeIdx(long[] arr, int idx) {
        long[] result = new long[arr.length - 1];
        System.arraycopy(arr, 0, result, 0, idx);
        if (arr.length != idx) {
            System.arraycopy(arr, idx + 1, result, idx, arr.length - idx - 1);
        }
        return result;
    }
}
