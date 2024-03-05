public class LinearQueue {
    Object arr[];
    int size;
    int rear = -1;

    LinearQueue(int size) {
        arr = new Object[size];
        this.size = size;
    }

    boolean isEmpty() {
        return rear == -1;
    }

    void add(Object data) {
        if (rear == size - 1) {
            System.out.println("Full Queue");
            return;
        }

        rear++;
        arr[rear] = data;
    }

    Object remove() {
        if (isEmpty())
            return -1;
        Object front = arr[0];
        for (int i = 0; i < rear; i++)
            arr[i] = arr[i + 1];
        rear--;
        return front;
    }

    Object peek() {
        if (isEmpty())
            return -1;
        return arr[0];
    }

    void display() {
        while (!isEmpty()) {
            System.out.println(peek());
            remove();
        }
    }

    public static void main(String[] args) {
        var queue = new LinearQueue(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        queue.display();

    }
}
