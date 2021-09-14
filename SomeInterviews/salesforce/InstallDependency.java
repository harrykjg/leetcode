package SomeInterviews.salesforce;

import java.util.*;

public class InstallDependency {
    public static void main(String[] args){
        InstallDependency id=new InstallDependency();
        String depend0="telnet tcpip netcard";
        String depend1="tcpip netcard";
        String depend2="netcard tcpip";
        String depend3="dns tcpip netcard";
        String depend4="browser tcpip html";
        id.depend(depend0);
        id.depend(depend1);
        id.depend(depend2);
        id.depend(depend3);
        id.depend(depend4);
        id.install("netcard");
        id.install("telnet");
        id.install("foo");
        id.remove("netcard");
        id.install("browser");
        id.install("dns");
        id.list();
        id.remove("telnet");
        id.remove("netcard");
        id.remove("dns");
        id.remove("netcard");
        id.install("netcard");
        id.remove("tcpip");
        id.remove("browser");
        id.remove("tcpip");
        id.list();

    }

//    Map<String, Set<String>> map=new HashMap<>();
//    Map<String,Integer> de=new HashMap<>();
//    Set<String> installed=new LinkedHashSet<>();
//    Set<String> explicitlyInstall=new LinkedHashSet<>();//这个非常tricky，netcard是一个没有dependenvy的东西，而且是explicitly installed的，所以后面
//    public InstallDependency(){                 //当没有东西再depend on他的时候，他仍然不能被删
//    }
//
//    void depend(String s){
//        if (s==null||s.length()==0){
//            return;
//        }
//        System.out.println("DEPEND "+s);
//        String[] sa=s.split(" ");
//        String key=sa[0];
//        for (int i=1;i<sa.length;i++){
//            if(checkIfADependsOnB(sa[i],key)){
//                System.out.println(sa[i]+" depends on "+key+", ignoring command");
//                return;
//            }
//        }
//
//        if (map.containsKey(key)){
//            System.out.println("??");
//        }else{
//            map.put(key,new LinkedHashSet<>());
//            for (int i=1;i<sa.length;i++){
//                map.get(key).add(sa[i]);
////                if (!de.containsKey(sa[i])){
////                    de.put(sa[i],1);
////                }else{
////                    de.put(sa[i],de.get(sa[i])+1);
////                }
//            }
//        }
//
//    }
//    boolean checkIfADependsOnB(String a,String b){//看a是否depend on b那就要看map里的a的依赖有没有b
//        if (!map.containsKey(a)){//如果a直接没有依赖，则返回false
//            return false;
//        }
//
//        for (String nei:map.get(a)){//否则，看a的每一个依赖是否依赖b
//            if (nei.equals(b)){
//                return true;
//            }else{
//                if (checkIfADependsOnB(nei,b)){
//                    return true;
//                }
//            }
//
//        }
//        return false;
//    }
//
//    void install(String s){
//        System.out.println("Install "+s );
//        if (installed.contains(s)){
//            System.out.println(s+ "already installed");
//            return;
//        }
//        if (!map.containsKey(s)){//没dep直接install
//            installed.add(s);
//            explicitlyInstall.add(s);
//            System.out.println("Installing "+ s);
//            return;
//        }
//        //否则先装dep,并且每个dep的degree+1
//        for (String dep:map.get(s)){
//            if (installed.contains(dep)){
//                de.put(dep,de.getOrDefault(dep,0)+1);
//            }else {
//                de.put(dep,de.getOrDefault(dep,0)+1);
//                installHelper(dep);
//            }
//        }
//
//        System.out.println("Installing "+ s);
//        installed.add(s);
//
//    }
//
//    void installHelper(String s){
//        if (!installed.contains(s)){
//            if (!map.containsKey(s)){
//                installed.add(s);
//                System.out.println("Installing "+s);
//            }else{
//                for(String dep:map.get(s)){
//                    de.put(dep,de.getOrDefault(dep,0)+1);
//                    installHelper(dep);
//                }
//                installed.add(s);
//                System.out.println("Installing "+s);
//            }
//        }
//    }
//
//    void remove(String s){
//        System.out.println(" Remove "+s);
//        if (!installed.contains(s)){
//            System.out.println(s+" is not installed");
//            return;
//        }
//        if (de.containsKey(s)){
//            if (de.get(s)>0){
//                System.out.println(s+ "is still needed");
//                return;
//            }
//        } else{
//            System.out.println(" removing "+s);
//            if (map.containsKey(s)){
//                for (String dependency:map.get(s)){
//                    de.put(dependency,de.get(dependency)-1);
//                    if (de.get(dependency)==0&&!explicitlyInstall.contains(dependency)){
//                        de.remove(dependency);
//                        removeHelper(dependency);
//                    }
//                }
//            }
//
//            installed.remove(s);
//        }
//    }
//    void removeHelper(String s){
//
//        System.out.println(" removing "+s);
//        if (map.containsKey(s)){
//            for (String dependency:map.get(s)){
//                de.put(dependency,de.get(dependency)-1);
//                if (de.get(dependency)==0&&!explicitlyInstall.contains(dependency)){
//                    de.remove(dependency);
//                    removeHelper(dependency);
//                }
//            }
//        }
//
//        installed.remove(s);
//
//    }
//    void list(){
//        System.out.println("LIST");
//        for (String s:installed){
//            System.out.println(s);
//        }
//    }


