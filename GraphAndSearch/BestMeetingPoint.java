package GraphAndSearch;

public class BestMeetingPoint {
    //10/13/2021
    //以为和ShortestDistancefromAllBuildings 一样，其实有一些不一样，比如这里1是可以作为终点的。但是用那的bfs方法会超时
    //见答案，这里用的是中位数,把所有是1的row和col坐标记下来，比如rows记录了1，4，6三个位置，index在3/2则中间这个点肯定是距离所有点的最近的点，如1，3，则index2/2=1也对
    //同理cols，然后把这个row中间点和rows的所有点的差值加起来，加上cols的终点和所有cols其他点的差值的和加起来就是答案。
}
