import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] dist = new int[n+1][n+1];
		int answer = 0;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if (i != j) {
					dist[i][j] = 10000;
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken());
			int big = Integer.parseInt(st.nextToken());
			
			dist[small][big] = 1;
		}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if (i != k && j != k && i != j) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			int cnt = 0;
			for(int j=1; j<=n; j++) {
				if (i != j && Math.min(dist[i][j], dist[j][i]) != 10000) {
					cnt++;
				}
			}
			if (cnt == n-1) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}