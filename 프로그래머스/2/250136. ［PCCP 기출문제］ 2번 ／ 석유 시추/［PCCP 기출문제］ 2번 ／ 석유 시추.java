import java.util.*;

class Solution {
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    int rlen, clen;
    int[] oils;
    
    public int solution(int[][] land) {
        int answer = 0;

        rlen = land.length;
        clen = land[0].length;
        oils = new int[clen];
        
        for(int r=0; r<rlen; r++) {
            for(int c=0; c<clen; c++) {
                
                if (land[r][c] == 1) {
                    BFS(r, c, land);
                }
            }
        }
        
        for(int i=0; i<clen; i++) {
            if (oils[i] > answer) {
                answer = oils[i];
            }
        }
        
        return answer;
    }
    
    void BFS(int r, int c, int[][] land) {
        
        int amount = 1;
        HashSet<Integer> cols = new HashSet<>();
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        
        cols.add(c);
        queue.offer(new Pair(r, c));
        land[r][c] = 0;
        
        while(!queue.isEmpty()) {
            
            Pair now = queue.poll();
            
            for(int i=0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                
                if (0 <= nr && nr < rlen && 0 <= nc && nc < clen && land[nr][nc] == 1) {
                    land[nr][nc] = 0;
                    amount++;
                    cols.add(nc);
                    queue.add(new Pair(nr, nc));
                }
            }
        }
        
        for(int col : cols) {
            oils[col] += amount;
        }
    }
}

class Pair {
    public int r;
    public int c;
    
    public Pair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

