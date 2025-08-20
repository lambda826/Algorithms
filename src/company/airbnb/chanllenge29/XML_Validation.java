package company.airbnb.chanllenge29;

/*

题目是做XML validator
就‍‌‍‌‌‍‍‍‍‌‌‌‍‍‌‍‌‌‌是给你XML string, 让你判断是否符合规范，返回true or false

例子：
    <a>test</a>   返回true
    <a><b>text</b></a>  返回true
    <a><b>text</a></b>  返回 closing tag 不符
    <a><b<>text</b></b>  返回parsing error
    还有些test case记不清了，最后有2个test case 没过， 估计是tag格式验证或者特殊字符处理，感觉用regular expression好做，但是试了10分钟没弄出来放弃了。

*/

public class XML_Validation {
}
