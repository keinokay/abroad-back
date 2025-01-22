package com.abroad.baekjunghyun.controller.api.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.baekjunghyun.config.auth.PrincipalDetail;
import com.abroad.baekjunghyun.dto.ResponseDto;
import com.abroad.baekjunghyun.model.qna.Board;
import com.abroad.baekjunghyun.model.video.Video;
import com.abroad.baekjunghyun.service.qna.QnaService;

@RestController
public class QnaApiController {
	@Autowired
	QnaService boardService;
	
	@PostMapping("/v1/qna")
	public ResponseDto<Board> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
		Board newBoard = boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Board>(HttpStatus.OK.value(), newBoard); 
	}

	@GetMapping("/v1/qna/{id}")
	public ResponseDto<Board> findById(@PathVariable int id) {
		Board board = boardService.글상세보기(id);
		return new ResponseDto<Board>(HttpStatus.OK.value(), board);
	}
	
	@GetMapping("/v1/qna")
	public ResponseDto<Page<Board>> AllBoard(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.글목록(pageable);	
		return new ResponseDto<Page<Board>>(HttpStatus.OK.value(), boards);
	}
	
	
	@PatchMapping("/v1/qna/{id}")
	public ResponseDto<Board> update(@PathVariable int id, @RequestBody Board board){
		Board newBoard = boardService.글수정하기(id, board);
		return new ResponseDto<Board>(HttpStatus.OK.value(), newBoard);
	}
	
	@DeleteMapping("/v1/qna/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id){
		System.out.println(id + "/////////////////////////////////////////");
		boardService.글삭제하기(id); 
		return new ResponseDto<Integer>(HttpStatus.OK.value(), id);
	}

}
