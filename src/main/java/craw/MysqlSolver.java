package craw;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by pengfei on 2017/4/17.
 */
public class MysqlSolver {
    private static final Logger log = Logger.getLogger(MysqlSolver.class) ;
    private static String type ;
    public static void changeType(String type){
        MysqlSolver.type = type;
    }
    private static Properties p = new Properties() ;
    static {
        InputStream is = MysqlSolver.class.getClassLoader().getResourceAsStream("jdbc.properties") ;
        try {
            p.load(is);
            log.info("================load jdbc.properties==============");
            Enumeration enumeration = p.propertyNames() ;
            while (enumeration.hasMoreElements()){
                String name  = (String) enumeration.nextElement();
                log.info(name + "=" + p.get(name)) ;
             }
             log.info("=============================================")  ;
            Class.forName(p.getProperty("jdbc.driver")) ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static List<String> idss = new ArrayList<String>() ;

    public static void refresh(){
        idss.clear();
    }

    public static List<String> updateSaver(){
        return idss ;
    }

    public static void save(String description , double price , byte[] image) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection((String)p.get("jdbc.url") ,
                (String)p.get("jdbc.username"),(String)p.get("jdbc.password")) ;
        connection.setAutoCommit(false);
        String sql = "insert into commodity(id , description , price , image , `type`)\n" +
                "values (? , ? , ? , ? ,?) ; " ;
        PreparedStatement ps = connection.prepareStatement(sql) ;
        String id = UUID.randomUUID().toString() ;
        ps.setString(1, id);
        ps.setString(2 , description);
        ps.setDouble(3 , price);
        ps.setBytes(4 , image);
        ps.setString(5 , type);
        ps.executeUpdate() ;
        connection.commit();
        idss.add(id) ;
        System.out.println("存储" + id + "成功");
    }

    public static  List<String> getImageId(String type) throws SQLException {
        Connection connection = DriverManager.getConnection((String)p.get("jdbc.url") ,
                (String)p.get("jdbc.username"),(String)p.get("jdbc.password")) ;
        String sql = "select id from commodity where type = ?" ;
        PreparedStatement ps = connection.prepareStatement(sql) ;
        ps.setString(1 , type);
        ResultSet rs = ps.executeQuery() ;
        List<String> list = new ArrayList<String>( ) ;
        while(rs.next()){
            list.add(rs.getString("id")) ;
        }
        return list ;
    }

}
