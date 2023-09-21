package com.fssa.dynamicdesign.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fssa.dynamicdesign.model.Architect;
import com.fssa.dynamicdesign.service.ArchitectService;
import com.fssa.dynamicdesign.service.exception.ServiceException;

@WebServlet("/architect_update")
public class ArchitectUpdateServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();

		// Retrieve data from the form fields
		String profileImage = request.getParameter("profileImage");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phoneNumber = request.getParameter("number");
		String address = request.getParameter("address");
		String coverPhoto = request.getParameter("coverPhoto");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String education = request.getParameter("education");
		int experience = Integer.parseInt(request.getParameter("experience"));
		String degreeCertificatePhoto = request.getParameter("degreeCertificatePhoto");
		String nataCertificatePhoto = request.getParameter("nataCertificatePhoto");

		// Create an Architect object with the updated data
		Architect architect = new Architect(profileImage, name, gender, phoneNumber, address, coverPhoto, email,
				password, education, experience, degreeCertificatePhoto, nataCertificatePhoto);

		// Update the architect's profile in the database
		ArchitectService architectService = new ArchitectService();
		try {
			architectService.updateArchitect(architect, email);
			out.println("<h1>Successfully Updated the Architect</h1>");
       	 	response.sendRedirect("architect_home.jsp");

		} catch (ServiceException e) {
			String msg = e.getMessage();
			String[] error = msg.split(":");
			
			request.setAttribute("profileImage",profileImage);
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("number", phoneNumber);
			request.setAttribute("address", address);
			request.setAttribute("coverPhoto", coverPhoto);
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.setAttribute("education", education);
			request.setAttribute("experience", experience);
			request.setAttribute("degreeCertificatePhoto", degreeCertificatePhoto);
			request.setAttribute("nataCertificatePhoto", nataCertificatePhoto);
			
			
			RequestDispatcher patcher = request.getRequestDispatcher("architect_update.jsp?error="+error[1]);
			patcher.forward(request, response);
		//	response.sendRedirect("architect_register.jsp?error="+error[1]);
			out.print(e.getMessage());			// Handle the service exception, e.g., redirect to an error page.
		}
	}
}
