package de.unidue.inf.is.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Donation;
import de.unidue.inf.is.domain.Project;

public class ProjectDao {

	private int row;

	public static ArrayList<Project> getVorgaenger(String email) {
		ArrayList<Project> mylist = new ArrayList<>();
		String ProjectSql = "select * from dbp027.projekt where ersteller = ?";

		try {
			Connection connection = DBUtil.getExternalConnection();

			try (PreparedStatement ps = connection.prepareStatement(ProjectSql)) {
				ps.setString(1, email);

				ResultSet set = ps.executeQuery();

				while (set.next()) {

					Project p =  new Project( set.getString(2), set.getInt(1),set.getString(4));
					mylist.add(p);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mylist;
	}
	
	
	/******************************************************************************************************************************/
	

	public int CreateProject(String titel, String beschreibung, double fin, int Kategorie, int vorgaenger,
			String ersteller) {

		try {
			Connection connection = DBUtil.getExternalConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO dbp027.projekt (titel, beschreibung, finanzierungslimit, ersteller, vorgaenger, kategorie) values (?,?,?,?,?,?)");
			preparedStatement.setString(1, titel);
			preparedStatement.setString(2, beschreibung);
			preparedStatement.setDouble(3, fin);
			preparedStatement.setString(4, ersteller);
			preparedStatement.setInt(5, vorgaenger); // modify vorgaenger (must be variable)!!
			preparedStatement.setInt(6, Kategorie);
			row = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	/***********************************************************************************************/

	public void editProject(String titel, String beschreibung, double fin, int Kategorie, int vorgaenger, int kennung) {

		try {
			Connection connection = DBUtil.getExternalConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE dbp027.projekt SET (titel, beschreibung, finanzierungslimit, vorgaenger, kategorie)=(?,?,?,?,?) where kennung = ?");

			preparedStatement.setString(1, titel);
			preparedStatement.setString(2, beschreibung);
			preparedStatement.setDouble(3, fin);
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, Kategorie);
			preparedStatement.setInt(6, kennung);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***********************************************************************************************/

	public void deleteProject(int kennung) {

		String sqlGetDonationAmountAndDonator = "select spender, spendenbetrag from dbp027.spenden where projekt = ?"; // can
																														// be
																														// multiple
		String sqlGetCommentID = "select * from dbp027.schreibt where projekt = ?"; // can be multiple
		String SqlGetActualRecord = "select * from dbp027.konto where inhaber = ?";
		String SqlDonationBack = "update dbp027.konto set guthaben = ? where inhaber = ?";
		String SqlDeleteComment = "delete from dbp027.schreibt where projekt = ?";
		String SqlDeleteCommentText = "delete from dbp027.kommentar where id =  ?";
		String SqlDeleteDonation = "delete from dbp027.spenden where projekt = ?";
		String SqlDeleteProject = "delete from dbp027.projekt where kennung = ?";
		try {
			Connection connection = DBUtil.getExternalConnection();

			try (PreparedStatement psGetDonationAmountAndDonator = connection
					.prepareStatement(sqlGetDonationAmountAndDonator);
					PreparedStatement psGetCommentID = connection.prepareStatement(sqlGetCommentID);
					PreparedStatement psGetActualRecord = connection.prepareStatement(SqlGetActualRecord);
					PreparedStatement psDonationBack = connection.prepareStatement(SqlDonationBack);
					PreparedStatement psDeleteComment = connection.prepareStatement(SqlDeleteComment);
					PreparedStatement psDeleteCommentText = connection.prepareStatement(SqlDeleteCommentText);
					PreparedStatement psDeleteDonation = connection.prepareStatement(SqlDeleteDonation);
					PreparedStatement psDeleteProject = connection.prepareStatement(SqlDeleteProject)) {

				psGetDonationAmountAndDonator.setInt(1, kennung);
				psDeleteDonation.setInt(1, kennung);

				psGetCommentID.setInt(1, kennung);
				psDeleteComment.setInt(1, kennung);
				psDeleteProject.setInt(1, kennung);
				ResultSet Dset = psGetDonationAmountAndDonator.executeQuery();
				while (Dset.next()) {

					psGetActualRecord.setString(1, Dset.getString(1));
					double amount = Dset.getDouble(2);
					ResultSet Rset = psGetActualRecord.executeQuery();
					while (Rset.next()) {
						double NewGuthaben = amount + Rset.getDouble(2);
						psDonationBack.setDouble(1, NewGuthaben);
						psDonationBack.setString(2, Dset.getString(1));
						psDonationBack.executeUpdate();
					}
				}

				psDeleteDonation.executeUpdate(); // delete donation from spenden

				ResultSet Cset = psGetCommentID.executeQuery();
				psDeleteComment.executeUpdate();
				while (Cset.next()) {
					psDeleteCommentText.setInt(1, Cset.getInt(3));
					psDeleteCommentText.executeUpdate();
				}

				psDeleteProject.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/******************************************************************************************/
	public ArrayList<Project> getAllProjects() {
		ArrayList<Project> HomeProjects = new ArrayList<>();
		String ProjectSql = "select * from dbp027.projekt";
		String AkSpendenSql = "select sum(spendenbetrag) from dbp027.spenden where projekt = ?";
		String EstellerSql = "select name from dbp027.benutzer join dbp027.projekt on ersteller = email where kennung = ?";

		try {
			Connection connection = DBUtil.getExternalConnection();

			try (PreparedStatement ps = connection.prepareStatement(ProjectSql);
					PreparedStatement psSelectAktuelSpenden = connection.prepareStatement(AkSpendenSql);
					PreparedStatement psSelectErsteller = connection.prepareStatement(EstellerSql)) {

				try (ResultSet set = ps.executeQuery()) {
					while (set.next()) {
						Project project = new Project(set.getInt(1), set.getString(2), set.getString(3),
								set.getString(4), set.getDouble(5), set.getInt(8));

						psSelectAktuelSpenden.setInt(1, project.getKennung());
						psSelectErsteller.setInt(1, project.getKennung());

						// actual total donations
						ResultSet Sset = psSelectAktuelSpenden.executeQuery();
						while (Sset.next()) {

							project.setAktulleSpende(Sset.getDouble(1));

						}
						// project owner
						ResultSet Eset = psSelectErsteller.executeQuery();
						while (Eset.next()) {

							project.setErsteller(Eset.getString(1));

						}
						HomeProjects.add(project); // add the project to the list!!
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return HomeProjects;
	}

	/******************************************************************************************************/

	public Project getProjectDetails(int id) {
		ArrayList<Donation> donations = new ArrayList<>();
		ArrayList<Comment> comments = new ArrayList<>();
		Project project = null;

		String ProjectSql = "select * from dbp027.projekt where kennung = ?";
		String SpendenSql = "select name, email, projekt,spendenbetrag,sichtbarkeit from dbp027.benutzer join dbp027.spenden on spender=email where projekt = ?";
		String KommentSql = "select name, text, datum, sichtbarkeit,projekt,id,benutzer from dbp027.kommentar join dbp027.schreibt on id = kommentar join dbp027.benutzer on benutzer = email where projekt = ?";
		String AkSpendenSql = "select sum(spendenbetrag) from dbp027.spenden where projekt = ?";
		String EstellerSql = "select name from dbp027.benutzer join dbp027.projekt on ersteller = email where kennung = ?";

		try {
			Connection connection = DBUtil.getExternalConnection();

			try (PreparedStatement ps = connection.prepareStatement(ProjectSql);
					PreparedStatement psSelectSpenden = connection.prepareStatement(SpendenSql);
					PreparedStatement psSelectKomment = connection.prepareStatement(KommentSql);
					PreparedStatement psSelectAktuelSpenden = connection.prepareStatement(AkSpendenSql);
					PreparedStatement psSelectErsteller = connection.prepareStatement(EstellerSql)) {
				ps.setInt(1, id);
				psSelectSpenden.setInt(1, id);
				psSelectKomment.setInt(1, id);
				psSelectAktuelSpenden.setInt(1, id);
				psSelectErsteller.setInt(1, id);
				
				ResultSet set = ps.executeQuery();
				ResultSet SSset = psSelectSpenden.executeQuery();
				ResultSet SKset = psSelectKomment.executeQuery();
				ResultSet Sset = psSelectAktuelSpenden.executeQuery();
				ResultSet Eset = psSelectErsteller.executeQuery();
				
				while (set.next()) {
					project = new Project(set.getInt(1), set.getString(2), set.getString(3), set.getString(4),
							set.getDouble(5), set.getInt(8));
					project.setErstllerEmail(set.getString(6));
					project.setVorgaenger(set.getInt(7));

					// select Donations
					while (SSset.next()) {
						donations.add(new Donation(SSset.getString(1), SSset.getString(2), SSset.getInt(3),
								SSset.getDouble(4), SSset.getString(5)));
					}
					project.setSpende(donations);

					// select comments
					while (SKset.next()) {
						comments.add(new Comment(SKset.getString(1), SKset.getString(2), SKset.getTimestamp(3),
								SKset.getString(4), SKset.getInt(5), SKset.getInt(6), SKset.getString(7)));
					}
					project.setKommentar(comments);

					// actual total donations
					while (Sset.next()) {

						project.setAktulleSpende(Sset.getDouble(1));

					}

					// project owner
					while (Eset.next()) {

						project.setErsteller(Eset.getString(1));

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return project;
	}

	/******************************************************************************************************/

	public void MakingDonation(String spender, int kennung, double spendenbetrag, String sichtbarkeit) {

		try {
			Connection connection = DBUtil.getExternalConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO dbp027.spenden (spender, projekt, spendenbetrag, sichtbarkeit) values (?,?,?,?)");
			preparedStatement.setString(1, spender);
			preparedStatement.setInt(2, kennung);
			preparedStatement.setDouble(3, spendenbetrag);
			preparedStatement.setString(4, sichtbarkeit);
			PreparedStatement psR = connection.prepareStatement("select * from dbp027.konto where inhaber = ?");
			PreparedStatement ps = connection.prepareStatement("update dbp027.konto set guthaben = ? where inhaber = ?");
			
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***************************************************************************************************/

	public void MakingComment(String text, String sichtbarkeit, String benutzer, int Projektkennung) {

		Random ran = new Random();
		int kommentKennung = ran.nextInt(1000) + 50;
		String insertCommentText = "INSERT INTO dbp027.kommentar (id, text, sichtbarkeit) VALUES (?,?,?)";
		String insertComment = "INSERT INTO dbp027.schreibt (benutzer, projekt, kommentar) VALUES (?,?,?)";

		try {
			Connection connection = DBUtil.getExternalConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertCommentText);
			preparedStatement.setInt(1, kommentKennung);
			preparedStatement.setString(2, text);
			preparedStatement.setString(3, sichtbarkeit);
			preparedStatement.executeUpdate();

			PreparedStatement ps = connection.prepareStatement(insertComment);
			ps.setString(1, benutzer);
			ps.setInt(2, Projektkennung);
			ps.setInt(3, kommentKennung);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***********************************************************************************************/
	public String seachProjects(String title) {
		Project project = null;
		List<Project> result = null;
		String basem = null;
		try {
			Connection connection = DBUtil.getExternalConnection();

			String sql = "INSERT INTO dbp027.benutzer (email, name, beschreibung) VALUES ('test44', 'testest44', 'testestest44')";
			// String sql = "select beschreibung from projekt where titel ='Ubuntu Touch'";
			Statement statement = connection.createStatement();
			System.out.println("2nd");

			// ResultSet set = statement.executeUpdate(sql);
			statement.executeUpdate(sql);

			/*
			 * String sql =
			 * "INSERT INTO dbp027.benutzer (email, name, beschreibung) VALUES ('test', 'testest', 'testestest')"
			 * ;
			 * 
			 * Statement statement = connection.createStatement(); ResultSet set =
			 * statement.executeQuery(sql); while(set.next()) {
			 * 
			 * project.setBeschreibung(set.getString("beschreibung")); basem =
			 * set.getString("name"); System.out.println(basem);
			 * 
			 * project = new Project(set.getString("title"), set.getInt("kennung"),
			 * set.getString("beschreibung"), set.getString("status"),
			 * set.getDouble("finanzierungslimit")); result.add(project); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return basem;
	}

}
