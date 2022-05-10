
import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer,Node> map;
    private NodeList nodeList;
    private int cap;

    public LRUCache(int cap) {
        this.map = new HashMap<>();
        this.nodeList = new NodeList();
        this.cap = cap;
    }
    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        makeRecently(key);
        return node.value;
    }

    private void makeRecently(int key) {
        Node node = map.get(key);
        nodeList.remove(node);
        map.remove(key);
        int value = node.value;
        Node newNode = new Node(key,value);
        nodeList.add(newNode);
        map.put(key,newNode);

    }
    public void put(int key,int value){
        if(map.containsKey(key)){
            map.get(key).value = value;
            //这里可能出问题
            makeRecently(key);
            return;
        }
        if(map.size() >= cap){
            //删除头
            Node node = nodeList.removeFirst();
            map.remove(node.key);
        }
        Node node = new Node(key,value);
        nodeList.add(node);
        map.put(key,node);
        return;

    }
}

class Node{
    public int key;
    public int value;
    public Node prev;
    public Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
class NodeList{
    Node head = new Node(0,0);
    Node tail = new Node(0,0);

    public NodeList() {
        head.next = tail;
        tail.prev = head;
    }

    public Node removeFirst() {
        // 这里
       Node res = head.next;
       res.next.prev = head;
       head.next = res.next;
       return res;
    }

    public void add(Node node) {
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    public void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;

    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(3);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.put(3, 3); // 缓存是 {1=1, 2=2}
        lRUCache.put(4, 4); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(4);
        int i1 = lRUCache.get(3);
        int i2 = lRUCache.get(2);
        int i3 = lRUCache.get(1);
        lRUCache.put(5, 5); // 缓存是 {1=1, 2=2}
        int i4 = lRUCache.get(1);
        int i5 = lRUCache.get(2);
        int i6 = lRUCache.get(3);
        int i7 = lRUCache.get(4);
        int i8 = lRUCache.get(5);


    }
}
