import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * x 축을 지나는 수직선만을 선택한 후, 위로 올라갔다 아래로 내려갈때를 한 쌍으로  x축과 고유번호를 부여해 기록,
 * 각 수직선 사이에 다른 원소가 있는지, 각 수직선 외부에 쌍이 되는 다른 원소가 있는지 탐색하여
 * 현재 고유번호의 수직선들이 구성하는 봉우리가 다른 봉우리를 포함하는지, 혹은 다른 봉우리에 포함되는지
 * 확인할 수 있다
 */

public class Main {
	
	// x 축을 지나는 수직선의 x좌표와 고유코드를 함께 저장하는 리스트
	static List<Pair> list = new ArrayList<>();
	static Deque<Pair> deque = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N 입력 받기
		int N = Integer.parseInt(st.nextToken());
		
		// 가장 처음 꼭짓점을 먼저 받아 리스트에 추가하고, 시작점이 x축의 위인지 아래인지 파악한다
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int startY = y;
		int prevY = y;
		int minX = x;
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			// x 축 상승 수직선
			if (prevY < 0 && y > 0 || prevY > 0 && y < 0) {
				deque.offer(new Pair(x));
				minX = Math.min(minX, x);
			}
			
			prevY = y;
		}
		
		// 시작 점이 가장 왼쪽, y축이 0보다 클 경우 첫번째 수직선을 추가해주어야 한다
		if (deque.size()%2 != 0) {
			deque.offer(new Pair(x));
			minX = Math.min(minX, x);
		}
		
		// 첫번째 수직선 찾기
		while(deque.peek().x != minX) {
			deque.offer(deque.poll());
		}
		
		// 수직선은 반드시 올라가고 내려가거나, 내려가고 올라오기때문에
		// 같은 봉우리의 두 쌍의 수직선은 항상 붙어있다
		int pairKey = 0;
		while(!deque.isEmpty()) {
			Pair p1 = deque.poll();
			Pair p2 = deque.poll();
			p1.number = pairKey;
			p2.number = pairKey++;
			list.add(p1);
			list.add(p2);
		}
		
		
		// 수직선들을 x좌표 기준으로 정렬
		Collections.sort(list);
		
		int isNotInclude = 0;
		int notBeIncluded = 0;
		int innerCount = 0;
		
		Deque<Integer> stk = new ArrayDeque<>();
		stk.add(list.get(0).number);
		
		for(int i=1; i<list.size(); i++) {
			
			// 스택에 비어있지 않고 마지막 원소가 현재 원소와 같을경우
			if (!stk.isEmpty() && stk.peekLast() == list.get(i).number) {
				
				if (stk.size() == 1) {
					notBeIncluded++;
				}
				
				// 바로 이전 원소가 같은 수직선 코드일 경우, 다른 봉우리를 포함하지 않는다
				if (list.get(i-1).number == list.get(i).number) {
					isNotInclude++;
				}
				stk.pollLast();
			}
			
			else {
				stk.addLast(list.get(i).number);
			}
			
		}
		
		System.out.println(notBeIncluded + " " + isNotInclude);
	}
}

// x축을 지나는 수직선의 x좌표와 고유코드를 가지는 클래스
class Pair implements Comparable<Pair> {
	int x;
	int number;
	
	public Pair(int x) {
		this.x = x;
	}

	@Override
	public int compareTo(Pair o) {
		return this.x - o.x;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + number + ")";
	}
}