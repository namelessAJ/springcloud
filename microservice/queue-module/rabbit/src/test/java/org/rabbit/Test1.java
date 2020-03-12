package org.rabbit;

/**
 * Unit test for simple App.
 */
public class Test1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(demo1(3));
        long end = System.currentTimeMillis();
        System.out.println(" 运行时间： " + (end - start));

    }


    public static int demo1(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should  > 1");
        }
        if (n == 1 || n == 2) {
            return n;
        } else {
            return demo1(n - 2) + demo1(n - 1);
        }
    }

}
