package com.wk.learning.es;

import com.github.pagehelper.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductIndexRepository extends ElasticsearchRepository<ProductInfoIndex, String> {

    List<ProductInfoIndex> findByPriceBetween(Double priceStart, Double priceEnd);
    List<ProductInfoIndex> findByPriceLessThanEqual(Double priceStart);
    List<ProductInfoIndex> findByPriceGreaterThan(Double priceStart);

    List<ProductInfoIndex> findByCreateTimeBetween(long crtTimeStart, long crtTimeEnd, Pageable pageable);


    List<ProductInfoIndex> findByRemarkLike(String title, Sort sort);
    List<ProductInfoIndex> findByTitle(String title, Sort sort);

    Page<ProductInfoIndex> findByTitleStartingWith(String title, Pageable pageable);
}