package com.codewithedward.store.repositories;

import com.codewithedward.store.dtos.ProductSummary;
import com.codewithedward.store.dtos.ProductSummaryDTO;
import com.codewithedward.store.entities.Category;
import com.codewithedward.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product>
{

    // @Query("select p from Product p join p.category where p.price between :min and :max order by p.name")
    @Procedure("findProductsByPrice")
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("select p from Product p where p.price between :min and :max")
    long countProducts(BigDecimal min, BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    //@Query("select p.id, p.name from Product p where p.category = :category")
    List<ProductSummary> findByCategory(@Param("category") Category category);
}
