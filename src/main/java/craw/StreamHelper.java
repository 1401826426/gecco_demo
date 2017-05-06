package craw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by pengfei on 2017/4/24.
 */
public class StreamHelper {


    public static String read(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder("") ;
        Scanner scanner = new Scanner(is) ;
        boolean flag = false;
        while(scanner.hasNext()){
            String s = scanner.nextLine() ;
            if("eof".equals(s))break ;
            if(flag)sb.append("\n") ;
            flag = true ;
            sb.append(s) ;
        }
        return sb.toString() ;
    }


    public  static void write(OutputStream os , String s){
        PrintWriter pw = new PrintWriter(os) ;
        pw.println(s);
        pw.flush();
    }



}
