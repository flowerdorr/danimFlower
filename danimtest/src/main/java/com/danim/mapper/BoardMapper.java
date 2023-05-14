package com.danim.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.danim.dao.BoardVO;

public interface BoardMapper {
	
	@Select("SELECT boardnumber,name,subject,pwd,content,hit,regdate FROM project1_board")
	public List<BoardVO> boardListData(Map<?, ?> map);
	
	// 게시판1 페이지당 8개로 잘랐을 때 총페이지 수
	@Select("SELECT CEIL(COUNT(*)/8.0) FROM project1_board")
	public int boardTotalPage();
	
	// 게시판1 글번호 자동증가, 글쓰기 데이터추가
	@SelectKey(keyProperty="boardnumber",resultType=int.class,before=true,
			statement="SELECT NVL(MAX(boardnumber)+1,1) as boardnumber FROM project1_board")
	@Insert("INSERT INTO project1_board(boardnumber,name,subject,pwd,content) "
			+"VALUES(#{boardnumber},#{name},#{subject},#{pwd},#{content})")
	public void boardInsert(BoardVO vo);
	
	// 게시판1 조회수증가
	@Update("UPDATE project1_board SET "
			+"hit=hit+1 "
			+"WHERE boardnumber=#{boardnumber}")
	public void boardHitIncrement(int boardnumber);
	
	// 게시판1 상세보기
	@Select("SELECT * FROM project1_board WHERE boardnumber=#{boardnumber}")
	public BoardVO boardDetailData(int boardnumber);
	
	// 게시판1 수정
	@Update("UPDATE project1_board SET content=#{content}, subject=#{subject} WHERE boardnumber=#{boardnumber} AND pwd=#{pwd}")
	public void boardUpdate(Map<?, ?> map);
	
	// 게시판1 삭제 
	@Delete("DELETE FROM project1_board "
			+"WHERE boardnumber=#{boardnumber}")
	public void boardDelete(int boardnumber);
	
	// 검색 
	@Select("SELECT boardnumber, name, subject, pwd, content, hit, regdate "
			+ "FROM project1_board "
			+ "WHERE subject LIKE '%${search_keyword}%' OR content LIKE '%${search_keyword}%' AND boardnumber BETWEEN #{start} AND #{end} ORDER BY boardnumber ")
	public List<BoardVO> boardList_search(Map<?, ?> map);
	
	// 검색 총 페이지 출력
	@Select("SELECT CEIL(COUNT(*) / 10.0) FROM project1_board WHERE #{finding} LIKE '%#{voca}%' AND root = 0")
	public int searchTotalPage(); 
	
}