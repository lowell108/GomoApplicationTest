package gomoService;

/**
 * The GomoVideo class will contain methods to access the GomoVideo table in the
 * Db
 * 
 * @author Lowell
 *
 */
public class GomoVideo {

	private int id;
	private int videoLengthMS;
	private String videoName;

	/**
	 * Constructor for data return from Db
	 * 
	 */
	public GomoVideo() {
		super();

	}

	/**
	 * Constructor for inserting video row into DbS
	 * 
	 * @param id
	 * @param videoLength
	 * @param videoName
	 * @param startTime
	 * @param endTime
	 */
	public GomoVideo(int id, int videoLength, String videoName, long startTime, long endTime) {
		super();
		this.id = id;
		this.videoLengthMS = videoLength;
		this.videoName = videoName;

	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVideoLengthMS() {
		return videoLengthMS;
	}

	public void setVideoLengthMS(int videoLength) {
		this.videoLengthMS = videoLength;
	}

}
