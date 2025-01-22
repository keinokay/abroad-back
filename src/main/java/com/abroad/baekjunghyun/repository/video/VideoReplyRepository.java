package com.abroad.baekjunghyun.repository.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abroad.baekjunghyun.model.qna.Reply;
import com.abroad.baekjunghyun.model.video.VideoReply;

public interface VideoReplyRepository extends JpaRepository<VideoReply, Integer>{
	public Page<VideoReply> findByVideoId(int videoId, Pageable pageable);

}
