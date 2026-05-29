package SomeInterviews.databricks;

import java.util.*;

public class CIDR {
//https://leetcode.com/discuss/post/7614788/databricks-sde-ii-interview-experience-b-6n8v/
    //https://leetcode.com/discuss/post/5743277/databricks-l5-sse-technical-phone-screen-l4zi/

    static void main() {
        List<String[]> rules=new ArrayList<>();
        rules.add(new String[]{"ALLOW", "192.168.100.0/24"});
        rules.add(new String[]{"DENY", "192.168.0.5/30"});
        rules.add(new String[]{"ALLOW", "192.168.1.1/22"});
        rules.add(new String[]{"ALLOW", "1.0.0.0/8"});
        rules.add(new String[]{"ALLOW", "2.3.4.9"});
        rules.add(new String[]{"DENY", "8.8.8.8/1"});
        rules.add(new String[]{"ALLOW", "5.6.7.8"});


        System.out.println(firewall(rules,"192.168.100.255"));
        System.out.println(firewall(rules,"192.168.101.1"));
        System.out.println(firewall(rules,"1.2.3.4"));
        System.out.println(firewall(rules,"5.6.7.8"));
        System.out.println(firewall(rules,"1.2.3.4"));
        System.out.println(firewall(rules,"100.1.1.1"));
        System.out.println(firewall(rules,"-1.2.3.4"));
        System.out.println(firewall(rules,"256.2.3.4"));
        System.out.println(firewall(rules,"123.45.67.89"));


    }

//rule是按顺序匹配的，如果前面匹配到了就不用管后面了
    static boolean firewall(List<String[]> rules,String ip){

        Map<String,Boolean> map=new LinkedHashMap<>();//gpt说用map的话，如果rule有重复则会导致后面得覆盖前面的rule就不对
        for (String[] r:rules){
            map.put(r[1],r[0].equals("ALLOW")?true:false);
        }
        String[] segment=ip.split("\\.");//直接写句号是不行的
        if(segment.length!=4){
            return false;
        }
        for(int i=0;i<4;i++){
            if(Integer.valueOf(segment[i])>255){//不检测的话有可能是前面mask match了，实际是个不合法的ip
                return false;
            }
        }

        int s1=convertToInt(ip,32);
        for(String rule:map.keySet()){
            String[] cidr=rule.split("/");
            if(cidr.length==2){
                int i=convertToInt(cidr[0],Integer.valueOf(cidr[1]));
                int mask=Integer.valueOf(cidr[1]);
                if( (i>>>(32-mask))==(s1>>>(32-mask)) ){//注意这里用>>>比>>好，因为>>>代表无符号高位补0，具体为啥比>>好我不太明白
                    return map.get(rule);   //
                }
                /*
                gpt说更符合子网掩码的写法是
                int m = (int)(0xFFFFFFFFL << (32 - 24)); 这是一个全是11111111111的数，左移8位就变成1111111 00000000就是子网掩码
                return (rule & m) == (target & m);
                 */
            }else{//没有mask的情况
                int i=convertToInt(cidr[0],32);
                if(i==s1){
                    return map.get(rule);
                }
            }

        }
        return false;

    }

    static int convertToInt(String ip,int mask)  {
        String[] ips=ip.split("\\.");
        int s1=0;//开始想错了用1去and
        for(int i=0;i<4;i++){
            s1=s1<<8;
            s1|=Integer.valueOf(ips[i]);
        }
        int rest=32-mask;
        s1>>=rest;
        s1<<=rest;
        return s1;
    }

    /*
    follow up
    query 是 CIDR，问“整个 CIDR 是否 allow”，即target CIDR 里的每个 IP 最终都必须是 allow

gpt解释
rules:
1. ALLOW 10.0.0.0/25
2. DENY  10.0.0.0/26
3. ALLOW 10.0.0.128/25

target:
10.0.0.0/24

如果你错误地说“DENY 只要和 target 有交集就 false”，那会误判。
因为 rule2 命中的 [0,63] 这部分，其实已经在 rule1 时被更早的 ALLOW 决定了。
所以 rule2 对它们不该再起作用。
这就是为什么必须维护 remaining，而不是直接拿每条 rule 去撞整个 target。


     */




        // 主要难点其实是1：cidr怎么转成interval，看cidrToInterval方法
        //2：把remaining的interval 和rule的交集这一段减去。也不是很难，写出来有点麻烦而已
        // targetCidr 是否在有序 ACL 下“整体都 allow”
        public static boolean allowEntireCidr(List<String[]> rules, String targetCidr) {
            Interval target = cidrToInterval(targetCidr);
            if (target == null) return false;

            List<Interval> remaining = new ArrayList<>();
            remaining.add(target);

            for (String[] rule : rules) {
                String action = rule[0];
                String cidr = rule[1];

                Interval ruleInterval = cidrToInterval(cidr);
                if (ruleInterval == null) {
                    return false; // 也可以选择 continue，看题意
                }

                if (action.equals("ALLOW")) {
                    remaining = subtractOverlap(remaining, ruleInterval);
                    if (remaining.isEmpty()) {
                        return true; // target 全部已被允许
                    }
                } else if (action.equals("DENY")) {
                    if (hasOverlap(remaining, ruleInterval)) {
                        return false; // target 中仍未决策的部分被 deny 命中
                    }
                } else {
                    return false; // 非法 rule action
                }
            }

            // 还有没被 allow 掉的部分 => 默认 deny
            return remaining.isEmpty();
        }

