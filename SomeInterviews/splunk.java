package SomeInterviews;

public class splunk {
    public static void main(String[] args){
        splunk splunk=new splunk();
        System.out.println(splunk.solution("id,name,age,score\n1,Jack,NULL,12\n17,Betty,28,11"));
    }
    public String solution(String S) {
        String[] splited=S.split("\n");
        StringBuilder sb=new StringBuilder();
        System.out.println(splited.length);
        for(int i=0;i<splited.length;i++){
            String[] rowSplit=splited[i].split(",");
            boolean found=false;
            for(String s:rowSplit){
                // System.out.println(s);
                if(s.equals("NULL")){
                    // System.out.println(s);
                    found=true;
                    break;
                }
            }
            if(!found){
                sb.append(splited[i]);
                sb.append("\\n");
            }
        }
        System.out.println(sb.toString());
        return sb.substring(0,sb.length()-2);
    }
}
