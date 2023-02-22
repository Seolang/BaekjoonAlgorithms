import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* 1. 오른쪽 상단으로 가는 경로를 최우선으로 선택해야한다
* 2. 한 방향으로 이동하여 반대쪽 끝에 도달한다면, 그 라인의 다른 경로는 확인하지 않는다
* 3. 현재 탐색의 파이프 연결유무와 관계없이, 한번 지나간 경로는 다시 접근하지 않는다
*/
public class Main {
	
	static int R, C, maxPipeline;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		/*
        * 모든 시작점에 대하여 DFS를 시행함 (파이프 연결을 시도함)
        */
		for(int i=0; i<R; i++) {
			if (dfs(i, 0, i, 0)) maxPipeline++;
		}
		System.out.println(maxPipeline);
	}
	
	public static boolean dfs(int row, int col, int startRow, int pipeline) {
		if (col == C-1) {
			map[row][col] = 'X';
			return true;
		}
		
        /*
        * 어떤 경로로 파이프 연결이 성공하면, 그 하위의 탐색은 시행하지 않음
        */
		boolean flag = false;
		
		if (0 <= row - 1 && map[row-1][col+1] == '.')  {
			map[row-1][col+1] = 'X';
			flag = dfs(row-1, col+1, startRow, pipeline);
		}
		
		if (!flag && map[row][col+1] == '.')  {
			map[row][col+1] = 'X';
			flag = dfs(row, col+1, startRow, pipeline);
		}
		
		if (!flag && row + 1 < R && map[row+1][col+1] == '.')  {
			map[row+1][col+1] = 'X';
			flag = dfs(row+1, col+1, startRow, pipeline);
		}

		return flag;
	}
}