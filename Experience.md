##大数操作
* 在java.math库中
* BigInteger对象，有许多方法，加减乘除，取余数等
* BigDecimal对象

##Collections
* Collections.sort()不能接收set和map类型，必须加入Comparator<Type>，并实现其compareTo()方法
* set的遍历可以通过Iteration<Type>或者foreach进行遍历
* Map的遍历：1）Set<Map.Entry<K,V>>:entrySet()方法，再使用foreach遍历；2）对Map.Entry<K,V>对象取Iteration
* Map的其他方法：keySet(),values()

##ASCII码
* @：64
* A：65
* Z：90
* a：97
* z：122
* {：123

##Character
* isLetterOrDigit()
* toLowerCase()
* getNumericValue()
* isWhitespace()

##进制转换
* 十进制转换成N进制
  * Integer.toBinaryString()
  * Integer.toOctalString()
  * Integer.toHexString()
  * Integer.toString(int, jinzhi)：不适合负数
* N进制转化成十进制
  * Integer.parseInt(String s,int radix)
  * Integer.valueOf(String s,int radix)
