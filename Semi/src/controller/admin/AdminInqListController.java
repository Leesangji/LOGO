package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Inquiry;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;
import utils.Paging;



@WebServlet("admin/inquiry/list")
public class AdminInqListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InquiryService inquiryService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 페이지 얻어오기
		int curPage = inquiryService.getCurPage(req);

		// 전체 페이지수 얻어오기
		int totalCount = inquiryService.getTotalCount();

		// 페이징 결과 담기 
		Paging paging = new Paging(totalCount, curPage,10); 

		// 게시물 리스트 조회하기
		List<Inquiry> list = inquiryService.getPagingList(paging);

		// 요청에 담기 
		req.setAttribute("inquirylist", list);

		// 페이징 정보 요청에 담기
		req.setAttribute("paging", paging);

	
		req.getRequestDispatcher("").forward(req, resp);
		
		

	}
}
