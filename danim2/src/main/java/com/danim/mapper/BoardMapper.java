package com.danim.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.danim.model.BoardVO;

public interface BoardMapper {
		@Select("SELECT review_num,writer,review_title,review_review_content,view_count,insert_date FROM review2")
		public List<BoardVO> boardListData(Map<?, ?> map);
		
		// �Խ���1 �������� 12���� �߶��� �� �������� ��
		@Select("SELECT CEIL(COUNT(*)/12.0) FROM review2")
		public int boardTotalPage();
		
		// �Խ���1 �۹�ȣ �ڵ�����, �۾��� �������߰�
		@SelectKey(keyProperty="review_num",resultType=int.class,before=true,
				statement="SELECT NVL(MAX(review_num)+1,1) as review_num FROM review2")
		@Insert("INSERT INTO review2(review_num,writer,review_title,review_content) "
				+"VALUES(#{review_num},#{writer},#{review_title},#{review_content})")
		public void boardInsert(BoardVO vo);
		
		// �Խ���1 ��ȸ������
		@Update("UPDATE review2 SET "
				+"view_count=view_count+1 "
				+"WHERE view_count=#{view_count}")
		public void boardHitIncrement(int view_count);
		
		// �Խ���1 �󼼺���
		@Select("SELECT * FROM review2 WHERE review_num=#{review_num}")
		public BoardVO boardDetailData(int review_num);
		
		// �Խ���1 ����
		@Update("UPDATE review2 SET review_content=#{review_content}, review_title=#{review_title} WHERE boardnumber=#{boardnumber} AND pwd=#{pwd}")
		public void boardUpdate(Map<?, ?> map);
		
		// �Խ���1 ���� 
		@Delete("DELETE FROM review2 "
				+"WHERE boardnumber=#{boardnumber}")
		public void boardDelete(int boardnumber);
		
		// �˻� 
		@Select("SELECT boardnumber, writer, review_title, pwd, review_content, view_count, insert_date "
				+ "FROM review2 "
				+ "WHERE review_title LIKE '%${search_keyword}%' OR review_content LIKE '%${search_keyword}%' AND boardnumber BETWEEN #{start} AND #{end} ORDER BY boardnumber ")
		public List<BoardVO> boardList_search(Map<?, ?> map);
		
		// �˻� �� ������ ���
		@Select("SELECT CEIL(COUNT(*) / 10.0) FROM review2 WHERE #{finding} LIKE '%#{voca}%' AND root = 0")
		public int searchTotalPage(); 
		
	}


