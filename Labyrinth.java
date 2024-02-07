import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Labyrinth {
    static boolean[][] vis;
    static StringBuilder ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][m];
        vis = new boolean[n][m];
        ans = new StringBuilder();
        Pair start = null;
        Pair end = null;
        for(int i =0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++){
                switch(line.charAt(j)) {
                    case '#':
                        grid[i][j] = 1;
                        break;
                    case 'A':
                        start = new Pair(i,j);
                        break;
                    case 'B':
                        end = new Pair(i,j);
                        break;
                    default:
                        break;
                }
            }
        }
        ans = recurse(start.x, start.y, n, m, new StringBuilder(), end.x, end.y, grid);
        if(ans==null) System.out.println("NO");
        else {
            System.out.println("YES");
            System.out.println(ans.length());
            System.out.println(ans);
        }

    }
    static int[] dX = new int[]{0,0,1,-1};
    static int[] dY = new int[]{1,-1,0,0};
    static StringBuilder recurse(int x, int y, int n, int m, StringBuilder path, int endX, int endY, int[][] grid) {
        if(x == endX && y == endY) {
            return path;
        }
        vis[x][y] = true;
        for(int i =0 ; i < 4; i++) {
            int newX = x+dX[i];
            int newY = y+dY[i];
            if(newX < 0 || newY < 0 || newX >= n || newY >= m || vis[newX][newY] || grid[newX][newY] == 1) {
                continue;
            }
            if(i == 0) {
                path.append('R');
            }
            else if(i ==1) {
                path.append('L');
            }
            else if(i == 2) {
                path.append('D');
            }
            else {
                path.append('U');
            }
            StringBuilder yes = recurse(newX, newY, n, m, path, endX, endY, grid);
            if(yes != null) return yes;
            path.deleteCharAt(path.length()-1);
        }
        return null;
    }
    static class Pair{
        int x,y;
        public Pair(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

}
