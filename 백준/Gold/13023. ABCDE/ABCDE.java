import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 문제 설명이 매우 난해하다...
 * 결론적으로 친구관계 연결 길이가 순환 없이 5 이상인 것이 존재하는지 찾으면 된다
 * 존재하기만 하면 되므로 DFS로 풀이하였다
 */

public class Main {
    static int N, M;
    static Map<Integer, List<Integer>> map;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();

        /*
         * 친구가 0~N-1 까지 연속적으로 존재한다는 보장이 없으므로
         * key 값으로 찾을 수 있는 map을 사용하였다.
         *
         * map의 값은 친구관계에 있는 다른 숫자를 넣는다.
         * visit은 N크기가 2000 정도라 배열로 만들어 사용하였다.
         */

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            List<Integer> listA = map.getOrDefault(a, new ArrayList<>());
            List<Integer> listB = map.getOrDefault(b, new ArrayList<>());
            listA.add(b);
            listB.add(a);
            map.put(a, listA);
            map.put(b, listB);
        }

        for(int start : map.keySet()) {
            visit = new boolean[2000];
            // 시작지점과 나 자신을 포함한 카운트 1을 매개변수로 입력
            boolean found = dfs(start, 1);

            // 체인을 하나라도 찾으면 바로 리턴하여 프로그램을 종료
            if (found) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
        return;
    }

    /*
     * @param start 시작 원소
     * @param cnt 친구 연쇄 수 (처음 시작이 1)
     *
     * 각 start 지점부터 자신의 이웃으로 하여금 dfs로 탐색해나간다
     * 친구 4명을 찾으면 cnt가 5가 되므로 찾았다는 flag를 리턴하고, 스택의 재귀함수들을 즉시 모두 종료시킨다
     */
    static boolean dfs(int start, int cnt) {
        if (cnt == 5) {
            return true;
        }

        visit[start] = true;
        boolean foundRelations = false;

        for(int friend : map.get(start)) {
            if (!visit[friend]) {
                visit[friend] = true;
                foundRelations |= dfs(friend, cnt+1);
                visit[friend] = false;
            }

            if (foundRelations) break;
        }

        return foundRelations;
    }
}