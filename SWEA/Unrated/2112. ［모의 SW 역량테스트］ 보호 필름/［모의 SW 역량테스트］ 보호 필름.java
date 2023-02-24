import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int D, W, K, minChemis;
	static int[][] cells, constCells;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cells = new int[D][W];
			constCells = new int[D][W];
			minChemis = Integer.MAX_VALUE;
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					constCells[i][j] = cells[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			selectLines(0, 0);
			sb.append("#").append(t).append(" ").append(minChemis).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean isSafe() {
		
		for(int j=0; j<W; j++) {
			int cellCount = 1;
			
			for(int i=0; i<D-1; i++) {
				if (cells[i][j] == cells[i+1][j]) {
					cellCount++;
				} else {
					cellCount = 1;
				}
				
				if (cellCount == K) break;
			}
			
			if (cellCount == K) continue;
			else return false;
		}
		return true;
	}
	
	static void selectLines(int idx, int cnt) {
		if (cnt > minChemis) return;
		
		if (isSafe()) {
			minChemis = Math.min(minChemis, cnt);
			return;
		}
		
		if (idx == D) return;
		
		// 약품을 쓰지 않았을 경우
		selectLines(idx+1, cnt);
		
		// A 약품을 썼을 경우
		Arrays.fill(cells[idx], 0);
		selectLines(idx+1, cnt+1);
		
		// B 약품을 썼을 경우
		Arrays.fill(cells[idx], 1);
		selectLines(idx+1, cnt+1);
		
		for(int i=0; i<W; i++) {
			cells[idx][i] = constCells[idx][i];
		}
	}
}