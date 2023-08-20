class MaxFlow{
    int V=6;
    bool bfs(int residual_g[V][V],int s,int t,int parent[]){

        bool visited[V];
        memset(visited, 0, sizeof(visited));
        queue<int> q;
        q.push(s);
        visited[s]=true;
        parent[s]=-1;
        while(!q.empty()){
            int u=q.front();
            q.pop();
            for(int v=0;v<V;v++){
                if(!visited[v]&&residual_g[u][v]>0){
                    
                    visited[v]=true;
                    q.push(v);
                    parent[v]=u;
                    if(v==t) 
                        return true;
                }
            }
        }
        return false;
    }
    int Ford_Fulkerson(int graph[V][V],int s,int t){
        int residual_g[V][V];
        for(int i=0;i<V;i++)
            for(int j=0;j<V;j++)
                residual_g[i][j]=g[i][j];
        int parent[V];
        int max_flow=0;
        while(bfs(residual_g,s,t))
        {
            int flow=INT_MAX;
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                flow=min(flow, residual_g[u][v]);
            }
          
            max_flow+=flow;
            for(int v=t;v!=s;v=parent[v])
            {
                int u=parent[v];
                residual_g[u][v]-=flow;
                residual_g[v][u]+=flow;
            }
        }
        return max_flow;
    }
};
