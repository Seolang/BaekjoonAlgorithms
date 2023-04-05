import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C;
	static char[][] map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static Queue<Point> sq = new ArrayDeque<Point>();
	static Queue<Point> wq = new ArrayDeque<Point>();
	static boolean[][] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		visit = new boolean[R][C];
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if (map[i][j] == 'S') {
					sq.offer(new Point(j, i));
					map[i][j] = '.';
				} 
				else if (map[i][j] == '*') {
					wq.offer(new Point(j, i));
				}
			}
		}// end input
		
		System.out.println(BFS());
		
	}
	
	static String BFS() {
		String answer = "KAKTUS";
		int timer = 0;
		
		while(!sq.isEmpty()) {
			int sizeWq = wq.size();
			int sizeSq = sq.size();
			timer++;
			
			for(int i=0; i<sizeWq; i++) {
				Point now = wq.poll();
				
				for(int dir=0; dir<4; dir++) {
					int nr = now.y + dr[dir];
					int nc = now.x + dc[dir];
					
					if (isValid(nr, nc) && map[nr][nc] == '.') {
						map[nr][nc] = '*';
						wq.offer(new Point(nc, nr));
					}
				}
			}
			
			for(int i=0; i<sizeSq; i++) {
				Point now = sq.poll();
				
				for(int dir=0; dir<4; dir++) {
					int nr = now.y + dr[dir];
					int nc = now.x + dc[dir];
					
					if (isValid(nr, nc) && !visit[nr][nc]) { 
						if (map[nr][nc] == 'D') {
							return Integer.toString(timer);
						}
						else if (map[nr][nc] == '.') {
							visit[nr][nc] = true;
							sq.offer(new Point(nc, nr));
						}
					}
				}
			}
		}
		
		return answer;
	}
	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}