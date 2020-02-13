package de.unidue.inf.is;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.UserStore;
import de.unidue.inf.is.utils.ProjectDao;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("action").equals("MyProfile")) {
			String LoggedNutzerEmail = request.getParameter("email");
            String LoggedNutzerName = request.getParameter("name");
			UserStore store = new UserStore();
			User user =  new User();
			user = store.getUserDetails(LoggedNutzerEmail, LoggedNutzerName);
			
			
			request.setAttribute("email", user.getEmail());
			request.setAttribute("name", user.getName());
			request.setAttribute("noOwned",user.getNoOfOwnedProjects());
			request.setAttribute("noDonated", user.getNoOfDonatedProjects());
			request.setAttribute("Eprojects", user.getOwnedProjects());
			request.setAttribute("Dprojects", user.getDonatedProjects());
			
			request.getRequestDispatcher("/profile.ftl").forward(request, response);


		} else if(request.getParameter("action").equals("profile")) {
			String NutzerEmail = request.getParameter("erstellerEmail");
            String NutzerName = request.getParameter("ersteller");
			UserStore store = new UserStore();
			User user =  new User();
			user = store.getUserDetails(NutzerEmail, NutzerName);
			
			
			request.setAttribute("email", user.getEmail());
			request.setAttribute("name", user.getName());
			request.setAttribute("noOwned",user.getNoOfOwnedProjects());
			request.setAttribute("noDonated", user.getNoOfDonatedProjects());
			request.setAttribute("Eprojects", user.getOwnedProjects());
			request.setAttribute("Dprojects", user.getDonatedProjects());
			
			request.getRequestDispatcher("/profile.ftl").forward(request, response);
		}
			
	}
/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
*/
}
