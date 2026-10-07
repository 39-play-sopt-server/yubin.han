package org.sopt.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.domain.Category;
import org.sopt.domain.Post;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostRepositoryConcurrencyTest {

    @Test
    @DisplayName("여러 스레드가 동시에 게시글을 저장해도 id가 중복되지 않는다")
    void saveConcurrently() throws InterruptedException {
        PostRepository postRepository = new PostRepository();
        int threadCount = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    Post post = new Post(postRepository.generateId(), Category.FREE, "제목", "내용", "작성자");
                    postRepository.save(post);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executor.shutdown();

        assertEquals(threadCount, postRepository.findAll().size());
    }
}
