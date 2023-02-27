import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M;
	static int[] disjoint;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			disjoint = new int[N+1];
			for(int i=1; i<=N; i++) {
				disjoint[i] = i;
			}
			
			boolean[] group = new boolean[N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			int answer = 0;
			for(int i=1; i<=N; i++) {
				if (find(i) == i) answer++;
			}
			
			System.out.println("#"+t+" "+answer);
		}
	}
	
	static int find(int n) {
		if (disjoint[n] == n) return n;
		
		return disjoint[n] = find(disjoint[n]);
	}
	
	static boolean union(int main, int sub) {
		int mp = find(main);
		int sp = find(sub);
		
		if (mp == sp) return false;
		
		disjoint[sp] = mp;
		
		return true;
	}
}