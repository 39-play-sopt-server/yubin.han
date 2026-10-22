package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = "member")
    Page<Post> findAll(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("delete from Post p where p.member.id = :memberId")
    void deleteAllByMemberId(@Param("memberId") Long memberId);
}
