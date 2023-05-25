package utils.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class JdbcUtils {

    private static Connection connection;

    private static String URL_HEAD = "jdbc:mysql://";

    private static String BASE_URL_ATTACHMENT = "?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf-8";

    private static Map<String,String> dataSource = new HashMap();

    static {
        //register Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //read dataSource from properties or yml
        String projectUrl = System.getProperty("user.dir");
        String resources = projectUrl + "\\src\\main\\resources";

        File file = new File(resources);
        File[] files = file.listFiles();
        for (File f : files) {
            String fName = f.getName();
            if (f.isFile() && (fName.contains(".yml") || fName.contains(".properties") || fName.contains(".yaml"))){
                loadInfo(file);
            }
        }

    }

    private static void loadInfo(File file){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int read;
            while ((read = fileReader.read()) != -1){
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JdbcUtils(){
    }

    /**
     * register connection dataSource information
     * @param url
     * @param user
     * @param password
     * @throws SQLException
     */
    public static void register(String url,String user,String password) throws SQLException {
        connection = DriverManager.getConnection(url,user,password);
    }

    /**
     * register connection dataSource information
     * @param serverUrl
     * @param database
     * @param user
     * @param password
     * @throws SQLException
     */
    public static void register(String serverUrl,String database,String user,String password) throws SQLException {
        connection = DriverManager.getConnection(URL_HEAD+serverUrl+"/"+database+BASE_URL_ATTACHMENT,user,password);
    }

    /**
     * getPrepareStatement
     * @param sql
     * @return
     * @throws SQLException
     */
    public static PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

}
