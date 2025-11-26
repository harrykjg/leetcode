package 灵神.图论.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle773 {
    public static void main(String[] args) {

    }
    //不会
    //看回以前的
    public int slidingPuzzle(int[][] board) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<board.length;i++){
            sb.append(board[i][0]);
            sb.append(board[i][1]);
            sb.append(board[i][2]);
        }
        Queue<String> q=new LinkedList<>();
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        q.offer(sb.toString());
        int dist=0;
        Set<String> set=new HashSet<>();
        set.add(sb.toString());
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                String cur=q.poll();
                if(cur.equals("123450")){
                    return dist;
                }
                set.add(cur);
                int now=0;
                for(int j=0;j<cur.length();j++){//找0在哪
                    if(cur.charAt(j)=='0'){
                        now=j;
                        break;
                    }
                }
                //把now转换成坐标系
                int nowRow=now/3;
                int nowCol=now%3;
                for(int j=0;j<dx.length;j++){
                    int row=nowRow+dx[j];
                    int col=nowCol+dy[j];
                    if(row>=0&&row<2&&col>=0&&col<3){//找出可以和0换的位置
                        int nextPos=row*3+col;
                        StringBuilder next=new StringBuilder(cur);
                        char temp=next.charAt(nextPos);
                        next.setCharAt(nextPos,'0');
                        next.setCharAt(now,temp);
                        String nextStr=next.toString();
                        if(!set.contains(nextStr)){
                            q.offer(nextStr);
                            set.add(nextStr);
                        }
                    }
                }
            }
            dist++;
        }
        return -1;
    }

}
