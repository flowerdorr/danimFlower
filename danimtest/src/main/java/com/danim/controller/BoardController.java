package com.danim.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.danim.dao.BoardDAO;
import com.danim.dao.BoardVO;

@Controller
public class BoardController {
	@Autowired(required=false)
	private BoardDAO dao;

	// 리스트
	// ==================================================================================================================================================
	@RequestMapping("list")
	public String board_list(Model model, String page, HttpSession session, HttpServletRequest request) {
		try {
			if (page == null) {
				page = "1";
			}
			int currpage = Integer.parseInt(page); // 현재 페이지
			int totalpage = dao.boardTotalPage(); // 총 페이지
			int rowSize = 8; // 한번에 출력될 게시글
			int start = (rowSize * currpage) - (rowSize - 1);
			int end = (rowSize * currpage);
			int block = 5; // 페이지 블록
			int startpage = ((currpage - 1) / block * block) + 1;
			int endpage = ((currpage - 1) / block * block) + block;
			if (endpage > totalpage) {
				endpage = totalpage;
			}

			// 해쉬맵에 시작 / 끝 변수 담기
			// ------------------------------------------------------
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);

			List<BoardVO> list = dao.boardListData(map); // DAO의 메소드 리턴값을 받는
															// List 변수

			model.addAttribute("list", list);
			model.addAttribute("block", block);
			model.addAttribute("currpage", currpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("startpage", startpage);
			model.addAttribute("endpage", endpage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "board/list";
	}

	// 상세보기
	// ====================================================================================================================================================
	@RequestMapping("detail")
	public String board_detail(String no, Model model) {
		BoardVO vo = dao.boardDetailData(Integer.parseInt(no));
		model.addAttribute("vo", vo);
		return "board/detail";
	}

	// 작성 페이지만 출력
	// ===========================================================================================================================================================
	@RequestMapping("insert.do")
	public String board_insert(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		// String id=(String)session.getAttribute("id");
		// System.out.println("id : " + id);
		return "board/insert";
	}

	// 글쓰기 수행
	// ===========================================================================================================================================================
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(@ModelAttribute("vo") BoardVO vo, HttpSession session, HttpServletRequest request,
			MultipartFile file) {
		try {
			session = request.getSession();
			String id = (String) session.getAttribute("id");
			// System.out.println("id : " + id);
			// vo.setId(id);

			dao.boardInsert(vo);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "redirect:../list.do";
	}

	// 삭제
	// ===========================================================================================================================================================
	@RequestMapping("delete.do")
	public String boardDelete(String no) {

		dao.boardDelete(Integer.parseInt(no));

		return "redirect:../list.do";
	}

	// 수정 전 내용 가져오기
	// ==============================================================================================================================================
	@RequestMapping("update.do")
	public String boardUpdate(String no, Model model) {

		BoardVO board_vo = dao.boardDetailData(Integer.parseInt(no));

		model.addAttribute("board_vo", board_vo);

		return "update";
	}

	// 수정 수행
	// =====================================================================================================================================================
	@RequestMapping("update_ok.do")
	public String boardUpdate_ok(String boardnumber, String content, String subject) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("boardnumber", boardnumber);
		map.put("content", content);
		map.put("subject", subject);

		dao.boardUpdate(map);

		System.out.println("수정 OK");

		return "redirect:../detail.do?boardnumber=" + boardnumber;
	}

	// 검색
	// ==================================================================================================================================================
	@RequestMapping("search.do")
	public String boardlist_search(Model model, String page, HttpSession session, HttpServletRequest request,
			Comparable<Integer> search_keyword) {
		try {
			if (page == null) {
				page = "1";
			}
			int currpage = Integer.parseInt(page); // 현재 페이지
			int totalpage = dao.boardTotalPage(); // 총 페이지
			int rowSize = 8; // 한번에 출력될 게시글
			int start = (rowSize * currpage) - (rowSize - 1);
			int end = (rowSize * currpage);
			int block = 5; // 페이지 블록
			int startpage = ((currpage - 1) / block * block) + 1;
			int endpage = ((currpage - 1) / block * block) + block;
			if (endpage > totalpage) {
				endpage = totalpage;
			}

			// 해쉬맵에 시작 / 끝 변수 담기
			// ------------------------------------------------------
			Map<String, Comparable<Integer>> map = new HashMap<String, Comparable<Integer>>();
			map.put("start", start);
			map.put("end", end);
			map.put("search_keyword", search_keyword);

			List<BoardVO> list = dao.boardList_search(map); // DAO의 메소드 리턴값을 받는
															// List 변수

			// 페이지로 보낼 파라미터들
			// -----------------------------------------------------------
			model.addAttribute("list", list);
			model.addAttribute("block", block);
			model.addAttribute("currpage", currpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("startpage", startpage);
			model.addAttribute("endpage", endpage);
			model.addAttribute("search_keyword", search_keyword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "search";
	}
}