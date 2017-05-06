package craw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by pengfei on 2017/4/24.
 */
public class TestSocket {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost" , 10000) ;
        OutputStream os = socket.getOutputStream() ;
        String value = "http://list.yhd.com/c21347-0-81472" + "\t" + "camera" ;
        StreamHelper.write(os,value);
        StreamHelper.write(os , "eof");
        InputStream is = socket.getInputStream() ;
        String result = StreamHelper.read(is)  ;
        System.out.println(result) ;
        os.close();
        is.close();
        socket.close();
//        MysqlSolver.refresh();
//        Server.run("http://list.yhd.com/c21347-0-81472" , "camera");
    }
}
