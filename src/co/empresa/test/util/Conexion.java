package co.empresa.test.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {
 private Connection con = null;
 private static Conexion db;
 private PreparedStatement preparedStatement;
 
 private static final String url ="jdbc:mysql://localhost:3306/";
 private static final String dbName = "sistema";
 private static final String driver = "com.mysql.jdbc.Driver";
 private static final String userName = "root";
 private static final String password = "";

 public Conexion () {
	 
	 try {
		 
		 Class.forName(driver).newInstance();
		 con = (Connection)DriverManager.getConnection(url+dbName, userName, password);
		 
	 }catch (InstantiationException | IllegalAccessException
			 | ClassNotFoundException | SQLException e) {
		 e.printStackTrace();
		 System.out.print("Primer e");
	 } catch (Exception e) {
		 e.printStackTrace();
		 System.out.print("2do e");
	 }
 }
 
 public void cerrarConexion() {
	 try {
		 con.close();
	 } catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 //Patron singleton, verifica, si hay conexion todo bien, sino crea una
 public static Conexion getConexion() {
	 if(db == null) {
		 db = new Conexion();
	 }
	 
	 return db;
 }
 
 
 //Proceso de consulta
 public ResultSet query() throws SQLException {
	 ResultSet res = preparedStatement.executeQuery();
	 return res;
 }
 
 //Proceso de ejecución
 public int execute() throws SQLException {
	 int result = preparedStatement.executeUpdate();
	 return result;
 }
 
 public Connection getCon() {
	 return this.con;
 }
 
 public PreparedStatement setPreparedStatement (String sql) throws SQLException {
	 this.preparedStatement = con.prepareStatement(sql);
	 return this.preparedStatement;
 }
 
}