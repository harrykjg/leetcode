package SomeInterviews.roblox;

import java.util.*;

public class AutoEmailGeneration {
    static void main() {

        String[] detectedWords = {"bot", "scam", "cheat"};
        String[][] categories = {
                {"scam", "Fraud"},
                {"cheat", "Gameplay"},
                {"bot", "Gameplay"}
        };
        String[][] instructions = {
                {"Fraud", "Fraud message"},
                {"Gameplay", "Gameplay message"}
        };
        List<List<String>> rs=AutoEmailGeneration.generateEmailBody(detectedWords,categories,instructions);
        for (int i=0;i<rs.size();i++){
            for (int j=0;j<rs.get(i).size();j++){
                System.out.print(rs.get(i).get(j));
            }
        }
    }

    //要3个map，第一个找keyword对应catagory，第二个事catagory敌营instruction，然后第三个是<string,Set<string>>遍历detectedWords，然后把对应的catagory作为key，
    //value是这个catogory的所有keyword，这样能按顺序输出这些keyword对应的instruction
    public static List<List<String>> generateEmailBody(String[] detectedWords, String[][] categories, String[][] instructions) {
        List<List<String>> rs=new ArrayList<>();
        Map<String, Set<String>> map=new LinkedHashMap<>();
        Map<String,String> keyCate=new HashMap<>();
        Map<String,String> inst=new HashMap<>();
        for (int i=0;i<categories.length;i++){
            keyCate.putIfAbsent(categories[i][0],categories[i][1]);
        }
        for (int i=0;i<instructions.length;i++){
            inst.putIfAbsent(instructions[i][0],instructions[i][1].trim());
        }
        for (int i=0;i<detectedWords.length;i++){
            String key=detectedWords[i];
            String cata=keyCate.get(key);
            if(cata!=null){
                map.putIfAbsent(cata,new TreeSet<>());
                map.get(cata).add(key);
            }
        }
        for (Map.Entry<String,Set<String>> entry:map.entrySet()){
            StringBuilder sb=new StringBuilder();
            List<String> al=new ArrayList<>();
            sb.append("Detected Keywords:");
            for (String s:entry.getValue()){
                sb.append(" ");
                sb.append(s);
                sb.append(",");
            }
            sb=sb.deleteCharAt(sb.length()-1);
            al.add(sb.toString());
            sb=new StringBuilder();
            sb.append("Instruction: ");
            sb.append(inst.get(entry.getKey()));
            al.add(sb.toString());
            rs.add(al);
        }
        return rs;
    }
}
