package dao;

public interface Constants {

	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String CONNECTION_URL_NEW_SCHEMA = "jdbc:mysql://localhost:3306/assicurato_new?user=root&password=root&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	public static final String CONNECTION_URL_OLD_SCHEMA = "jdbc:mysql://localhost:3306/assicurato_old?user=root&password=root&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

}
