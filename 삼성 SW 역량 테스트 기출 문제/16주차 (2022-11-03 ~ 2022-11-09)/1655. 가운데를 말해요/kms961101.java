package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1655_가운데를말해요 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(minHeap.size() == maxHeap.size()) maxHeap.add(num);
			else minHeap.add(num);
			if(!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
				int temp = minHeap.poll();
				minHeap.add(maxHeap.poll());
				maxHeap.add(temp);
			}
			sb.append(maxHeap.peek() + "\n");        
		}
		System.out.println(sb);
	}
}
