package com.geek.base.hashcode;

/**
 * @author G.E.E.K.
 * @create 2022-07-15 23:22
 * 1.Integer、Byte、Short、Character都是转换为int类型作为hashcode
 * 2.Long 取高32位和低32位与值转成int类型作为hashcode
 * 3.Boolean true值hashcode为1231，false值hashcode为1237
 * 4.
 */
public class HashMapTest {


    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a.hashCode()+"  "+b.hashCode());
        System.out.println(a.equals(b));
        System.out.println();
        long number = 1;
        Long a1 =(long)1;
        Long b1 = number<<32;
        System.out.println(a1+" "+b1);
        System.out.println(a1.hashCode()+" "+b1.hashCode() );
        System.out.println(a1.equals(b1));
    }
}
