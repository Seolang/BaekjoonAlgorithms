import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N, answer;
	static int[][] map, dp;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		} // end input
		
		System.out.println(DFS(0, 0));
	}
	
	static int DFS(int r, int c) {
		if (r == M-1 && c == N-1) {
			return 1;
		}
		
		if (dp[r][c] != -1) return dp[r][c];
		
		dp[r][c] = 0;
		
		for(int dir=0; dir<4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (isValid(nr, nc) && map[r][c] > map[nr][nc]) {
				dp[r][c] += DFS(nr, nc);
			}
		}
		
		return dp[r][c];
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < M && 0 <= c && c < N;
	}
	
	static void print() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}