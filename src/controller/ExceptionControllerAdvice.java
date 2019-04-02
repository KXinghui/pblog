package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import exception.CustException;
import pojo.User;
import util.Page;
import util.PageResult;

@ControllerAdvice
public class ExceptionControllerAdvice {

	// @ExceptionHandler(Exception.class)
	// public String custHandle(HttpServletRequest request, Exception exception,
	// Model model) {
	// CustException custException = new CustException(request.getRemoteHost(),
	// exception.getMessage(),
	// request.getRequestURL().toString());
	// custException.setErrMsg("未知原因");
	// custException.initCause(exception);
	//
	// model.addAttribute("custException", custException);
	// return "fore/err";
	// }

	@ResponseBody
	@ExceptionHandler(value = { (IOException.class) })
	public String handle(HttpServletRequest request, IOException ioException) {
		return "上传失败:" + ioException;
	}

	@ExceptionHandler(CustException.class)
	public String custHandle(HttpServletRequest request, CustException custException, Model model) {
		CustException newCustException = new CustException(request.getRemoteHost(), custException.getMessage(),
				request.getRequestURL().toString());

		model.addAttribute("custException", newCustException);
		return "fore/err";
	}

}