        // 判断 ruleInterval 是否和 remaining 中任一段有交集
        private static boolean hasOverlap(List<Interval> remaining, Interval ruleInterval) {
            for (Interval cur : remaining) {
                if (overlap(cur, ruleInterval) != null) {
                    return true;
                }
            }
            return false;
        }

        // 从 remaining 中减去 ruleInterval 覆盖的部分
        // remaining 里的区间两两不重叠
        private static List<Interval> subtractOverlap(List<Interval> remaining, Interval ruleInterval) {
            List<Interval> next = new ArrayList<>();

            for (Interval cur : remaining) {
                Interval inter = overlap(cur, ruleInterval);

                if (inter == null) {
                    // 没交集，原样保留
                    next.add(cur);
                } else {
                    // 有交集，最多拆成左右两段
                    if (cur.start < inter.start) {
                        next.add(new Interval(cur.start, inter.start - 1));
                    }
                    if (inter.end < cur.end) {
                        next.add(new Interval(inter.end + 1, cur.end));
                    }
                }
            }
            return next;
        }

        // 计算两个闭区间的交集
        private static Interval overlap(Interval a, Interval b) {
            long s = Math.max(a.start, b.start);
            long e = Math.min(a.end, b.end);
            if (s > e) return null;
            return new Interval(s, e);
        }

        // 把 CIDR 转成闭区间 [start, end]
        // 支持：
        // "192.168.1.0/24"
        // "1.2.3.4" 视为 /32
        public static Interval cidrToInterval(String cidr) {
            try {
                String ipStr;
                int prefix;

                if (cidr.contains("/")) {
                    String[] parts = cidr.split("/");
                    if (parts.length != 2) return null;
                    ipStr = parts[0];
                    prefix = Integer.parseInt(parts[1]);
                } else {
                    ipStr = cidr;
                    prefix = 32;
                }

                if (prefix < 0 || prefix > 32) return null;

                long ip = ipToLong(ipStr);
                long mask = prefixToMask(prefix);

                long start = ip & mask;
                long end = start | (~mask & 0xFFFFFFFFL);//这个得记，maske的反码去and 111111得到的就是mask长度的11111，再去或start，nb

                return new Interval(start, end);
            } catch (Exception e) {
                return null;
            }
        }

        // IP -> unsigned 32-bit long
        private static long ipToLong(String ip) {
            String[] parts = ip.split("\\.");
            if (parts.length != 4) {
                throw new IllegalArgumentException("Invalid IP: " + ip);
            }

            long val = 0;
            for (String p : parts) {
                int x = Integer.parseInt(p);
                if (x < 0 || x > 255) {
                    throw new IllegalArgumentException("Invalid IP part: " + p);
                }
                val = (val << 8) | x;
            }
            return val;
        }

        // prefix -> mask
        // /24 => 11111111 11111111 11111111 00000000
        private static long prefixToMask(int prefix) {
            if (prefix == 0) return 0L;
            return (0xFFFFFFFFL << (32 - prefix)) & 0xFFFFFFFFL;
        }

        // 辅助打印
        private static String longToIp(long x) {
            return ((x >> 24) & 255) + "." +
                    ((x >> 16) & 255) + "." +
                    ((x >> 8) & 255) + "." +
                    (x & 255);
        }

        public static void main(String[] args) {
            List<String[]> rules1 = Arrays.asList(
                    new String[]{"ALLOW", "192.168.1.0/25"},
                    new String[]{"DENY", "192.168.1.128/26"},
                    new String[]{"ALLOW", "192.168.1.192/26"}
            );

            System.out.println(allowEntireCidr(rules1, "192.168.1.0/24"));
            // false
            // 前128个 allow
            // 128~191 deny
            // 192~255 allow
            // target 整体不是全 allow

            List<String[]> rules2 = Arrays.asList(
                    new String[]{"ALLOW", "10.0.0.0/25"},
                    new String[]{"DENY", "10.0.0.0/26"},
                    new String[]{"ALLOW", "10.0.0.128/25"}
            );

            System.out.println(allowEntireCidr(rules2, "10.0.0.0/24"));
            // false
            // 因为 64~127 没被 allow，最后默认 deny

            List<String[]> rules3 = Arrays.asList(
                    new String[]{"ALLOW", "10.0.0.0/25"},
                    new String[]{"ALLOW", "10.0.0.128/25"},
                    new String[]{"DENY", "10.0.0.0/26"}
            );

            System.out.println(allowEntireCidr(rules3, "10.0.0.0/24"));
            // true
            // target 先被前两个 allow 全覆盖，后面的 deny 不再影响

            List<String[]> rules4 = Arrays.asList(
                    new String[]{"ALLOW", "192.168.1.0/24"}
            );

            System.out.println(allowEntireCidr(rules4, "192.168.1.64/26"));
            // true

            List<String[]> rules5 = Arrays.asList(
                    new String[]{"ALLOW", "192.168.1.0/25"}
            );

            System.out.println(allowEntireCidr(rules5, "192.168.1.0/24"));
            // false
            // 后半段没被 all
        }
    static class Interval {
        long start;
        long end;

        Interval(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
}
