package SomeInterviews.karat;

import java.util.*;

public class AdsConversionRate {
    public static void main(String[] args){
        List<String> ids=Arrays.asList("3123122444","234111110", "8321125440", "99911063");
        List<String> ads=Arrays.asList("122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets","96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats","82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets","92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens");
        List<String> ips=Arrays.asList("2339985511,122.121.0.155","234111110,122.121.0.1","3123122444,92.130.6.145","39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
                "8321125440,82.1.106.8","99911063,92.130.6.144");
        AdsConversionRate ac=new AdsConversionRate();
        ac.find(ids,ads,ips);
    }

    //9/18/2021,实际上就是要统计2个数字，一是广告点击次数，2是几个人买了。先统计ads里每一个广告被点了几次，还要统计每个广告哪些ip点击了，
    // 然后对于每个广告，逐个看每个ip，去ips里找对应userid，只有这个userid存在bought里的才算是买了的。
    List<String> find(List<String> bought,List<String> ads,List<String> ips){
        List<String> rs=new ArrayList<>();
        HashMap<String,Integer> count=new HashMap<>();
        HashMap<String,List<String>> map=new HashMap<>();
        HashSet set=new HashSet(bought);
        HashMap<String,String> ipToUser=new HashMap<>();
        for (String s:ips){
            String[] ss=s.split(",");
            ipToUser.put(ss[1],ss[0]);
        }
        for (String s:ads){
            String[] ad=s.split(",");
            count.put(ad[2],count.getOrDefault(ad[2],0)+1);
            String ip=ad[0];
            if (!map.containsKey(ad[2])){
                map.put(ad[2],new ArrayList<>());
            }
            if (ipToUser.containsKey(ip)){
                String user=ipToUser.get(ip);
                if (set.contains(user)){
                    map.get(ad[2]).add(user);//加user还是加ip都无所谓，反正只要要看这个list的大小，代表几个人买了
                }
            }
        }
        for (Map.Entry<String,List<String>> entry:map.entrySet()){
            int click=count.get(entry.getKey());
            int bou=entry.getValue().size();
            String temp=bou+" "+ "of"+ click+" "+entry.getKey();
            rs.add(temp);
        }
        for (String s:rs){
            System.out.println(s);
        }
        return rs;
    }
}
