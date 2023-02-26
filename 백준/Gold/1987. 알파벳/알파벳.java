import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int R, C;
    static char[][] map;
    static boolean[] visit;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visit = new boolean[26];
        for(int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(DFS(0, 0, 1));
    }

    static int DFS(int si, int sj, int chain) {
        visit[map[si][sj] - 'A'] = true;
        int result = chain;

        for(int i=0; i<4; i++) {
            int ni = si+di[i];
            int nj = sj+dj[i];

            if (isValid(ni, nj) && !visit[map[ni][nj] - 'A']) {
                result = Math.max(result, DFS(ni, nj, chain+1));
                visit[map[ni][nj] - 'A'] = false;
            }

        }
        return result;
    }

    static boolean isValid(int i, int j) {
        return 0<=i && i<R && 0<=j && j<C;
    }
}