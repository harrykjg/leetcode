import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
//http://blog.csdn.net/fightforyourdream/article/details/17497883
//http://blog.csdn.net/ljphhj/article/details/22292131
//http://blog.csdn.net/linhuanmars/article/details/22715747
//http://blog.csdn.net/fytain/article/details/12191351
//��hashset��contains������arraylist��contains������60ms����

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

	public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {// ���ϵ�
		if (node == null)
			return null;
		LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
		map.put(node, copy);// �������ȰѸ��Ƶ�һ���Ž�map�����whileѭ����������copy
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
		HashSet<Integer> set = new HashSet<Integer>();// �����жϸ�node��neighbor�Ƿ���¹�

		ls.offer(node);
		while (!ls.isEmpty()) {
			UndirectedGraphNode ori = ls.poll();
			UndirectedGraphNode n = null;
			if (!map.containsKey(ori.label)) {// ���������Ʒ��û�����������޵Ļ��Ž��������þɵ�
				n = new UndirectedGraphNode(ori.label);
				map.put(ori.label, n);
			} else {
				n = map.get(ori.label);
			}
			if (!set.contains(ori.label)) {// �����lable��copy��neighborû�����¹��Ļ�������֮

				for (int i = 0; i < ori.neighbors.size(); i++) {
					if (!map.containsKey(ori.neighbors.get(i).label)) {// �����neighborû�����Ļ�
						UndirectedGraphNode nn = new UndirectedGraphNode(ori.neighbors.get(i).label);
						n.neighbors.add(nn);
						map.put(ori.neighbors.get(i).label, nn);
						ls.add(ori.neighbors.get(i));

					} else {// �������neighbor�ͱ�����map����
						n.neighbors.add(map.get(ori.neighbors.get(i).label));
					}

				}
				set.add(ori.label);
			}

		}
		return map.get(node.label);

	}
	//�ڶ���д������˼·֮������˼��β��С��������ȵ�һ���ڵ���while֮ǰ�͸����˵ģ����������
	//�ѵ�һ���ڵ����ϵ�while���渴�Ƶġ�˼·�����ȸ��Ƶ�ǰ�ڵ��label���ٰ�����ڵ��neibor
	//Ҳ�������ˣ�������neibor��Ľڵ���hashmap��û�еĻ��ż���q�����򲻼��ˣ���д��ʱ������
	public UndirectedGraphNode cloneGraph4(UndirectedGraphNode node) {
		if(node==null){
			return null;
		}
		HashMap<UndirectedGraphNode,UndirectedGraphNode> hm=new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
		LinkedList<UndirectedGraphNode> q=new LinkedList<UndirectedGraphNode>();
		q.add(node);
		UndirectedGraphNode head=null;//���head����������¼��һ�����Ƶĵ㣬�������ص�
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
					q.add(temp);//�������û���ƹ��Ĳżӽ�q
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