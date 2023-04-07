import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int max = Integer.MIN_VALUE, N;
	static char[] problem;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		problem = br.readLine().toCharArray();
		recur(0, 0);
		System.out.println(max);
	}
	
	public static int calc(int a, int b, char oper) {
		if (oper == '+') 
			return a + b;
		else if (oper == '-') 
			return a - b;
		else 
			return a * b;
	}
	
	
	public static void recur(int idx, int rst) {
		if (idx >= N) {
			max = Math.max(rst, max);
			return;
		}
		
		char operand = idx == 0 ? '+' : problem[idx-1];
		
		if (idx+2 < N) {
			int bracketNum = calc(problem[idx]-'0', problem[idx+2]-'0', problem[idx+1]);
			recur(idx+4, calc(rst, bracketNum, operand));
		}
		recur(idx+2, calc(rst, problem[idx]-'0', operand));
		
	}
}