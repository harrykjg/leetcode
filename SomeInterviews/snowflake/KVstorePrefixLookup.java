package SomeInterviews.snowflake;

public class KVstorePrefixLookup {
    /*
    Design an in-memory key-value store that supports efficient value lookup by key prefix.
    Implement the KVStore class: KVStore() Initialize an in-memory key-value store.
    void set(String key, int value) Insert a new key-value pair. Throw an error if the key already exists
    . int get(String key) Return the current value for key, or -1 if absent.
     void update(String key, int value) Overwrite the value for an existing key. Throw an error if the key is missing.
      void deleteKey(String key) Remove key if it exists.
      public List<Integer> getByPrefix(String prefix) {
     */
    //那就是用一个map，key是string，value是int，set和get，update就是直接调用map。
    //但getByPrefix关键就是这个class有一个trie的node作为root，插入删除key的时候都操作trie，然后getbyprefix就是按trie搜，到某个节点的时候
    //获取这个节点包含的所有词（本身如果是一词，以及其所有孩子所成的词）
}
/*
gpt答案，follow up 1，Followup1: 要支持begin，commit，rollback操作。常数时间复杂度 。
思想就是设一个base的map，和一个dequeue<map>， 支持nested事务，就是当开启事务的时候就往dequeue里加一个新的map，然后crud都在这一层上，
commit的话就是把这一层上的修改放到上一层transaction里（如果有的话，没有的话就是放到base的map里），rollback就是扔掉当前层的map。
还有就是在事务里get key的话，是从最顶层事务开始找，没有这个key的话就要向下层map找。

import java.util.*;

public class KVStore {
    // base 表示已经提交的数据，也就是主存储
    private final Map<String, Integer> base;

    // txns 是事务栈
    // 栈顶表示当前正在执行的 transaction
    // 每一层 Map 只记录当前 transaction 修改过的 key
    private final Deque<Map<String, TxnValue>> txns;

    public KVStore() {
        this.base = new HashMap<>();
        this.txns = new ArrayDeque<>();
    }

    // 开启一个新的 transaction
    public void begin() {
        txns.push(new HashMap<>());
    }

    // 提交当前 transaction
    public void commit() {
        if (txns.isEmpty()) {
            throw new IllegalStateException("No active transaction");
        }

        // 弹出当前 transaction
        Map<String, TxnValue> top = txns.pop();

        // 如果外面还有 transaction，说明是 nested transaction
        // 那么当前 transaction 的修改合并到父 transaction
        if (!txns.isEmpty()) {
            Map<String, TxnValue> parent = txns.peek();

            for (Map.Entry<String, TxnValue> entry : top.entrySet()) {
                parent.put(entry.getKey(), entry.getValue());
            }
        } else {
            // 如果外面没有 transaction，说明提交到 base
            for (Map.Entry<String, TxnValue> entry : top.entrySet()) {
                String key = entry.getKey();
                TxnValue val = entry.getValue();

                // 如果当前 transaction 里删除了这个 key
                // commit 后 base 里也要删除
                if (val.deleted) {
                    base.remove(key);
                } else {
                    // 否则写入最新值
                    base.put(key, val.value);
                }
            }
        }
    }

    // 回滚当前 transaction
    public void rollback() {
        if (txns.isEmpty()) {
            throw new IllegalStateException("No active transaction");
        }

        // 直接丢弃当前 transaction layer
        txns.pop();
    }

    // 插入新 key
    public void set(String key, int value) {
        // set 要求 key 不存在
        if (containsKey(key)) {
            throw new IllegalArgumentException("Key already exists: " + key);
        }

        put(key, value);
    }

    // 获取 key 的当前值
    public int get(String key) {
        // 先从事务栈顶往下找
        // 因为越靠近栈顶，修改越新
        for (Map<String, TxnValue> txn : txns) {
            if (txn.containsKey(key)) {
                TxnValue val = txn.get(key);

                // 如果当前 transaction 标记这个 key 被删除
                if (val.deleted) {
                    return -1;
                }

                // 否则返回 transaction 里的最新值
                return val.value;
            }
        }

        // 如果所有 transaction layer 都没找到
        // 再去 base 里面找
        return base.getOrDefault(key, -1);
    }

    // 更新已有 key
    public void update(String key, int value) {
        // update 要求 key 必须存在
        if (!containsKey(key)) {
            throw new IllegalArgumentException("Key does not exist: " + key);
        }

        put(key, value);
    }

    // 删除 key
    public void deleteKey(String key) {
        // 如果 key 当前不存在，直接返回
        if (!containsKey(key)) {
            return;
        }

        if (txns.isEmpty()) {
            // 如果当前没有 transaction，直接从 base 删除
            base.remove(key);
        } else {
            // 如果当前在 transaction 里，不能直接删 base
            // 只能在当前 transaction 里放一个 deleted marker
            txns.peek().put(key, TxnValue.deleted());
        }
    }

    // 写入 key-value
    private void put(String key, int value) {
        if (txns.isEmpty()) {
            // 没有 transaction，直接写 base
            base.put(key, value);
        } else {
            // 有 transaction，写到当前 transaction layer
            txns.peek().put(key, TxnValue.value(value));
        }
    }

    // 判断 key 在当前视图里是否存在
    private boolean containsKey(String key) {
        return get(key) != -1;
    }

    // TxnValue 用来表示 transaction 里的某个 key 的状态
    private static class TxnValue {
        // deleted = true 表示这个 key 在当前 transaction 里被删除了
        boolean deleted;

        // 如果 deleted = false，value 表示当前 transaction 里的值
        Integer value;

        private TxnValue(boolean deleted, Integer value) {
            this.deleted = deleted;
            this.value = value;
        }

        // 创建一个普通 value
        static TxnValue value(int value) {
            return new TxnValue(false, value);
        }

        // 创建一个删除标记，也叫 tombstone
        static TxnValue deleted() {
            return new TxnValue(true, null);
        }
    }
}
 */