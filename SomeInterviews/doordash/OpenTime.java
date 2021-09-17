package SomeInterviews.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenTime {
    public static void main(String[] args) {
        OpenTime ot = new OpenTime();
        List<String> rs = ot.convertToTokens("mon 10:00 am", "mon 12:00 am");
        for (String s : rs) {
            System.out.println(s);
        }
        System.out.println(Math.round(42d/5d)*5);//要round42到40，43到45这样的话可以这样写
        int i=56;
        int temp=i/5;
        int possible1=temp*5;
        int possible2=(temp+1)*5;
        int gap1=Math.abs(possible1-i);
        int gap2=Math.abs(possible2-i);
        if (gap1>gap2){
            System.out.println(possible2);
        }else {
            System.out.println(possible1);
        }

    }

    public List<String> open(String start, String end) {
        Map<String, Integer> map = new HashMap<>();
        map.put("mon", 1);
        map.put("tue", 2);
        map.put("wed", 3);
        map.put("thu", 4);
        map.put("fri", 5);
        map.put("sat", 6);
        map.put("sun", 7);
        List<String> rs = new ArrayList<>();
        String[] s = start.split(" ");
        int startDay = map.get(s[0]);
        int startHour = Integer.parseInt(s[1].substring(0, 2));
        int startMin = Integer.parseInt(s[1].substring(3, 5));
        boolean startAm = s[2].equals("am") ? true : false;
        if (!startAm) {
            if (startHour != 12) {//12:pm的话实际上就是12点中午,所以只有12pm不用加12
                startHour += 12;
            }
        }else if (startHour==12){//真恶心这些题，要是是12点am的话实际上是24点，然后后面construct里要单独判断hour是不是24
            startHour+=12;

        }
        String[] e = end.split(" ");
        int endDay = map.get(e[0]);
        int endHour = Integer.parseInt(e[1].substring(0, 2));
        int endMin = Integer.parseInt(e[1].substring(3, 5));
        boolean endAm = e[2].equals("am") ? true : false;
        if (!endAm) {
            if (endHour != 12) {//12:pm的话实际上就是12点中午
                endHour += 12;
            }
        }else if (endHour==12){
            endHour+=12;
        }
        String bb = construct(startDay, startHour, startMin);
        String ee = construct(endDay, endHour, endMin);
        rs.add(bb.trim());
        while (!bb.equals(ee)) {
            bb = add5(bb);
            rs.add(bb);
        }

        return rs;
    }

    String add5(String time) {
        int day = Integer.parseInt(time.substring(0, 1));
        int hour = Integer.parseInt(time.substring(1, 3));
        int min = Integer.parseInt(time.substring(3));

        min += 5;
        if (min == 60) {
            min = 0;
            hour += 1;
        }
        if (hour == 24) {
            hour = 0;
            day += 1;
        }
        if (day > 7) {
            day -= 7;
        }
        return construct(day, hour, min);
    }

    String construct(int day, int hour, int min) {
        StringBuilder sb = new StringBuilder();
        if (hour==24){
            day+=1;
            hour=0;
        }
        day%=7;
        sb.append(day);
        if (hour == 0) {
            sb.append(0);
            sb.append(0);
        } else if (hour < 10) {
            sb.append(0);
            sb.append(hour);
        } else {
            sb.append(hour);
        }
        if (min == 0) {
            sb.append(0);
            sb.append(0);
        } else if (min < 10) {
            sb.append(0);
            sb.append(min);
        } else {
            sb.append(min);
        }
        return sb.toString();
    }

//这个是别人的方法，把day，hour，min都转化成min，然后+5，然后再从min可推出day和hour和min，叼
        public class Token {
            int day, min;

            public Token(int day, int min) {
                this.day = day;
                this.min = min;
            }

            @Override
            public String toString() {
                return String.format("%d%02d%02d", day, min / 60, min % 60);
            }
        }

        public List<String> convertToTokens(String start, String end) {
            Map<String, Integer> map = new HashMap<>();
            map.put("mon", 1);
            map.put("tue", 2);
            map.put("wed", 3);
            map.put("thu", 4);
            map.put("fri", 5);
            map.put("sat", 6);
            map.put("sun", 7);
            Token t1 = parseToken(start, map);
            Token t2 = parseToken(end, map);
            List<String> ans = new ArrayList<String>();
            while (t1.day * 24 * 3600 + t1.min <= t2.day * 24 * 3600 + t2.min) {
                ans.add(t1.toString());
                t1.min += 5;
            }
            return ans;
        }

        public Token parseToken(String s, Map<String, Integer> map) {
            String[] parts = s.split(" ");
            int day = map.get(parts[0]);
            int min = parts[2].equals("pm") ? 12 * 60 : 0;
            int hour = Integer.parseInt(parts[1].split(":")[0]);
            int mins = Integer.parseInt(parts[1].split(":")[1]);
            return new Token(day, min + hour * 60 + mins);
        }

}
