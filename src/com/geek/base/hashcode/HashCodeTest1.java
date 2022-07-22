package com.geek.base.hashcode;

/**
 * @author G.E.E.K.
 * @create 2022-07-15 23:45
 *
 * 只重写 hashcode:
 * 这两个不同的对象 hashcode 相同，但是 equals 不同。
 */
public class HashCodeTest1 {
    private final int number;
    public HashCodeTest1(int number){
        this.number =number;
    }

    @Override
    public int hashCode() {
        return number % 8;
    }

    public static void main(String[] args) {
        HashCodeTest1 a = new HashCodeTest1(1);
        HashCodeTest1 b = new HashCodeTest1(1);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a.equals(b));
    }
}
