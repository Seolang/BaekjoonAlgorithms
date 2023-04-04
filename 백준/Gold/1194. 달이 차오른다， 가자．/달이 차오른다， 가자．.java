
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static char[][] maze;
	static int[][] visit;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str;
		Tuple start = new Tuple(0, 0, 1 << ('g'-'a'), 0);
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		visit = new int[N][M];
		
		for(int i=0; i<N; i++) {
			str = br.readLine();
			for(int j=0; j<M; j++) {
				maze[i][j] = str.charAt(j);
				if (maze[i][j] == '0') {
					start.r = i;
					start.c = j;
				}
			}
		} // end input
		
		System.out.println(BFS(start));
	}
	
	static int BFS(Tuple start) {
		Queue<Tuple> q = new ArrayDeque<>();
		visit[start.r][start.c] = start.keys;
		q.offer(start);
		
		while(!q.isEmpty()) {
			Tuple now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				if (!isValid(nr, nc) || maze[nr][nc] == '#') continue;
				
				// escape cond
				if (maze[nr][nc] == '1') return now.mov+1;
				
				if ((visit[nr][nc] | now.keys) == visit[nr][nc]) continue;
				
				if ('A' <= maze[nr][nc] && maze[nr][nc] <= 'F' 
						&& ((1 << (maze[nr][nc] - 'A')) & now.keys) == 0) 
					continue;
				
				int nextKey = now.keys;
				
				if ('a' <= maze[nr][nc] && maze[nr][nc] <= 'f') {
					nextKey |= (1 << maze[nr][nc] - 'a');
				}
				visit[nr][nc] = nextKey;
				q.offer(new Tuple(nr, nc, nextKey, now.mov+1));
			}
			
		}
		return -1;
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
}

class Tuple {
	int r, c, keys, mov;
	
	public Tuple(int r, int c, int keys, int mov) {
		this.r = r;
		this.c = c;
		this.keys = keys;
		this.mov = mov;
	}
}
