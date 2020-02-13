package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.UserStore;

public final class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static List<User> users = new ArrayList<>();
	private UserStore user = new UserStore();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (users.isEmpty()) {
			users = user.getAllUsers();
		}
		request.setAttribute("users", users);
		request.getRequestDispatcher("/logInPage.ftl").forward(request, response);
		
		

		/*
		 * // mach was User userToAdd = new User("Manfred", "Mustermann"); try
		 * (UserStore userStore = new UserStore()) { userStore.addUser(userToAdd); //
		 * userStore.somethingElse(); userStore.complete(); }
		 */
		// mach noch mehr

	}
}
