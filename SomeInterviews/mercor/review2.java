package SomeInterviews.mercor;

public class review2 {
    /*
     CSV 找问题
给一段 Python，代码大概这样：
def to_csv(table):
    output = ""
    for row in table:
        for cell in row:         ----》cell可能不一定是string，可能是int
            output += cell    ----》python的string是immutable，一直加会效率不高，应该先放在arry里最后再用join整合
            output += ","
        output += "\n"
    return output
spot issues with this code. 包括最后多出来的逗号、字符串拼接(immutable in python)、非字符串输入，以及内容本身带逗号/引号/换行怎么处理。


    def to_csv(table):
    lines = []
    for row in table:
        cells = []
        for cell in row:
            cells.append(escape_cell(cell))
        lines.append(",".join(cells))---->",".join就是把cells这个array的东西用“，”拼在一起，“，”不会再开头也不会再结尾
    return "\n".join(lines)


    def escape_cell(cell):
    s = str(cell)---》把int转成string
    # 如果内容里有双引号，CSV 规则里要变成两个双引号，这应该optional把
    if '"' in s:
        s = s.replace('"', '""')
    # 有逗号、双引号、换行时，整个字段要用双引号包起来，这应该optional把
    if "," in s or '"' in s or "\n" in s:
        s = '"' + s + '"'
    return s
     */
}
