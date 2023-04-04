import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static ArrayList<Point> list;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			n = Integer.parseInt(br.readLine())+2;
			list = new ArrayList<>();
			visit = new boolean[n];
			int x;
			int y;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				list.add(new Point(x, y));
			}
			if (BFS()) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}
	
	static boolean BFS() {
		Queue<Point> q = new ArrayDeque<Point>();
		visit[0] = true;
		q.offer(list.get(0));
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			for(int i=0; i<n; i++) {
				if (!visit[i] && runnable(now, list.get(i))) {
					visit[i] = true;
					q.offer(list.get(i));
					
					if (i == n-1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	static boolean runnable(Point from, Point to) {
		return Math.abs(from.x - to.x) + Math.abs(from.y - to.y) <= 1000;
	}
}