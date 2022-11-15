package coding_test.Algo2022년_11월.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://velog.io/@jini_eun/%EB%B0%B1%EC%A4%80-14725%EB%B2%88-%EA%B0%9C%EB%AF%B8%EA%B5%B4-Java-Python 블로그 참고
public class BOJ_14725_개미굴 {
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 트라이라는 자료 구조를 만드는 문제
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie("");
        Trie node;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            node = trie;
            for (int j = 0; j < K; j++) {
                String name = st.nextToken();
                int idx = -1;

                for (int k = 0; k < node.list.size(); k++) {
                    if(node.list.get(k).name.equals(name)){
                        idx = k;
                        break;
                    }
                }

                if(idx == -1){
                    node.list.add(new Trie(name));
                    node = node.list.get(node.list.size() - 1);
                }
                else{
                    node = node.list.get(idx);
                }
            }
        }

        print(trie, -1);
        System.out.println(ans);
    }

    private static void print(Trie trie, int depth) {
        Collections.sort(trie.list, new Comparator<Trie>() {
            @Override
            public int compare(Trie o1, Trie o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        if(depth != -1){
            for (int i = 0; i < depth; i++) {
                ans.append("--");
            }
            ans.append(trie.name).append("\n");
        }

        for(Trie tr : trie.list){
            print(tr, depth + 1);
        }
    }

    static class Trie {
        ArrayList<Trie> list;
        String name;

        Trie(String name){
            list = new ArrayList<>();
            this.name = name;
        }
    }
}
