/**
 * 
 */
package gomoService;

import java.util.List;

import gomoData.GomoUserData;
import gomoData.GomoUvtByUserVideoData;
import gomoData.GomoVideoFragmentData;

/**
 * @author Lowell Gilbertson
 *
 */
public class CalculateUvt {

	
	public CalculateUvt() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Herein lies the meat of the matter. Roll through all users, then all videos
	 * viewed by said users and then all fragments from that video.
	 * Find the Unique Video Time then write to the
	 * Database for review.
	 * 
	 * @return
	 */
	public boolean doUvtCalculationsAndStoreToDatabase() {
		boolean bool = false;
		GomoUserData userData = new GomoUserData();
		GomoVideoFragmentData videoFragmentData = new GomoVideoFragmentData();
		GomoUvtByUserVideoData uvtData = new GomoUvtByUserVideoData();
		// First, Delete prior results -- this is necessary for this test. In "real
		// life" this call would need to be modified so that it only deletes certain
		// results.
		try {
			uvtData.deleteTestResultsFromDb();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/**
		 * First, get all gomousers from the Db
		 */

		List<GomoUser> allGomoUsers = userData.getAllGomoUsers();

		for (GomoUser thisUser : allGomoUsers) {

			long currentVideoID = 0;
			// Pick an impossible start time
			long priorFragmentStartTime = -1;
			// Pick an impossible end time
			long priorFragmentEndTime = 300000000;
			// pick an impossible uvt time
			long thisUVT = -1;

			int numberOfProcessedFragmentsOfThisVideo = 0;
			int numberOfTOTAL_FragmentsForThisVideoByUserID = 0;

			/*
			 * Get all videos for this user and loop through
			 */
			List<Long> listOfVideoIdsForThisUser = videoFragmentData.getAllVideoIdsByUserID( thisUser.getId());

			for (Long thisVideoID : listOfVideoIdsForThisUser) {
				
				List<GomoVideoFragment> listOfVideoFragments = videoFragmentData.getAllGomoVideoFragmentsByUserIDAndVideoID(thisUser.getId(), thisVideoID);
				
				
				for (GomoVideoFragment this_user_video_fragment : listOfVideoFragments) {
		            

					currentVideoID = this_user_video_fragment.getVideoID();
					numberOfTOTAL_FragmentsForThisVideoByUserID = listOfVideoFragments.size();

					if ( numberOfProcessedFragmentsOfThisVideo == 0) {
						/**
						 * Then this is the first fragment for this video. It is guarenteed by the
						 * sort() function to have the lowest startTime. Capture the information
						 * 
						 */
						numberOfProcessedFragmentsOfThisVideo++;

						priorFragmentStartTime = this_user_video_fragment.getStartTimeInMS();
						priorFragmentEndTime = this_user_video_fragment.getEndTimeInMS();
						thisUVT = this_user_video_fragment.getEndTimeInMS()
								- this_user_video_fragment.getStartTimeInMS();

					} else if (this_user_video_fragment.getStartTimeInMS() >= priorFragmentStartTime
							&& this_user_video_fragment.getEndTimeInMS() <= priorFragmentEndTime
							&& numberOfProcessedFragmentsOfThisVideo > 0) {

						// Then this fragment is entirely contained within the prior fragment. Do
						// Nothing.
						numberOfProcessedFragmentsOfThisVideo++;

					} else if (this_user_video_fragment.getStartTimeInMS() >= priorFragmentStartTime
							&& (this_user_video_fragment.getStartTimeInMS() < priorFragmentEndTime)
							&& this_user_video_fragment.getEndTimeInMS() > priorFragmentEndTime) {
						/**
						 * then this fragment will expand the UVT on this video.
						 */
						
						thisUVT += this_user_video_fragment.getEndTimeInMS()
								- priorFragmentEndTime;
						numberOfProcessedFragmentsOfThisVideo++;
						priorFragmentStartTime = this_user_video_fragment.getStartTimeInMS();
						priorFragmentEndTime = this_user_video_fragment.getEndTimeInMS();

					} else if (this_user_video_fragment.getEndTimeInMS() > priorFragmentEndTime) {
						/**
						 * Then this is a fragment is  discontinuous with the prior ones.
						 */
						thisUVT += this_user_video_fragment.getEndTimeInMS()
								- this_user_video_fragment.getStartTimeInMS();
						numberOfProcessedFragmentsOfThisVideo++;

						priorFragmentStartTime = this_user_video_fragment.getStartTimeInMS();
						priorFragmentEndTime = this_user_video_fragment.getEndTimeInMS();
					}

					

				} // end the foreach fragment loop
				
				/*
				 * Store UVT records to the Database. Reset variables
				 */
				if ((numberOfProcessedFragmentsOfThisVideo == numberOfTOTAL_FragmentsForThisVideoByUserID)) {
					/**
					 * Store the UVT for this video into the Db
					 */
					GomoUvtByUserVideo uniqueViewingTimeByUserAndVideo = new GomoUvtByUserVideo();
					uniqueViewingTimeByUserAndVideo.setUserID(thisUser.getId());
					uniqueViewingTimeByUserAndVideo.setVideoID(currentVideoID);
					uniqueViewingTimeByUserAndVideo.setUniqueViewingTime(thisUVT);
					try {
						uvtData.insertUVT_testResultsToDb(uniqueViewingTimeByUserAndVideo);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					currentVideoID = 0;
					priorFragmentStartTime = -1;
					priorFragmentEndTime = 300000000;
					numberOfProcessedFragmentsOfThisVideo = 0;
				}
			} // end of foreach video loop			
		} // end the user for loop
		bool = true;
		return bool;
	}
}
