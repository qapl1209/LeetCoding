import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Djikstra_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<Integer, ArrayList<Edge>> map = new HashMap<>();
        long[] weight = new long[n];
        Arrays.fill(weight, Long.MAX_VALUE);
        weight[0]=0;
        boolean[] vis = new boolean[n];
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            map.get(a).add(new Edge(b,w));
            map.get(b).add(new Edge(a,w));
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while(!q.isEmpty()) {
            int node = q.poll();
            vis[node]=true;
            for(Edge e : map.get(node)) {
//                if(vis[e.neighbor]) {
//                    continue;
//                }
                if(weight[node] + e.weight < weight[e.neighbor]) {
                    weight[e.neighbor] = weight[node] + e.weight;
                    parent[e.neighbor] = node;
                    q.add(e.neighbor);
                }
            }
        }
        if(weight[n-1] == Long.MAX_VALUE) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            ArrayList<Integer> ans = new ArrayList<>();
            int cur = n;
            while(cur != 0) {
                ans.add(cur);
                cur = parent[cur-1];
            }
            for(int i = ans.size()-1; i >= 0; i--) {
                sb.append(ans.get(i)+" ");
            }
            System.out.println(sb);
        }

    }
    static class Edge implements Comparable<Edge>{
        int neighbor, weight;
        public Edge(int neighbor, int weight){
            this.neighbor = neighbor;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
