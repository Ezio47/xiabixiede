package com.sgcc.lcy;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.sgcc.sbInfo.*;
import com.sgcc.DAO.*;

public class excle2oracle {
	private static Connection conn = null;
	private static PreparedStatement  pst = null;
	//private static Statement st = null;
	//private static ResultSet rs = null;
	
	/*
	 * 功能：JAVA读取txt文件内容
	 * @param:filePath文件路径
	 */
	public static void readTxtFile(String filePath) throws SQLException{
		String modules = "114175218555550127243463030373404633890173142172980243285671965286165508781026299720708000609635609721590647895223284966841531864407481743274648490928817732890157727729919423801862383921957465115863262316277980918155638539432383398082402771885258702297514660518161972938651830419825105971130685974273889454449";
		String public_exponent = "65537";
		
		try{
			
			conn = connectDB.dbConn("192.168.1.128:1521:orcl","csdz","sys");
			if(conn == null){
				System.out.println("连接失败");
				System.exit(0);
			}
			String encoding = "UTF-8";
			
			SbInfo sb = new SbInfo();
			
			
			File file =  new File(filePath);
			if(file.isFile() && file.exists()){
				InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader reader = new BufferedReader(inputReader);
				String lineTxt = null;
				String sql = null;
				sql = "insert into t_sb_info(ssdz,dydj,lbmc,sbmc,sbid,jgmc) values(?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				while((lineTxt = reader.readLine()) != null){
					String[] sqllist = lineTxt.split("@");
					sb.setSsdz(sqllist[0].toString().trim());
					sb.setDydj(sqllist[1].toString().trim());
					sb.setLbmc(sqllist[2].toString().trim());
					sb.setSbmc(sqllist[3].toString().trim());
					sb.setSbid(sqllist[4].toString().trim());
					sb.setJgmc(sqllist[5].toString().trim());
					
					pst.setString(1, sb.getSsdz());
					pst.setString(2,sb.getDydj());
					pst.setString(3,sb.getLbmc());
					pst.setString(4,sb.getSbmc());
					pst.setString(5,sb.getSbid());
					pst.setString(6,sb.getJgmc());
					if(pst.executeUpdate() == 0){
						System.out.println("数据更新失败");
					}
					
				}
				inputReader.close();
			}else{
				System.out.println("找不到指定的文件");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}
	
	public static void main(String []args) throws SQLException{
		String file = "D:\\Repository\\python\\excel.txt";
		readTxtFile(file);
		
	}
}
