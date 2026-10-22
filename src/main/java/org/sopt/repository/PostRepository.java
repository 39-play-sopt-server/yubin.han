package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByIdAsc();

    @Modifying(clearAutomatically = true)
    @Query("delete from Post p where p.member.id = :memberId")
    void deleteAllByMemberId(@Param("memberId") Long memberId);
}
