import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static boolean[] visit;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[100001];
        visit = new boolean[100001];


        for(int i=1; i<=100000; i++) {
            adjList[i] = new ArrayList<>();
        }

        int from = 0, to, weight;

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));

        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int count = 0;
        long result = 0;

        pq.offer(new Edge(from, 0));

        while(!pq.isEmpty() && count < V) {
            Edge now = pq.poll();

            if (visit[now.vertex]) continue;

            result += now.weight;
            count++;
            visit[now.vertex] = true;

            for(Edge e : adjList[now.vertex]) {
                if (!visit[e.vertex]) {
                    pq.offer(e);
                }
            }
        }
        System.out.println(result);
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        public Edge(int v, int w) {
            this.vertex = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return vertex + " " + weight;
        }
    }
}