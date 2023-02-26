import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N;
    static char[][] pic;
    static boolean[][] visit;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pic = new char[N][N];

        for(int i=0; i<N; i++) {
            pic[i] = br.readLine().toCharArray();
        }

        visit = new boolean[N][N];
        int normalAreaCount = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (!visit[i][j]) {
                    BFS(i, j);
                    normalAreaCount++;
                }
            }
        }

        visit = new boolean[N][N];
        int colorWeakAreaCount = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (!visit[i][j]) {
                    BFSColorWeakness(i, j);
                    colorWeakAreaCount++;
                }
            }
        }

        System.out.println(normalAreaCount + " " + colorWeakAreaCount);
    }

    static void BFS(int si, int sj) {
        char target = pic[si][sj];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sj, si));
        visit[si][sj] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int i=0; i<4; i++) {
                int ni = cur.y + di[i];
                int nj = cur.x + dj[i];

                if (isValid(ni, nj) && !visit[ni][nj] && pic[ni][nj] == target) {
                    visit[ni][nj] = true;
                    q.offer(new Point(nj, ni));
                }
            }
        }
    }

    static void BFSColorWeakness(int si, int sj) {
        boolean findB = pic[si][sj] == 'B';

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sj, si));
        visit[si][sj] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int i=0; i<4; i++) {
                int ni = cur.y + di[i];
                int nj = cur.x + dj[i];

                if (isValid(ni, nj) && !visit[ni][nj]) {
                    boolean flag = false;
                    if (findB && pic[ni][nj] == 'B') {
                        flag = true;
                    } else if (!findB && pic[ni][nj] != 'B') {
                        flag = true;
                    }

                    if (flag) {
                        visit[ni][nj] = true;
                        q.offer(new Point(nj, ni));
                    }
                }
            }
        }
    }

    static boolean isValid(int i, int j) {
        return 0 <= i && i < N && 0 <= j && j < N;
    }
}