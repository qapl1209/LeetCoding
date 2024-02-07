import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TwoButtons {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] vis = new boolean[1000000];
        Queue<Integer> q = new LinkedList<>();
        int layer = -1;
        int ans = -1;
        q.add(n);
        while(!q.isEmpty()) {
            layer++;
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int num = q.poll();
//                System.out.println(num);
                if(num == m) {
                    ans = layer;
                    break;
                }
                if(num < m && !vis[num*2]) {
                    q.add(num * 2);
                    vis[num*2]=true;
                }
                if(num - 1 >= 0 && !vis[num-1]) {
                    q.add(num-1);
                    vis[num-1]=true;
                }
            }
            if(ans != -1) break;
        }
        System.out.println(ans);
    }
}
