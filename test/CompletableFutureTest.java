package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFutureTest{
    private Supplier<QueryUtils> queryUtilsSupplier = QueryUtils::new;
    public static void main(String[] args) {
        long begin= System.currentTimeMillis();
        //多线程环境需要注意线程安全问题
        List<UserInfo> userInfoList= Collections.synchronizedList(new ArrayList<>());
        for(int i=0;i<=20;i++){
            UserInfo userInfo=new UserInfo();
            userInfo.setId(i);
            userInfo.setName("username"+i);
            userInfo.setCarId(i);
            userInfo.setJobId(i);
            userInfo.setHomeId(i);
            userInfoList.add(userInfo);
        }
        //stream 查询一个用户花费3s  并行计算后一个用户1秒左右 查询21个用户花费21秒
        //parallelStream 速度更慢
        userInfoList.stream()
                .map(userInfo->{
                    CompletableFutureTest queryUserService=new CompletableFutureTest();
                    userInfo =queryUserService.converUserInfo(userInfo);
                    return userInfo;
                }).collect(Collectors.toList());
        System.out.println("=============");
        long end=System.currentTimeMillis();
        System.out.println(end-begin);
    }
    public UserInfo converUserInfo(UserInfo userInfo) {
        QuerySuppiler querySuppiler1 = new QuerySuppiler(userInfo.getCarId(), "car", queryUtilsSupplier.get());
        CompletableFuture<String> getCarDesc = CompletableFuture.supplyAsync(querySuppiler1);
        getCarDesc.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String carDesc) {
                userInfo.setCarDes(carDesc);
            }
        });
        QuerySuppiler querySuppiler2 = new QuerySuppiler(userInfo.getHomeId(), "home", queryUtilsSupplier.get());
        CompletableFuture<String> getHomeDesc = CompletableFuture.supplyAsync(querySuppiler2);
        getHomeDesc.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String homeDesc) {
                userInfo.setHomeDes(homeDesc);
            }
        });
        QuerySuppiler querySuppiler3 = new QuerySuppiler(userInfo.getJobId(), "job", queryUtilsSupplier.get());
        CompletableFuture<String> getJobDesc = CompletableFuture.supplyAsync(querySuppiler3);
        getJobDesc.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String jobDesc) {
                userInfo.setJobDes(jobDesc);
            }
        });
        CompletableFuture<Void> getUserInfo = CompletableFuture.allOf(getCarDesc, getHomeDesc, getJobDesc);
        getUserInfo.thenAccept(new Consumer<Void>() {
            @Override
            public void accept(Void result) {
                System.out.println("done" );
            }
        });
        getUserInfo.join();
        return userInfo;
    }
}

class UserInfo {
    private Integer id;
    private String name;
    private Integer jobId;
    private String jobDes;
    private Integer carId;
    private String carDes;
    private Integer homeId;
    private String homeDes;
    public Integer getId() {
        return id;
    }
    public UserInfo setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getJobId() {
        return jobId;
    }
    public UserInfo setJobId(Integer jobId) {
        this.jobId = jobId;
        return this;
    }
    public String getJobDes() {
        return jobDes;
    }
    public UserInfo setJobDes(String jobDes) {
        this.jobDes = jobDes;
        return this;
    }
    public Integer getCarId() {
        return carId;
    }
    public UserInfo setCarId(Integer carId) {
        this.carId = carId;
        return this;
    }
    public String getCarDes() {
        return carDes;
    }
    public UserInfo setCarDes(String carDes) {
        this.carDes = carDes;
        return this;
    }
    public Integer getHomeId() {
        return homeId;
    }
    public UserInfo setHomeId(Integer homeId) {
        this.homeId = homeId;
        return this;
    }
    public String getHomeDes() {
        return homeDes;
    }
    public UserInfo setHomeDes(String homeDes) {
        this.homeDes = homeDes;
        return this;
    }
}

class QueryUtils {
    public String queryCar(Integer carId){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "car_desc";
    }
    public String queryJob(Integer jobId){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "job_desc";
    }
    public String queryHome(Integer homeId){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "home_desc";
    }
}

class QuerySuppiler implements Supplier<String> {
    private Integer id;
    private String type;
    private QueryUtils queryUtils;
    public QuerySuppiler(Integer id, String type,QueryUtils queryUtils) {
        this.id = id;
        this.type = type;
        this.queryUtils=queryUtils;
    }
    @Override
    public String get() {
        if("home".equals(type)){
            return queryUtils.queryHome(id);
        }else if ("job".equals(type)){
            return queryUtils.queryJob(id);
        }else if ("car".equals(type)){
            return queryUtils.queryCar(id);
        }
        return null;
    }
}