package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.utils.ProjectDao;


@WebServlet("/HomepageServlet")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static List<Project> HomeProjects = new ArrayList<>();
    private static List<Project> OpenHomeProjects = new ArrayList<>();
    private static List<Project> ClosedHomeProjects = new ArrayList<>();
    private ProjectDao p = new ProjectDao(); 
    private String LoggedNutzerEmail;
	private String LoggedNutzerName;
	private int kategorie;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		  

		 LoggedNutzerName = request.getParameter("name");
		 LoggedNutzerEmail = request.getParameter("email");
		
		 
		 request.setAttribute("name", request.getParameter("name"));   // get from LoginPage and set in Homepage
		 request.setAttribute("email", request.getParameter("email")); 
		 
			HomeProjects = p.getAllProjects();
		 
		 for(Project project : HomeProjects)
		 {
			// kategorie = project.getKategorie();
			 //request.setAttribute("kategorie", kategorie);
			 
			 if(project.getStatus().equals("offen")) {
				 
				 OpenHomeProjects.add(project);
				 }else {
					 ClosedHomeProjects.add(project);
				 }
		 }
		 
		 // must be modified (repeating results)!!
		request.setAttribute("Oprojects", OpenHomeProjects);
		request.setAttribute("Cprojects", ClosedHomeProjects);

        request.getRequestDispatcher("/TrialHomePage.ftl").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
