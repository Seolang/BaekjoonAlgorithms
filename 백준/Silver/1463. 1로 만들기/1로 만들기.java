import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		
		for(int i=1; i<n; i++) {
			dp[i+1] = Math.min(dp[i+1], dp[i]+1);
			
			if (i*2 <= n) {
				dp[i*2] = Math.min(dp[i*2], dp[i]+1);
			}
			
			if (i*3 <= n) {
				dp[i*3] = Math.min(dp[i*3], dp[i]+1);
			}
		}
		
		System.out.println(dp[n]);
	}
}