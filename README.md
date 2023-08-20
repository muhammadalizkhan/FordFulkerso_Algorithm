# FordFulkerso_Algorithm
In Graph Theory, maximum flow is the maximum amount of flow that can flow from source node to sink node in a given flow network.

Ford-Fulkerson method implemented as per the Edmonds-Karp algorithm is used to find the maximum flow in a given flow network.

The Ford-Fulkerson algorithm is used to detect maximum flow from start vertex to sink vertex in a given graph. In this graph, every edge has the capacity.


Select any arbitrary path from S to T. In this step, we have selected path S-A-B-T . The minimum capacity among the three edges is 2 ( B-T ). Based on this, update the flow/capacity for each path.
