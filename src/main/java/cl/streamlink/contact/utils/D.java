package cl.streamlink.contact.utils;

public class D extends C implements B,A {


    @Override
    public void draw() {

    }
}
interface A {
    default void draw() {
        System.err.println("A");
    }
}

interface B {
    default void draw() {
        System.err.println("A");
    }
}

class C implements B {

}