package craw;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List ;
import java.util.ArrayList ;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pengfei on 2017/4/24.
 */
public class Server {
    public  static  void run(String url , String type){
        HttpGetRequest start = new HttpGetRequest(url);
        MysqlSolver.changeType(type);
        GeccoEngine ge = GeccoEngine.create()
                .classpath("craw")
                .start(start)
                .thread(3)
                .interval(2000) ;
        ge.run();
        ge.closeUnitlComplete();
    }

    private static class ServerListener implements Runnable{
        private Socket socket ;
        private final org.slf4j.Logger log = LoggerFactory.getLogger(ServerListener.class) ;

        ServerListener(Socket socket){
            this.socket = socket ;
        }

        private String getReturn() throws SQLException {
            List<String> ids = MysqlSolver.updateSaver();
            StringBuilder sb = new StringBuilder("") ;
            for(String id:ids){
                sb.append(id + "\n") ;
            }
            return sb.toString() ;
        }

        public void run() {
            InputStream is = null;
            OutputStream os = null ;
            try {
                is = socket.getInputStream();
                String s = StreamHelper.read(is) ;
                log.info("接收到命令" + s.toString()) ;
                String[] args = s.split("\t") ;
                MysqlSolver.refresh();
                Server.run(args[0] , args[1]) ;
                os = socket.getOutputStream() ;
                StreamHelper.write(os ,getReturn());
                StreamHelper.write(os , "eof\n");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(socket.getInetAddress() + " 执行结束") ;
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10000) ;
        ExecutorService es = Executors.newFixedThreadPool(5) ;
        while(true){
            Socket socket = serverSocket.accept() ;
            System.out.println("接收到请求" + socket.getInetAddress() + "  " +socket.getPort()) ;
            ServerListener sl = new ServerListener(socket) ;
            es.execute(sl);
        }
    }

}
