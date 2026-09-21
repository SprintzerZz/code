
import java.util.HashMap;

class LRUCache {

    public static void main(String[] args) {
        // 测试 1：读取后更新使用顺序，容量不足时淘汰最久未使用的键。
        LRUCache lru = new LRUCache(2);
        check("空缓存读取", -1, lru.get(1));
        lru.put(1, 1);
        lru.put(2, 2);
        check("读取 key=1", 1, lru.get(1));
        lru.put(3, 3); // key=1 刚被访问，淘汰 key=2。
        check("key=2 已淘汰", -1, lru.get(2));
        lru.put(4, 4); // 淘汰 key=1。
        check("key=1 已淘汰", -1, lru.get(1));
        check("key=3 仍存在", 3, lru.get(3));
        check("key=4 仍存在", 4, lru.get(4));

        // 测试 2：更新已有键，同时更新使用顺序，不增加缓存大小。
        LRUCache updated = new LRUCache(2);
        updated.put(1, 10);
        updated.put(2, 20);
        updated.put(1, 100);
        check("更新已有键不增加大小", 2, updated.size);
        updated.put(3, 30);
        check("更新后 key=1 被保留", 100, updated.get(1));
        check("更新后 key=2 被淘汰", -1, updated.get(2));
        check("新键 key=3 存在", 30, updated.get(3));

        // 测试 3：容量为 1。
        LRUCache single = new LRUCache(1);
        single.put(1, 10);
        single.put(1, 11);
        check("容量为 1 时更新", 11, single.get(1));
        single.put(2, 20);
        check("容量为 1 时淘汰旧键", -1, single.get(1));
        check("容量为 1 时保留新键", 20, single.get(2));

        System.out.println("所有测试通过！");
    }

    private static void check(String description, int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError(description + "：期望 " + expected + "，实际 " + actual);
        }
        System.out.println("通过：" + description + "，结果 = " + actual);
    }

    class DListNode{
        int key;
        int value;
        DListNode prev;
        DListNode next;
        public DListNode(){}
        public DListNode(int key,int value){
            this.key=key;
            this.value=value;
        }
    }

    HashMap<Integer, DListNode> cache=new HashMap<Integer, DListNode>();
    //  双向链表的伪头部和伪尾部
    DListNode head;
    DListNode tail;
    int size;//当前大小
    int capacity;//总体容量


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new DListNode();
        this.tail = new DListNode();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public int get(int key) {
        //存在
        if(cache.containsKey(key)){
            DListNode node=cache.get(key);
            //将该节点移动到头部
            moveToHead(node);
            return node.value;
        }
        //不存在
        return -1;
    }
    
    public void put(int key, int value) {
        //存在——>更新value并移动到头部
        //不存在——>创建新节点，添加到头部——>判断是否超过容量，超过容量则删除尾部节点
        if(cache.containsKey(key)){
            DListNode node=cache.get(key);
            node.value=value;
            moveToHead(node);
        }else{
            DListNode node=new DListNode(key,value);
            cache.put(key,node);
            addToHead(node);
            size++;
            if(size>capacity){
                //删除尾部节点
                DListNode tail=removeTail();
                cache.remove(tail.key);
                size--;
            }
        }
        
    }
    private void moveToHead(DListNode node){
        //先删除该节点
        removeNode(node);
        //再添加到头部
        addToHead(node);
    }
    private void removeNode(DListNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    private void addToHead(DListNode node){
        node.prev=head;
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
    }
    private DListNode removeTail(){
        DListNode node=tail.prev;
        removeNode(node);
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
