package SomeInterviews.reddit;

import java.util.*;

public class ReconstructBillingStatus {
    /*
    An advertising platform lost its primary database that stored advertisers' current billing status. Fortunately, the system still retains a complete historical log of all financial transactions. Your task is to reconstruct the final billing status for each user using this transaction history.

You are given a 2D list of unordered transactions, where each element represents a single transaction made by one user. Each transaction may record an ad delivery charge, a payment, or both. Some monetary fields may be missing (provided as "") and should be ignored.

Each transaction record is in the format: [<transactionId>, <userId>, <adDeliveryPennies>, <paymentPennies>, <transactionTimestamp>]

transactionId: A unique transaction identifier.
userId: String, unique identifier of the user (e.g., "u1", "u2")
adDeliveryPennies: The ad delivery cost in pennies, or an empty string "" if not present.
paymentPennies: The payment amount in pennies, or an empty string "" if not present.
transactionTimestamp: The transaction time, represented as a string integer.
For each user, compute two totals starting from 0:

totalAdDeliveryPennies: The total ad delivery cost in pennies.
totalPaymentPennies: The total payment amount in pennies.
After processing all transactions, return each user's final billing status in the following format:[<userId>, <totalAdDeliveryPennies>, <totalPaymentPennies>]. The output list should be ordered by userId in ascending lexicographic order.

Constraints

1 ≤ transactions.length ≤ 10⁵
Each transaction contains exactly 5 elements.
adDeliveryPennies and paymentPennies are either empty strings or strings representing non-negative integers.
Example 1:

Input:
transactions =
[
  ["t1", "u1", "1000", "", "1"],
  ["t2", "u1", "1000", "", "2"],
  ["t3", "u1", "", "500", "3"],
  ["t4", "u1", "1000", "500", "4"]
]
Output: [["u1", "3000", "1000"]]
Explanation: All transactions belong to the same user with userId "u1". After processing all transactions, the final ad delivery total is $3000 (1000 + 1000 + 1000), and the final payment total is $1000 (500 + 500).

Example 2:

Input:
transactions =
[
  ["t1", "u1", "500", "", "1"],
  ["t2", "u2", "300", "", "2"],
  ["t3", "u1", "", "200", "3"],
  ["t4", "u2", "", "100", "4"]
]

Output: [["u1", "500", "200"], ["u2", "300", "100"]]

Example 3:

Input:
transactions =
[
  ["t1", "u1", "5000", "1000", "1"],
  ["t2", "u2", "3000", "", "2"],
  ["t3", "u3", "", "2000", "3"],
  ["t4", "u1", "2500", "", "4"],
  ["t5", "u2", "", "1500", "5"],
  ["t6", "u3", "4000", "3000", "6"],
  ["t7", "u1", "1500", "500", "7"],
  ["t8", "u2", "2000", "1000", "8"],
  ["t9", "u3", "3500", "", "9"],
  ["t10", "u1", "", "2000", "10"]
]

Output: [["u1", "9000", "3500"], ["u2", "5000", "2500"], ["u3", "7500", "5000"]]
     */

