package SomeInterviews.snowflake;

import java.util.*;

public class TopKBookSales {
    private Map<String, Long> sales;
    private TreeSet<Book> ranking;
//就是用map装count然后用treeset排序就完事了，直接抄的gpt的代码这里
    public BookSalesTracker() {
        sales = new HashMap<>();
        ranking = new TreeSet<>((a, b) -> {
            if (a.count != b.count) {
                return Long.compare(b.count, a.count); // 销量高的排前面
            }
            return a.title.compareTo(b.title); // 字母序小的排前面
        });
    }
    public List<String> bestSellers(List<String> books, List<Integer> counts, int k) {
        for (int i = 0; i < books.size(); i++) {
            String title = books.get(i);
            long add = counts.get(i);
            long oldCount = sales.getOrDefault(title, 0L);
            // 如果旧记录存在，要先从 TreeSet 删除旧的排序节点
            if (sales.containsKey(title)) {
                ranking.remove(new Book(title, oldCount));
            }
            long newCount = oldCount + add;
            sales.put(title, newCount);
            ranking.add(new Book(title, newCount));
        }
        List<String> res = new ArrayList<>();
        int limit = Math.min(k, ranking.size());
        int idx = 0;
        for (Book book : ranking) {
            if (idx == limit) {
                break;
            }
            res.add(book.title);
            idx++;
        }
        return res;
    }
    private static class Book {
        String title;
        long count;
        Book(String title, long count) {
            this.title = title;
            this.count = count;
        }
    }
}
