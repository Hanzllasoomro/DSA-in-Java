public class AdjacencyMatrixGraph {
    int vertices;
    int[][] matrix;

    public AdjacencyMatrixGraph(int vertices){
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
    }
    public void addEdge(int source, int destination){
        matrix[source][destination] = 1;
        matrix[destination][source] = 1;
    }
    public void display(){
        for(int i=0; i<vertices; i++){
            for(int j=0; j<vertices; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int findDegree(int vertex){
        int degree = 0;
        for(int i=0; i<vertices; i++){
            if(matrix[vertex][i] == 1){
                degree++;
            }
        }
        return degree;
    }
    public int findAdjacentMatrix(int vertex){
        int count = 0;
        for(int i=0; i<vertices; i++){
            if(matrix[vertex][i] == 1){
                count++;
            }
        }
        return count;
    }

    public void printPath(int vertex){
        System.out.println("Path from vertex " +vertex + " to it's connection ");
        for(int i=0; i<vertices; i++)
            if(matrix[vertex][i] == 1)
                System.out.println(vertex + " -> " + i);
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        System.out.println("--------------------------------ADJACENCY MATRIX GRAPH-----------------------------------");
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.display();
        System.out.println("Degree of vertex 0: " + graph.findDegree(0));
        System.out.println("Degree of vertex 1: " + graph.findDegree(1));
        System.out.println("Degree of vertex 2: " + graph.findDegree(2));
        System.out.println("Degree of vertex 3: " + graph.findDegree(3));
        System.out.println("Degree of vertex 4: " + graph.findDegree(4));
        System.out.println("Adjacent matrix of vertex 0: " + graph.findAdjacentMatrix(0));
        System.out.println("Adjacent matrix of vertex 1: " + graph.findAdjacentMatrix(1));
        System.out.println("Adjacent matrix of vertex 2: " + graph.findAdjacentMatrix(2));
        System.out.println("Adjacent matrix of vertex 3: " + graph.findAdjacentMatrix(3));
        System.out.println("Adjacent matrix of vertex 4: " + graph.findAdjacentMatrix(4));
        System.out.println("-------PATHS-------");
        graph.printPath(0);
        graph.printPath(1);
        graph.printPath(2);
        graph.printPath(3);
        graph.printPath(4);

    }
}
