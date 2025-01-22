package com.abroad.baekjunghyun.service.video;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.baekjunghyun.model.User;
import com.abroad.baekjunghyun.model.qna.Board;
import com.abroad.baekjunghyun.model.qna.Reply;
import com.abroad.baekjunghyun.model.video.Video;
import com.abroad.baekjunghyun.model.video.VideoReply;
import com.abroad.baekjunghyun.repository.qna.QnaReplyRepository;
import com.abroad.baekjunghyun.repository.qna.QnaRepository;
import com.abroad.baekjunghyun.repository.video.VideoReplyRepository;
import com.abroad.baekjunghyun.repository.video.VideoRepository;

@Service
public class VideoReplyService {

	@Autowired
	VideoReplyRepository videoReplyRepository;
	@Autowired
	VideoRepository videoRepository; 

	@Transactional
	public VideoReply 댓글저장(User user, int videoId, VideoReply videoReply ){
		Video video = videoRepository.findById(videoId).
				orElseThrow(()->{
					return new IllegalArgumentException("댓글 쓰기 실패: 아이디를 찾을수 없습니다.");
				});
		
		 if (videoReply.getContent() == null || videoReply.getContent().trim().isEmpty()) {
		        throw new IllegalArgumentException("댓글 내용이 없습니다.");
		    }
		 
		videoReply.setUser(user);
		videoReply.setVideo(video);
		videoReplyRepository.save(videoReply);
		
		return videoReply;
	}
	
	@Transactional
	public void 댓글삭제(int videoReplyId) {
		videoReplyRepository.deleteById(videoReplyId);
	}
	
	@Transactional
	public VideoReply 댓글수정(int videoReplyId, VideoReply videoReply) {
		VideoReply findVideoReply =  videoReplyRepository.findById(videoReplyId)
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글 수정 실패: 아이디를 찾을수 없습니다.");
				});
		
		if (videoReply.getContent() == null || videoReply.getContent().trim().isEmpty()) {
	        throw new IllegalArgumentException("댓글 내용이 없습니다.");
	    }
		
		findVideoReply.setContent(videoReply.getContent());
		findVideoReply.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		return videoReply;
	}
	  
	@Transactional(readOnly = true)
	public Page<VideoReply>댓글목록(int videoId, Pageable pageable){
		return videoReplyRepository.findByVideoId(videoId, pageable);
	} 
}
