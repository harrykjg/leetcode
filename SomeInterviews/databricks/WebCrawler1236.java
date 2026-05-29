package SomeInterviews.databricks;

import java.util.*;

public class WebCrawler1236 {
    static void main() {

    }




//还以为是直接遍历所有url，看是否含有starturl的domain就完事了，其实不是，不能直接拿到所有url，只能从starturl开始去拿他所能连接到的url，包含domain的
    //才过去访问，那么直接bfs dfs加去重都行
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> memo=new HashSet<>();
        Queue<String> q=new PriorityQueue<>();
        q.offer(startUrl);
        memo.add(startUrl);
        List<String> rs=new ArrayList<>();
        while (!q.isEmpty()){
            String url=q.poll();
            rs.add(url);
            String domain=getDomain(url);
            System.out.println(domain);
            List<String> urls=htmlParser.getUrls(url);
            for(String u:urls){
                if(u.contains(domain)&&!memo.contains(u)){
                    q.offer(u);
                    memo.add(u);
                }
            }
        }
        return rs;
    }

    String getDomain(String url){
        url=url.substring(7);
        String[] split=url.split("/");
        return split[0];
    }

}
interface HtmlParser {
     public List<String> getUrls(String url);
}

