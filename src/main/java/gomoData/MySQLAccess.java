package gomoData;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Class that provides access to the database
 * and some helpful static constants.
 * @author Lowell Gilbertson
 *
 */
public  class MySQLAccess {
	private Connection connect = null;


	public static final String INSERT_START = "Insert into ";
	public static final String SELECT_ALL_FROM = " Select * from ";
	public static final String SELECT_COUNT_FROM = " Select count(*) from ";
	public static final String VALUES = " ) values ( ";
	public static final String COMMA = " , ";
	public static final String COMMA_SINGLEQUOTE = " , '";
	public static final String SINGLEQUOTE_COMMA_SINGLEQUOTE = "' , '";
	public static final String SINGLEQUOTECOMMA = " ' ,";
	public static final String DELETE_FROM = " delete from ";
    public static final String AND = " and ";
	public static final String FROM = " from ";
	public static final String WHERE = " where ";
	public static final String EQUALS = " = ";
	public static final String INNER_JOIN = " inner join ";
	public static final String SELECT_DISTINCT = " select distinct ";
	public static final String ORDER_BY = " order by ";

	protected Connection getConnection() throws Exception {
		try {

			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/gomo?" + "user=root&password=@Aru58exa");

			// Statements allow to issue SQL queries to the database

			return connect;

		} catch (Exception e) {
			throw e;
		}

	}


	



}