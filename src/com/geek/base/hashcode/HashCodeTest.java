package com.geek.base.hashcode;

/**
 * @author G.E.E.K.
 * @create 2022-07-15 23:44
 *
 * 不重写 hashcode 和 equals：
 * 两个不同的对象hashcode(a)和hashcode(b)肯定不同,a 对象和 b 对象调用 equal 函数肯定返回 false.
 *
 */
public class HashCodeTest {
    private final int number;
    public HashCodeTest(int number){
        this.number =number;
    }



    public static void main(String[] args) {
        HashCodeTest a = new HashCodeTest(1);
        HashCodeTest b = new HashCodeTest(1);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.equals(b));
    }
}