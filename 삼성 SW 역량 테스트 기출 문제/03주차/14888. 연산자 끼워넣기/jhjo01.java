import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_14888 {
	static int N, R, T;
	static int[] op, numbers;
	static boolean[] isSelected;
	static int max=-999999999, min=999999999;
	static List<Integer> data;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		data = new ArrayList<>();
		
		for(int i = 0; i < N; i++)
		{
			data.add(sc.nextInt());
		}
		
		int add = sc.nextInt();
		int sub = sc.nextInt();
		int mul = sc.nextInt();
		int div = sc.nextInt();
		R =  add+sub+mul+div;
		op = new int[R];
		numbers = new int[R];
		isSelected = new boolean[R+1];
		for(int i = 0; i < op.length; i++)
		{
			if(add != 0)
			{
				op[i] = 1;
				add--;
				continue;
			}
			if(sub != 0)
			{
				op[i] = 2;
				sub--;
				continue;
			}
			if(mul != 0) 
			{
				op[i] = 3;
				mul--;
				continue;
			}
			if(div != 0) 
			{
				op[i] = 4;
				div--;
				continue;
			}
		}
		
		perm(0);
		System.out.println(max + "\n" + min);
		
		
	}
	
	private static void perm(int cnt) // cnt : 직전까지 뽑은 순열에 포함된 수의 개수, cnt + 1 번째에 해당하는 순열에 포함될 수를 뽑기
	{
		if(cnt == R)
		{
//			System.out.println(Arrays.toString(numbers));
			getMinMax(numbers, data);
			return;
		}
		// 가능한 모든 수에 대해 시도
		for (int i = 0; i < R; i++)
		{
			// 시도하는 수가 선택되었는지 판단
			if(isSelected[i]) continue;
			// 선택되지 않았다면 수를 사용
			numbers[cnt] = op[i-1];
			isSelected[i] = true;
			// 다음 수 뽑으러 가기
			perm(cnt + 1);
			// 사용했던 수에 대한 선택을 되돌리기
			isSelected[i] = false;
		}
	}
	
	private static void getMinMax(int[] arr, List<Integer> data)
	{
		List<Integer> d = new ArrayList<>();
		d.addAll(data);
		for(int i = 0; i < R; i++)
		{
			switch(arr[i])
			{
			case 1:
				d.set(0, d.get(0) + d.get(1));
				d.remove(1);
				break;
			case 2:
				d.set(0, d.get(0) - d.get(1));
				d.remove(1);
				break;
			case 3:
				d.set(0, d.get(0) * d.get(1));
				d.remove(1);
				break;
			case 4:
				d.set(0, d.get(0) / d.get(1));
				d.remove(1);
				break;
			}
		}
		max = Math.max(max, d.get(0));
		min = Math.min(min, d.get(0));
	}
	
}
