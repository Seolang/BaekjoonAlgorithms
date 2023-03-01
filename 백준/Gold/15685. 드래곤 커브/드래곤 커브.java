/*
     n 세대 커브 = n-1 세대 그래프 + n-1 세대 그래프 90도 회전
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, startX, startY;
    static boolean[][] map = new boolean[101][101];
    static ArrayList<Integer> directions;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            directions = new ArrayList<>();
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[startY][startX] = true;

            startX += dx[d];
            startY += dy[d];
            if (isValid(startY, startX)) map[startY][startX] = true;
            directions.add(d);

            for(int j=0; j<g; j++) {
                dragonCurve();
            }
        }

        int result = 0;

        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    static void dragonCurve() {

        for(int i=directions.size()-1; i>=0; i--) {
            int dir = (directions.get(i) + 1) % 4;
            startX += dx[dir];
            startY += dy[dir];

            if(isValid(startY, startX)) map[startY][startX] = true;

            directions.add(dir);
        }
    }


    static boolean isValid(int r, int c) {
        return 0 <= r && r <= 100 && 0 <= c && c <= 100;
    }
}