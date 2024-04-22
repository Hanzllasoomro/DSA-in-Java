public class Graph {
    class Node {
        int source;
        int destination;
        Node next;

        public Node(int source, int destination) {
            this.source = source;
            this.destination = destination;
            this.next = null;
        }
    }

    class LinkedList {
        Node root;
    }
    int vertex;
    LinkedList array[];
    public Graph(int vertex){
        this.vertex = vertex;
        array = new LinkedList[vertex];
        for(int i=0; i<vertex; i++){
            array[i] = new LinkedList();
            array[i].root = null;
        }
    }
    public void addEdge(int source, int destination){
        Node newNode = new Node(source, destination);
        newNode.next = array[source].root;
        array[source].root = newNode;

        newNode = new Node(destination, source);
        newNode.next = array[destination].root;
        array[destination].root = newNode;
    }

    public void display(){
        for(int i=0; i<array.length; i++){
            Node current = array[i].root;
            System.out.print(i + " -> ");
            while(current != null){
                System.out.print(current.destination + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        System.out.println("---------PATHS---------");
        graph.display();
    }

}
