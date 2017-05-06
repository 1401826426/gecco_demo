package craw;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;

/**
 * Created by pengfei on 2017/4/9.
 */
public class CrawController {

    public  void run(String url , String type){
        HttpGetRequest start = new HttpGetRequest(url);
        MysqlSolver.changeType(type);
        GeccoEngine.create()
                .classpath("craw")
                .start(start)
                .thread(3)
                .interval(2000)
                .run();
    }

    public static void main(String[] args){
        String url = "https://list.tmall.com/search_product.htm?q=%C1%AC%D2%C2%C8%B9&pos=1&from=.list.pc_1_searchbutton&acm=201603071.1003.2.708708&type=p&scm=1003.2.201603071.OTHER_1481909015720_708708" ;
        new CrawController().run(url , "dress");
    }
}
