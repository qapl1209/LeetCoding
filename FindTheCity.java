class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
      // Floyd-Warshall Sol.n, N^3
        int[][] matrix = new int[n][n];
        for(int[] row : matrix) {
            Arrays.fill(row, 10001);
        }
        for(int[] edge : edges) {
            matrix[edge[0]][edge[1]] = edge[2];
            matrix[edge[1]][edge[0]] = edge[2];
        }
        for(int i = 0; i < n; i++) {
            matrix[i][i] = 0;
        }
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][k]+matrix[k][j], matrix[i][j]);
                }
            }
        }
        int min = n;
        int minCity = -1;
        for(int i = 0; i < n; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] <= distanceThreshold) count++;
            }
            if(count <= min) {
                min = count;
                minCity = i;
            }
        }
        return minCity;
    }
}