    //就是按uid求和吧，transactionid.然后输出要按uid排序。不需要考虑timestamp，因为是算总和
    public List<List<String>> processTransactions(List<List<String>> transactions) {
        // TODO: Implement processTransactions logic
        List<List<String>> rs=new ArrayList<>();
        Map<String,List<String>> map=new TreeMap<>();
        for(List<String> trans:transactions){
            String uid=trans.get(1);
            int adDeliveryPennies=trans.get(2).equals("")?0:Integer.valueOf(trans.get(2));
            int paymentPennies=trans.get(3).equals("")?0:Integer.valueOf(trans.get(3));
            map.putIfAbsent(uid,new ArrayList<>());
            int totalDeli=map.get(uid).size()==0?adDeliveryPennies:Integer.valueOf(map.get(uid).get(0))+adDeliveryPennies;
            int totalPay=map.get(uid).size()==0?paymentPennies:Integer.valueOf(map.get(uid).get(1))+paymentPennies;
            if(map.get(uid).size()==0){
                map.get(uid).add(""+totalDeli);
                map.get(uid).add(""+totalPay);
            }else{
                map.get(uid).set(0,""+totalDeli);
                map.get(uid).set(1,""+totalPay);
            }

        }
        for(String key:map.keySet()){
            ArrayList<String> al=new ArrayList<>();
            al.add(key);
            al.addAll(map.get(key));
            rs.add(al);
        }
        return rs;

    }
    /*
    Follow-up 1:
Now let's add a concept of an overwrite transaction, where each transaction may indicate whether its monetary values should overwrite a user's current billing values or be added to them.

Each transaction is given in the following format: [<transactionId>, <userId>, <adDeliveryPennies>, <paymentPennies>, <transactionTimestamp>, <overwrite>], where:

(New) overwrite: A string value "true", "false".
For a monetary field that is present in a transaction:

If overwrite is "true", the corresponding value in the user's billing status is replaced by the transaction value.
If overwrite is "false", the transaction value is added to the current total, following the same accumulation rules as in the previous question.
After processing all transactions, return each user's final billing status in the following format:[<userId>, <totalAdDeliveryPennies>, <totalPaymentPennies>]. The output list should be ordered by userId in ascending lexicographic order.

Example 1:

Input:
transactions =
[
  ["t1", "u1", "1000", "", "1", "false"],
  ["t2", "u2", "1000", "", "1", "false"],
  ["t3", "u1", "1000", "", "2", "false"],
  ["t4", "u2", "1000", "", "2", "false"],
  ["t5", "u2", "1000", "500", "3", "false"],
  ["t6", "u1", "", "500", "3", "false"],
  ["t7", "u2", "1000", "", "4", "false"],
  ["t8", "u1", "1000", "500", "4", "true"],
  ["t9", "u2", "", "2000", "5", "true"],
  ["t10", "u2", "", "600", "7", "false"],
  ["t11", "u1", "", "100", "13", "false"]
]
Output: [["u1", "1000", "600"], ["u2", "4000", "2600"]]
Explanation:

For user "u1":

Ad delivery values 1000 and 1000 are added first.
Payment value 500 is added.
An overwrite transaction replaces ad delivery with 1000 and payment with 500.
A later payment of 100 is added.
Final totals are ad delivery = 1000 and payment = 600.
For user "u2":

Ad delivery values 1000, 1000, and 1000 are added.
Payment value 500 is added.
An overwrite transaction replaces the payment total with 2000.
A later payment of 600 is added.
Final totals are ad delivery = 4000 and payment = 2600.
Example 2:

Input:
transactions =
[
  ["t1", "u1", "1000", "", "1", "false"],
  ["t2", "u1", "500", "", "2", "false"],
  ["t3", "u1", "2000", "", "3", "true"],
  ["t4", "u1", "", "300", "4", "false"]
]
Output: [["1", "2000", "300"]]

Example 3:

Input:
transactions =
[
  ["t1", "u1", "1000", "500", "1", "false"],
  ["t3", "u2", "500", "", "1", "false"],
  ["t2", "u1", "2000", "300", "2", "false"],
  ["t5", "u2", "", "1000", "2", "false"],
  ["t4", "u1", "100", "", "3", "true"],
  ["t7", "u2", "3000", "500", "3", "true"],
  ["t6", "u1", "", "200", "4", "false"],
  ["t8", "u1", "50", "100", "5", "false"]
]
Output: [["1", "150", "1100"], ["2", "3000", "500"]]
     */
    //这里overwirtie是指重写total的值。这里就要考虑时间顺序了，因为有了overwrite，先后顺序就要考虑。因此得先排序
    public List<List<String>> processTransactions(List<List<String>> transactions) {
        // TODO: Implement processTransactions logic
        List<List<String>> rs=new ArrayList<>();
        Map<String,List<String>> map=new TreeMap<>();
        Collections.sort(transactions,(a,b)->{
            long l1=Long.parseLong(a.get(4));
            long l2=Long.parseLong(b.get(4));
            return Long.compare(l1,l2);
        });
        for(List<String> trans:transactions){
            String uid=trans.get(1);
            int adDeliveryPennies=trans.get(2).equals("")?0:Integer.valueOf(trans.get(2));
            int paymentPennies=trans.get(3).equals("")?0:Integer.valueOf(trans.get(3));
            map.putIfAbsent(uid,new ArrayList<>());
            int totalDeli=map.get(uid).size()==0?adDeliveryPennies:Integer.valueOf(map.get(uid).get(0))+adDeliveryPennies;
            int totalPay=map.get(uid).size()==0?paymentPennies:Integer.valueOf(map.get(uid).get(1))+paymentPennies;
            if(map.get(uid).size()==0){//第一次加那么是否overwrite都无所谓
                map.get(uid).add(""+totalDeli);
                map.get(uid).add(""+totalPay);
            }else{
                if(trans.get(5).equals("true")){//只overwrite有值的那一个,注意得重新判断是否为空，否则adDeliveryPennies不能区分0和空
                    if(!trans.get(2).equals("")){
                        map.get(uid).set(0,""+adDeliveryPennies);
                    }
                    if(!trans.get(3).equals("")){
                        map.get(uid).set(1,""+paymentPennies);
                    }
                }else{
                    map.get(uid).set(0,""+totalDeli);
                    map.get(uid).set(1,""+totalPay);
                }
            }

        }
        for(String key:map.keySet()){
            ArrayList<String> al=new ArrayList<>();
            al.add(key);
            al.addAll(map.get(key));
            rs.add(al);
        }
        return rs;
    }
    /*
    Follow-up 2:
Now we add support for undoLast and redoLast operations in the billing system. These operations allow reverting or reapplying a user's most recent regular transaction. A regular transaction is any transaction that does not set undoLast or redoLast to be "true".

Each transaction is given in the following format: [<transactionId>, <userId>, <adDeliveryPennies>, <paymentPennies>, <transactionTimestamp>, <overwrite>, <undoLast>, <redoLast>], where:

(New) undoLast: A string value "true" or "false". If "true", undo the last regular transaction (if any) for this user. Undoing a transaction reverses its effect on the user's current status.
(New) redoLast: A string value "true" or "false". If "true", redo the most recently undone transaction for this user. If no transaction has been undone, this has no effect.
A transaction may also have an "overwrite" flag as described in previou question. If "overwrite" is present, treat the transaction as a regular transaction. "undoLast" and "redo_Last" operations are mutually exclusive and override any monetary values in the transaction.

After processing all transactions, return each user's final billing status in the following format:[<userId>, <totalAdDeliveryPennies>, <totalPaymentPennies>]. The output list should be ordered by userId in ascending lexicographic order.

Example 1:

Input:
transactions =
[
  ["t1", "u1", "1000", "", "1", "false", "false", "false"],
  ["t2", "u1", "", "", "2", "false", "true", "false"],
  ["t3", "u1", "", "500", "3", "false", "false", "false"],
  ["t4", "u1", "1000", "500", "4", "false", "false", "false"]
]
Output: [["u1", "1000", "1000"]]

Explanation:

Transaction "t1" applies ad delivery += 1000.
Transaction "t2" undoes the most recent regular transaction ("t1"), resetting totals to zero.
Transaction "t3" applies payment += 500.
Transaction t4 applies ad delivery += 1000 and payment += 500.
Final totals are ad delivery = 1000 and payment = 1000.
Example 2:

Input:
transactions =
[
  ["t1", "u1", "1000", "", "1", "false", "false", "false"],
  ["t2", "u1", "500", "", "2", "false", "false", "false"],
  ["t3", "u1", "", "", "3", "false", "true", "false"],
  ["t4", "u1", "", "200", "4", "false", "false", "false"]
]
Output: [["u1", "1000", "200"]]

Example 3:

Input:
transactions =
[
  ["t1", "u1", "1000", "500", "1", "false", "false", "false"],
  ["t2", "u1", "2000", "300", "2", "false", "false", "false"],
  ["t3", "u1", "", "", "3", "false", "true", "false"],
  ["t4", "u1", "", "", "4", "false", "false", "true"],
  ["t5", "u1", "", "", "5", "false", "true", "false"],
  ["t6", "u1", "", "", "6", "false", "false", "true"]
]
Output: [["u1", "3000", "800"]]
     */
    //就是加了undolast和redolast，这两个不会同时为true。ai说如果undo或redo有值，则不考虑overwrite的值
    //而且redo、undo要找last的值，而且是基于uid的，那么我就设一个map，其中key是uid，value是last transaction，注意这个last transaction不会
    //又是一个undo redo，因为只有regular的operation才放进去，overwrite也是regular
    //难点在这，如果undo的是overwrite的话，是没法用map只记录上一个operation的，因为还需要overwrite之前的状态.
    //还有更难的，就是可以redo 一个undo的trans
    /*
t1: ad += 100       → 100
t2: ad += 200       → 300
t3: ad += 500       → 800
t4: undoLast        → 300（撤销 t3）
t5: undoLast        → 100（撤销 t2）
t6: redoLast        → 300（恢复 t2）---》注意不是恢复t1
t7: redoLast        → 800（恢复 t3）
     */
    // 记录一笔 regular transaction 执行后的状态。很难，直接不写了
    class State {
        long ad;
        long payment;

