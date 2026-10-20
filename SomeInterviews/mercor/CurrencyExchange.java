package SomeInterviews.mercor;

import java.util.*;

public class CurrencyExchange {
    /*
    题目涉及一个包含货币汇率信息的数组，例如 ['USD', 'GBP', 0.77]，表示 1 美元（USD）等于 0.77 英镑（GBP）.
输入包含“源货币” 和“目标货币”.
     */
    //巧妙的就是写了一个edge，然后定义Map<String, List<Edge>> graph = new HashMap<>();建图，这样给定from作为key，所有邻居作为list<edge>
    //比较好写
    class Solution {
        static class Rate {
            String from;
            String to;
            double rate;
            Rate(String from, String to, double rate) {
                this.from = from;
                this.to = to;
                this.rate = rate;
            }
        }
        static class Edge {
            String to;
            double rate;
            Edge(String to, double rate) {
                this.to = to;
                this.rate = rate;
            }
        }
        public double getExchangeRate(List<Rate> rates, String source, String target) {
            Map<String, List<Edge>> graph = new HashMap<>();
            for (Rate r : rates) {
                graph.putIfAbsent(r.from,new ArrayList<>());
                graph.get(r.from).add(new Edge(r.to,r.rate));
                graph.putIfAbsent(r.to,new ArrayList<>());
                graph.get(r.to).add(new Edge(r.from,1.0/r.rate));
            }
            if (!graph.containsKey(source) || !graph.containsKey(target)) {
                return -1.0;
            }
            return dfs(source, target, 1.0, graph, new HashSet<>());
        }
        private double dfs(String cur, String target, double product, Map<String, List<Edge>> graph, Set<String> visited) {
            if (cur.equals(target)) {
                return product;
            }
            visited.add(cur);//这个visit就够了，不会出现某个currency访问过，然后到另一个点再访问他的情况
            for (Edge edge : graph.get(cur)) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                double result = dfs(edge.to, target, product * edge.rate, graph, visited);
                if (result != -1.0) {
                    return result;
                }
            }
            return -1.0;
        }
    }

}
