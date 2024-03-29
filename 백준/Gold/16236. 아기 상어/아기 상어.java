import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, sharkSize = 2, ateFishNum = 0, fishes, sharkR, sharkC, timer;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    if (map[i][j] == 9) {
                        sharkR = i;
                        sharkC = j;
                        map[i][j] = 0;
                    } else {
                        fishes++;
                    }
                }
            }
        } // end Input

        boolean ateFish = true;

        while(ateFish && fishes != 0) {
            ateFish = BFS(sharkR, sharkC);
        }

        System.out.println(timer);
    }

    static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (i == sharkR && j == sharkC) System.out.print(9 + " ");
                else System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("move : " + timer + " sharkSize : " + sharkSize);
    }

    static boolean BFS(int initR, int initC) {
        boolean[][] visit = new boolean[N][N];
        int steps = 0;

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(initC, initR));
        visit[initR][initC] = true;

        Point fish = new Point(N, N);

        while(!q.isEmpty()) {
            steps++;
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point now = q.poll();

                for(int dir=0; dir<4; dir++) {
                    int nr = now.y + dr[dir];
                    int nc = now.x + dc[dir];

                    if (isValid(nr, nc) && !visit[nr][nc]) {
                        int mapVal = map[nr][nc];

                        if (mapVal != 0 && mapVal < sharkSize) {
                            visit[nr][nc] = true;
                            if (nr < fish.y || nr == fish.y && nc < fish.x) {
                                fish.setLocation(nc, nr);
                            }

                        } else if (mapVal == 0 || mapVal == sharkSize) {
                            q.offer(new Point(nc, nr));
                            visit[nr][nc] = true;
                        }
                    }
                }
            }
            if (fish.x != N) {
                fishes--;
                ateFishNum++;
                if (ateFishNum == sharkSize) {
                    ateFishNum = 0;
                    sharkSize++;
                }
                timer += steps;
                map[fish.y][fish.x] = 0;
                sharkR = fish.y;
                sharkC = fish.x;
                return true;
            }
        }

        return false;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}