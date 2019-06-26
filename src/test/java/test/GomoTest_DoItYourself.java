
package test;

import org.junit.*;
import gomoData.GomoVideoFragmentData;
import java.io.FileInputStream;
import gomoService.GomoService;
import gomoService.GomoVideoFragment;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;



/**
 * To use this class, first modify the main method
 * to insert your own values for the fragments.
 * You may run as "java application" for as many
 * times as you wish, provided that you are sure
 * to comment the delete statement that cleans
 * existing fragments from the Database.
 * 
 * Then run or debug as a JUnit test.
 * 
 * 
 * 
 * @author Lowell Gilbertson
 *
 */
public class GomoTest_DoItYourself extends DbUnit {

	public GomoTest_DoItYourself(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("gomoDIY_test.xml"));
	}

	@Override
	public DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	@Override
	public DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	@Test
	public void testCalculateUVT() {
		try {

			GomoService gservice = new GomoService();
			gservice.calculateUVT_byUser();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {

		GomoTest_DoItYourself diy = new GomoTest_DoItYourself("GomoTest_DoItYourself");
		diy.setUpNewVideoFragments();

	}

	/**
	 * Use this main method to add your own video fragments
	 * the Db is set up with three users (1,2,3) and 
	 * three videos (1,2,3). Any other values will result 
	 * in a Sql foreign key exception.
	 * 
	 * Also, the program assumes reliable actors where
	 * it comes to the start and end times of fragments. 
	 * It does not check to be sure that the start and end times
	 * are within the length of the video. 
	 * 
	 */
	private void setUpNewVideoFragments() {
		
		

		GomoVideoFragmentData data = new GomoVideoFragmentData();
		/**
		 * First, clean all existing fragments from the Db.
		 * When inserting the second to the nth fragments,
		 * comment the delete statement below. 
		 */
		//data.deleteAllVideoFragmentsFromTheDatabawse();
		GomoVideoFragment thisFragment = new GomoVideoFragment();
		thisFragment.setUserID(3);
		thisFragment.setVideoID(3);
		thisFragment.setStartTimeInMS(103300);
		thisFragment.setEndTimeInMS(1113000);
		try {
			data.insertVideoFragment(thisFragment);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
