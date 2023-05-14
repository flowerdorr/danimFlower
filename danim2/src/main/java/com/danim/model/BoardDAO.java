package com.danim.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danim.mapper.BoardMapper;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper mapper;
	
	//게시판 목록 읽기
	public List<BoardVO> boardListData(Map<?, ?> map){
		return mapper.boardListData(map);
	}
	
	//게시판 총 페이지 읽기
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}

	// 게시판 조회수증가,상세보기
	public BoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

	// 게시판 추가
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}

	// 게시판 수정
	public void boardUpdate(Map<?, ?> map) {
		mapper.boardUpdate(map);
	}

	// 게시판 삭제
	public void boardDelete(int no) {
		mapper.boardDelete(no);
	}
	
	//  검색
	public List<BoardVO> boardList_search(Map<?, ?> map){
		return mapper.boardList_search(map);
	}
	
	// 검색 후 총페이지 읽기
	public int searchTotalPage() {
		return mapper.searchTotalPage();
	}
}
