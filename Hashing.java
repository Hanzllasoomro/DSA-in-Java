import java.util.*;
import java.util.LinkedList;

public class Hashing {
    static class HashMap<K, V>{
        class Node{
            K key;
            V value;
            public Node(K key , V value){
                this.key = key;
                this.value = value;
            }
        }
        int n;
        int N;
        LinkedList<Node> buckets[];
        HashMap(){
            this.N = 4;
            this.buckets = new LinkedList[4];
            for(int i =0 ; i <4; i++)
                this.buckets[i]  = new LinkedList<>();
        }
        void put(K key , V value){
            int bi = hashFunction(key);
            int di = searchInLL(key , bi);

            if(di == -1 ) {
                buckets[bi].add(new Node(key, value));
                n++;
            }
             else{
                    Node node = buckets[bi].get(di);
                    node.value = value;
             }
            double lamda = (double) n/N;
             if(lamda > 2.0)
                 rehash();
        }
        private void rehash(){
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[N+2];
            for(int i=0 ; i <N+2 ; i++)
                buckets[i] = new LinkedList<>();
            for(int i=0 ; i <oldBucket.length ; i++) {
                LinkedList<Node> ll = oldBucket[i];
                for(int j =0 ; j < ll.size(); j++){
                    Node node = ll.get(j);
                    put(node.key , node.value);
                }
            }
        }
        private int hashFunction(K key){
            int bi = key.hashCode();
            return Math.abs(bi) % N;
        }
        private int searchInLL(K key , int bi){
            LinkedList<Node> ll = buckets[bi];
            int di = 0;
            for(int i =0 ; i <ll.size() ; i++)
                if(ll.get(i).key == key)
                    return i;
            return -1;
        }
        boolean isEmpty(){
            return n == 0;
        }
        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();
            for(int i = 0 ; i < buckets.length; i++){
                LinkedList<Node> ll = buckets[i];
                for(int j =0 ; j <ll.size(); j++){
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }
        public V get(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key , bi);

            if(di == -1 )
                return null;
            else{
                Node node = buckets[bi].get(di);
                return node.value ;
            }
        }
        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di == -1)
                return null;
            else {
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }
        }

        public boolean containsKey(K key){
            int bi = hashFunction(key);
            int di = searchInLL(key , bi);

            if(di == -1)
                return false;
            else
                return true;
        }
    }

    public static void main(String[] args) {
        HashMap<String , Integer> map = new HashMap<>();
        map.put("Pak" , 190);
        map.put("China", 200);
        map.put("US", 50);

        ArrayList<String> keys = map.keySet();
        for(int i=0 ; i < keys.size(); i++)
            System.out.println(keys.get(i)+" "+ map.get(keys.get(i)));
    }
}
