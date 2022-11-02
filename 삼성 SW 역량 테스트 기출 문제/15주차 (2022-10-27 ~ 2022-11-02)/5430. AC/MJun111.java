import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        outer : while(t-- > 0) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> dq = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine(),"[,]");
            for (int i = 0; i < n; i++)
                dq.add(Integer.parseInt(st.nextToken()));

            int r = 0;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R')
                    r++;
                else if (p.charAt(i) == 'D' && !dq.isEmpty()) {
                    if (r % 2 == 0)
                        dq.pollFirst();
                     else
                        dq.pollLast();
                }
                else {
                    System.out.println("error");
                    continue outer;
                }
            }

            StringBuilder sb = new StringBuilder();

            sb.append("[");
            if (r % 2 == 0) {
                Iterator<Integer> iter = dq.iterator();
                while (iter.hasNext())
                    sb.append(iter.next() + ",");
            }
            else {
                Iterator<Integer> rIter = dq.descendingIterator();
                while (rIter.hasNext())
                    sb.append(rIter.next() + ",");
            }
            if (sb.charAt(sb.length() - 1) == ',')
                sb.deleteCharAt(sb.length()-1);
            sb.append("]");

            System.out.println(sb.toString());
        }

    }
}