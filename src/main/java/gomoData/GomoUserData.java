package gomoData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import gomoService.GomoUser;

/**
 * This class will hold the relationship between the GomoUser and the GomoVideo
 * tables
 * 
 * @author Lowell Gilbertson
 *
 */
public class GomoUserData extends MySQLAccess {

	public boolean insertUser(GomoUser gUser) throws Exception {
		boolean bool = false;
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(INSERT_START);
			sb.append(" GomoUser ( ");
			sb.append(" first_name, last_name   ");
			sb.append(VALUES);
			sb.append(gUser.getFirst_name());
			sb.append(COMMA);
			sb.append(gUser.getLast_name());
			sb.append(" ); ");

			statement.execute(sb.toString());
			System.out.println(sb.toString());

		} catch (Exception ex) {
			throw ex;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		}

		return bool;

	}

	/**
	 * Returns true if the user exists in the Db, false if not
	 * 
	 * @param userID
	 * @return
	 */
	public boolean getUserByID(long userID) {

		boolean bool = false;
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(SELECT_COUNT_FROM);
			sb.append(" gomouser  ");
			sb.append(WHERE);
			sb.append(" id   ");
			sb.append(EQUALS);
			sb.append(userID);

			ResultSet rs = statement.executeQuery(sb.toString());
			int rowCount = 0;
			while (rs.next()) {
				rowCount = rs.getInt(1);
			}
			if (rowCount > 0) {
				bool = true;
			}

		} catch (Exception sqlEx) {

			sqlEx.printStackTrace();

		} finally

		{
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return bool;

	}

	public List<GomoUser> getAllGomoUsers() {

		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		List<GomoUser> listOfAllGomoUsers = new ArrayList<GomoUser>();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(SELECT_ALL_FROM);
			sb.append(" gomouser  ");

			ResultSet rs = statement.executeQuery(sb.toString());

			while (rs.next()) {
				GomoUser gUser = new GomoUser();
				gUser.setId(rs.getInt("id"));
				gUser.setFirst_name(rs.getString("first_name"));
				gUser.setLast_name(rs.getString("last_name"));

				listOfAllGomoUsers.add(gUser);

			}

		} catch (Exception sqlEx) {

			sqlEx.printStackTrace();

		} finally

		{
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return listOfAllGomoUsers;

	}

}