        State(long ad, long payment) {
            this.ad = ad;
            this.payment = payment;
        }
    }

    public List<List<String>> processTransactions(List<List<String>> transactions) {
        List<List<String>> rs = new ArrayList<>();
        // TreeMap 保证最后按 userId 排序
        Map<String, Deque<State>> applied = new TreeMap<>();
        Map<String, Deque<State>> undone = new HashMap<>();
        // 按 timestamp 排序
        Collections.sort(transactions, (a, b) -> {
            long t1 = Long.parseLong(a.get(4));
            long t2 = Long.parseLong(b.get(4));
            return Long.compare(t1, t2);
        });
        for (int i = 0; i < transactions.size(); i++) {
            List<String> trans = transactions.get(i);
            String uid = trans.get(1);
            // 每个用户独立维护两个 Stack
            if (!applied.containsKey(uid)) {
                applied.put(uid, new ArrayDeque<>());
                undone.put(uid, new ArrayDeque<>());
                // 初始状态，不能被 undo
                applied.get(uid).push(new State(0, 0));
            }
            Deque<State> undoStack = applied.get(uid);
            Deque<State> redoStack = undone.get(uid);
            // Undo：把最近一笔生效的状态移到 redoStack
            if (trans.get(6).equals("true")) {
                if (undoStack.size() > 1) {
                    redoStack.push(undoStack.pop());
                }
                // Redo：恢复最近一次被撤销的状态
            } else if (trans.get(7).equals("true")) {
                if (!redoStack.isEmpty()) {
                    undoStack.push(redoStack.pop());
                }
            } else {
                // Regular transaction，包括 overwrite
                State cur = undoStack.peek();
                long totalDeli = cur.ad;
                long totalPay = cur.payment;
                boolean overwrite = trans.get(5).equals("true");
                if (!trans.get(2).isEmpty()) {
                    long adDeliveryPennies = Long.parseLong(trans.get(2));
                    if (overwrite) {
                        totalDeli = adDeliveryPennies;
                    } else {
                        totalDeli += adDeliveryPennies;
                    }
                }
                if (!trans.get(3).isEmpty()) {
                    long paymentPennies = Long.parseLong(trans.get(3));
                    if (overwrite) {
                        totalPay = paymentPennies;
                    } else {
                        totalPay += paymentPennies;
                    }
                }
                // 保存这笔交易执行后的完整状态
                undoStack.push(new State(totalDeli, totalPay));
                // 新 regular transaction 会清空 redo 历史
                redoStack.clear();
            }
        }
        // 当前金额就是 applied Stack 的栈顶
        for (String uid : applied.keySet()) {
            State cur = applied.get(uid).peek();
            List<String> row = new ArrayList<>();
            row.add(uid);
            row.add("" + cur.ad);
            row.add("" + cur.payment);
            rs.add(row);
        }
        return rs;
    }
}
