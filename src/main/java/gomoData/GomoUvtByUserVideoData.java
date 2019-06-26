package gomoData;

import java.sql.Connection;
import java.sql.Statement;
import gomoService.*;

/**
 * This class will hold the UVT results.
 * tables
 * 
 * @author Lowell Gilbertson
 *
 */
public class GomoUvtByUserVideoData extends MySQLAccess {

	public boolean insertUVT_testResultsToDb(GomoUvtByUserVideo testResults) throws Exception {
		boolean bool = false;
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(INSERT_START);
			sb.append(" gomo_uvt_by_user_video ( ");
			sb.append(" user_id,  video_id , unique_viewing_time  ");
			sb.append(VALUES);
			sb.append(testResults.getUserID());
			sb.append(COMMA);
			sb.append(testResults.getVideoID());
			sb.append(COMMA);
			sb.append(testResults.getUniqueViewingTime());
			sb.append(" ); ");

			statement.execute(sb.toString());

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

	public boolean deleteTestResultsFromDb() throws Exception {
		boolean bool = false;
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(DELETE_FROM);
			sb.append(" gomo_uvt_by_user_video  ");
			sb.append(" ; ");

			statement.executeUpdate(sb.toString());

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
}
