package SomeInterviews.mercor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Review1 {
    /*
    Review a piece of code, what's wrong with it?
这道题目其实Javascript很不合理，因为他目的是讨论race condition及parallel threading。问到后面我都有点听不懂，换了用C++来解释mutex lock for Threads。
基本问题是read during set may have stale value, 还缺GC cache eviction policy，不然需要无限的内存。
JS 需要 Queue scheduler，blocking writes，parallel reads，完全没有threading因为JS是single thread
C++ 需要 Mutex lock for multithreads
面官说他有12个不同语言的版本，但是考的是race condition, mutex, threading, garbage collection, eviction policy。
const cache = new Map()

async function retrieve(key, val) {

if (cache.has(key)) {
return cache.get(key)
}

const newVal = await longRunningFn(key)
cache.set(key)
return newVal
}

我自己很久没讨论sort algorithm，最后的mutex和GC是面官暗示才达到的

考点一层层推：
并发问题 —— 两个线程同时 miss，昂贵计算会被做两遍
加锁；然后追问怎么保证只做一次（double-checked locking：拿到锁后再查一次缓存）
锁该加在哪一行、加粗了会怎样（所有请求包括 cache hit 都被串行化）
最后追问 Python 的 dict 在多线程下读写是什么行为、读到的会是旧值还是新值
python 版
cache = {}

def compute(x):
    if x in cache:
        return cache[x]
    result = expensive_computation(x)
    cache[x] = result
    return result


    可以这样改，
import threading
cache = {}
key_locks = {}    ----》per key lock，要求的话才这样写
key_locks_guard = threading.Lock()  ----》cache的lock

#初始化per key lock
def get_key_lock(key):
    # 保证同一个 key 只创建一把 lock
    with key_locks_guard:
        if key not in key_locks:
            key_locks[key] = threading.Lock()
        return key_locks[key]

def compute(x):
    # fast path
    value = cache.get(x) ----》题目是read的时候不atomic，这样就行了，不用上锁
    if value is not None:
        return value
    lock = get_key_lock(x)
    with lock:
        # double check
        # 等锁期间，其他线程可能已经算完并写入 cache
        value = cache.get(x)
        if value is not None:    ----》这叫double cache check，这样才能防止expensive computation被调用两次
            return value
        result = expensive_computation(x)
        cache[x] = result
        return result
     */

//java版本
    public class CacheService {
        private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        private final ConcurrentHashMap<String, ReentrantLock> keyLocks = new ConcurrentHashMap<>();
        public String compute(String key) {
            // fast path：单次 get 是 thread-safe
            String value = cache.get(key);
            if (value != null) {
                return value;
            }
            // 同一个 key 拿到同一把 lock
            ReentrantLock lock = keyLocks.computeIfAbsent(key, k -> new ReentrantLock());
            lock.lock();
            try {
                // double check等锁期间，别人可能已经算完了
                value = cache.get(key);
                if (value != null) {
                    return value;
                }
                value = expensiveComputation(key);
                cache.put(key, value);
                return value;
            } finally {
                lock.unlock();
            }
        }
        private String expensiveComputation(String key) {
            return "value-" + key;
        }
    }
}
