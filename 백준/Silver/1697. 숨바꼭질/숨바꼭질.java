import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static boolean[] visit = new boolean[100001];	// 방문 여부 저장
	static int[] time = new int[100001];			// 방문 당시 시간 저장
	static int N, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		System.out.println(BFS());
	}
	
	static int BFS() {
		// 시작점이 동생위치이면 즉시 종료
		if (N == K) return 0;
		
		Queue<Integer> q = new ArrayDeque<>();
		
		visit[N] = true;
		q.offer(N);
		
		int current, curTime;
		while(!q.isEmpty()) {
			current = q.poll();
			curTime = time[current] + 1;
			
			// x - 1로 이동
			if (0 <= current - 1 && !visit[current-1]) {
				if (current - 1 == K) return curTime;
				
				visit[current-1] = true;	
				time[current-1] = curTime;
				q.offer(current-1);
			}
			
			// x + 1로 이동
			if (current + 1 <= 100000 && !visit[current+1]) {
				if (current + 1 == K) return curTime;
				
				visit[current+1] = true;
				time[current+1] = curTime;
				q.offer(current+1);
			}
			
			// 2 * x 로 이동
			current *= 2;
			if (current <= 100000 && !visit[current]) {
				if (current == K) return curTime;
				
				visit[current] = true;
				time[current] = curTime;
				q.offer(current);
			}
		}
		
		return -1;
	}
}