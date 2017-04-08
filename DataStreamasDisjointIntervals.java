import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 502575560 on 11/7/16.
 */
public class DataStreamasDisjointIntervals {

    List<Interval> list = new LinkedList<>();
//自己改了几次,没用binarysearch都能accept,其实就是用if else处理几种case就行了,自己举例子试试
    /**
     * Initialize your data structure here.
     */
    public DataStreamasDisjointIntervals() {

    }

    public void addNum(int val) {
        if (list.size() == 0) {
            Interval in = new Interval(val, val);
            list.add(in);
            return;
        }
        boolean flag = false;
        int i = 0;
        for (; i < list.size(); i++) {
            if (val == list.get(i).start || val == list.get(i).end) {//如果这个插入的数和list中的interval的start或end相等,就结束
                flag = true;
                return;
            }
            if (val < list.get(i).start) {//如果小于start
                if (val == list.get(i).start - 1) {//并且刚好等于start-1
                    list.get(i).start = val;
                } else {//否则插入新的interval
                    Interval in = new Interval(val, val);
                    list.add(i, in);
                }
                flag = true;
                return;
            } else if (val <= list.get(i).end) {//如果插入的数再star和end之间,也直接返回
                return;
            } else if (val == list.get(i).end + 1) {//如果刚好大于end+1
                if (i + 1 < list.size() && list.get(i + 1).start - 1 == val) {//并且后一个interval的start-1等于这个数,则前后两个interval和这个数可以合并
                    Interval temp = list.remove(i + 1);
                    list.get(i).end = temp.end;

                }else{//否则就把当前interval的end改了
                    list.get(i).end=val;
                }
                flag = true;
                return;
            }
            //否则就进入下层循环继续找这个数该插入的位置
        }

        if (flag == false && i == list.size()) {
            Interval in = new Interval(val, val);
            list.add(in);
        }

    }

    public List<Interval> getIntervals() {
        return list;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}