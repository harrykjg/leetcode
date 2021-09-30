package SomeInterviews.karat;

import java.util.*;

public class BadgeAccess {
    public static void main(String[] args){
        BadgeAccess ba=new BadgeAccess();
//        String[][] records=new String[][]{{"Paul", "enter"}, {"Paul", "exit"}, {"Paul", "exit"},
//                {"Paul", "enter"}, {"Martha", "enter"}, {"Martha", "exit"}};
        String[][] records=new String[][]{{"Martha", "exit"}, {"Paul", "enter"}, {"Martha", "enter"},
               {"Martha", "exit"}, {"Jennifer", "enter"}, {"Paul", "enter"},
                {"Curtis", "enter"}, {"Paul", "exit"}, {"Martha", "enter"}, {"Martha", "exit"}, {"Jennifer", "exit"},
        };
//        String[][] times={
//                {"Paul", "1355"},
//                {"Jennifer", "1910"},
//                {"John", "835"},
//                {"John", "830"},
//                {"Paul", "1315"},
//                {"John", "1615"},
//                {"John", "1640"},
//                {"Paul", "1405"},
//                {"John", "855"},
//                {"John", "930"},
//                {"John", "915"},
//                {"John", "730"},
//                {"John", "940"},
//                {"Jennifer", "1335"},
//                {"Jennifer", "730"},
//                {"John", "1630"},
//                {"Jennifer", "5"}
//                };
//        ba.getInvalidEntryExit(records);
        String[][] times={
                {"James", "1300"}, {"Martha", "1600"}, {"Martha", "1620"}, {"Martha", "1530"}
        };
        ba.multipleAccess(times);
    }
    //https://leetcode.com/discuss/interview-question/1475545/Karat-or-Freedom-Financial-or-Badge-Access-or-Coding-Round
//进去不刷卡的放进一个set，出去不刷卡的放进另一个set
    private List<Set<String>> getInvalidEntryExit(String records[][]) {
        HashMap<String,List<String>> map=new HashMap<>();
        for (int i=0;i<records.length;i++){
            if (!map.containsKey(records[i][0])){
                map.put(records[i][0],new ArrayList<>());
            }
            map.get(records[i][0]).add(records[i][1]);
        }
        List<Set<String>> rs=new ArrayList<>();
        Set<String> invalidEntry=new HashSet<>();
        Set<String> invalidExit=new HashSet<>();
        for (Map.Entry<String,List<String>> entry:map.entrySet()){
            List<String> behav=entry.getValue();
            int count=0;
            for (int i=0;i<behav.size();i++){
                if (behav.get(i).equals("exit")&&count==0){
                    //进入没刷卡
                    invalidEntry.add(entry.getKey());
                    count=0;
                }else if(behav.get(i).equals("exit")){//合法出去
                    count--;
                }else if(behav.get(i).equals("enter")&&count==1){//出去没刷卡
                    invalidExit.add(entry.getKey());
                    count=1;//这里容易错，如果连续进来2次再出去一次，那么第二次进的时候会来到这里，此时要把count设成1，以便下一次合法出去，不然不对
                }else if(behav.get(i).equals("enter")&&count==0){//合法进入
                    count++;
                }
            }
            if (count==1){//这里容易漏判断
                invalidExit.add(entry.getKey());
            }
        }
        rs.add(invalidEntry);
        rs.add(invalidExit);
        for (String s:invalidEntry){
            System.out.println(s);
        }
        System.out.println();
        for (String s:invalidExit){
            System.out.println(s);
        }
        return rs;
    }

    //第二问，一小时内多次access
    //https://leetcode.com/discuss/interview-question/1379389/Wayfair-Karat
    public Map<String,List<Integer>> multipleAccess(String[][] input){
        Map<String,List<Integer>> rs=new HashMap<>();
        Map<String,List<Integer>> map=new HashMap<>();
        for (int i=0;i<input.length;i++){
            if (!map.containsKey(input[i][0])){
                map.put(input[i][0],new ArrayList<>());
            }
            map.get(input[i][0]).add(Integer.parseInt(input[i][1])); //这里参考链接的，直接转成数字，相差100以内的都是1小时，而开始自己想的是转成分钟再算
        }
        for (Map.Entry<String,List<Integer>> entry:map.entrySet()){
            List<Integer> ls=entry.getValue();
            Collections.sort(ls,(a,b)->a-b);//现在对于每个人，他的access都是排序的了，我的想法是用sliding window
            int b=0;
            int e=0;
            while (e<ls.size()){
                while (e<ls.size()&&ls.get(e)-ls.get(b)<=100){
                    e++;
                }
                if (e-b>=3){
                    if (!rs.containsKey(entry.getKey())){
                        rs.put(entry.getKey(),new ArrayList<>(ls.subList(b,e)));
                    }
                }
                b++;
            }
        }
        for (Map.Entry<String,List<Integer>> entry:rs.entrySet()){
            System.out.print(entry.getKey()+" : ");
            for (Integer in:entry.getValue()){
                System.out.print(in+" ");
            }
            System.out.println();
        }
        return rs;
    }

}
