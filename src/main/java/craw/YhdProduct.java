package craw;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Text;
import lombok.Data;

/**
 * Created by pengfei on 2017/4/9.
 */
@Data
public class YhdProduct implements Product{

    @Image({"data-lazy-img", "src" , "original"})
//    @Image(download = "H:\\data\\yhd\\images")
    @HtmlField(cssPath = ".proImg > a > img")
    private String image ;

    @Text
    @HtmlField(cssPath = ".proPrice .num")
    private double price ;

    @Text
    @HtmlField(cssPath = ".proName > a")
    private String description ;



}
