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
	
	//�Խ��� ��� �б�
	public List<BoardVO> boardListData(Map<?, ?> map){
		return mapper.boardListData(map);
	}
	
	//�Խ��� �� ������ �б�
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}

	// �Խ��� ��ȸ������,�󼼺���
	public BoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}

	// �Խ��� �߰�
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}

	// �Խ��� ����
	public void boardUpdate(Map<?, ?> map) {
		mapper.boardUpdate(map);
	}

	// �Խ��� ����
	public void boardDelete(int no) {
		mapper.boardDelete(no);
	}
	
	//  �˻�
	public List<BoardVO> boardList_search(Map<?, ?> map){
		return mapper.boardList_search(map);
	}
	
	// �˻� �� �������� �б�
	public int searchTotalPage() {
		return mapper.searchTotalPage();
	}
}