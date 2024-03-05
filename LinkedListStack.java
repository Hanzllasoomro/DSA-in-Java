public class LinkedListStack {
    private Node top;
    private int size = 0;

    public static void main(String[] args) {

        var list = new LinkedList();
        var stack = new LinkedListStack();
        list.add("apple");
        list.add("oranges");
        list.add("kiwi");
        list.add("lemon");
        list.add("mangoes");

        stack.toStack(list);
        System.out.println(stack.search("kiwi"));

    }

    boolean isEmpty() {
        if (top != null)
            return false;
        return true;
    }

    int getSize() {
        int s = 0;
        Node temp = top;
        while (temp != null) {
            temp = temp.next;
            ++s;
        }
        return s;
    }

    Object peek() {
        if (top == null) throw new IllegalStateException();
        return top.data;
    }
    Object peekSecondFromBottom(){
        if(top == null)
            return "Stack doesn't contain any element";
        if(top.next == null)
            return "Stack doesn't contain second element";
        Node temp = top;
        while (temp.next.next != null)
            temp = temp.next;
            return temp.data;
    }
    Object peekBottom(){
        if(top == null)
            return "Stack doesn't contain any element";
        if(top.next == null)
            return "Stack doesn't contain second element";
        Node temp = top;
        while (temp.next != null)
            temp = temp.next;
        return temp.data;
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

    void push(Object o, int position) {
        if (top == null || position >= size)
            top = new Node(o, top);
        Node temp = top;
        for (int i = size; i >= 1; i--) {
            if (i == position) {
                Node newNode = new Node(o, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        size++;
    }

    void pushAtBottom(Object data, LinkedListStack s) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        Object temp = s.pop();
        pushAtBottom(data, s);
        s.push(temp);
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

    Node delete() {
        if (top == null)
            return top;
        top = top.next;
        return top;
    }

    Node delete(int position) {
        if (top == null)
            return top;
        else if (position >= size)
            return top.next;
        else {
            Node temp = top;
            for (int i = 1; i <= size; i++) {
                if (i == position) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
        size--;
        return top;
    }

    Object deleteBottom() {
        Node temp = top;
        while (temp.next.next != null)
            temp = temp.next;
        Node bottom = temp.next;
        temp.next = temp.next.next;
        size--;
        return bottom.data;
    }

    void display() {
        for (Node temp = top; temp != null; temp = temp.next)
            System.out.println(pop());
        System.out.println("[null]");
    }

    int getPosition(Object data) {
        int position = size;
        Node temp = top;
        if (data == top.data)
            return size;
        for (; temp.next != null; temp = temp.next) {
            if (data == temp.data) {
                return position;
            }
            position--;
        }
        if (data != temp.data)
            position = -1;
        return position;
    }

    boolean search(Object data) {
        if (data == top.data)
            return true;
        for (Node temp = top; temp != null; temp = temp.next) {
            if (data.equals(temp.data))
                return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Node temp = top; temp != null; temp = temp.next)
            stringBuilder.append(temp.data + ", ");
        stringBuilder.append("null ]");
        return stringBuilder.toString();
    }

    boolean isEqual(ArrayStack stack) {
        for (Node temp = top; temp != null; temp = temp.next)
            if (temp.data != stack.pop())
                return false;
        return true;
    }
    boolean isBottomEqual(LinkedListStack stack){
        if(stack.isEmpty() || top == null)
            return false;
        Node temp = stack.top ;
        while (temp.next != null)
            temp = temp.next;
        if(temp.data == peekBottom())
            return true;
        return false;
    }

    void reverse(LinkedListStack stack) {
        if (stack.isEmpty())
            return;
        Object temp = stack.pop();
        reverse(stack);
        pushAtBottom(temp, stack);
    }

    boolean doesContain(int numberOfElements) {
        int count = 0;
        for (Node temp = top; temp != null; temp = temp.next) {
            count++;
            if (count >= numberOfElements)
                return true;
        }
        return false;
    }
    void toStack(LinkedList list){
        for(LinkedList.Node temp = list.head; temp!=null ; temp =temp.nextNode)
            push(temp.data);
    }

    class Node {
        private Object data;
        private Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

    }
}
