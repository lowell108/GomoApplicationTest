package gomoData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gomoService.GomoVideoFragment;

/**
 * This class will hold the relationship between the GomoUser and the GomoVideo
 * tables
 * 
 * @author Lowell Gilbertson
 *
 */
public class GomoVideoFragmentData extends MySQLAccess {

	public boolean insertVideoFragment(GomoVideoFragment userVid) throws Exception {
		boolean bool = false;
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(INSERT_START);
			sb.append(" gomo_video_fragment ( ");
			sb.append(" userid , videoid, start_time, end_time  ");
			sb.append(VALUES);
			sb.append(userVid.getUserID());
			sb.append(COMMA);
			sb.append(userVid.getVideoID());
			sb.append(COMMA);
			sb.append(userVid.getStartTimeInMS());
			sb.append(COMMA);
			sb.append(userVid.getEndTimeInMS());
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

	public List<GomoVideoFragment>  getAllGomoVideoFragmentsByUserIDAndVideoID(long userID, long videoID) {


		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		List<GomoVideoFragment> listOfAllGomoUserVideos = new ArrayList<GomoVideoFragment>();

		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(SELECT_ALL_FROM);
			sb.append(" gomo_video_fragment  ");
			sb.append(WHERE);
			sb.append(" userid ");
			sb.append(EQUALS);
			sb.append(userID);
			sb.append(AND);
			sb.append("videoid");
			sb.append(EQUALS);
			sb.append(videoID);
			sb.append(" order by start_time asc");
			
			ResultSet rs = statement.executeQuery(sb.toString());

			while (rs.next()) {
				GomoVideoFragment gVid = new GomoVideoFragment();
				gVid.setId(rs.getInt("id"));
				gVid.setUserID(rs.getInt("userid"));
				gVid.setVideoID(rs.getInt("videoid"));
				gVid.setStartTimeInMS(rs.getInt("start_time"));
				gVid.setEndTimeInMS(rs.getInt("end_time"));
				listOfAllGomoUserVideos.add(gVid);

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

		return listOfAllGomoUserVideos;

	}

	public List<GomoVideoFragment>  deleteAllVideoFragmentsFromTheDatabawse() {

		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		List<GomoVideoFragment> listOfAllGomoUserVideos = new ArrayList<GomoVideoFragment>();

		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(DELETE_FROM);
			sb.append(" gomo_video_fragment  ");
			
			statement.execute(sb.toString());

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

		return listOfAllGomoUserVideos;

	}


	
	
	public int getNumberOfVideoFragmentsByUserIDAndVideoID(long userID, long videoID) {

		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		int rowCount = 0;
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(SELECT_COUNT_FROM);
			sb.append(" gomo_video_fragment  ");
			sb.append(WHERE);
			sb.append(" userid ");
			sb.append(EQUALS);
			sb.append(userID);
			sb.append(AND);
			sb.append(" videoid ");
			sb.append(EQUALS);
			sb.append(videoID);

			ResultSet rs = statement.executeQuery(sb.toString());

			while (rs.next()) {
				rowCount = rs.getInt(1);

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

		return rowCount;

	}

	public List<Long> getAllVideoIdsByUserID(long userID) {
		Statement statement = null;
		Connection connect = null;
		StringBuffer sb = new StringBuffer();
		List<Long> listOfAllVideosForThisUserID = new ArrayList<Long>();
		long videoID = 0;
		try {
			connect = getConnection();
			statement = connect.createStatement();
			sb.append(SELECT_DISTINCT);
			sb.append("  videoid from ");
			sb.append(" gomo_video_fragment  ");
			sb.append(WHERE);
			sb.append(" userid ");
			sb.append(EQUALS);
			sb.append(userID);
			sb.append( ORDER_BY);
			sb.append("videoid");

			ResultSet rs = statement.executeQuery(sb.toString());

			while (rs.next()) {
				videoID = rs.getInt("videoid");
				listOfAllVideosForThisUserID.add(videoID);

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
		return listOfAllVideosForThisUserID;

	}

	public static void main(String args[]) throws Exception {

	}

}
