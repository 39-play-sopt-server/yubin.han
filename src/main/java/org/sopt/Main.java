package org.sopt;

public class Main {

    public static void main(String[] args) {
        PostController controller = new PostController(new PostView());
        controller.run();
    }
}