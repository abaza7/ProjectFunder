package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Donation;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.utils.ProjectDao;

/**
 * Servlet implementation class ProjectDetails
 */
@WebServlet("/ProjectDetails")
public class ProjectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectDao dao = new ProjectDao();
	private Project project = null;
	private int kennung;
	private String LoggedNutzerName;
	private String LoggedNutzerEmail;
	private String ErstllerEmail;
	private String ErstellerName;
	private String ProjectTitle;
	private String status;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("action").equals("id")) {

			kennung = Integer.parseInt(request.getParameter("kennung"));
			LoggedNutzerName = request.getParameter("name");
			LoggedNutzerEmail = request.getParameter("email");

			project = dao.getProjectDetails(kennung);

			ErstllerEmail = project.getErstllerEmail();
			ErstellerName = project.getErsteller();
			ProjectTitle = project.getTitle();
			status = project.getStatus();
			
			
			
			
			request.setAttribute("title", project.getTitle());
			request.setAttribute("ersteller", project.getErsteller());
			request.setAttribute("erstellerEmail", project.getErstllerEmail());
			request.setAttribute("beschreibung", project.getBeschreibung());
			request.setAttribute("finanzierungslimit", project.getFinanzierungslimit());
			request.setAttribute("aktulleSpende", project.getAktulleSpende());
			request.setAttribute("status", project.getStatus());
			request.setAttribute("vorgaenger", project.getVorgaenger());
			request.setAttribute("email", LoggedNutzerEmail);
			request.setAttribute("kennung", project.getKennung());
			
			request.setAttribute("donations", project.getSpende());
			request.setAttribute("comments", project.getKommentar());
			request.setAttribute("LoggedNutzerName", LoggedNutzerName);

			if (ErstllerEmail.equals(LoggedNutzerEmail)) {
				request.setAttribute("LoggedNutzerEmail", "same");
			} else {
				request.setAttribute("LoggedNutzerEmail", "NotSame");
			}

			request.getRequestDispatcher("/ProjectDetails.ftl").forward(request, response);

		}

		else if (request.getParameter("action").equals("makeDonation")) {
			request.setAttribute("LoggedNutzerName", LoggedNutzerName);
			request.setAttribute("LoggedNutzerEmail", LoggedNutzerEmail);
			request.setAttribute("title", ProjectTitle);
			request.setAttribute("ErstellerName", ErstellerName);
			request.setAttribute("ErstllerEmail", ErstllerEmail);
			request.setAttribute("kennung", kennung);
			request.setAttribute("status", status);

			request.getRequestDispatcher("/Spenden.ftl").forward(request, response);

		}

		else if (request.getParameter("action").equals("editProject")) {
			request.setAttribute("email", project.getErstllerEmail());
			request.getRequestDispatcher("ProjektEditieren.ftl").forward(request, response);

		}

		else if (request.getParameter("action").equals("comment")) {
			request.setAttribute("LoggedNutzerName", LoggedNutzerName);
			request.setAttribute("LoggedNutzerEmail", LoggedNutzerEmail);
			request.setAttribute("title", ProjectTitle);
			request.setAttribute("ErstellerName", ErstellerName);
			request.setAttribute("ErstllerEmail", ErstllerEmail);
			request.setAttribute("kennung", kennung);
			request.setAttribute("status", status);

			request.getRequestDispatcher("/SchreibeKommentar.ftl").forward(request, response);

		}

		else if (request.getParameter("action").equals("delete")) {
			dao.deleteProject(kennung);
			
			response.sendRedirect("http://localhost:8080/projectFunder/HomepageServlet?action=userEmail&email="+LoggedNutzerEmail+"&name="+LoggedNutzerName);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// making donation

		if (request.getParameter("action").equals("MakingDonation")) {
			double SpendenBetrag = Double.parseDouble(request.getParameter("SpendenBetrag"));
			String sicher;

			if (request.getParameter("Anonym") == null) {
				sicher = "oeffentlich";
			} else {
				sicher = "privat";
			}

			dao.MakingDonation(LoggedNutzerEmail, kennung, SpendenBetrag, sicher);
			response.sendRedirect("http://localhost:8080/projectFunder/ProjectDetails?action=id&kennung=" + kennung
					+ "&email=" + LoggedNutzerEmail + "&name=" + LoggedNutzerName);

		}

		// Editing
		else if (request.getParameter("action").equals("makingEdition")) {

			int vor = 0;
			int kat = 0;
			String titel = (String) request.getParameter("titel"); // must be refined!!
			String finanzierungslimit = (String) request.getParameter("Finanzierungslimit");
			String Kategorie = (String) request.getParameter("Kategorie");
			String beschreibung = (String) request.getParameter("beschreibung");
			String vorgaenger = (String) request.getParameter("vorgaenger");
			String ersteller = (String) request.getParameter("ersteller");

			if (vorgaenger == "kein") {
				vor = 0;
			}

			switch (Kategorie) {
			case "Health":
				kat = 1;
				break;
			case "Art":
				kat = 2;
				break;
			case "Education":
				kat = 3;
				break;
			case "Tech":
				kat = 4;
				break;

			}

			double fin = Double.parseDouble(finanzierungslimit);

			dao.editProject(titel, beschreibung, fin, kat, vor, kennung);

			response.sendRedirect("http://localhost:8080/projectFunder/ProjectDetails?action=id&kennung=" + kennung
					+ "&email=" + LoggedNutzerEmail + "&name=" + LoggedNutzerName);

		}

		// making Comment
		else if (request.getParameter("action").equals("MakingComment")) {

			String kommentar = (String) request.getParameter("kommentar");
			String sicher;

			if (request.getParameter("Anonym") == null) {
				sicher = "oeffentlich";
			} else {
				sicher = "privat";
			}
			dao.MakingComment(kommentar, sicher, LoggedNutzerEmail, kennung);

			response.sendRedirect("http://localhost:8080/projectFunder/ProjectDetails?action=id&kennung=" + kennung
					+ "&email=" + LoggedNutzerEmail + "&name=" + LoggedNutzerName);
		}
	}

}
