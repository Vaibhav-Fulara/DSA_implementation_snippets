import java.util.*;
import java.lang.*;
import java.io.*;
class Main{
  public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine().trim());
      while(T-->0){
          String[] s = br.readLine().trim().split(" ");
          int V = Integer.parseInt(s[0]);
          int E = Integer.parseInt(s[1]);
          ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
          for(int i = 0; i < V; i++) adj.add(i, new ArrayList<Integer>());
          for(int i = 0; i < E; i++){
              String[] S = br.readLine().trim().split(" ");
              int u = Integer.parseInt(S[0]);
              int v = Integer.parseInt(S[1]);
              adj.get(u).add(v);
              adj.get(v).add(u);
          }
          Solution obj = new Solution();
          int[] ans = obj.articulationPoints(V, adj);
          for(int i: ans)
              System.out.print(i + " ");
          System.out.println();
     }
}


class Solution{
    public int[] articulationPoints(int V, ArrayList<ArrayList<Integer>> adj){
        
        HashSet<Integer> aps = new HashSet<>();     // stores the articulation points
        boolean[] vis = new boolean[V];             // array to mark visited points
        int[] discoveryTime = new int[V];           // array to store discovery time
        int[] lowestTime = new int[V];              // array to store lowest time reached
        
        for(int i = 0; i < V; i++){
            if(!vis[i]){
                dfs(adj, aps, vis, discoveryTime, lowestTime, i, -1);
            }
        }
        
        if(aps.size() == 0)  return new int[] {-1};
        
        else{
            int[] res = new int[aps.size()];
            int idx = 0;
            for(int val: aps) res[idx++] = val;
            Arrays.sort(res);
            return res;
        }
    }
    
    int time = 0;
    public void dfs(ArrayList<ArrayList<Integer>> adj, HashSet<Integer> aps, boolean[] vis, int[] discoveryTime, int[] lowestTime, int u, int parent){
        
        vis[u] = true;
        discoveryTime[u] = lowestTime[u] = ++time;
        
        int c = 0;
        for(int v: adj.get(u)){
            if(v == parent) continue;
            else if(vis[v]) lowestTime[u] = Math.min(lowestTime[u], discoveryTime[v]);
            else {
                c++;
                dfs(adj, aps, vis, discoveryTime, lowestTime, v, u);
                lowestTime[u] = Math.min(lowestTime[u], lowestTime[v]);
                
                if(parent != -1 && lowestTime[v] >= discoveryTime[u]) aps.add(u);
            }
        }
        
        if(parent == -1 && c > 1) aps.add(u);
    }
}
