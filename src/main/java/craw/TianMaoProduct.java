package craw;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Text;
import lombok.Data;

/**
 * Created by pengfei on 2017/4/25.
 */
@Data
public class TianMaoProduct implements Product{


    @Text
    @HtmlField(cssPath = ".product-iWrap .productTitle > a")
    private String description ;


    @Text
    @HtmlField(cssPath = ".product-iWrap .productPrice > em")
    private double price ;


    @Image({"data-ks-lazyload-custom", "src" , "data-ks-lazyload" , })
    @HtmlField(cssPath = ".product-iWrap .productImg-wrap > a > img")
    private String image ;

}


