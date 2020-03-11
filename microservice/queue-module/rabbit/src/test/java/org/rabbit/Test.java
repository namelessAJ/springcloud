package org.rabbit;

/**
 * Unit test for simple App.
 */
public class Test {
    public static void main(String[] args) {
        new TestB(3);
    }
}

class TestA {
    private static int a = 0;

    static {
        System.out.println("父类静态代码块:a=" + a);
    }

    {
        System.out.println("父类代码块:a=" + this.a);
    }

    public TestA(int a) {
        this.a = a;
        System.out.println("父类构造函数:a=" + this.a);
    }
}

class TestB extends TestA {
    private static int a = 1;

    {
        System.out.println("子类代码块:a=" + this.a);
    }

    static {
        System.out.println("子类静态代码块:a=" + a);
    }

    public TestB(int a) {
        super(a);
        this.a = a;
        System.out.println("子类构造函数:a=" + this.a);
    }
}