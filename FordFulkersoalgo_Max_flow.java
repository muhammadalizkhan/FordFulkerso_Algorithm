class MaxFlow{
    // Initializing V=6 because
    // number of vertices is 6.
    static final int V=6;
    public boolean bfs(int residual_g[][],int s,int t,int parent[]){
        // visited array will keep track
        // of all the visited vertices.
        boolean visited[]=new int[V];
        
        // Queue to keep vertices in Queue
        // for doing BFS. 
        Queue<Integer> q=new LinkedList<Integer>();
        // Add source in q and mark it visited. 
        q.add(s);
        visited[s]=true;
        // Make parent of source -1.
        parent[s]=-1;
        
        // Standard BFS loop
        while(!q.isEmpty()){
            int u=q.poll();
            for(int v=0;v<V;v++){
                if(!visited[v]&&residual_g[u][v]>0){
                    
                    visited[v]=true;
                    q.add(v);
                    parent[v]=u;
                    
                    // If we found a connection to sink 
                    // from any of the nodes. We will return
                    // true i.e. augmenting path exists. 
                    if(v==t) 
                        return true;
                }
            }
        }
        // If we can't reach to sink node then
        // return false which will indicate
        // that there is no augmenting path left.
        return false;
    }
    public int Ford_Fulkerson(int graph[][],int s,int t){
        
        // Creating a residual graph with residual
        // capacity as the capacity of the original graph.
        int residual_g[][]=new int[V][V];
        
        // residual_g[i][j] indicates residual capacity
        // of edge from i to j. If residual_g[i][j]==0
        // it means no edge exists.
        for(int i=0;i<V;i++)
            for(int j=0;j<V;j++)
                residual_g[i][j]=g[i][j];
        
        // Parent array will store the augmenting
        // path found during the BFS. 
        int parent[]=new int[V];
        
        // Initializing max flow as 0 i.e. there
        // is no flow initially.
        int max_flow=0;
        
        // Iterating in breadth-first manner till there
        // is augmenting path from source to sink. 
        while(bfs(residual_g,s,t))
        {
            // Initializing flow of the current path
            // with a very big value. 
            int flow=Integer.MAX_VALUE;
            
            // Finding minimum residual capacity of the
            // edges in augmenting path i.e. maximum 
            // flow through that path.
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                flow=Math.min(flow, residual_g[u][v]);
            }
            // Adding maximum flow of this path
            // to the overall flow.
            max_flow+=flow;
            
            // Updating residual capacities of edges 
            // and the back edges along the path. 
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                // Decreasing capacity of the forward edge.
                residual_g[u][v]-=flow;
                // Increasing capacity of the backward edge.
                residual_g[v][u]+=flow;
            }
        }
        
        // Returning our answer
        // i.e. Maximum flow through
        // the network. 
        return max_flow;
    }
}
