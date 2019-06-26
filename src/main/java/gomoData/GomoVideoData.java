package gomoData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import gomoService.GomoVideo;

/**
 * This class will hold the relationship between the GomoUser and the GomoVideo
 * tables
 * 
 * @author Lowell
 *
 */
public class GomoVideoData extends MySQLAccess {

	public boolean insertVideo(GomoVideo gVid) throws Exception {
		boolean bool = false;
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(INSERT_START);
			sb.append(" gomovideo ( ");
			sb.append(" videolength, videoname   ");
			sb.append(VALUES);
			sb.append(gVid.getVideoLengthMS());
			sb.append(COMMA);
			sb.append(gVid.getVideoName());
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
	 * Returns true if the video exists in the Db, false if not
	 * 
	 * @param userID
	 * @return
	 */
	public boolean getVideoByID(long videoID) {

		boolean bool = false;
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(SELECT_COUNT_FROM);
			sb.append(" GomoVideo  ");
			sb.append(WHERE);
			sb.append(" id   ");
			sb.append(EQUALS);
			sb.append(videoID);

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

	public static void main(String args[]) throws Exception {
		GomoVideoData data = new GomoVideoData();
		boolean retBool = data.getVideoByID(2);
		System.out.println(retBool);

	}

}
