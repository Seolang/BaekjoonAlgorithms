import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		dp = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			int days = sc.nextInt();
			int cost = sc.nextInt();
			
			dp[i] = Math.max(dp[i-1], dp[i]);
			
			if (i+days-1 <= N)
				dp[i+days-1] = Math.max(dp[i+days-1], dp[i-1] + cost);
		}
		
		System.out.println(dp[N]);
	}
}