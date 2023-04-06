import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, M, answer;
	static int[][] map;
	static Shark[] sharks;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		sharks = new Shark[M+1];
		
		int r=0, c=0, s=0, d=0, z=0;
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			sharks[i] = new Shark(i, r, c, s, d, z);
			map[r][c] = i;
		} // end Input
		
		for(int j=0; j<C; j++) {
			for(int i=0; i<R; i++) {
				if (map[i][j] != 0) {
					answer += sharks[map[i][j]].size;
					sharks[map[i][j]].alive = false;
					map[i][j] = 0;
					break;
				}
			}
			for(int i=1; i<=M; i++) {
				if (sharks[i].alive) {
					map[sharks[i].r][sharks[i].c] = 0;
				}
			}
			
			for(int i=1; i<=M; i++) {
				if (sharks[i].alive) {
					sharks[i].move();
				}
			}
		}
		System.out.println(answer);
	}
	
	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("================");
	}
	
	static class Shark {
		int num;
		int r, c;
		int speed;
		int dir;
		int size;
		boolean alive = true;
		
		
		public Shark(int num, int r, int c, int speed, int dir, int size) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		
		public void fight() {
			if (map[r][c] == 0) {
				map[r][c] = num;
			}
			else {
				Shark enemy = sharks[map[r][c]];
				if (size > enemy.size) {
					enemy.alive = false;
					map[r][c] = num;
				}
				else {
					alive = false;
				}
			}
		}
		
		public void move() {
			int movingPoint = speed;
			while(true) {
				if (dir == 1) {
					if (r - movingPoint >= 0) {
						r -= movingPoint;
						fight();
						return;
					}
					
					movingPoint -= r;
					r = 0;
					dir = 2;
				}
				else if (dir == 2) {
					if (r + movingPoint < R) {
						r += movingPoint;
						fight();
						return;
					}
					
					movingPoint -= (R - r - 1);
					r = R-1;
					dir = 1;
					
				}
				else if (dir == 3) {
					if (c + movingPoint < C) {
						c += movingPoint;
						fight();
						return;
					}
					
					movingPoint -= (C - c - 1);
					c = C-1;
					dir = 4;
					
				}
				else {
					if (c - movingPoint >= 0) {
						c -= movingPoint;
						fight();
						return;
					}
					
					movingPoint -= c;
					c = 0;
					dir = 3;
				}
			} //end while
		} // end move
	}
}