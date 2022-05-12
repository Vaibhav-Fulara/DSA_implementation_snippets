public class Main{
  class Node {
      Node[]links = new Node[26];
      boolean flag;
      public Node(){}
      boolean containsKey(char ch){
          return links[ch-'a']!=null;
      }
      Node get(char ch){
          return links[ch-'a'];
      }
      void put(char ch, Node node){
          links[ch-'a'] = node;
      }
      void setEnd(){
          flag = true;
      }
      boolean isEnd(){
          return flag;
      }
  }

  class Trie {
      private Node root;
      public Trie() {
          root = new Node();
      }

      public void insert(String word) {
          Node node = root;
          for(char ch:word.toCharArray()){
              if(!node.containsKey(ch)){
                  node.put(ch, new Node());
              }
              node = node.get(ch);
          }
          node.setEnd();
      }

      public boolean search(String word) {
          Node node = root;
          for(char ch:word.toCharArray()){
              if(!node.containsKey(ch)){
                  return false;
              }
              node = node.get(ch);
          }
          return node.isEnd();
      }

      public boolean startsWith(String prefix) {
          Node node = root;
          for(char ch:prefix.toCharArray()){
              if(!node.containsKey(ch)){
                  return false;
              }
              node = node.get(ch);
          }
          return true;
      }

     public static void main(String args[]){
        int n = 5;
        int type[] = {1, 1, 2, 3, 2};
        String value[] = {"hello", "help", "help", "hel", "hel"};
        Trie trie=new Trie();
        for (int i = 0; i < n; i++) {
          if (type[i] == 1) trie.insert(value[i]);
          else if (type[i] == 2) {
            if (trie.search(value[i])) System.out.println( "true" );
            else System.out.println("false");
          }
          else {
            if (trie.startsWith(value[i])) System.out.println("true" );
            else System.out.println("false");
          }
       }
    }
}
