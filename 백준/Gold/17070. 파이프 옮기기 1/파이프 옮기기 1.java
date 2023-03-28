import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][N][3];
        int[][] map = new int[N][N];
        dp[0][1][0] = 1;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=0; i<N; i++) {
            for(int j=1; j<N; j++) {
                if (isValid(i, j+1, N) && map[i][j+1] != 1) {
                    dp[i][j+1][0] += dp[i][j][0] + dp[i][j][1];
                }

                if (isValid(i+1, j, N) && map[i+1][j] != 1) {
                    dp[i+1][j][2] += dp[i][j][2] + dp[i][j][1];;
                }

                if(isValid(i, j+1, N) && map[i][j+1] != 1 && isValid(i+1, j, N) && map[i+1][j] != 1 && isValid(i+1, j+1, N) && map[i+1][j+1] != 1) {
                    dp[i+1][j+1][1] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
                }
            }
        }
        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
    }

    static boolean isValid(int r, int c, int N) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}