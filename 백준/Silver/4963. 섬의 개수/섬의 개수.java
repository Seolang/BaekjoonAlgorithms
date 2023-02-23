import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] map;
	static int W, H;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int result = 0;
			W = sc.nextInt();
			H = sc.nextInt();
			
			if (W == 0 && H == 0) break;
			
			map = new int[H][W];
			
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if (map[i][j] == 1) {
						BFS(i, j);
						result++;
					}
				}
			}
			
			System.out.println(result);
		}
	}
	
	static void BFS(int si, int sj) {
		map[si][sj] = 0;
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(sj, si));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0; i<8; i++) {
				int nextX = now.x + dj[i];
				int nextY = now.y + di[i];
				
				if (isValid(nextX, nextY) && 
						map[nextY][nextX] == 1
				) {
					map[nextY][nextX] = 0;
					q.offer(new Point(nextX, nextY));
				}
			}
		}
	}
	
	static boolean isValid(int x, int y) {
		return 0 <= x && x < W && 0 <= y && y < H;
	}
}