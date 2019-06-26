package test;



import org.junit.*;
import gomoService.GomoService;
import java.io.FileInputStream;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 * Test case that works on a full set of data.
 * @author Lowell Gilbertson
 *
 */
public class GomoTest_1 extends DbUnit{

	
	
    public GomoTest_1(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
    
    @Override
	public IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("full.xml"));
    }
 
    @Override
	public DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }
 
    @Override
	public DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

	@Test
	public void testCalculateUVT()  {
		try {

			GomoService gservice = new GomoService();
			gservice.calculateUVT_byUser();
			
	        IDataSet databaseDataSet = getConnection().createDataSet();

			 ITable uvtTable = databaseDataSet.getTable("gomo_uvt_by_user_video");
             int result = (Integer) uvtTable.getValue(0, "unique_viewing_time");
             int expectedValue = 240000;
             
             assertEquals("The result doesn't equal the expected result", result, expectedValue);
            	 
             result = (Integer) uvtTable.getValue(1, "unique_viewing_time");
             expectedValue = 111000;
             
             assertEquals("The result doesn't equal the expected result", result, expectedValue);
            
             
              result = (Integer) uvtTable.getValue(2, "unique_viewing_time");
             expectedValue = 110892;
             
             assertEquals("The result doesn't equal the expected result", result, expectedValue);
            
             
             result = (Integer) uvtTable.getValue(3, "unique_viewing_time");
             expectedValue = 100000;
             
             assertEquals("The result doesn't equal the expected result", result, expectedValue);
            
             
             result = (Integer) uvtTable.getValue(4, "unique_viewing_time");
             expectedValue = 179978;
             
             assertEquals("The result doesn't equal the expected result", result, expectedValue);
            
             
             
		} catch (Exception e) {
			fail();
		}

	}


}
 