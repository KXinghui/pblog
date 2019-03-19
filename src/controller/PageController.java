package controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import exception.CustException;

@Controller
public class PageController {

	@RequestMapping("{redirect}Page")
	public String foreRedirect(@PathVariable String redirect, HttpSession session) {
		String contextPath = session.getServletContext().getContextPath();
		if (null == session.getAttribute("contextPath")) {
			session.setAttribute("contextPath", contextPath);
		}

		String[] auth = { "register", "login", "editUserInfo" };
		if (Arrays.asList(auth).contains(redirect)) {
			// model.addAttribute("user", new User());
			return String.format("fore/%s", redirect);
		}
		return redirect;
	}

	@RequestMapping("{redirect}AdminPage")
	public String adminRedirect(@PathVariable String redirect) {
		return String.format("admin/%s", redirect);
	}

	@RequestMapping(value = { "404", "400", "500" })
	public String errRedirect(HttpServletRequest request, Model model) {
		CustException custException = new CustException(request.getRemoteHost(), "未知原因",
				request.getRequestURL().toString());
		model.addAttribute("custException", custException);
		return "fore/err";
	}

}
