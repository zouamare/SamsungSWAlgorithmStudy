package solo.study;

import java.util.Scanner;

public class BOJ_12865_평범한배낭 {
	static class Node{
		int weight, value;

		public Node(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		int bag[][] = new int[n + 1][k + 1];
		Node arr[] = new Node[n + 1];
		
		for(int i = 1; i <= n; i++) {
			arr[i] = new Node(sc.nextInt(), sc.nextInt());
		}
		
		
		for(int i = 1; i <= n; i++) {
			int weight = arr[i].weight;
			int value = arr[i].value;
			for(int j = 1; j <= k; j++) {
				if(j - weight >= 0) {
					bag[i][j] = Math.max(bag[i - 1][j], bag[i - 1][j - weight] + value);
                } else {
                    bag[i][j] = bag[i - 1][j];
                }
            }
        }
 
        System.out.println(bag[n][k]);
	}
}
