import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, C, maxArrLen, result;
	static int[][] arr, maxArr;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			maxArr = new int[N][N-M+1];
			result = 0;
			maxArrLen = N-M+1;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N-M+1; j++) {
					maxArr[i][j] = maxSubSet(i, j, 0, 0, 0);
				}
			}
			
			comb(0, 0, 0, 0);
			
			System.out.println("#"+t+" "+result);
		}
	}
	
	static void comb(int pi, int pj, int cnt, int sum) {
		if (cnt == 2) {
			result = Math.max(result, sum);
			return;
		}
		
		for(int i=pi; i<N; i++) {
			for(int j = pi == i ? pj : 0; j<maxArrLen; j++) {
				comb(i, j + M, cnt+1, sum + maxArr[i][j]);
			}
		}
	}
	
	static int maxSubSet(int i, int j, int sum, int doubleSum, int cnt) {
		if (sum > C) return 0;
		
		if (cnt == M) {
			return doubleSum;
		}
		int result = 0;
		
		result = Math.max(maxSubSet(i, j+1, sum + arr[i][j], doubleSum + arr[i][j]*arr[i][j], cnt+1),
				maxSubSet(i, j+1, sum , doubleSum, cnt+1));
		
		return result;
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static void print(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}