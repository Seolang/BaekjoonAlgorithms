import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] map = new ArrayList[N+1];
		int[] degree = new int[N+1];
		boolean[] visit = new boolean[N+1];
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		for(int i=1; i<=N; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken());
			int tall = Integer.parseInt(st.nextToken());
			
			map[small].add(tall);
			degree[tall]++;
		} // end input
		
		
		for(int i=1; i<=N; i++) {
			if (degree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now).append(" ");
			
			for(int child : map[now]) {
				if (--degree[child] == 0) {
					q.offer(child);
				}
			}
		}
			
		System.out.println(sb);
	}
}