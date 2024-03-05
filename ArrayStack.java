public class ArrayStack {
    private Object[] a;
    private int size;

    ArrayStack(int capacity) {
        a = new Object[capacity];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public Object peek() {
        if (size == 0) throw new IllegalStateException();
        return a[size - 1];
    }

    public Object pop(){
        if(size == 0) throw new IllegalStateException();
            Object temp = a[--size];
        a[size] = null;
        return temp;
    }
    public void push(Object o){
        if(size == a.length)
            resize();
        a[size++] = o;
    }
    void pushArray(Object[] a){
        for(int i =0 ; i < a.length ; i++)
            push(a[i]);
    }
    public int size(){  return size;    }
    private void resize() {
        Object[] aa = a;
        a = new Object[2 * a.length];
        System.arraycopy(aa, 0, a, 0, size);
    }
    void display(){
        for(int i = 0; i < a.length ; i++)
            System.out.println(pop());
        System.out.println("[ null ]");
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[");
        for(int i =0 ; i< a.length; i++)
            stringBuilder.append(a[i]+",  ");
        stringBuilder.append("null ]");
        return stringBuilder.toString();
    }
    Object[] nextGreaterElement(Object[] array){
        Object[] result = new Object[array.length];
        for(int i = array.length-1 ; i>=0 ; i--){
            if(!isEmpty())
                while(!isEmpty())
                    pop();
            if (isEmpty())
                result[i] = -1;
            else result[i] = peek();
            push(array[i]);
        }
        return result;
    }
    boolean isEqual(LinkedListStack stack){
        for(int i = 0 ; i<a.length ; i++)
            if(a[i] != stack.pop())
                return false;
        return true;
    }
    boolean search(Object data){
        for(int i=0 ; i <a.length ; i++)
            if(a[i] == data)
                return true;
        return false;
    }

    public static void main(String[] args) {

        Object[] array = {1,2,3,4,5,6,7,8,9,10};
        var stack2 = new ArrayStack(array.length);
        stack2.pushArray(array);

        stack2.display();

    }
    }