import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] scoreBoard, games;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; i++) {
			result = 0;
			scoreBoard = new int[6][3];
			games = new int[6][6];
			st = new StringTokenizer(br.readLine());
			int[] games = new int[3];
			boolean flag = false;
			
			for(int j=0; j<6; j++) {
				scoreBoard[j][0] = Integer.parseInt(st.nextToken());
				scoreBoard[j][1] = Integer.parseInt(st.nextToken());
				scoreBoard[j][2] = Integer.parseInt(st.nextToken());
			}
			
			play(0, 1);
			System.out.println(result);
		}
	}
	
	public static void play(int idx, int cnt) {
		if (idx == 6) {
			
			result = 1;
			return;
		}
		
		if (cnt == 6) {
			int[] tscore = new int[3];
			for(int i=0; i<6; i++) {
				if (idx != i) {
					tscore[games[idx][i]]++;
				}
			}
			
			if (tscore[0] == scoreBoard[idx][0] &&
					tscore[1] == scoreBoard[idx][1] &&
					tscore[2] == scoreBoard[idx][2]
			) {
				play(idx+1, idx+2);
			}
			return;
		}
		
		for(int i=0; i<3; i++) {
			games[idx][cnt] = i;
			games[cnt][idx] = 2-i;
			play(idx, cnt+1);
		}
	}
}