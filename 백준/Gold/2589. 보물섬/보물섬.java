import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C; // 보물 지도의 세로 크기, 가로 크기
	static int ans; // 보물 사이의 최단 거리
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	
	static char[][] map; // 보물 지도
	static int[][] visited; // 보물 지도에서 방문 처리를 위한 배열
	
	public static void bfs(int x, int y) {
		
		visited = new int[R][C]; // 각 bfs 함수 별, 방문 처리 배열 동적 할당
		int maxDist = 0; // 현재 위치에서 가장 먼 육지 거리 저장
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = 1;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 범위를 벗어나거나, 방문 처리됐거나 바다인 경우 무시
				if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] != 0 || map[nx][ny] == 'W') continue;
				
				visited[nx][ny] = visited[p.x][p.y] + 1;
				maxDist = Math.max(maxDist, visited[nx][ny]);
				q.add(new Point(nx, ny));
			}
		}
		
		ans = Math.max(ans, maxDist - 1); // 처음 시작 거리를 1로 잡았기 때문에 1을 뺌
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; // 보물 지도 배열 동적 할당
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		} // end input
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 현재 지도의 위치가 육지인 경우에 실행
				if (map[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}
}