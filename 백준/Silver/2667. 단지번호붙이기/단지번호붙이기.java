import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class Main {
	
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int towns = 0;
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (map[i][j] == '1') {
                    list.add(DFS(i, j));
                    towns++;
                }
            }
        }

        list.sort(Comparator.naturalOrder());
        System.out.println(towns);
        for(int n : list) {
            System.out.println(n);
        }
    }
    
    static int DFS(int si, int sj) {
    	int area = 1;
    	map[si][sj] = '0';
    	
    	for(int i=0; i<4; i++) {
    		int nextX = sj + dx[i];
            int nextY = si + dy[i];
            if (isValid(nextX, nextY) && map[nextY][nextX] == '1') {
                area += DFS(nextY, nextX);
            }
    	}
    	
    	return area;
    }

    static int BFS(int si, int sj) {
        int area = 1;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sj, si));
        map[si][sj] = '0';

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int i=0; i<4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (isValid(nextX, nextY) && map[nextY][nextX] == '1') {
                    q.offer(new Point(nextX, nextY));
                    map[nextY][nextX] = '0';
                    area++;
                }
            }

        }
        return area;
    }

    static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}