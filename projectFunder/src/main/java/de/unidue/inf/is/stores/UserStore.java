package de.unidue.inf.is.stores;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

public final class UserStore implements Closeable {

	private Connection connection;
	private boolean complete;
	private ArrayList<User> users = new ArrayList<>();

	public UserStore() throws StoreException {
		try {
			connection = DBUtil.getExternalConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new StoreException(e);
		}
	}

	public ArrayList<User> getAllUsers() {
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from dbp027.benutzer");
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {

				user = new User(set.getString("name"), set.getString("email"), set.getString("beschreibung"));
				users.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	/****************************************************************************************/
	public User getUserDetails(String NutzerEmail, String NutzerName) {
		User user = null;
		Project ow = null;
		Project de = null;
        int numberOfOwned = 0;
        int numberOfDonated = 0;
		ArrayList<Project> owned = new ArrayList<>();
		ArrayList<Project> donatedFor = new ArrayList<>();
		
		String SqlSelectOwnedprojects = "select * from dbp027.projekt where ersteller = ?";
		String SqlSelectDonatedProjects = "select * from dbp027.projekt where kennung in (select projekt from dbp027.spenden where spender = ? )";
		String SqlSelectnumberOfOwned = "select count(*) as count from dbp027.projekt where ersteller = ?" ;
		String SqlSelectnumberOfDonated = "select count(*) as count from dbp027.spenden where spender = ?";
		String AkSpendenSql = "select sum(spendenbetrag) from dbp027.spenden where projekt = ?";

		try (PreparedStatement psSelectOwnedprojects = connection.prepareStatement(SqlSelectOwnedprojects);
				PreparedStatement psSelectDonatedProjects = connection.prepareStatement(SqlSelectDonatedProjects);
				PreparedStatement psSelectnumberOfOwned = connection.prepareStatement(SqlSelectnumberOfOwned);
				PreparedStatement psSelectnumberOfDonated = connection.prepareStatement(SqlSelectnumberOfDonated);
				PreparedStatement psAkSpenden = connection.prepareStatement(AkSpendenSql)) 
				{

			psSelectOwnedprojects.setString(1, NutzerEmail);
			psSelectDonatedProjects.setString(1, NutzerEmail);
			psSelectnumberOfOwned.setString(1, NutzerEmail);
			psSelectnumberOfDonated.setString(1, NutzerEmail);

			ResultSet SOset = psSelectOwnedprojects.executeQuery();
			ResultSet SDset = psSelectDonatedProjects.executeQuery();
		    ResultSet SNOset = psSelectnumberOfOwned.executeQuery();
			ResultSet SNDset = psSelectnumberOfDonated.executeQuery();

			while (SOset.next()) {
				
		
				int i = SOset.getInt(1);
				ow = new Project(SOset.getString(2), SOset.getInt(1), SOset.getString(4));
				psAkSpenden.setInt(1, i);
				ResultSet AkSpeSet = psAkSpenden.executeQuery();
				
				if(AkSpeSet.next()) {
				ow.setAktulleSpende(AkSpeSet.getDouble(1));
				}
				owned.add(ow);

			}
			
			while (SDset.next()) {
				de = new Project(SDset.getString(2), SDset.getInt(1), SDset.getString(4));
				psAkSpenden.setInt(1, SDset.getInt(1));
				
				ResultSet AkSpeSet = psAkSpenden.executeQuery();
				if(AkSpeSet.next()) {
					//System.out.println(AkSpeSet.getDouble(1));
					de.setAktulleSpende(AkSpeSet.getDouble(1));
					}

				donatedFor.add(de);
			}

			
			if(SNOset.next())
			{
				numberOfOwned = SNOset.getInt(1);
			}
			
			if(SNDset.next())
			{
				numberOfDonated = SNDset.getInt(1);
			}
			
			user = new User( NutzerName, NutzerEmail, numberOfOwned, numberOfDonated, owned, donatedFor);
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	/*********************************************************************************************/
	public void addUser(User userToAdd) throws StoreException {
		/*
		 * try { PreparedStatement preparedStatement = connection
		 * .prepareStatement("insert into user (firstname, lastname) values (?, ?)");
		 * preparedStatement.setString(1, userToAdd.getFirstname());
		 * preparedStatement.setString(2, userToAdd.getLastname());
		 * preparedStatement.executeUpdate(); } catch (SQLException e) { throw new
		 * StoreException(e); }
		 */
	}

	public void complete() {
		complete = true;
	}

	@Override
	public void close() throws IOException {
		if (connection != null) {
			try {
				if (complete) {
					connection.commit();
				} else {
					connection.rollback();
				}
			} catch (SQLException e) {
				throw new StoreException(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new StoreException(e);
				}
			}
		}
	}

}
