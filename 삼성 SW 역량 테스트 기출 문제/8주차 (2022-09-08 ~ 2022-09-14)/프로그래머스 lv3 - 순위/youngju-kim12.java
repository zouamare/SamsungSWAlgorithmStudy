package programmers;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int f[][] = new int[n+1][n+1];
        
        for(int i=0;i<results.length;i++){
            int x = results[i][0];
            int y = results[i][1];
            
            f[x][y]=1;
            f[y][x]=-1;
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                for(int k=1;k<n+1;k++){
                    if(f[i][k]==1 && f[k][j]==1){
                        f[i][j]=1;
                        f[j][i]=-1;
                    }
                    if(f[i][k]==-1 && f[k][j]==-1){
                        f[i][j]=-1;
                        f[j][i]=1;
                    }
                }
            }
        }
        for(int i=1;i<n+1;i++){
            int cnt=0;
            for(int j=1;j<n+1;j++){
                if(f[i][j]!=0) cnt++;
            }
            if(cnt==n-1) answer++;
        }
        return answer;
    }
}
