package com.eshopweb.repository;

import com.eshopweb.domain.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Intellij Idea
 * Created by ivosahlik on 05/12/2018
 */
public interface ProductDtoRepository extends JpaRepository<ProductDto, Long> {

    @Query(value = "select " +
            "p.id," +
            "p.product_name," +
            "p.product_description," +
            "p.shipping_weight," +
            "p.concurent_price," +
            "p.our_price," +
            "p.in_stock_number," +
            "p.active," +
            "p.type," +
            "p.created," +
            "p.user_created," +
            "p.product_name_path," +
            "t.type_title_path," +
            "s.sub_categorytitle_path," +
            "c.category_title_path, " +
            "p.getaquote, " +
            "p.add_to_cart, " +
            "p.display_price, " +
            "p.estimated_delivery, " +
            "p.banner1, " +
            "p.banner2, " +
            "p.banner3, " +
            "p.banner4 " +
            "from product p " +
            "left join type t on p.type_id = t.id " +
            "left join sub_category s on s.id = t.sub_category_id " +
            "left join category c on c.id = s.category_id " +
            "where p.active = ?1 and c.active = true and s.active = true and t.active = true and c.app like %?2%", nativeQuery = true)
    List<ProductDto> findAllSeoByProduct(boolean active, String app);

    @Query(value = "select " +
            "p.id," +
            "p.product_name," +
            "p.product_description," +
            "p.shipping_weight," +
            "p.concurent_price," +
            "p.our_price," +
            "p.in_stock_number," +
            "p.active," +
            "p.type," +
            "p.created," +
            "p.user_created," +
            "p.product_name_path," +
            "t.type_title_path," +
            "s.sub_categorytitle_path," +
            "c.category_title_path, " +
            "p.getaquote, " +
            "p.add_to_cart, " +
            "p.display_price, " +
            "p.estimated_delivery, " +
            "p.banner1, " +
            "p.banner2, " +
            "p.banner3, " +
            "p.banner4 " +
            "from product p " +
            "inner join type t on t.id = p.type_id " +
            "inner join sub_category s on s.id = t.sub_category_id " +
            "inner join category c on c.id = s.category_id " +
            "where p.active = true and c.active = true and s.active = true and t.active = true and" +
            " lower(p.product_name) like ?1 and c.app like %?2%", nativeQuery = true)
    List<ProductDto> findAllByKeyword(String keyword, String app);
}
