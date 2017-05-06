package craw;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Text;
import lombok.Data;

/**
 * Created by pengfei on 2017/4/25.
 */
@Data
public class JdProduct implements Product{


    @Text
    @HtmlField(cssPath = ".gl-i-wrap .p-price .J_price > i")
    private double price ;


    @Text
    @HtmlField(cssPath = ".gl-i-wrap .p-name > a")
    private String description ;

    @Image({"data-lazy-img", "src" , "original"})
    @HtmlField(cssPath = ".gl-i-wrap .p-img > a > img")
    private String image ;
}
