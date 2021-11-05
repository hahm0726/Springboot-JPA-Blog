package com.blog.hahmlog.like.repository;

import com.blog.hahmlog.like.model.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike,Integer> {
}
