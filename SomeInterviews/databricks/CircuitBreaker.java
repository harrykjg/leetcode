package SomeInterviews.databricks;

import java.util.List;

public class CircuitBreaker {
    Server server;
    int failureThreshold;
    int resetThreshold;
    int curReq;
    int fialed;
    public CircuitBreaker(int failureThreshold,int resetThreshold,Server server){
        this.server=server;
        this.failureThreshold=failureThreshold;
        this.resetThreshold=resetThreshold;
    }
}
class Gateway{

    public Gateway(CircuitBreaker primaryBreaker, CircuitBreaker secondaryBreaker){

    }
     List<String> routeRequests(int totalRequests){

    }
}

class Server {//这个是写好的吧，哪个server第几个request会success或者failure是由输入定好了的
    private final List<Boolean> outcomes;
    int callCount = 0;

    Server(List<Boolean> outcomes) {
        this.outcomes = outcomes;
    }

    public boolean handle(int requestId) {
        callCount++;
        if (requestId < 0 || requestId >= outcomes.size()) {
            throw new IllegalArgumentException("No outcomes outcome for requestId=" + requestId);
        }
        return outcomes.get(requestId);
    }
}
//感觉这题有点费，就是按规则实现吧，看gpt的答案
/*
import java.util.*;

class Server {
    private final List<Boolean> outcomes;

    public Server(List<Boolean> outcomes) {
        this.outcomes = outcomes;
    }

    public boolean handle(int requestId) {
        return outcomes.get(requestId);
    }
}

class CircuitBreaker {
    Server server;
    int failureThreshold;
    int resetThreshold;

    boolean isOpen = false;
    int consecutiveFailures = 0;
    int consecutiveBlocked = 0;

    public CircuitBreaker(Server server, int failureThreshold, int resetThreshold) {
        this.server = server;
        this.failureThreshold = failureThreshold;
        this.resetThreshold = resetThreshold;
    }

    // 当前 request 是否允许真正打到 server
    public boolean isAvailable() {
        return !isOpen;
    }

    // breaker open 时，这个 request 被跳过
    public void onBlocked() {
        consecutiveBlocked++;
        if (consecutiveBlocked >= resetThreshold) {
            isOpen = false;
            consecutiveBlocked = 0;
            consecutiveFailures = 0;
        }
    }

    // breaker closed 时，真正发送请求
    public boolean call(int requestId) {
        boolean ok = server.handle(requestId);

        if (ok) {
            consecutiveFailures = 0;
        } else {
            consecutiveFailures++;
            if (consecutiveFailures >= failureThreshold) {
                isOpen = true;
                consecutiveBlocked = 0;
            }
        }
        return ok;
    }
}

class Gateway {
    CircuitBreaker primaryBreaker;
    CircuitBreaker secondaryBreaker;

    public Gateway(CircuitBreaker primaryBreaker, CircuitBreaker secondaryBreaker) {
        this.primaryBreaker = primaryBreaker;
        this.secondaryBreaker = secondaryBreaker;
    }

    public List<String> routeRequests(int totalRequests) {
        List<String> res = new ArrayList<>();

        for (int requestId = 0; requestId < totalRequests; requestId++) {
            boolean triedPrimary = false;
            boolean triedSecondary = false;

            boolean primarySuccess = false;

            // try primary
            if (primaryBreaker.isAvailable()) {
                triedPrimary = true;
                primarySuccess = primaryBreaker.call(requestId);
            } else {
                primaryBreaker.onBlocked();
            }

            // try secondary only if primary did not succeed
            if (!primarySuccess) {
                if (secondaryBreaker.isAvailable()) {
                    triedSecondary = true;
                    secondaryBreaker.call(requestId);
                } else {
                    secondaryBreaker.onBlocked();
                }
            }

            if (triedPrimary && triedSecondary) {
                res.add("Primary -> Secondary");
            } else if (triedPrimary) {
                res.add("Primary");
            } else if (triedSecondary) {
                res.add("Secondary");
            } else {
                res.add("Rejected");
            }
        }

        return res;
    }
}
 */