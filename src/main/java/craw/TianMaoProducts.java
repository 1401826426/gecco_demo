package craw;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import lombok.Data;
import java.util.List ;

/**
 * Created by pengfei on 2017/4/25.
 */
@Gecco(matchUrl = "https://list.tmall.com/search_product.htm?{text}" , pipelines = {"processProducePipeline" , "consolePipeline"})
@Data
public class TianMaoProducts implements Products{

    @Request private HttpRequest request ;

    @HtmlField(cssPath = "#J_ItemList .product  ")
    private List<TianMaoProduct> products ;

    @HtmlField(cssPath = "#J_ItemList")
    private String data ;



}
