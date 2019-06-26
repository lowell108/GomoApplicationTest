package gomoService;


/**
 * The java object that holds the UVT by userID and videoID.
 * @author Lowell Gilbertson
 *
 */
public class GomoUvtByUserVideo {

	private long userID;
	private long videoID;
	private long uniqueViewingTime;

	public GomoUvtByUserVideo() {
		super();

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

	public long getUniqueViewingTime() {
		return uniqueViewingTime;
	}

	public void setUniqueViewingTime(long uniqueViewingTime) {
		this.uniqueViewingTime = uniqueViewingTime;
	}


}