    Map<String, Set<String>> map=new HashMap<>();
    Map<String,Integer> de=new HashMap<>();
    Set<String> totalInstalled=new LinkedHashSet<>();
    Set<String> implictitlyInstalled=new LinkedHashSet<>();
    Set<String> explicitlyInstall=new LinkedHashSet<>();//这个非常tricky，netcard是一个没有dependenvy的东西，而且是explicitly installed的，所以后面
    public InstallDependency(){                 //当没有东西再depend on他的时候，他仍然不能被删
    }

    void depend(String s){
        if (s==null||s.length()==0){
            return;
        }
        List<String> al=new ArrayList<>();

        System.out.println("DEPEND "+s);
        String[] sa=s.split(" ");
        String key=sa[0];
        for (int i=1;i<sa.length;i++){
            if(checkIfADependsOnB(sa[i],key)){
                System.out.println(sa[i]+" depends on "+key+", ignoring command");
                return;
            }
        }

        if (map.containsKey(key)){
            System.out.println("??");
        }else{
            map.put(key,new LinkedHashSet<>());
            for (int i=1;i<sa.length;i++){
                map.get(key).add(sa[i]);
            }
        }

    }
    boolean checkIfADependsOnB(String a,String b){//看a是否depend on b那就要看map里的a的依赖有没有b
        if (!map.containsKey(a)){//如果a直接没有依赖，则返回false
            return false;
        }
        for (String nei:map.get(a)){//否则，看a的每一个依赖是否依赖b
            if (nei.equals(b)){
                return true;
            }else{
                if (checkIfADependsOnB(nei,b)){
                    return true;
                }
            }
        }
        return false;
    }

    void install(String s){
        System.out.println("Install "+s );
        if (implictitlyInstalled.contains(s)||explicitlyInstall.contains(s)){
            System.out.println(s+ "already installed");
            explicitlyInstall.add(s);//比如显示implicitly install，然后再explicitly install，就要加上
            totalInstalled.add(s);
            return;
        }
        if (!map.containsKey(s)){//没dep直接install
            explicitlyInstall.add(s);
            totalInstalled.add(s);
            System.out.println("Installing "+ s);
            return;
        }
        //否则先装dep,并且每个dep的degree+1
        for (String dep:map.get(s)){
            if (explicitlyInstall.contains(dep)||implictitlyInstalled.contains(dep)){
                de.put(dep,de.getOrDefault(dep,0)+1);
            }else {
                de.put(dep,de.getOrDefault(dep,0)+1);
                installHelper(dep);
            }
        }

        System.out.println("Installing "+ s);
        explicitlyInstall.add(s);
        totalInstalled.add(s);

    }

    void installHelper(String s){
        if (!explicitlyInstall.contains(s)&&!implictitlyInstalled.contains(s)){
            if (!map.containsKey(s)){
                implictitlyInstalled.add(s);
                totalInstalled.add(s);
                System.out.println("Installing "+s);
            }else{
                for(String dep:map.get(s)){
                    de.put(dep,de.getOrDefault(dep,0)+1);
                    installHelper(dep);
                }
                implictitlyInstalled.add(s);
                totalInstalled.add(s);
                System.out.println("Installing "+s);
            }
        }
    }

    void remove(String s){
        System.out.println(" Remove "+s);
        if (!implictitlyInstalled.contains(s)&&!explicitlyInstall.contains(s)){
            System.out.println(s+" is not installed");
            return;
        }
        if (de.containsKey(s)){
            if (de.get(s)>0){
                System.out.println(s+ "is still needed");
                return;
            }
        } else{
            System.out.println(" removing "+s);
            if (map.containsKey(s)){
                for (String dependency:map.get(s)){
                    de.put(dependency,de.get(dependency)-1);
                    if (de.get(dependency)==0&&!explicitlyInstall.contains(dependency)){
                        de.remove(dependency);
                        removeHelper(dependency);
                    }
                }
            }

            explicitlyInstall.remove(s);
            totalInstalled.remove(s);
        }
    }
    void removeHelper(String s){

        System.out.println(" removing "+s);
        if (map.containsKey(s)){
            for (String dependency:map.get(s)){
                de.put(dependency,de.get(dependency)-1);
                if (de.get(dependency)==0&&!explicitlyInstall.contains(dependency)){
                    de.remove(dependency);
                    removeHelper(dependency);
                }
            }
        }

        implictitlyInstalled.remove(s);
        totalInstalled.remove(s);

    }
    void list(){
        System.out.println("LIST");
        for (String s:totalInstalled){
            System.out.println(s);
        }
    }
}
