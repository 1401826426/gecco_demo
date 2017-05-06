package craw;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import lombok.Data;

import java.util.List;

/**
 * Created by pengfei on 2017/4/25.
 */
@Data
@Gecco(matchUrl = "https://list.jd.com/list.html?cat={id}" , pipelines = {"consolePipeline","processProducePipeline"})
public class JdProducts implements Products {

    @Request
    private HttpRequest request ;

    @HtmlField(cssPath = "#plist .gl-warp .gl-item")
    private List<JdProduct> products ;

    @HtmlField(cssPath = "#plist")
    private String data ;
}
