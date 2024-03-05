public class CircularQueue {
    Object arr[];
    int size;
    int rear = -1;
    int front = -1;

    CircularQueue(int size) {
        arr = new Object[size];
        this.size = size;
    }

    boolean isEmpty() {
        return rear == -1 && front == -1;
    }

    boolean isFull() {
        return (rear + 1) % size == front;
    }

    void add(Object data) {
        if (isFull()) {
            System.out.println("Full Queue");
            return;
        }
        if (front == -1)
            front = 0;

        rear = (rear + 1) % size;
        arr[rear] = data;
    }

    Object remove() {
        if (isEmpty())
            return -1;
        Object result = arr[front];
        if (rear == front)
            rear = front = -1;
        else front = (front + 1) % size;
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
