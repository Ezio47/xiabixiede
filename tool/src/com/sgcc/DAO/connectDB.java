package com.sgcc.DAO;

import java.sql.*;

/**
 * @author Nari RealWorld
 *
 */
public class connectDB {

	private static Connection conn = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	
	/**
	 * @param iphostsid
	 * @param name
	 * @param psd
	 * @return
	 */
	public static Connection dbConn(String iphostsid, String name, String psd) {
		Connection con = null;
		String  modulus = "114175218555550127243463030373404633890173142172980243285671965286165508781026299720708000609635609721590647895223284966841531864407481743274648490928817732890157727729919423801862383921957465115863262316277980918155638539432383398082402771885258702297514660518161972938651830419825105971130685974273889454449";

		String private_exponent = "111492309106299365140300952970181368617260640243590897196288337312033417236970567820103909471212446638580293473161795138974281928170126937826688427513794453211205711936678612193164862019575630381780117393244227051417854135678291606280708676576851482742990852045860530029657151911790266267059018095269653649457";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String conStr = "jdbc:oracle:thin:@" + iphostsid;
			con = DriverManager.getConnection(conStr, name, psd);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return con;

	}

	public static void main(String[] args) throws SQLException {
		try {
			conn = dbConn("192.168.1.128:1521:orcl", "csdz", "sys");
			if (conn == null) {
				System.out.println("连接失败");
				System.exit(0);
			}
			st = conn.createStatement();
			String sql = "select t.sblx,t.sort,t.tbname from T_SB_FORMS t";

			rs = st.executeQuery(sql);
			while (rs.next()) {

				System.out.println(rs.getString(1) + " " + rs.getInt(2) + "　"
						+ rs.getString(3));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}
