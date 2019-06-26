package gomoService;

import customExceptions.InvalidUserException;
import customExceptions.InvalidVideoException;
import gomoData.GomoUserData;
import gomoData.GomoVideoData;
import gomoData.MySQLAccess;

/**
 * This class will hold the relationship between the GomoUser and the GomoVideo
 * tables
 * 
 * @author Lowell
 *
 */
public class GomoVideoFragment extends MySQLAccess implements Comparable<GomoVideoFragment> {

	private long id;
	private long userID;
	private long videoID;
	private long startTimeInMS;
	private long endTimeInMS;

	/**
	 * Empty constructor
	 * 
	 */
	public GomoVideoFragment() {
		super();

	}

	/**
	 * Constructor used for data insert
	 * 
	 * @param userID
	 * @param videoID
	 * @throws InvalidUserException
	 * @throws InvalidVideoException
	 */
	public GomoVideoFragment(long userID, long videoID) throws InvalidUserException, InvalidVideoException {
		if (checkUserID(userID)) {
			this.userID = userID;

		} else {
			throw new InvalidUserException("User with id " + userID + " not in the Database");
		}
		if (checkVideoID(videoID)) {
			this.videoID = videoID;
		} else {
			throw new InvalidVideoException("Video with id " + videoID + " not in the Database");
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStartTimeInMS() {
		return startTimeInMS;
	}

	public void setStartTimeInMS(long startTimeInMS) {
		this.startTimeInMS = startTimeInMS;
	}

	public long getEndTimeInMS() {
		return endTimeInMS;
	}

	public void setEndTimeInMS(long endTimeInMS) {
		this.endTimeInMS = endTimeInMS;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getVideoID() {
		return videoID;
	}

	public void setVideoID(long videoID) {
		this.videoID = videoID;
	}

	private boolean checkUserID(long userID) {
		boolean result = false;
		GomoUserData data = new GomoUserData();
		if (data.getUserByID(userID)) {
			result = true;
		}
		return result;
	}

	private boolean checkVideoID(long videoID) {
		boolean result = false;
		GomoVideoData data = new GomoVideoData();
		if (data.getVideoByID(videoID)) {
			result = true;
		}
		return result;
	}

	@Override
	public int compareTo(GomoVideoFragment gUserVid) {
		return (int) (this.getStartTimeInMS() - gUserVid.getStartTimeInMS());

	}

}
