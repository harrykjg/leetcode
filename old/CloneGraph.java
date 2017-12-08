import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
//http://blog.csdn.net/fightforyourdream/article/details/17497883
//http://blog.csdn.net/ljphhj/article/details/22292131
//http://blog.csdn.net/linhuanmars/article/details/22715747
//http://blog.csdn.net/fytain/article/details/12191351
//用hashset的contains方法比arraylist的contains方法快60ms左右

public class CloneGraph {
	public static void main(String[] args) {
		CloneGraph cg = new CloneGraph();
		UndirectedGraphNode node = new UndirectedGraphNode(0);
		UndirectedGraphNode node2 = new UndirectedGraphNode(1);
		UndirectedGraphNode node3 = new UndirectedGraphNode(2);
		node.neighbors.add(node2);
		node2.neighbors.add(node3);
		node3.neighbors.add(node3);
		// {0,1#1,2#2,2}
		System.out.println(cg.cloneGraph4(node).label);

	}



	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		LinkedList<UndirectedGraphNode> ls = new LinkedList<UndirectedGraphNode>();
		UndirectedGraphNode n = new UndirectedGraphNode(node.label);
		ls.add(node);
		while (!ls.isEmpty()) {
			UndirectedGraphNode cur = new UndirectedGraphNode(ls.peek().label);

			if (!map.containsKey(ls.peek().label)) {
				map.put(ls.peek().label, cur);
				for (int i = 0; i < ls.peek().neighbors.size(); i++) {
					if (!map.containsKey(ls.peek().neighbors.get(i).label)) {
						UndirectedGraphNode nn = new UndirectedGraphNode(
								ls.peek().neighbors.get(i).label);
						map.get(ls.peek().label).neighbors.add(nn);
					} else {
						map.get(ls.peek().label).neighbors.add(cur);
					}

					if (!map.containsKey(ls.peek().neighbors.get(i).label)) {
						ls.add(ls.peek().neighbors.get(i));
					}
				}
			}
			ls.poll();
		}

		return map.get(node.label);

	}

	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {// 网上的
		if (node == null)
			return null;
		LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
		map.put(node, copy);// 他就是先把复制的一个放进map里，再在while循环里更新这个copy
		queue.offer(node);
		while (!queue.isEmpty()) {
			UndirectedGraphNode cur = queue.poll();
			for (int i = 0; i < cur.neighbors.size(); i++) {
				if (!map.containsKey(cur.neighbors.get(i))) {
					copy = new UndirectedGraphNode(cur.neighbors.get(i).label);
					map.put(cur.neighbors.get(i), copy);
					queue.offer(cur.neighbors.get(i));
				}
				UndirectedGraphNode xx = map.get(cur.neighbors.get(i));
				map.get(cur).neighbors.add(xx);
			}
		}
		return map.get(node);

	}

	public UndirectedGraphNode cloneGraph3(UndirectedGraphNode node) {
		if (node == null)
			return null;
		LinkedList<UndirectedGraphNode> ls = new LinkedList<UndirectedGraphNode>();

		HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		HashSet<Integer> set = new HashSet<Integer>();// 用来判断该node的neighbor是否更新过

		ls.offer(node);
		while (!ls.isEmpty()) {
			UndirectedGraphNode ori = ls.poll();
			UndirectedGraphNode n = null;
			if (!map.containsKey(ori.label)) {// 看这个复制品有没被创建过，无的话才建，否则拿旧的
				n = new UndirectedGraphNode(ori.label);
				map.put(ori.label, n);
			} else {
				n = map.get(ori.label);
			}
			if (!set.contains(ori.label)) {// 若这个lable的copy的neighbor没被更新过的话，更新之

				for (int i = 0; i < ori.neighbors.size(); i++) {
					if (!map.containsKey(ori.neighbors.get(i).label)) {// 若这个neighbor没创建的话
						UndirectedGraphNode nn = new UndirectedGraphNode(ori.neighbors.get(i).label);
						n.neighbors.add(nn);
						map.put(ori.neighbors.get(i).label, nn);
						ls.add(ori.neighbors.get(i));

					} else {// 否则这个neighbor就保存在map里了
						n.neighbors.add(map.get(ori.neighbors.get(i).label));
					}

				}
				set.add(ori.label);
			}

		}
		return map.get(node.label);

	}
	//第二次写，看了思路之后调试了几次才行。他们是先第一个节点在while之前就复制了的，我这个就是
	//把第一个节点整合到while里面复制的。思路就是先复制当前节点的label，再把这个节点的neibor
	//也都复制了，如果这个neibor里的节点在hashmap里没有的话才加入q，否则不加了，我写的时候搞错了
	public UndirectedGraphNode cloneGraph4(UndirectedGraphNode node) {
		if(node==null){
			return null;
		}
		HashMap<UndirectedGraphNode,UndirectedGraphNode> hm=new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
		LinkedList<UndirectedGraphNode> q=new LinkedList<UndirectedGraphNode>();
		q.add(node);
		UndirectedGraphNode head=null;//这个head就是用来记录第一个复制的点，用来返回的
		while(!q.isEmpty()){
			UndirectedGraphNode ori=q.poll();
			UndirectedGraphNode copy=null;
			
			if(!hm.containsKey(ori)){
				copy=new UndirectedGraphNode(ori.label);
				hm.put(ori, copy);
			}else{
				copy=hm.get(ori);
			}
			if(head==null){
				head=copy;
			}
			List<UndirectedGraphNode> neib=ori.neighbors;
			for(int i=0;i<neib.size();i++){
				UndirectedGraphNode temp=neib.get(i);
				if(hm.containsKey(temp)){
					copy.neighbors.add(hm.get(temp));
				}else{
					UndirectedGraphNode neibcopy=new UndirectedGraphNode(temp.label);
					copy.neighbors.add(neibcopy);
					hm.put(temp,neibcopy );
					q.add(temp);//就是这里，没复制过的才加进q
				}
				
			}
		}
		return head;
		
	}
}
class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}