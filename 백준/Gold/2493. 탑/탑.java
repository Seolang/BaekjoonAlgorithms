import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Deque<Integer> stk = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=n-1; i>=0; i--) {
			int curHeight = arr[i];
			
			while(!stk.isEmpty() && arr[stk.peek()] <= curHeight) {
				arr[stk.poll()] = i+1;
			}
			stk.push(i);
		}
		
		while(!stk.isEmpty()) {
			arr[stk.poll()] = 0;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int s : arr) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		System.out.println(sb);
	}

}

class Pair {
	int idx;
	int h;
	
	public Pair(int idx, int h) {
		this.idx = idx;
		this.h = h;
	}
}