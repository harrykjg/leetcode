import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testAsync {
    public static void main(String[] args){
        testAsync ta=new testAsync();
//        for(int i=0;i<3;i++){
//            try {
//                ta.sleep2();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.println("done");
        System.out.println("Hello World");
        List<metadata> md=new ArrayList<>();
        metadata m1=new metadata("a");
        md.add(m1);
        metadata m2=new metadata("b");
        md.add(m2);
        person p=new person();
        p.setMetadata(md);
        String[] ff=p.metadata.stream().map(metadata::getFriend).toArray(String[]::new);
        Arrays.stream(ff).forEach(System.out::println);
        System.out.println("xxxx");

    }
    public void sleep2() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("sleeping 2000");
    }


    public void sleep1() throws InterruptedException {
        Thread.sleep(2);
        System.out.println("sleeping 2");
    }

}

class person{
    String name;
    List<metadata> metadata;
    public void setMetadata(List<metadata> metadata){
        this.metadata=metadata;
    }
}
class metadata{
    String friend;
    public metadata(String friend){
        this.friend=friend;
    }
    public String getFriend(){
        return friend;
    }

}