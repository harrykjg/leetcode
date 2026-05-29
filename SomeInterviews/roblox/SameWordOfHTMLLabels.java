package SomeInterviews.roblox;

import java.util.List;
import java.util.Stack;

public class SameWordOfHTMLLabels {
    /*
    Same Word of HTML Labels
You are given the roots of two N-ary trees, each representing a hierarchical HTML-like label structure. In this structure, every label can contain either:
A non-empty string value, which appears only in labels without any children, or
No string value (empty or null text) but can group together one or more child labels, arranged from top to bottom.
A leaf label is any label that holds a non-empty string and does not contain any child labels. All other labels are simply used for grouping and their text should be considered empty or ignored.
Your task is to extract the text values of all leaf labels from top to bottom, and concatenate them in their appearance order within the structure.
Return true if the resulting strings for both label structures are identical (matching in both case and character order). Otherwise, return false.
The HTML label data structure is as follows:
class HtmlLabel {
    String text;
    List<HtmlLabel> children;
    ...
}

Constraints
Every leaf label contains a non-empty string of up to 100 characters.
The top-to-bottom order of children must be preserved.
0 ≤ number of labels in each structure ≤ 10^5
Example
Input:
    Label 1:
         <root>
             <container>
                 <div> "He" </div>
                 <div> "llo" </div>
             </container>
             <p> "World" </p>
         </root>

     Label 2:
         <root>
             <div> "Hello" </div>
             <container>
                 <span> "Wor" </span>
                 <span> "ld" </span>
             </container>
         </root>

Output:
true
Explanation:
In the first structure, the leaf labels are "He" + "llo" + "World" → "HelloWorld".
In the second, the leaves are "Hello" + "Wor" + "ld" → "HelloWorld".
The final concatenated strings are equal.

     */
    //应该就是dfs，按顺序先把第一个leaf的值搞出来，后面得leaf的值逐个append过来
    public boolean isSame(HtmlLabel root1,HtmlLabel root2){
        String s1=dfs(root1);
        String s2=dfs(root2);
        return s1.equals(s2);
    }
    String dfs(HtmlLabel root){
        StringBuilder sb=new StringBuilder();
        if(root==null){
            return "";
        }
        if(root.text!=null&&root.text.length()!=0){
            return root.text;
        }

        List<HtmlLabel> neighbour=root.children;
        if(neighbour!=null){
            for (HtmlLabel nei:neighbour){
                sb.append(dfs(nei));
            }
        }

        return sb.toString();
    }
    //答案用stack
    public boolean sameWordOfHtmlLabels(HtmlLabel root1, HtmlLabel root2) {
        String str1 = getLeafConcatenation(root1);
        String str2 = getLeafConcatenation(root2);
        return str1.equals(str2);
    }

    private String getLeafConcatenation(HtmlLabel root) {
        if (root == null)
            return "";

        StringBuilder result = new StringBuilder();
        Stack<HtmlLabel> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            HtmlLabel current = stack.pop();

            // If it's a leaf node (has text and no children or empty children)
            if (current.text != null && !current.text.isEmpty()
                    && (current.children == null || current.children.isEmpty())) {
                result.append(current.text);
            }

            // Add children to stack in reverse order for left-to-right traversal
            if (current.children != null) {
                for (int i = current.children.size() - 1; i >= 0; i--) {
                    if (current.children.get(i) != null) {
                        stack.push(current.children.get(i));
                    }
                }
            }
        }

        return result.toString();
    }
}

class HtmlLabel {
    String text;
    List<HtmlLabel> children;
}
