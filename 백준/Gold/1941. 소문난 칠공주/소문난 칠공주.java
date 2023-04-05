import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	
	static char[][] map = new char[5][5];
	static boolean[][] visit;
	static ArrayList<Point> som = new ArrayList<>();
	static ArrayList<Point> yeon = new ArrayList<>();
	static Point[] select = new Point[7];
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<5; j++) {
				if (map[i][j] == 'S') {
					som.add(new Point(j, i));
				} 
				else {
					yeon.add(new Point(j, i));
				}
				map[i][j] = 'X';
			}
		}
		if (som.size() < 4) {
			System.out.println(0);
			return ;
		}
		combSom(0, 0);
		System.out.println(answer);
	}
	
	static void combSom(int idx, int cnt) {
		if (cnt >= 4) {
			combYeon(0, cnt);
		}
		
		if (cnt == 7) return;
		
		for(int i=idx; i<som.size(); i++) {
			select[cnt] = som.get(i);
			map[select[cnt].y][select[cnt].x] = 'O';
			combSom(i+1, cnt+1);
			map[select[cnt].y][select[cnt].x] = 'X';
		}
	}
	
	static void combYeon(int idx, int cnt) {
		if (cnt == 7) {
			visit = new boolean[5][5];
			BFS(select[0]);
			
			return;
		}
		
		for(int i=idx; i<yeon.size(); i++) {
			select[cnt] = yeon.get(i);
			map[select[cnt].y][select[cnt].x] = 'O';
			combYeon(i+1, cnt+1);
			map[select[cnt].y][select[cnt].x] = 'X';
		}
	}
	
	static void BFS(Point start) {
		Queue<Point> q = new ArrayDeque<Point>();
		visit[start.y][start.x] = true;
		q.offer(start);
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			cnt++;
			for(int i=0; i<4; i++) {
				int nr = now.y + dr[i];
				int nc = now.x + dc[i];
				
				if (isValid(nr, nc) && !visit[nr][nc] && map[nr][nc] == 'O') {
					q.offer(new Point(nc, nr));
					visit[nr][nc] = true;
				}
			}
		}
		
		if (cnt == 7) answer++;
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < 5 && 0 <= c && c < 5;
	}
	
	static void print() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static void print2() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				System.out.print(visit[i][j] + " ");
			}
			System.out.println();
		}
	}
}

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

}