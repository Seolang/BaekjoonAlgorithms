import java.util.*;

class Solution {
    public int solution(int N, int number) {

        HashSet<Integer>[] dp = new HashSet[9];
        
        for(int i=0; i<=8; i++) {
            dp[i] = new HashSet<>();
        }
        
        int n = N;
        dp[1].add(N);
        
        if (n == number) {
            return 1;
        }
        
        for(int i=2; i<=8; i++) {
            
            n = n*10 + N;
            dp[i].add(n);
            
            for(int j=1; j<i; j++) {
                
                HashSet<Integer> frontDp = dp[j];
                HashSet<Integer> backDp = dp[i-j];
                
                for(int front : frontDp) {
                    for(int back : backDp) {
                        dp[i].add(front + back);
                        
                        dp[i].add(front - back);
                        
                        dp[i].add(front * back);
                        
                        if (front != 0 && back != 0) {
                            dp[i].add(front / back);
                        }
                    }
                }
            }
            
            for(int num : dp[i]) {
                if (num == number) return i;
            }
        }
        
        return -1;
    }
}