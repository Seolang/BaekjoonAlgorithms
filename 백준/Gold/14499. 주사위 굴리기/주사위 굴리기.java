import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 지난 로봇청소기 문제와 비슷하게 톱니바퀴와 그 동작 메커니즘을 객체로 구현하여 문제를 해결하였다
 * 
 * 특히, 기어의 연쇄적인 움직임의 경우 재귀함수의 함수 실행 예약 특성을 이용하여 먼저 자신의 양쪽 기어가 움직일지의 여부를 확인 한 후
 * 자신을 회전시키므로써 기어 회전후 왼쪽, 오른쪽 극성의 값이 바뀌어 양쪽과 극성 비교가 불가능한 상황을 해결하였다
 */

public class Main {
	
	static int N, M, row, col;
	static int[][] map;
	static int[] dice;
	static StringBuilder sb;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	sb = new StringBuilder();
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	row = Integer.parseInt(st.nextToken());
    	col = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	map = new int[N][M];
    	dice = new int[7];
    	
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<M; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<K; i++) {
    		rollDice(Integer.parseInt(st.nextToken()));
    	}
    	System.out.println(sb);
    }
    
    static void rollDice(int dir) {
    	if (dir == 1) {
    		if (!isValid(row, col+1)) return;
    		
    		int temp = dice[1];
    		dice[1] = dice[4];
    		dice[4] = dice[6];
    		dice[6] = dice[3];
    		dice[3] = temp;
    		col++;
    		
    		if (map[row][col] == 0) {
    			map[row][col] = dice[6];
    		} else {
    			dice[6] = map[row][col];
    			map[row][col] = 0;
    		}
    		sb.append(dice[1]).append("\n");
    		
    	} else if (dir == 2) {
    		if (!isValid(row, col-1)) return;
    		
    		int temp = dice[1];
    		dice[1] = dice[3];
    		dice[3] = dice[6];
    		dice[6] = dice[4];
    		dice[4] = temp;
    		col--;
    		
    		if (map[row][col] == 0) {
    			map[row][col] = dice[6];
    		} else {
    			dice[6] = map[row][col];
    			map[row][col] = 0;
    		}
    		sb.append(dice[1]).append("\n");
    		
    	} else if (dir == 3) {
    		if (!isValid(row-1, col)) return;
    		
    		int temp = dice[1];
    		dice[1] = dice[5];
    		dice[5] = dice[6];
    		dice[6] = dice[2];
    		dice[2] = temp;
    		row--;
    		
    		if (map[row][col] == 0) {
    			map[row][col] = dice[6];
    		} else {
    			dice[6] = map[row][col];
    			map[row][col] = 0;
    		}
    		sb.append(dice[1]).append("\n");
    		
    	} else {
    		if (!isValid(row+1, col)) return;
    		
    		int temp = dice[1];
    		dice[1] = dice[2];
    		dice[2] = dice[6];
    		dice[6] = dice[5];
    		dice[5] = temp;
    		row++;
    		
    		if (map[row][col] == 0) {
    			map[row][col] = dice[6];
    		} else {
    			dice[6] = map[row][col];
    			map[row][col] = 0;
    		}
    		sb.append(dice[1]).append("\n");
    	}
    }
    
    static boolean isValid(int r, int c) {
    	return 0 <= r && r < N && 0 <= c && c < M;
    }
}