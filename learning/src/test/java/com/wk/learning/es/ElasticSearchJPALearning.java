package com.wk.learning.es;

import com.alibaba.fastjson.JSONObject;
import com.wk.learning.JunitApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ElasticSearchJPALearning extends JunitApplicationRunner {

    @Autowired
    private ProductIndexRepository productIndexRepository;

    @Test
    public void deleteProductIndexBatch() {
        productIndexRepository.deleteAll();
    }


    public void createProductIndex(String title) throws InterruptedException {
        ProductInfoIndex productIndex = new ProductInfoIndex();
        productIndex.setId(UUID.randomUUID().toString());
        productIndex.setPrice(Math.random() * 100);
        productIndex.setTitle(title == null ? "2020年冬天最流行的网红棉大衣,史上最便宜" : title);
        productIndex.setRemark("错过这一天,再等一年");
        productIndex.setCreateTime(new Date().getTime());
        productIndex.setRemark11("hahahahhaha");
        productIndexRepository.save(productIndex);
        Thread.sleep(1000);
    }

    @Test
    public void createProductIndexBatch() throws InterruptedException {
        createProductIndex("今年最最好看的最最便宜的没有之一");
        createProductIndex("今年最最丑的最贵的没有之一");
        createProductIndex("今年最最便宜的没有之一");
        createProductIndex("今年最最好看的没有之一");
        createProductIndex("今年最最贵的没有之一");
        createProductIndex("今年最最丑的没有之一");
    }

    @Test
    public void findProductByTitle() {
        Sort sort = Sort.by(Sort.Order.asc("price"));
        List<ProductInfoIndex> productIndexList = productIndexRepository.findByTitle("最便宜", sort);
        productIndexList.stream().forEach(obj -> log.info("{}", obj));
    }

    @Test
    public void findProductByRemark() {
        //价格顺序
        Sort sort = Sort.by(Sort.Order.asc("createTime"));
        List<ProductInfoIndex> productIndexList = productIndexRepository.findByRemarkLike("一年", sort);
        productIndexList.stream().forEach(obj -> log.info("{}", JSONObject.toJSONString(obj)));
    }

    /**
     * 排序分页.
     */
    @Test
    public void findProductByTitleWithPage() {
        Sort.Order orderPrice = Sort.Order.asc("price");
        Sort sort = Sort.by(orderPrice);
        Pageable pageable = PageRequest.of(0, 3, sort);
        List<ProductInfoIndex> productIndexList = productIndexRepository.findByTitleStartingWith("最", pageable);
        productIndexList.stream().forEach(obj -> log.info("{}", obj));
    }

    /**
     * 排序分页.
     */
    @Test
    public void findByCreateTimeBetween() {
        Sort.Order orderPrice = Sort.Order.asc("price");
        Sort sort = Sort.by(orderPrice);
        Pageable pageable = PageRequest.of(1, 3, sort);
        List<ProductInfoIndex> productIndexList = productIndexRepository.findByCreateTimeBetween(1624498817091l, 1624517872619l, pageable);
        productIndexList.stream().forEach(obj -> log.info("{}", JSONObject.toJSONString(obj)));
    }
}
