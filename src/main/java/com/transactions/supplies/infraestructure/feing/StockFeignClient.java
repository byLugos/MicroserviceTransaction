package com.transactions.supplies.infraestructure.feing;

import com.transactions.supplies.infraestructure.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "stock", url = "http://localhost:8000/auxArticles", configuration = FeignClientConfig.class)
public interface StockFeignClient {
    @GetMapping("/findByName")
    Long findArticleIdByName(@RequestParam("name") String name);

    @GetMapping("/{articleId}/stock")
    int getStock(@PathVariable("articleId") Long articleId);

    @PutMapping("/{articleId}/quantity")
    void updateStock(@PathVariable("articleId") Long articleId, @RequestParam int quantity);
}
