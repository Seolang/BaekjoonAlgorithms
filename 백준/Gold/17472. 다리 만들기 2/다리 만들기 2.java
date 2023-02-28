import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 먼저 각 섬의 주변 연결 유무를 DFS를 통해 확인하여 각각의 섬을 분리한다
 * 2. 다음으로 각 섬에 순서대로 1번부터 번호를 부여한다
 * 3. 섬들의 해안선에서 부터 각 방향으로 다리를 이어보아 각각 다른 섬에 대하여 다리를 놓을 수 있을 때, 최소값이 되는 값을 인접행렬로 저장한다
 * 4. 인접 행렬과 Prim 알고리즘을 이용하여 각 섬들을 최소 신장 트리로 연결한다
 * 5. 최소 신장 트리의 총 가중치 값을 구한다
 */

public class Main {
	
	static int N, M; // 지도의 세로와 가로 길이
	static int isleNum = 1; // 지도에 표시할 섬 번호
	static int bridgeSum; // 다리의 총 길이 합
	static int[][] map; // 지도 정보를 담은 배열
	static int[][] adjMatrix; // 각 섬간의 연결 정보를 담은 인접행렬
	static boolean[][] mapVisited; // 지도 방문 여부 배열
	
	// 변위 델타 배열
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로 길이 입력
		M = Integer.parseInt(st.nextToken()); // 가로 길이 입력
		map = new int[N][M]; // 지도 초기화
		
		// 섬의 정보를 map 배열에 저장
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input
		
		// 각 섬에 대하여 번호를 부여하고 지도에 표시
		mapVisited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (map[i][j] == 1 && !mapVisited[i][j]) { // 지도의 좌표의 값이 1이고, 아직 방문하지 않았으면 현재 섬 번호를 넣고서 dfs 실행
					dfs(i, j, isleNum++);
				}
			}
		} // end search Island
		
		adjMatrix = new int[isleNum][isleNum]; // 인접 행렬을 섬의 개수만큼 2차원 배열로 초기화
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				connectBridge(i, j);	// 지도의 각 좌표에 대해서 4방향으로 다리를 뻗어본다
			}
		}
		Prim();
		System.out.println(bridgeSum);
	}
	
	// Prim 알고리즘
	static void Prim() {
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();	// Edge 클래스에 대한 priority queue 생성
		pq.offer(new Edge(1, 0)); // 1번 정점을 초기값으로 간선 입력
		boolean[] visit = new boolean[isleNum]; // 정점에 대한 방문 체크 배열 생성
		int cnt = 1; // 방문 정점 변수
		
		while(!pq.isEmpty() && cnt != isleNum) { // pq가 모두 비거나, 모든 정점을 방문할때까지 실행
			Edge now = pq.poll(); // 현재 간선 정보를 가져온다
			
			if (visit[now.vertex]) continue; // 이미 방문한 정점을 가리키면 패스한다
			
			visit[now.vertex] = true; // 도착한 정점에 방문 체크
			cnt++;					  // 방문 횟수 증가
			bridgeSum += now.weight;  // 총 다리 길이에 현재 간선의 가중치만큼 추가
			
			for(int v=1; v<isleNum; v++) { // 다른 정점들에 대해 순회
				if (!visit[v] && adjMatrix[now.vertex][v] != 0) {	// 아직 방문하지 않았고, 현재 정점과 연결된 정점이면
					pq.add(new Edge(v, adjMatrix[now.vertex][v])); // pq에 다음 정점과 가중치를 입력
				}
			}
		}
		
		for(int i=1; i<isleNum; i++) { // 모든 정점에 대하여
			if (!visit[i]) {		 // 만약 방문하지 않은 정점이 있다면
				bridgeSum = -1;		 // 결과에 -1을 입력하고 종료
				return;
			}
		}
	}
	
	// 모든 정점에 대하여 다리를 연결시켜보는 함수
	static void connectBridge(int r, int c) {
		int curIsle = map[r][c];  // 현재 좌표가 속한 섬의 번호
		int otherIsle;			  // 다리가 연결되었을 때, 반대쪽 섬의 번호
		
		for(int i=0; i<4; i++) {  // 동서남북 4방향에 대하여
			int nr = r + dr[i];   // 다음 좌표의 초기값으로 한칸 넘어간 위치를 입력
			int nc = c + dc[i];
			
			int bridgeLen = 0;	  // 다리의 길이 변수
			
			while(isValid(nr, nc) && map[nr][nc] == 0) {  // 다음 좌표가 지도를 벗어나거나 0이 아닌 수를 만날때까지 반복
				bridgeLen++;	// 다리가 나아가므로 증가
				nr += dr[i];	// 다음 좌표값 입력
				nc += dc[i];
			}
			
			// 마지막으로 멈춘 좌표가 지도 내부에 있고 그 좌표의 값이 같은 섬이 아니고 다리의 길이가 2 이상이면
			if (isValid(nr, nc) && map[nr][nc] != curIsle && bridgeLen > 1) { 
				
				otherIsle = map[nr][nc];	// 반대쪽 섬의 번호 입력
				
				// 기존의 다리 길이와 비교하여 더 작은 값을 선택하여 입력, 혹은 다리 길이값이 없다면 바로 입력
				if (adjMatrix[curIsle][otherIsle] == 0) 
					adjMatrix[curIsle][otherIsle] = bridgeLen;
				else 
					adjMatrix[curIsle][otherIsle] = Math.min(adjMatrix[curIsle][otherIsle], bridgeLen);
				
				adjMatrix[otherIsle][curIsle] = adjMatrix[curIsle][otherIsle]; // 반대쪽 인접행렬에 대해서도 같은값을 입력
			}
		}
	}
	
	// 섬을 탐색하는  DFS
	static void dfs(int r, int c, int isleNum) {
		mapVisited[r][c] = true;	// 방문한 좌표 표시
		map[r][c] = isleNum;		// 현재 섬 좌표의 번호를 소속 섬의 번호로 변환
		
		for(int i=0; i<4; i++) { // 동서남북 4방향에 대하여
			int nr = r + dr[i];	 // 해당 방향의 다음좌표로 전진
			int nc = c + dc[i];
			
			// 만약 다음 좌표가 지도 내에 있고, 지도의 표시가 1이며 아직 방문하지 않은 좌표이면
			if (isValid(nr, nc) && map[nr][nc] == 1 && !mapVisited[nr][nc]) {
				dfs(nr, nc, isleNum);	// 재귀함수 실행
			}
		}
	}
	
	// 인덱스 r과 c가 각각 0 ~ N, 0 ~ M 사이인지 (유효한 인덱스인지) 검사하는 함수
	static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M; // 인덱스가 유효범위 안이면 true, 아니면 false 반환
	}
	
	
	// 출력 테스트용 함수들
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void printAdjMat() {
		for(int i=1; i<isleNum; i++) {
			for(int j=1; j<isleNum; j++) {
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// 연결된 반대쪽 정점과 간선의 가중치 정보를 담은 클래스
	static class Edge implements Comparable<Edge> {
		int vertex;	// 반대쪽 정점
		int weight;	// 가중치
		
		// 생성자
		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		// Priority Queue에서의 우선순위를 위하여 compareTo 메소드 오버라이드
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;	// 오름차순 정렬
		}

		@Override
		public String toString() {
			return "Edge [vertex=" + vertex + ", weight=" + weight + "]";
		}
	}
}