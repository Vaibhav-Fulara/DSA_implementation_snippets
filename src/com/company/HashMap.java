package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapImplementation {
    public static class HashMap<K,V>{
        private class HMNode{
            K key;
            V value;
            HMNode(K key,V value){
                this.key = key;
                this.value = value;
            }
        }
        private int size;
        private LinkedList<HMNode>[]buckets;

        public HashMap(){
            initbuckets(4);
            size = 0;
        }

        private void initbuckets(int N){
            buckets = new LinkedList[N];
            for(int i=0; i<N; i++) buckets[i] = (new LinkedList<>());
        }

        private int findBucket(K key){
            int idx = key.hashCode();
            return Math.abs(idx) % buckets.length;
        }

        private int findDataInBucket(K key, int bi){
            LinkedList<HMNode>temp = buckets[bi];
            if(temp.size()==0) return -1;
            for(int i=0; i<temp.size(); i++){
                if(temp.get(i).key.equals(key)) return i;
            }
            return -1;
        }

        private void rehashing(){
            LinkedList<HMNode>[]prevBuckets = buckets;
            initbuckets(prevBuckets.length*2);
            size = 0;

            for (LinkedList<HMNode> curr : prevBuckets) {
                for (HMNode node : curr) {
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value){
            int bi = findBucket(key);
            int di = findDataInBucket(key, bi);
            if(di == -1){
                LinkedList<HMNode>temp = buckets[bi];
                HMNode node = new HMNode(key, value);
                temp.add(node);
                size++;
            }
            else{
                LinkedList<HMNode>temp = buckets[bi];
                HMNode node = temp.get(di);
                node.value = value;
            }

            double lambda = (size*1.0)/buckets.length;
            if(lambda>2) rehashing();
        }

        public V get(K key){
            int bi = findBucket(key);
            int di = findDataInBucket(key, bi);
            if(di == -1) return null;
            LinkedList<HMNode>list = buckets[bi];
            return list.get(di).value;
        }

        public boolean containsKey(K key){
            int bi = findBucket(key);
            int di = findDataInBucket(key, bi);
            return di != -1;
        }

        public V remove(K key){
            int bi = findBucket(key);
            int di = findDataInBucket(key, bi);
            if(di==-1) return null;
            LinkedList<HMNode>list = buckets[bi];
            HMNode node = list.get(di);
            V val = node.value;
            list.remove(di);
            size--;
            return val;
        }

        public int size(){
            return size;
        }

        public void display() {
            System.out.println("Display Begins");
            for (int bi = 0; bi < buckets.length; bi++) {
                System.out.print("Bucket" + bi + " ");
                for (HMNode node : buckets[bi]) {
                    System.out.print( node.key + "@" + node.value + " ");
                }
                System.out.println(".");
            }
            System.out.println("Display Ends");
        }

        public ArrayList<K> keyset(){
            ArrayList<K>ans = new ArrayList<>();
            for(LinkedList<HMNode> list:buckets){
                for(HMNode curr:list){
                    ans.add(curr.key);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap();

        String str = br.readLine();
        while (!str.equals("quit")) {
            if (str.startsWith("put")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                Integer val = Integer.parseInt(parts[2]);
                map.put(key, val);
            } else if (str.startsWith("get")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.get(key));
            } else if (str.startsWith("containsKey")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.containsKey(key));
            } else if (str.startsWith("remove")) {
                String[] parts = str.split(" ");
                String key = parts[1];
                System.out.println(map.remove(key));
            } else if (str.startsWith("size")) {
                System.out.println(map.size());
            } else if (str.startsWith("keyset")) {
                System.out.println(map.keyset());
            } else if (str.startsWith("display")) {
                map.display();
            }
            str = br.readLine();
        }
    }
}
