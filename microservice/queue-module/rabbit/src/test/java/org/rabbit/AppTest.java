package org.rabbit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test() {
        int ret = 0;

        try {
            ret = 1;
            ret = 5 / 0;
            return ret;
        } catch (Exception e) {
            ret = 2;
        } finally {
            ret = 3;
            return ret;
        }

    }

}
