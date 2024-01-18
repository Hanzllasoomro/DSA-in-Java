public class LinkedListStack {
    private Node top;
    private int size = 0;

    class Node {
        private Object data;
        private Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    Object peek() {
        if (top == null) throw new IllegalStateException();
        return top.data;
    }

    Object pop() {
        Object oldTop = top.data;
        top = top.next;
        size--;
        return oldTop;
    }

    void push(Object o) {
        top = new Node(o, top);
        size++;
    }
    void push(Object o , int position){
        if(top == null || position >= size)
            top = new Node(o, top);
            Node temp = top;
            for (int i = size; i >= 1; i--) {
                if(i == position){
                    Node newNode = new Node(o, temp.next);
                    temp.next = newNode;
                     break;
                }
                temp = temp.next;
            }
        size++;
    }

    Node delete(Object data) {
        if (top == null)
            return top;
        if (top.data == data)
            return top.next;
        for (Node p = top; top.next != null; p = p.next)
            if (p.next.data == data) {
                p.next = p.next.next;
                break;
            }
        size--;
        return top;
    }
    Node delete(int position){
        if(top == null)
            return top;
        else if(position >= size)
            return top.next;
        else {
            Node temp = top;
            for (int i = 1; i <= size ; i++) {
                if (i == position ) {
                    temp.next = temp.next.next;
                    break;
                }
                temp=temp.next;
            }
        }
        size--;
        return top;
    }
    Object deleteBottom(){
        Node temp = top;
        while(temp.next.next != null)
            temp = temp.next;
        Node bottom = temp.next;
        temp.next = temp.next.next;
        size--;
        return bottom.data;
    }
    void display() {
        for (Node temp = top; temp != null; temp = temp.next)
            System.out.println(temp.data );
        System.out.println("[null]");
    }
    int getPosition(Object data){
        int position = size;
        if(data == top.data)
           return size;
        for(Node temp = top ; temp.next != null ; temp = temp.next) {
            if (data == temp.data) {
                return position;
            }
            position--;
        }
        return position;
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[");
        for(Node temp = top; temp != null ; temp = temp.next)
            stringBuilder.append(temp.data +", ");
            stringBuilder.append("null ]");
            return stringBuilder.toString();
    }
    boolean isEqual(ArrayStack stack){
        for(Node temp = top; temp != null ; temp = temp.next)
            if(temp.data != stack.pop())
                return false;
        return true;
    }

    public static void main(String[] args) {

        var stack = new LinkedListStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(50);
        stack.push(60);
        stack.push(40,4);
        System.out.println(stack.toString());

        Object[] array = {10,20,30,40,50,60};
        var stack2 = new ArrayStack(6);
        stack2.pushArray(array);
        System.out.println(stack2.toString());

        System.out.println(stack.isEqual(stack2));

    }
}
