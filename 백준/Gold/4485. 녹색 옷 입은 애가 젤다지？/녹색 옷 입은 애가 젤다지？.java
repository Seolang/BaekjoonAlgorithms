import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BFS로 인접국가간의 연합 여부 및 연합의 종류를 분류하고
// 각 연합마다 인구의 수를 각각 따로 재조정한다

public class Main {

    static int N;
    static int[][] cave, dist;
    static boolean[][] visit;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = 0;
        while((N = Integer.parseInt(br.readLine())) != 0) {
            testCase++;

            cave = new int[N][N];
            dist = new int[N][N];
            visit = new boolean[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }// end input

            Dijkstra();
            sb.append("Problem ").append(testCase).append(": ").append(dist[N-1][N-1]).append("\n");
        }
        System.out.println(sb);
    }

    static void Dijkstra() {

        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> {
            return dist[a.r][a.c] - dist[b.r][b.c];
        });

        dist[0][0] = cave[0][0];
        pq.offer(new Pair(0, 0));

        while(!pq.isEmpty()) {
            Pair now = pq.poll();

            if (visit[now.r][now.c]) continue;
            visit[now.r][now.c] = true;


            if (now.r == N-1 && now.c == N-1) {
                return;
            }


            for(int i=0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if (isValid(nr, nc) && !visit[nr][nc] &&
                        dist[now.r][now.c] + cave[nr][nc] < dist[nr][nc]) {
                    dist[nr][nc] = dist[now.r][now.c] + cave[nr][nc];
                    pq.offer(new Pair(nr, nc));
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

}

class Pair {
    int r, c;

    public Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}