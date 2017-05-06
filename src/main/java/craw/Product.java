package craw;

import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * Created by pengfei on 2017/4/24.
 */
public interface Product extends HtmlBean{

    String getDescription ();

    double getPrice() ;


    String getImage() ;


}
