import java.util.*;

public class BFS {
    
    /**
     * Graph representation using adjacency list
     */
    static class Graph {
        private int vertices;
        private List<List<Integer>> adjacencyList;
        
        // Constructor
        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }
        
        // Add edge to the graph (undirected)
        public void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }
        
        /**
         * Breadth-First Search (BFS) algorithm
         * Time Complexity: O(V + E) where V is vertices and E is edges
         * Space Complexity: O(V) for the queue and visited array
         */
        public void bfs(int startVertex) {
            // Create a visited array to track visited vertices
            boolean[] visited = new boolean[vertices];
            
            // Create a queue for BFS traversal
            Queue<Integer> queue = new LinkedList<>();
            
            // Mark the starting vertex as visited and enqueue it
            visited[startVertex] = true;
            queue.add(startVertex);
            
            System.out.println("BFS Traversal starting from vertex " + startVertex + ":");
            
            // Process vertices from the queue
            while (!queue.isEmpty()) {
                // Dequeue a vertex and print it
                int currentVertex = queue.poll();
                System.out.print(currentVertex + " ");
                
                // Get all adjacent vertices
                for (int neighbor : adjacencyList.get(currentVertex)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            System.out.println();
        }
        
        /**
         * BFS that returns the traversal order as a list
         */
        public List<Integer> bfsTraversal(int startVertex) {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            
            visited[startVertex] = true;
            queue.add(startVertex);
            
            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                result.add(currentVertex);
                
                for (int neighbor : adjacencyList.get(currentVertex)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            return result;
        }
        
        /**
         * Find shortest path between two vertices
         */
        public List<Integer> findShortestPath(int start, int end) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            int[] parent = new int[vertices];
            Arrays.fill(parent, -1);
            
            visited[start] = true;
            queue.add(start);
            
            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                
                if (currentVertex == end) {
                    // Reconstruct path
                    List<Integer> path = new ArrayList<>();
                    int vertex = end;
                    while (vertex != -1) {
                        path.add(0, vertex);
                        vertex = parent[vertex];
                    }
                    return path;
                }
                
                for (int neighbor : adjacencyList.get(currentVertex)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        parent[neighbor] = currentVertex;
                        queue.add(neighbor);
                    }
                }
            }
            return new ArrayList<>(); // No path found
        }
    }
    
    public static void main(String[] args) {
        // Create a graph with 6 vertices
        Graph graph = new Graph(6);
        
        // Add edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        
        // Perform BFS from vertex 0
        graph.bfs(0);
        
        // Get BFS traversal as a list
        List<Integer> traversal = graph.bfsTraversal(0);
        System.out.println("BFS Traversal (as list): " + traversal);
        
        // Find shortest path between two vertices
        List<Integer> shortestPath = graph.findShortestPath(0, 5);
        System.out.println("Shortest path from 0 to 5: " + shortestPath);
    }
}