package com.fssa.dynamicdesign.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fssa.dynamicdesign.model.User;
import com.fssa.dynamicdesign.service.UserService;
import com.fssa.dynamicdesign.service.exception.ServiceException;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			String email = request.getParameter("email");
			String userName = request.getParameter("name");
			String phoneNumber = request.getParameter("phoneNumber");
			String type = request.getParameter("type");
			PrintWriter out = response.getWriter();

			out.println("Update User");

			UserService userService = new UserService();
			// check the userID , give valid details
			User user1 = new User();
			user1.setEmail(email);
			user1.setUsername(userName);
			user1.setPhonenumber(phoneNumber);
			user1.setType(type);
			try {
				userService.updateUser(user1, email);

				User user = userService.getUserByEmail(email);
				session.setAttribute("user", user);
				// out.println("Successfully Updated the user");
				response.sendRedirect("user_profile.jsp?email=" + email);

			} catch (ServiceException e) {
				String msg = e.getMessage();
				String[] error = msg.split(":");
				response.sendRedirect("user_update.jsp?error=" + error[error.length - 1] + "&email=" + email);
				out.print(e.getMessage());

			}

		} else {
			System.out.println("session invalid in the user update page you wants to login again");
			response.sendRedirect("user_login.jsp");
		}
	}

}