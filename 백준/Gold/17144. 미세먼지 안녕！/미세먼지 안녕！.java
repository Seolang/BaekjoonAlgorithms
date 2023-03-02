import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
	
	static int R, C, T;
	static int[] airFilter = new int[2];
	static int[][] room, spread;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        room = new int[R][C];
        
        for(int i=0; i<R; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<C; j++) {
        		room[i][j] = Integer.parseInt(st.nextToken());
        		
        		if (room[i][j] == -1) {
        			airFilter[1] = i;
        			airFilter[0] = i-1;
        		}
        	}
        }
        
        for(int i=0; i<T; i++) {
        	spreadDust();
        	runAirFilter();
        }
        System.out.println(getRemainDust());
        
    }
    
    static void runAirFilter() {
    	
    	int dir = 3;
    	int dirChangeCount = 0;
    	
    	int nowY = airFilter[0] - 1;
    	int nowX = 0;
    	
    	while(true) {
    		int nextY = nowY + dr[dir];
    		int nextX = nowX + dc[dir];
    		
    		if (!(0 <= nextY && nextY <= airFilter[0] &&
    			  0 <= nextX && nextX < C)) {
    			dir = (dir + 1) % 4;
    			dirChangeCount++;
    			continue;
    		}
    		
    		if (room[nextY][nextX] == -1)  {
    			room[nowY][nowX] = 0;
    			break;
    		}
    		
    		room[nowY][nowX] = room[nextY][nextX];
    		nowY = nextY;
    		nowX = nextX;
    	}
    	
    	dir = 1;
    	dirChangeCount = 0;
    	nowY = airFilter[1] + 1;
    	nowX = 0;
    	
    	while(true) {
    		int nextY = nowY + dr[dir];
    		int nextX = nowX + dc[dir];
    		
    		if (!(airFilter[1] <= nextY && nextY < R &&
      			  0 <= nextX && nextX < C)) {
    			dir = dir - 1;
    			if (dir < 0) dir = 3;
    			dirChangeCount++;
    			continue;
    		}
    		
    		if (room[nextY][nextX] == -1) {
    			room[nowY][nowX] = 0;
    			break;
    		}
    		
    		room[nowY][nowX] = room[nextY][nextX];
    		nowY = nextY;
    		nowX = nextX;
    	}
    }
    
    static void spreadDust() {
    	spread = new int[R][C];
    	
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			if (room[i][j] > 0) {
    				int spreadDust = room[i][j]/5;
    				
    				for(int k=0; k<4; k++) {
    					int nr = i+dr[k];
    					int nc = j+dc[k];
    					
    					if (isValid(nr, nc) && room[nr][nc] != -1) {
    						spread[nr][nc] += spreadDust;
    						room[i][j] -= spreadDust;
    					}
    				}
    			}
    		}
    	}
    	
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			room[i][j] += spread[i][j];
    		}
    	}
    }
    
    static int getRemainDust() {
    	int rst = 2;
    	
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			rst += room[i][j];
    		}
    	}
    	
    	return rst;
    }
    
    static void print() {
    	for(int i=0; i<R; i++) {
    		for(int j=0; j<C; j++) {
    			System.out.print(room[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
    
    static boolean isValid(int r, int c) {
    	return 0 <= r && r < R && 0 <= c && c < C;
    }    
}