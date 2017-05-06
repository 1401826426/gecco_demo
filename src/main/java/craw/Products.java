package craw;

import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List ;
/**
 * Created by pengfei on 2017/4/24.
 */
public interface Products extends HtmlBean{

    HttpRequest getRequest() ;

    List getProducts() ;

}
