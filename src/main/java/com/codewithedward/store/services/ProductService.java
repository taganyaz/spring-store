package com.codewithedward.store.services;

import com.codewithedward.store.entities.Category;
import com.codewithedward.store.entities.Product;
import com.codewithedward.store.repositories.CategoryRepository;
import com.codewithedward.store.repositories.ProductRepository;
import com.codewithedward.store.repositories.UserRepository;
import com.codewithedward.store.repositories.specifications.ProductSpec;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@AllArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void createWithNewCategory() {
        Category category = new Category("Category 1");

        var product = Product.builder()
                .name("Product 1")
                .description("Description ")
                .price(BigDecimal.valueOf(100))
                .category(category)
                .build();

        productRepository.save(product);
        System.out.println("product save success: " + product);
    }

    @Transactional
    public void createWithExistingCategory() {
        var category = categoryRepository.findById((byte)1).orElseThrow();

        var product = Product.builder()
                .name("Product 2")
                .description("Description 2")
                .price(BigDecimal.valueOf(200.5))
                .category(category)
                .build();

        productRepository.save(product);
        System.out.println("product save success: " + product);
    }

    @Transactional
    public void addToWishlist() {
        var user = userRepository.findById(1L).orElseThrow();

        var products = productRepository.findAll();

        products.forEach(user::addFavoriteProduct);

        userRepository.save(user);
    }

    public  void  delete() {
        productRepository.deleteById(1L);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(124), (byte)1);
    }

    @Transactional
    public void fetch() {
        // var products = productRepository.findByCategory(new Category((byte)1));
        var products = productRepository.findProducts(BigDecimal.valueOf(100), BigDecimal.valueOf(200));
        products.forEach(System.out::println);
    }

    public void fetchWithDynamicQuery() {
        var product = new Product();
        product.setName("Product");

        var matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria(null, BigDecimal.valueOf(100), BigDecimal.valueOf(200));
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> specification = Specification.not(null);

        if (name != null) {
            specification = specification.and(ProductSpec.hasName(name));
        }

        if (minPrice != null) {
            specification = specification.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }

        if (maxPrice != null) {
            specification = specification.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(specification).forEach(System.out::println);
    }

    public  void fetchSortedProducts() {
        var sort = Sort.by("name")
                .and(Sort.by("price").descending());

        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();

        products.forEach(System.out::println);
        System.out.println("total pages: " + totalPages);
        System.out.println("total elements: " + totalElements);

    }

}
