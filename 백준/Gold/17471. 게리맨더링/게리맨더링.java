import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	
	static int N, result = Integer.MAX_VALUE, test;
	static boolean[] select, visit;
	static Node[] nodes;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        select = new boolean[N+1];
        nodes = new Node[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
        	nodes[i] = new Node(Integer.parseInt(st.nextToken()), N+1);
        }
        
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int neighbors = Integer.parseInt(st.nextToken());
        	for(int j=0; j<neighbors; j++) {
        		nodes[i].neighbors[Integer.parseInt(st.nextToken())] = true;
        	}
        }
        
        
        subset(1, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
    
    public static void subset(int idx, int cnt) {
    	if (cnt == N) {
    		int cities = 0;
    		visit = new boolean[N+1];
    		
    		for(int i=1; i<=N; i++) {
    			if (select[i] == true) cities++;
    		}
    		
    		if (cities == 0 || cities == N) return;
    		
    		int alies = BFS(true);
    		int axis = BFS(false);
    		
    		for(int i=1; i<=N; i++) {
    			if (visit[i] == false) return;
    		}
    		
    		result = Math.min(result, Math.abs(alies - axis));
    		return;
    	}

		select[idx] = true;
		subset(idx+1, cnt+1);
		select[idx] = false;
		subset(idx+1, cnt+1);
    }
    
    public static int BFS(boolean target) {
    	int rst = 0;
    	int start = 0;
    	for(int i=1; i<=N; i++) {
    		if (select[i] == target) {
    			start = i;
    			break;
    		}
    	}
    	
    	
    	Queue<Integer> q = new ArrayDeque<>();
    	q.offer(start);
    	visit[start] = true;
    	rst += nodes[start].popular;
    	
    	while(!q.isEmpty()) {
    		int node = q.poll();
    		
    		for(int i=1; i<=N; i++) {
    			if (nodes[node].neighbors[i] && (select[i] == target) && !visit[i]) {
    				q.offer(i);
    				visit[i] = true;
    				rst += nodes[i].popular;
    			}
    		}
    	}
    	
    	return rst;
    }
    
}

class Node {
	int popular;
	boolean[] neighbors;
	
	public Node(int p, int N) {
		this.popular = p;
		this.neighbors = new boolean[N];
	}
}