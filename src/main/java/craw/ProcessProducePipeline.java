package craw;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.downloader.DownloadException;
import com.geccocrawler.gecco.downloader.DownloaderContext;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
import java.io.*;
import java.util.List ;

/**
 * Created by pengfei on 2017/4/9.
 */
@PipelineName("processProducePipeline")
public class ProcessProducePipeline implements Pipeline<Products> {

    public void process(Products products) {
        List<YhdProduct> productList = products.getProducts() ;
        for(Product product:productList){
            if("".equals(product.getImage()))continue;
            HttpRequest request =  products.getRequest().subRequest(product.getImage()) ;
            try {
                HttpResponse response = DownloaderContext.download(request) ;
                byte[] bytes = writeImage(response.getRaw()) ;
                MysqlSolver.save(product.getDescription() , product.getPrice() , bytes) ;
            } catch (DownloadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private byte[] writeImage(ByteArrayInputStream raw) throws IOException {
        byte[] bytes = new byte[raw.available()] ;
        raw.read(bytes) ;
        return bytes ;
    }

}
