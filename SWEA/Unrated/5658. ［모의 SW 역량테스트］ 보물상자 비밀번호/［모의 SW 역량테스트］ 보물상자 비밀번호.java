import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			String temp = br.readLine();
			int divider = temp.length()/4;
			temp += temp.substring(0, divider);
			
			Set<Long> set = new HashSet<>();
			for(int i=0; i<temp.length()-divider; i++) {
				set.add(Long.parseLong(temp.substring(i, divider+i), 16));
			}
			
			List<Long> list = new ArrayList<>(set);
			
			list.sort(Comparator.reverseOrder());
			sb.append("#").append(t).append(" ").append(list.get(K-1)).append("\n");
		}
		System.out.println(sb);
	}
}