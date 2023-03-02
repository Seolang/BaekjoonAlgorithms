import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, D, maxKillCount;
    static boolean[][] map, originalMap;
    static int[] archers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 원본 맵
        originalMap = new boolean[N][M];
        archers = new int[3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
            }
        }

        placeArchers(0, 0);

        System.out.println(maxKillCount);
    }

    /*
     * 궁수들을 위치를 조합하는 함수
     */
    static void placeArchers(int idx, int cnt) {
        if (cnt == 3) {
            map = new boolean[N][M];
            for(int i=0; i<N; i++) {
                map[i] = Arrays.copyOf(originalMap[i], M);
            }
            
            int killCount = simulate();
            maxKillCount = Math.max(maxKillCount, killCount);
            return;
        }

        if (idx == M) {
            return;
        }

        archers[cnt] = idx;
        placeArchers(idx+1, cnt+1);

        placeArchers(idx+1, cnt);
    }

    /*
     * 궁수들의 행동을 시뮬레이션 하는 함수로
     * 각 궁수들이 매 row마다 한번씩 사격을 시도하여 사살한 적을 기록한다
     */
    static int simulate() {
        int killCount = 0;
        int lineRow = N;

        while(lineRow > 0) {
        	killCount += shoot(0, lineRow);
            lineRow--;
        }

        return killCount;
    }

    /*
     * 현재 궁수의 위치로부터 사격범위 안의 적을 가까운 왼쪽부터 탐색하여
     * 발견시 적을 없애고 killCount를 1 리턴한다
     */
    static int shoot(int idx, int lineRow) {
    	if (idx == 3) return 0;
    	
        int range = Math.max(lineRow - D, 0);
        int minDist = D;
        int enemyCol = M;
        int enemyRow = N;
        int result = 0;

        for(int i=lineRow-1; i >= range; i--) {
            for(int j=0; j<M; j++) {
            	int distance = (lineRow - i) + Math.abs(archers[idx] - j);
            	
                if (map[i][j] && (distance < minDist || distance == minDist && j < enemyCol)) {
                    minDist = distance;
                    enemyRow = i;
                    enemyCol = j;
                }
            }
        }
        
        result += shoot(idx+1, lineRow);
        
        if (enemyRow < N && map[enemyRow][enemyCol]) {
        	map[enemyRow][enemyCol] = false;
        	return result + 1;
        }
        
        return result;
    }
    
    static void print() {
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			System.out.print(map[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}