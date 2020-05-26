# CSES
Java solutions to CSES Problem Set

## Graph-Section
### Building Teams - Find connected components in the graph.

### Labyrinth - BFS can be used to find the shortest path. Use 2 queues for, one for rows and one
   for columns for easy implementation.

#### Building Roads - Given n cities with m roads. If we could find out the isolated parts (clusters)
   of the graph, call it x. Then we need x-1 roads. 
   Ex : if u1     w1,w2     x1,x2,x3,x4      y1,y2,y3,y4     z1,z2  . Then we need 4 roads
         u1->w1,w2->x1,x2->y1,y4->z1
         u1->w2,w2->x4,x4->y2,y2->z1 , etc.

#### Message Route - Use BFS for the shortest path. Parent array can be used to trace the path.

#### Building Teams - Graph colouring. See if a bipartite graph can be made. 

### Round Trip - Detecting a cycle with more than 2 nodes. Use DFS with parent array to trace
   the path.
