# CSES
Java solutions to CSES Problem Set

## Graph-Algorithms
 <ins>Building Teams</ins> - Find connected components in the graph.

 <ins>Labyrinth</ins> - BFS can be used to find the shortest path. Use 2 queues for, one for rows and one
   for columns for easy implementation.

 <ins>Building Roads</ins> - Given n cities with m roads. If we could find out the number of isolated subgraphs
   of the graph, call it x. Then we need x-1 roads. Only 1 road is needed to connect any two components. 
   
 <ins>Message Route</ins> - Use BFS for the shortest path. Parent/prev array can be used to trace the path.

 <ins>Building Teams</ins> - Graph colouring. See if a bipartite graph can be made. 

 <ins>Round Trip</ins> - Detecting a cycle with more than 2 nodes. Use DFS with parent/prev array to trace
   the path.

 <ins>Shortest Routes I</ins> - Dijkstra algorithm. Even lazy dijkstra would work.

 <ins>Shortest Routes I</ins> - Precompute all distances using eager implementation of Dijkstra algorithm. After that each
   query would run in O(1).
