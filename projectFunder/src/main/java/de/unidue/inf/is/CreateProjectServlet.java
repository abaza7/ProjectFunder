package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.utils.ProjectDao;

/**
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProjectServlet")
public class CreateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao p = new ProjectDao();
	private Project pr;
	private int v;
	private int k;
    private String email; 
    private String name;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("action").equals("userEmail")) {

			ArrayList<Project> Vorgaenger = new ArrayList<>();
			email = request.getParameter("email");
			name = request.getParameter("name");
			
			
			Vorgaenger = ProjectDao.getVorgaenger(email);
			request.setAttribute("Vorgaenger", Vorgaenger);
			request.setAttribute("email", request.getParameter("email"));

			try {
				request.getRequestDispatcher("NewProject.ftl").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action").equals("CreateProject")) {
			String titel = (String) request.getParameter("titel"); // must be refined!!
			String finanzierungslimit = (String) request.getParameter("Finanzierungslimit");
			String Kategorie = (String) request.getParameter("Kategorie");
			String beschreibung = (String) request.getParameter("beschreibung");
			String ersteller = (String) request.getParameter("ersteller");
			String vor = (String) request.getParameter("vorgeanger");

			int vorgaegnger = Integer.parseInt(vor); 
			
			switch (Kategorie) {
			case "Health":
				k = 1;
				break;
			case "Art":
				k = 2;
				break;
			case "Education":
				k = 3;
				break;
			case "Tech":
				k = 4;
				break;

			}

			double f = Double.parseDouble(finanzierungslimit);
			pr = new Project(titel, beschreibung, f, k, vorgaegnger, ersteller);
			int row = p.CreateProject(pr.getTitle(), pr.getBeschreibung(), pr.getFinanzierungslimit(), k,
					pr.getVorgaenger(), pr.getErsteller());
			try {
				if (row == 0) {
					request.setAttribute("name", name);
					request.setAttribute("email", email);
					request.setAttribute("message",
							row + " Reihen" + " sind betroffen" + "\n" + "  Ein Fehler ist eingetretten");
					request.getRequestDispatcher("CreateError.ftl").forward(request, response);
				} else {

					request.setAttribute("name", name);
					request.setAttribute("email", email);
					request.setAttribute("message",
							row + " Reihen" + " sind betroffen  \n  Ein neues Projekt wurde gespeichert");
					request.getRequestDispatcher("CreateSuccess.ftl").forward(request, response);
				}
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else
			doGet(request, response);

	}
}
