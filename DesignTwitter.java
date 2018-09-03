import java.util.*;

/**
 * Created by 502575560 on 11/12/16.
 */
public class DesignTwitter {

    //草他吗这个tweetid不是自增的,他可以先发tweetid=1再发tweetid=6但是6才是最近的那个,要自增的话要自己设一个计数器作为tweeter的一个static变量
    //还有一个坑就是自己取消关注自己,但是还能看到自己发过的帖子,还有个坑就是,如果自己取消关注自己之后再发帖,那么自己还是能看到
    //搞了30多次才accept
    /** Initialize your data structure here. */

    Map<Integer,tuser> userMap=new HashMap<>();//用来存user,而user只存他follow的人和他的粉丝,也存他发过的tweet
    Map<Integer,TreeSet> tweetMap=new HashMap<>();//用来存这个user能看到的tweet,即他follow的人的tweet
    Map<Integer,tweet> database=new HashMap<>();//用来存所有的tweet,key是tweetid
    static int count;
    public DesignTwitter() {
        count=0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)){
            tuser user=new tuser(userId);
            userMap.put(userId,user);
        }

        tweet t=new tweet(userId,tweetId,count);
        count++;
        userMap.get(userId).tweets.add(t);
        database.put(tweetId,t);
        if(!tweetMap.containsKey(userId)){
            TreeSet<tweet> tset=new TreeSet<>(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((tweet)o1).time-((tweet)o2).time;
                }
            });
            tweetMap.put(userId,tset);
        }

        //这里给关注这个userid的人的tweetmap里加上他发的这条tweet,但如果是某个人先发帖了我再关注,这个情况cover不了,要在后面follow的时候cover
        //这里还要处理:虽然自己没关注自己,但是还得王里面加自己新发的tweet

        Set<Integer> followee=userMap.get(userId).followee;
        if(!followee.contains(userId)){//先把自己加上,完了再除去
            followee.add(userId);
            Iterator it=followee.iterator();
            while (it.hasNext()){
                tweetMap.get(it.next()).add(t);
            }
            followee.remove(userId);
        }else{
            Iterator it=followee.iterator();
            while (it.hasNext()){
                tweetMap.get(it.next()).add(t);
            }
        }



    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        TreeSet<tweet> set=tweetMap.get(userId);
        List<Integer> list=new ArrayList<>();
        if(set!=null){
            Iterator<tweet> it=set.descendingIterator();
            int i=0;
            while(it.hasNext()&&i<10){
                list.add(it.next().tid);
                i++;
            }
        }
        return list;

    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {

        if(!userMap.containsKey(followerId)){
            tuser user=new tuser(followerId);
            userMap.put(followerId,user);
        }
        userMap.get(followerId).follow.add(followeeId);
        if(!userMap.containsKey(followeeId)){
            tuser user=new tuser(followeeId);
            userMap.put(followeeId,user);

        }
        userMap.get(followeeId).followee.add(followerId);
//这个用来处理如果这个follower从未发过帖子,则tweetMap里从未包含过他,要加上
        if(!tweetMap.containsKey(followerId)){
            TreeSet<tweet> tset=new TreeSet<>(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((tweet)o1).time-((tweet)o2).time;
                }
            });
            tweetMap.put(followerId,tset);
        }

        //把followee的人的tweet加到follower的tweetmap里,但是这里有个情况没有考虑,但如果是followee被关注的时候没有发帖,被关注后才发帖的话,
        // 就要靠上面posttweet方法来cover
        //
        Set<tweet> tweets=userMap.get(followeeId).tweets;
        Iterator<tweet> it=tweets.iterator();
        while(it.hasNext()){
            tweetMap.get(followerId).add(it.next());
        }


    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(userMap.containsKey(followerId)&&!userMap.get(followerId).follow.contains(followeeId)){//假如这个人本来就没关注followee
            return;
        }
        if(!userMap.containsKey(followerId)){//假如这个follower没发过贴也没follow过人,则他usermap和tweetmap里就不会有他
            return;
        }

        if(userMap.containsKey(followeeId)){
            Set<tweet> tweets=userMap.get(followeeId).tweets;
            Iterator<tweet> it=tweets.iterator();
            while (it.hasNext()){
                tweet temp=it.next();
                if(temp.userid==followerId){//发现帖子是自己发的话,就不删掉,这里有个坑,这样写cover不到一个情况:自己先取消关注自己,自己再发帖,
                    continue;        //那么还是应该要看到自己发的帖子,由上面posttweet处理
                }else{
                    tweetMap.get(followerId).remove(temp);
                }

            }
        }

        userMap.get(followerId).follow.remove(followeeId);

        if(userMap.containsKey(followeeId)){
            userMap.get(followeeId).followee.remove(followerId);
        }


    }
}
class tweet{
    int userid;
    int tid;
    int time;
    public tweet (int userid, int tid,int time){
        this.userid=userid;
        this.tid=tid;
        this.time=time;
    }

}
class tuser{//这里想了一下要不要重写hashcode,见https://zhidao.baidu.com/question/56026085.html?fr=qrl&map=0&qbl=topic_question_0&word=java%20%B6%D4%CF%F3%20%C4%AC%C8%CFhashcode
    //一半hashcode重写的话也要重写equals方法,判断一个set是否有一个对象是就先看hashcode是否相通,(hashcode是有可能相同的,因为对象可以无限多但是hashcode是有限的),
    //hashcode相同的话再用equals方法判断
    int userid;
    Set<Integer> follow;//他关注的人
    Set<Integer> followee;//关注他的人
    Set<tweet> tweets;

    public tuser(int userid){
        this.userid=userid;
        follow=new HashSet<>();
        follow.add(userid);
        followee=new HashSet<>();
        followee.add(userid);
        tweets=new HashSet<>();
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        tuser tuser = (tuser) o;
//
//        if (userid != tuser.userid) return false;
//        if (follow != null ? !follow.equals(tuser.follow) : tuser.follow != null) return false;
//        return followee != null ? followee.equals(tuser.followee) : tuser.followee == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = userid;
//        result = 31 * result + (follow != null ? follow.hashCode() : 0);
//        result = 31 * result + (followee != null ? followee.hashCode() : 0);
//        return result;
//    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */