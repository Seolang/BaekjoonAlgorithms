import java.util.*;

class Solution {
    
    static String s_target;
    static String[] s_words;
    static int answer;
    static int[] indexStack;
    
    public int solution(String begin, String target, String[] words) {
        indexStack = new int[51];
        answer = Integer.MAX_VALUE;
        s_target = target;
        s_words = words;
        DFS(begin, 0);
        
        if (answer == Integer.MAX_VALUE)
            answer = 0;
        
        return answer;
    }
    
    public static void DFS(String currentWord, int lastIndex) {
        if (currentWord.equals(s_target)) {
            answer = lastIndex;
            return;
        }
        
        if (s_words.length <= lastIndex || answer <= lastIndex) {
            return;
        }
        
        for(int i=0; i<s_words.length; i++) {
            
            for(int j=0; j<lastIndex; j++) {
                if (i == indexStack[j]) 
                    continue;
            }
            
            if (diffOneWord(currentWord, s_words[i])) {
                indexStack[lastIndex] = i;
                DFS(s_words[i], lastIndex+1);
            }
        }
    }
    
    public static boolean diffOneWord(String a, String b) {
        
        boolean alreadyDiff = false;
        
        for(int i=0; i<a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                
                if (alreadyDiff) {
                    return false;
                }
                else {
                    alreadyDiff = true;
                }
            }
        }
        
        return alreadyDiff;
    }
}