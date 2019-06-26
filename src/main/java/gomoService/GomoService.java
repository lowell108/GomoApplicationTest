package gomoService;

import java.util.List;

import gomoData.GomoUserData;
import gomoData.GomoVideoFragmentData;
import gomoData.GomoVideoData;

import java.util.ArrayList;

/**
 * Service layer. Any business logic will go here.
 * 
 * @author Lowell Gilbertson
 *
 */
public class GomoService {

	public static final String SUCCESS = "Success";
	public static final String USER_NOT_IN_DB = "Cannot perform operation. User not found in gomouser table";
	public static final String VIDEO_NOT_IN_DB = "Cannot perform operation. Video not found in gomovideo table";
	public static final String UNKNOWN_EXCEPTION = "An exeption occurred. No more information available";

	public boolean calculateUVT_byUser() {
		boolean retBool = false;
		CalculateUvt calU = new CalculateUvt();
		calU.doUvtCalculationsAndStoreToDatabase();

		return retBool;
	}

	public String insertVideo(GomoVideo gVid) {
		String result = UNKNOWN_EXCEPTION;
		GomoVideoData data = new GomoVideoData();
		try {
			data.insertVideo(gVid);
			result = SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String insertUser(GomoUser gUser) {
		String result = UNKNOWN_EXCEPTION;
		GomoUserData data = new GomoUserData();
		try {
			data.insertUser(gUser);
			result = SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void insertUserVideo(GomoVideoFragment gUserVid) {
		try {
			GomoVideoFragmentData data = new GomoVideoFragmentData();
			data.insertVideoFragment(gUserVid);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Does the user exist in the Db. Find out and fail fast if not.
	 * 
	 * @param userID
	 * @return
	 */
	public boolean getUserByID(long userID) {
		boolean result = false;
		GomoUserData data = new GomoUserData();
		try {
			if (data.getUserByID(userID)) {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<GomoUser> getAllGomoUsers() {
		GomoUserData data = new GomoUserData();
		List<GomoUser> listOfAllUsers = new ArrayList<GomoUser>();
		try {
			listOfAllUsers = data.getAllGomoUsers();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfAllUsers;
	}

	/**
	 * Does the video exist in the Db? Find out and fail fast if not.
	 * 
	 * @param vidID
	 * @return
	 */
	public boolean getVideoByID(long vidID) {
		boolean result = false;
		GomoVideoData data = new GomoVideoData();
		try {
			if (data.getVideoByID(vidID)) {
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
