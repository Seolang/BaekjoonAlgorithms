import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input
		
		int path = 0;
		
		
		boolean[][] takePlace = new boolean[N][N];
		for(int i=0; i<N; i++) {
			boolean pathable = true;
			
			for(int j=0; j<N-1; j++) {
				int adder = 0;
				int anglePos;
				
				adder = arr[i][j] - arr[i][j+1];
				
				if (adder != 0) {
					if (Math.abs(adder) > 1)  {
						pathable = false;
						break;
					}
					
					anglePos = adder == 1 ? j+1 : j;
					int pointHeight = arr[i][anglePos];
					
					for(int k=0; k<L; k++) {
						if (0 > anglePos || 
								anglePos >= N ||
								arr[i][anglePos] != pointHeight ||
								takePlace[i][anglePos]
						) {
							pathable = false;
							break;
						} else {
							takePlace[i][anglePos] = true;
						}
						anglePos += adder;
					}
				}
				if (!pathable) break;
			}
			if (pathable) path++;
		}
		
		takePlace = new boolean[N][N];
		for(int j=0; j<N; j++) {
			boolean pathable = true;
			
			for(int i=0; i<N-1; i++) {
				int adder = 0;
				int anglePos;
				
				adder = arr[i][j] - arr[i+1][j];
				
				if (adder != 0) {
					if (Math.abs(adder) > 1)  {
						pathable = false;
						break;
					}
					
					anglePos = adder == 1 ? i+1 : i;
					int pointHeight = arr[anglePos][j];
					
					for(int k=0; k<L; k++) {
						if (0 > anglePos || 
								anglePos >= N ||
								arr[anglePos][j] != pointHeight ||
								takePlace[anglePos][j]
						) {
							pathable = false;
							break;
						} else {
							takePlace[anglePos][j] = true;
						}
						anglePos += adder;
					}
				}
				if (!pathable) break;
			}
			if (pathable) path++;
		}
		System.out.println(path);
	}
}