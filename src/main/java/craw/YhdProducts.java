package craw;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengfei on 2017/4/7.
 */
@Gecco(matchUrl = "http://list.yhd.com/{category}" , pipelines = {"processProducePipeline"})
@Data
public class YhdProducts implements Products{

    @Request
    private HttpRequest request ;

    @HtmlField(cssPath = ".mod_product_list .mod_search_pro .itemBox")
    private List<YhdProduct> products ;

    @HtmlField(cssPath = ".mod_product_list")
    private String data ;

    public static void main(String[] args){
//        new CrawController().run("http://list.yhd.com/c43263-0-150765" , "book");
        HttpGetRequest start = new HttpGetRequest("http://list.yhd.com/c43263-0-150765");
        MysqlSolver.changeType("book");
        GeccoEngine.create()
                .classpath("craw")
                .start(start)
                .thread(3)
                .interval(2000)
                .run();
    }
}



