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

import com.danim.model.BoardDAO;
import com.danim.model.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;

	// ����Ʈ
	@RequestMapping("list")
	public String board_list(Model model, String page, HttpSession session, HttpServletRequest request) {
		try {
			if (page == null) {
				page = "1";
			}
			int currpage = Integer.parseInt(page); // ���� ������
			int totalpage = dao.boardTotalPage(); // �� ������
			int rowSize = 8; // �ѹ��� ��µ� �Խñ�
			int start = (rowSize * currpage) - (rowSize - 1);
			int end = (rowSize * currpage);
			int block = 5; // ������ ���
			int startpage = ((currpage - 1) / block * block) + 1;
			int endpage = ((currpage - 1) / block * block) + block;
			if (endpage > totalpage) {
				endpage = totalpage;
			}

			// �ؽ��ʿ� ���� / �� ���� ���
			// ------------------------------------------------------
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);

			List<BoardVO> list = dao.boardListData(map); // DAO�� �޼ҵ� ���ϰ��� �޴�
															// List ����

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

	// �󼼺���
	// ====================================================================================================================================================
	@RequestMapping("detail")
	public String board_detail(String no, Model model) {
		BoardVO vo = dao.boardDetailData(Integer.parseInt(no));
		model.addAttribute("vo", vo);
		return "board/detail";
	}

	// �ۼ� �������� ���
	// ===========================================================================================================================================================
	@RequestMapping("insert")
	public String board_insert(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		// String id=(String)session.getAttribute("id");
		// System.out.println("id : " + id);
		return "board/insert";
	}

	// �۾��� ����
	// ===========================================================================================================================================================
	@RequestMapping("insert_ok")
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
		return "redirect:list";
	}

	// ����
	// ===========================================================================================================================================================
	@RequestMapping("delete")
	public String boardDelete(String no) {

		dao.boardDelete(Integer.parseInt(no));

		return "redirect:../board/list";
	}

	// ���� �� ���� ��������
	// ==============================================================================================================================================
	@RequestMapping("update")
	public String boardUpdate(String no, Model model) {

		BoardVO board_vo = dao.boardDetailData(Integer.parseInt(no));

		model.addAttribute("board_vo", board_vo);

		return "board/update";
	}

	// ���� ����
	// =====================================================================================================================================================
	@RequestMapping("update_ok")
	public String boardUpdate_ok(String review_num, String review_content, String review_title) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("review_num", review_num);
		map.put("review_content", review_content);
		map.put("review_title", review_title);

		dao.boardUpdate(map);

		System.out.println("���� OK");

		return "redirect:../board/detail?review_num=" + review_num;
	}

	// �˻�
	// ==================================================================================================================================================
	@RequestMapping("search")
	public String boardlist_search(Model model, String page, HttpSession session, HttpServletRequest request,
			Comparable<Integer> search_keyword) {
		try {
			if (page == null) {
				page = "1";
			}
			int currpage = Integer.parseInt(page); // ���� ������
			int totalpage = dao.boardTotalPage(); // �� ������
			int rowSize = 8; // �ѹ��� ��µ� �Խñ�
			int start = (rowSize * currpage) - (rowSize - 1);
			int end = (rowSize * currpage);
			int block = 5; // ������ ���
			int startpage = ((currpage - 1) / block * block) + 1;
			int endpage = ((currpage - 1) / block * block) + block;
			if (endpage > totalpage) {
				endpage = totalpage;
			}

			// �ؽ��ʿ� ���� / �� ���� ���
			// ------------------------------------------------------
			Map<String, Comparable<Integer>> map = new HashMap<String, Comparable<Integer>>();
			map.put("start", start);
			map.put("end", end);
			map.put("search_keyword", search_keyword);

			List<BoardVO> list = dao.boardList_search(map); // DAO�� �޼ҵ� ���ϰ��� �޴�
															// List ����

			// �������� ���� �Ķ���͵�
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

		return "board/search";
	}
}