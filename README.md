# SoftwareTest
第一次将自己的代码放到github可能格式有点错误，请谅解。
[测试用例]：
测试单个基础功能选项是否正确输出在result.txt
wc.exe -c file.c
wc.exe -w file.c
wc.exe -l file.c
wc.exe -a file.c
测试绝对路径下是否正确
-wc.exe -c [测试环境下的绝对路径]
测试多个选项能否共享一个参数
wc.exe -c -w -l -a file.c
测试同时读取不同的文件
wc.exe -c file1.c -w file2.c -l file3.c
测试指定输出文件
wc.exe -c -w -l -a file.c -o output1.txt
测试递归处理目录下符合条件的文件
wc.exe -c -w -l -a -s *.c -o output2.txt
测试停用词表
wc.exe -w -file.c -e stoplist.txt
执行所有选项命令
wc.exe -c -w -l -a -s *.c -o output3.txt -e stoplist.txt
[统计标准]：
换行计1个字符；
注释包括单行注释和多行注释