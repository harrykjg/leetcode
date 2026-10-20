package SomeInterviews.reddit;

import java.util.*;

public class OrganizeReportChain {
    /*
    A company's reporting structure is described as a list relationships, where each element is a list of strings. The first string represents a manager, and the remaining strings are that manager's direct reports. Each employee name is unique, and there is exactly one top-level manager who does not report to anyone.

Write a function that prints the complete organizational hierarchy following the rules below:

The top-level manager is printed on its own line with no indentation.
Every direct report is indented by exactly four dots ("....") more than their manager.
The indentation depth equals the number of levels below the top-level manager.
All direct reports under the same manager must be printed in alphabetical order.
Each employee appears exactly once in the output, even if they only appear as a manager or only as a subordinate.
The input parameter relationships is a list of lists, where each inner list contains a manager followed by one or more direct reports.

The final result must be returned as a single string, with each employee on its own line separated by a newline character ('\n'). There should be no trailing newline after the last line.

Constraints:

All names in relationships are non-empty and unique.
There is exactly one top-level manager.
1
1 ≤ relationships.length ≤
10
4
10
4

Each relationships[i] contains at least one string (the manager), and possibly multiple direct reports.
Example 1:

Input: relationships = [["a", "b", "c"], ["b", "d"], ["d", "e"]]
Output: "a\n....b\n........d\n............e\n....c"
Explanation: The hierarchy is shown below:

a
....b
........d
............e
....c
"a" is the top-level manager.
"b" and "c" report directly to "a" and are printed alphabetically.
"d" reports to "b", and "e" reports to "d".
Example 2:

Input: relationships = [["ceo", "vp1", "vp2"], ["vp1", "mgr1", "mgr2"], ["vp2", "mgr3"]]

Output: "ceo\n....vp1\n........mgr1\n........mgr2\n....vp2\n........mgr3"
Explanation: The hierarchy is shown below:

ceo
....vp1
........mgr1
........mgr2
....vp2
........mgr3
Example 3:

Input: relationships = [["ceo", "cto", "cfo", "coo"], ["cto", "vp_eng", "vp_product"], ["cfo", "vp_finance", "vp_accounting"], ["vp_eng", "eng1", "eng2", "eng3"], ["vp_product", "pm1", "pm2"], ["eng1", "junior1", "junior2"]]

Output: "ceo\n....cfo\n........vp_accounting\n........vp_finance\n....coo\n....cto\n........vp_eng\n............eng1\n................junior1\n................junior2\n............eng2\n............eng3\n........vp_product\n............pm1\n............pm2"
Explanation: The hierarchy is shown below:

ceo
....cfo
........vp_accounting
........vp_finance
....coo
....cto
........vp_eng
............eng1
................junior1
................junior2
............eng2
............eng3
........vp_product
............pm1
............pm2
     */
    //建图，然后另外拿一个map存每个人的深度.但是应该直接dfs就有深度了吧，打印顺序也是dfs，不是bfs，因为肯定是所有人都有manager的所以会都cover
    public String printOrganization(List<List<String>> relationships) {
        // TODO: Implement printOrganization logic
        Map<String,List<String>> map=new HashMap<>();
        StringBuilder sb=new StringBuilder();
        for (List<String> rs:relationships){
            map.putIfAbsent(rs.get(0),new ArrayList<>());
            for(int i=1;i<rs.size();i++){
                map.get(rs.get(0)).add(rs.get(i));
            }
        }
        Queue<String> q=new LinkedList<>();

        dfs(0,relationships.get(0).get(0),map,sb);

        return sb.substring(0,sb.length()-1);
    }
    void dfs(int depth,String cur,Map<String,List<String>> map,StringBuilder sb){
        String dot="....";
        for (int i=0;i<depth;i++){
            sb.append(dot);
        }
        sb.append(cur);
        sb.append("\n");
        List<String> neighbour=map.get(cur);
        if(neighbour!=null){
            Collections.sort(neighbour);
            for (String nei:neighbour){
                dfs(depth+1,nei,map,sb);
            }
        }
    }
    /*
    Follow-up 1:
As a follow-up to the previous organizational hierarchy problem, you are given the same reporting structure relationships.

This time, your task is to identify all skip-level relationships within the organization. A skip-level relationship is defined as a pair (manager, employee) such that:

The manager is exactly two levels above the employee in the hierarchy.
In other words, there exists an intermediate employee mid so that manager manages mid, and mid manages employee.
Given relationships, return a list of all skip-level pairs that exist in the organization. The order of the output does not matter.

Example 1:

Input: relationships = [["a", "b", "c"], ["b", "d"], ["d", "e"]]
Output: [["a", "d"], ["b", "e"]]
Explanation:

"a" is two levels above "d" via "a" -> "b" -> "d", so ("a", "d") is a skip-level pair.
"b" is two levels above "e" via "b" -> "d" -> "e", so ("b", "e") is also a skip-level pair.
No other skip-level pairs exist in this structure.
The hierarchy is shown below:

a
....b
........d
............e
....c
Example 2:

Input: relationships = [["ceo", "vp1", "vp2"], ["vp1", "mgr1", "mgr2"], ["vp2", "mgr3"], ["mgr1", "emp1"]]
Output: [["ceo", "mgr1"], ["ceo", "mgr2"], ["ceo", "mgr3"], ["vp1", "emp1"]]

Example 3:

Input: relationships = [["ceo", "cto", "cfo", "coo"], ["cto", "vp_eng", "vp_product"], ["cfo", "vp_finance", "vp_accounting"], ["vp_eng", "eng1", "eng2", "eng3"], ["vp_product", "pm1", "pm2"], ["eng1", "junior1", "junior2"]]
Output: [["ceo", "vp_eng"], ["ceo", "vp_product"], ["ceo", "vp_finance"], ["ceo", "vp_accounting"], ["cto", "eng1"], ["cto", "eng2"], ["cto", "eng3"], ["cto", "pm1"], ["cto", "pm2"], ["vp_eng", "junior1"], ["vp_eng", "junior2"]]
     */
    //就是找skip的，比如 [mrg, emp1][mrg,emp2],是两个两个的那就用bfs的时候看下下层
    public List<List<String>> getSkipLevelPairs(List<List<String>> relationships) {
        // TODO: Implement getSkipLevelPairs logic
        Map<String,List<String>> map=new HashMap<>();
        List<List<String>> rs=new ArrayList<>();
        for (List<String> re:relationships){
            map.putIfAbsent(re.get(0),new ArrayList<>());
            for(int i=1;i<re.size();i++){
                map.get(re.get(0)).add(re.get(i));
            }
        }
        Queue<String> q=new LinkedList<>();
        q.offer(relationships.get(0).get(0));
        int dist=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                String cur=q.poll();
                List<String> neighbour=map.get(cur);
                if(neighbour!=null){
                    for (String nei:neighbour){
                        //加skip
                        List<String> skips=map.get(nei);
                        if(skips!=null&&skips.size()>0){
                            for (String skip:skips){
                                List<String> al=new ArrayList<>();
                                al.add(cur);
                                al.add(skip);
                                rs.add(al);
                            }
                        }
                        q.offer(nei);
                    }
                }
            }
            dist++;
        }
        return rs;
    }
    /*
    Follow-up 2:
As a follow-up to the previous organizational hierarchy problems, you are given the same reporting structure relationships.

Given a target employee target, return the minimal organization subtree that includes:

target
all managers above target up to the top-level manager
all direct and indirect reports under target
Each employee will only have one manager
The returned hierarchy must follow these requirements:

The returned root must always be the highest manager in target's chain.
Use exactly four dots ("....") per level of indentation below the root. For example, the root is not indented, its direct reports are indented by four dots, the next level by eight dots, and so on.
For any manager, return their direct reports in alphabetical order.
Return an empty string if target does not exist in the organization.
Each employee must appear on their own line. Lines are separated using a line break character ('\n'). There must be no trailing newline after the last line.

Example 1:

Input: relationships = [["a", "b", "c"], ["b", "d"], ["d", "e"]], target = "d"
Output: "a\n....b\n........d\n............e"
Explanation: The result contains "d", its managers ("a", "b"), and all subordinates under "d" ("e"). Only the relevant branch is returned.
The subtree is shown below:

a
....b
........d
............e
Example 2:

Input: relationships = [["ceo", "sales", "engineering", "hr"], ["sales", "sales_mgr1", "sales_mgr2"], ["engineering", "eng_mgr1", "eng_mgr2"], ["hr", "hr_mgr"], ["eng_mgr1", "alice", "bob"]], target = "eng_mgr1"
Output: "ceo\n....engineering\n........eng_mgr1\n............alice\n............bob"
Explanation: The subtree is shown below:

ceo
....engineering
........eng_mgr1
............alice
............bob
Example 3:

Input: relationships = [["ceo", "cto", "cfo", "coo"], ["cto", "vp_eng", "vp_product", "vp_security"], ["cfo", "vp_finance", "vp_accounting"], ["coo", "vp_ops"], ["vp_eng", "eng_mgr1", "eng_mgr2", "eng_mgr3"], ["vp_product", "pm1", "pm2"], ["eng_mgr2", "dev1", "dev2", "dev3"], ["dev1", "intern1", "intern2"]], target = "dev1"
Output: "ceo\n....cto\n........vp_eng\n............eng_mgr2\n................dev1\n....................intern1\n....................intern2"
Explanation: The subtree is shown below:

ceo
....cto
........vp_eng
............eng_mgr2
................dev1
....................intern1
....................intern2
Hint 1
First, map out the entire reporting structure using a hash table, ensuring you track which employees are managers to easily locate the top-level root.

Hint 2
Use a recursive search to record the path from the root to the target; once found, you can control whether to print only the next path node or expand into the full subtree based on your current depth.

     */
    //实际上就是tree找一个target，答案是这条path 上的节点以及trager以及他的所有子树。遍历的时候就得记录path，像第一题那样，找到target才返回，
    //别的都drop,那怎么revert呢。不要用stringbuilder，直接用一个list存string，失败的删掉，就是backtracking。
    public String printReportChain(List<List<String>> relationships, String target) {
        // TODO: Implement printReportChain logic
        StringBuilder sb=new StringBuilder();
        Map<String,List<String>> map=new HashMap<>();
        for (List<String> re:relationships){
            map.putIfAbsent(re.get(0),new ArrayList<>());
            for(int i=1;i<re.size();i++){
                map.get(re.get(0)).add(re.get(i));
            }
        }
        List<String> path=new ArrayList<>();
        dfs2(0,relationships.get(0).get(0),map,target,path);//这里有漏洞，不是说第一个必然是ceo，要找的话就是用两个set，一个
        for (String p:path){      //记录所有人， 一个记录所有作为children出现的人，没有出现在children的那个就是ceo，这里没写
            sb.append(p);
        }
        if(sb.length()==0){
            return "";
        }
        return sb.substring(0,sb.length()-1);
    }
    boolean dfs2(int depth,String cur,Map<String,List<String>> map,String target,List<String> path){
        //开头直接判断是否是target，因为可能是target就是ceo
        if (cur.equals(target)) {
            helper(depth,cur,map,path);
            return true;
        }
        String dot="....";
        //用temp作为一个整体add进path里，否则remove的时候不好remove
        String temp="";
        for (int i=0;i<depth;i++){
            temp+=(dot);
        }
        temp+=cur+"\n";
        path.add(temp);
        List<String> neighbour=map.get(cur);
        if(neighbour!=null){
            Collections.sort(neighbour);
            for (String nei:neighbour){//不需要单独判断nei是否是target，dfs进去一开始就会判断
                if(dfs2(depth+1,nei,map,target,path)){
                    return true;
                }
            }
        }
        // 所有分支都没找到 target，回溯
        path.remove(path.size() - 1);
        return false;
    }
    void helper(int depth,String cur, Map<String,List<String>> map,List<String> path){
        String dot="....";
        for (int i=0;i<depth;i++){
            path.add(dot);
        }
        path.add(cur);
        path.add("\n");
        List<String> neighbour=map.get(cur);
        if(neighbour!=null){
            Collections.sort(neighbour);
            for (String nei:neighbour){
                helper(depth+1,nei,map,path);
            }
        }
    }
    /*
    Follow-up 3:
As a follow-up to the previous organizational hierarchy problem, you are given the same reporting structure relationships.

Given two employees emp1 and emp2, return their lowest common manager. The lowest common manager is the deepest employee in the hierarchy who is a manager of both emp1 and emp2. An employee can be considered a manager of themselves, so if emp1 equals emp2, the answer is that employee.

If either emp1 or emp2 does not exist in the organization, return "".

Example 1:

Input: relationships = [["A", "B", "C"], ["C", "D"], ["B", "E"]], emp1 = "C", emp2 = "E"
Output: "A"
Explanation: The hierarchy is shown below:

A
....B
........E
....C
........D
Both employees are under "A" in the hierarchy, and "A" is the deepest manager that is common to both reporting chains.

Example 2:

Input: relationships = [["a", "b", "c"], ["b", "d"], ["d", "e"]], emp1 = "b", emp2 = "e"
Output: "b"

Example 3:

Input: relationships = [["ceo", "cto", "cfo", "coo"], ["cto", "vp_eng", "vp_product"], ["vp_eng", "eng1", "eng2", "eng3"], ["eng1", "junior1", "junior2"], ["cfo", "vp_finance"]], emp1 = "eng1", emp2 = "eng2"
Output: "vp_eng"
     */
    //就是找LCA 1644，emp1,2不一定在树里，因此两边都要看吧,而且是多孩子的。但是这里直接抄的gpt的解法是通过找两条path，第一条path先找到，
    //把路径存在set里，第二条path从target往上找parent，第一个出现在set里的就是LCA。因此需要建一个parent map，而1644其实也可以这样做，
    //但是那里给的是root，而这里给的是relationship。1644可以不用建图，直接开始往下找，而这里需要建图，那就顺便建parent map也方便，这样
    //还更容易写吧
    public String lowestCommonManager(List<List<String>> relationships, String emp1, String emp2) {
        Map<String, String> parentMap = new HashMap<>();
        Set<String> all = new HashSet<>();
        // 建立 employee -> manager
        for (List<String> re : relationships) {
            String manager = re.get(0);
            all.add(manager);
            for (int i = 1; i < re.size(); i++) {
                String employee = re.get(i);
                parentMap.put(employee, manager);
                all.add(employee);
            }
        }
        // 任意员工不存在
        if (!all.contains(emp1) || !all.contains(emp2)) {
            return "";
        }
        // 记录 emp1 的所有祖先，包括自己
        Set<String> ancestors = new HashSet<>();
        String cur = emp1;
        while (cur != null) {
            ancestors.add(cur);
            cur = parentMap.get(cur);
        }
        // 从 emp2 向上找，第一个交集就是 LCA
        cur = emp2;
        while (cur != null) {
            if (ancestors.contains(cur)) {
                return cur;
            }
            cur = parentMap.get(cur);
        }
        return "";
    }

}
