public class HashTables {
    Data[] array;
    int size;
    public HashTables(int capacity){
        array = new Data[capacity];
    }
    private int hash(Object key){
        return (key.hashCode() & 0x7FFFFFFF% array.length);
    }
    public Object get(Object key){
        Object temp = array[hash(key)].value;
        return temp;
    }
    public void linearPut(Object key, Object value){
        int x = hash(key);
        for(int i=1; array[x]!=null; i++){
            x = (x+i)% array.length;
        }
        array[x] = new Data(key, value);
        size++;
    }
    public void quadraticPut(Object key, Object value){
        int x = hash(key);
        for(int i=1; array[x]!=null; i++){
            x = (x + (i*i))%array.length;
        }
        array[x] = new Data(key, value);
        size++;
    }
    public int size(){
        return size;
    }
    public void display(){
        for(int i=0; i< array.length; i++){
            if(array[i]!=null)
                System.out.println(array[i]);
        }
    }
    static class Data{
        Object key, value;
        public Data(Object key, Object value){
            this.key = key;
            this.value = value;
        }
        public String toString(){
            return key.toString() + ", " + value.toString();
        }
    }


    public static void main(String[] args) {
        HashTables table = new HashTables(10);
        table.linearPut(23,"Talha");
        table.linearPut(33, "Farooque");
        table.linearPut(29, "Rafy");
        table.linearPut(23, "Arbab");
        table.linearPut(25, "Parkash");
        System.out.println("Linear probing");
        table.display();

        HashTables table2 = new HashTables(10);
        table2.quadraticPut(23,"Talha");
        table2.quadraticPut(33, "Farooque");
        table2.quadraticPut(29, "Rafy");
        table2.quadraticPut(23, "Arbab");
        table2.quadraticPut(25, "Parkash");
        System.out.println("Quadratic probing");
        table2.display();
    }
}