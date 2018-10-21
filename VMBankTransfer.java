import java.math.BigDecimal;
import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

/**
 * Created by yufengzhu on 10/2/18.
 */
public class VMBankTransfer {
    public static void main(String[] args){
        account a=new account();
        a.setBalance(100);
        account b=new account();
        b.setBalance(230);
//        service sv=new service(a,b,500);
//        Thread thread=new Thread(sv);
//        thread.start();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            if ((i & 1) == 0) {
                threadPool.execute(new service(a, b, 10));
            }
            else {
                threadPool.execute(new service(b,a,10));
            }
//            service sv=new service(a,b,500);
//            Thread thread=new Thread(sv);
//            thread.start();

        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("aaaaaa");
    }




}
class account{
    private int balance;

    int getBalance(){

        return balance;
    }
    void setBalance(int balance){
        this.balance=balance;
    }


}
class service implements Runnable{
    account a;
    account b;
    int amount;
    Object tieLock=new Object();
    public service(account a,account b,int amount){
        this.a=a;
        this.b=b;
        this.amount=amount;
    }
    void transfer(account a,account b,int money) throws Exception{
        int fromHash = System.identityHashCode(a);
        int toHash = System.identityHashCode(b);

        if (fromHash < toHash) {
            synchronized (a) {
                synchronized (b) {
                    a.setBalance(a.getBalance()-money);
                    System.out.println(a.getBalance());
                    b.setBalance(b.getBalance()+money);
                    System.out.println(b.getBalance());
                }
            }
        } else if (fromHash > toHash) {
            synchronized (b) {
                synchronized (a) {
                    a.setBalance(a.getBalance()-money);
                    System.out.println(a.getBalance());
                    b.setBalance(b.getBalance()+money);
                    System.out.println(b.getBalance());
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (a) {
                    synchronized (b) {
                        a.setBalance(a.getBalance()-money);
                        System.out.println(a.getBalance());
                        b.setBalance(b.getBalance()+money);
                        System.out.println(b.getBalance());
                    }
                }
            }
        }
//        synchronized (a) {
//            synchronized (b) {
//                a.setBalance(a.getBalance()-money);
//                System.out.println(a.getBalance());
//                b.setBalance(b.getBalance()+money);
//                System.out.println(b.getBalance());
//            }
//        }

    }

    @Override
    public void run() {
        try{
            transfer(a,b,amount);
            System.out.print("xxxx");

        }catch (Exception ex){

        }
    }
}
