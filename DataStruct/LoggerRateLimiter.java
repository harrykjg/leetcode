package DataStruct;

import java.util.HashMap;

/**
 * Created by yufengzhu on 8/19/18.
 */
public class LoggerRateLimiter {
    HashMap<String,Integer> map=new HashMap<>();
    public LoggerRateLimiter() {

    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)){
            map.put(message,1);
            return true;
        }

        if(timestamp-map.get(message)>10){
            map.put(message,timestamp);
            return true;
        }
        return false;
    }
}
