package SomeInterviews.Confluent;

import java.util.*;

public class VariadicFunctionMatching {
    /*
    Design a function library that allows registering functions with specific argument types and retrieving matching functions based on input arguments.

Implement the FunctionLibrary class:

FunctionLibrary(List<List<String>> functions): Registers a list of functions by its name, a list of argument types, and a flag indicating whether the function is variadic. If a function is variadic (isVariadic = true), the last argument type can appear one or more times.
findMatches(List<String> arguments): Given a list of input argument types, returns all function names that match the input. A function matches if the input arguments correspond to the function's argument types, considering the variadic nature of the last argument if applicable.
Constraints:

Function names consist of uppercase and lowercase English letters.
Argument types are strings containing uppercase and lowercase English letters.
The number of functions registered does not exceed
10
4
10
4
 .
The number of arguments in findMatches does not exceed
10
3
10
3
 .
Example 1:

Input:
["FunctionLibrary", "findMatches", "findMatches", "findMatches", "findMatches", "findMatches"]

[
 [
  ["FuncA", "false", "String", "Integer", "Integer"],
  ["FuncB", "true", "String", "Integer"],
  ["FuncC", "true", "Integer"],
  ["FuncD", "true", "Integer", "Integer"],
  ["FuncE", "false", "Integer", "Integer", "Integer"],
  ["FuncF", "false", "String"],
  ["FuncG", "false", "Integer"]
 ],
 ["String"],
 ["Integer"],
 ["Integer", "Integer", "Integer", "Integer"],
 ["Integer", "Integer", "Integer"],
 ["String", "Integer", "Integer", "Integer"],
 ["String", "Integer", "Integer"],
 ["String", "String", "Integer", "Integer"]
]

Output:
[
 null,
 ["FuncF"],
 ["FuncC", "FuncG"],
 ["FuncC", "FuncD"],
 ["FuncC", "FuncD", "FuncE"],
 ["FuncB"],
 ["FuncA", "FuncB"],
 []
]

Explanation:

FunctionLibrary library = new FunctionLibrary(functions); // Register a list of functions.
findMatches(["String"]) // Return [FuncF] because only FuncF matches a single "String" argument.
findMatches(["Integer"]) // Return [FuncC, FuncG] as both functions can accept a single "Integer".
findMatches(["Integer", "Integer", "Integer", "Integer"]) // Returns [FuncC, FuncD] as FuncC and FuncD are variadic and match the exact input type sequence.
findMatches(["Integer", "Integer", "Integer"]) // Returns [FuncC, FuncD, FuncE]. FuncC and FuncD match with variadic arguments, FuncE matches a fixed signature.
findMatches(["String", "Integer", "Integer", "Integer"]) // Returns [FuncB] because FuncB is variadic and matches the "String" followed by three "Integer" arguments.
findMatches(["String", "Integer", "Integer"]) // Returns [FuncA, FuncB] as FuncA matches the fixed sequence and FuncB matches variadically.
findMatches(["String", "String", "Integer", "Integer"]) // Returns [] because no function matches the input sequence.

Hint 1
Consider parsing the function registration into a structured object that separates fixed arguments from the variadic argument type.

Hint 2
For variadic functions, ensure the input length is sufficient and strictly check that the trailing elements all match the variadic type.
     */
//感觉没有好方法，就是得一个一个function去查是不是match
    Map<String,List<String>> map=new HashMap<>();
    Set<String> set=new HashSet<>();
    public FunctionLibrary(List<List<String>> functions) {
        for(List<String> func:functions){
            String name=func.get(0);
            if(func.get(1).equals("true")){
                set.add(name);
            }
            map.putIfAbsent(name,new ArrayList<>());
            map.get(name).addAll(func.subList(2,func.size()));
        }
    }

    public List<String> findMatches(List<String> argumentTypes) {
        // TODO: Implement findMatches logic
        List<String> rs=new ArrayList<>();
        for(String key:map.keySet()){
            if(match(argumentTypes,key,set)){
                rs.add(key);
            }
        }
        return rs;
    }
    //必须满足，
    // 如果param长度都一样，则每一个都要相同，不用考虑varidic
    // 如果要比较的existing arguments比当前的input长，则肯定不match
    //如果input的argument长，则必须要是varidc且最后一个existing的param必须和对应位置的input的argument之后的所有param都相同才行
    boolean match(List<String> input,String key,Set<String> set){
        boolean isVaridic=set.contains(key)?true:false;
        List<String> existing=map.get(key);
        // ---------- 非 variadic ----------
        if (!isVaridic) {
            // 长度必须完全一样
            if (input.size() != existing.size()) {
                return false;
            }
            // 每个位置类型都必须一样
            for (int i = 0; i < existing.size(); i++) {
                if (!input.get(i).equals(existing.get(i))) {
                    return false;
                }
            }
            return true;
        }
        // ---------- variadic ----------
        //existing长的话就肯定不match
        if (input.size() < existing.size()) {
            return false;
        }
        // 最后一个之前的参数必须完全 match
        for (int i = 0; i < existing.size() - 1; i++) {
            if (!input.get(i).equals(existing.get(i))) {
                return false;
            }
        }
        // 最后一个参数可以重复多次
        String repeatType = existing.get(existing.size() - 1);
        for (int i = existing.size() - 1; i < input.size(); i++) {
            if (!input.get(i).equals(repeatType)) {
                return false;
            }
        }
        return true;
    }
}
