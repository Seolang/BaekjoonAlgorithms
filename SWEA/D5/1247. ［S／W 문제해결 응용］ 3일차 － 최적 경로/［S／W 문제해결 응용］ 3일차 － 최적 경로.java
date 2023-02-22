import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static Pair[] locations;		// 고객 위치 리스트
	static boolean[] visit;
	static Pair company, home;			// 집 위치
	static int N, result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			locations = new Pair[N];
			result = Integer.MAX_VALUE;
			visit = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			// 회사 위치
			company = new Pair(
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())
			);
			// 집 위치
			home = new Pair(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			
			for(int i=0; i<N; i++) {
				locations[i] = new Pair(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}
			
			DFS(company, 0, 0);
			System.out.println("#" + t + " " + result);
		}
	}
	
	static void DFS(Pair loc, int dist, int cnt) {
		/*
		 * 현재까지 거리가 지금까지 최단거리보다 크면 pruning
		 */
		if (dist > result) {
			return;
		}
		
		/*
		 * 모든 고객을 순회했다면 집까지의 거리를 계산해서 최종 거리를 구한다
		 */
		if (cnt == N) {
			int finalDist = dist + calcDist(loc, home);
			result = Math.min(finalDist, result);
			return;
		}
		
		/*
		 * 각 고객을 순열로 방문하는 DFS
		 */
		for(int i=0; i<N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				DFS(locations[i], dist+calcDist(loc, locations[i]), cnt+1);
				visit[i] = false;
			}
		}
		
	}
	
	/*
	 * 두 지점 사이의 거리 계산 메소드
	 */
	static int calcDist(Pair a, Pair b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
}

class Pair {
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}