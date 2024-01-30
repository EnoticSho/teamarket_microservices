package com.example.teamarket.core.specification;

import com.example.teamarket.core.entity.Product;
import org.springframework.data.jpa.domain.Specification;

/**
 * Utility class for creating specifications for querying products.
 */
public class ProductSpecifications {

    /**
     * Creates a specification to filter products with a price greater than or equal to the given price.
     *
     * @param price The minimum price to filter by.
     * @return A Specification object for filtering products by price.
     */
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    /**
     * Creates a specification to filter products with a price less than or equal to the given price.
     *
     * @param price The maximum price to filter by.
     * @return A Specification object for filtering products by price.
     */
    public static Specification<Product> priceLessOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    /**
     * Creates a specification to filter products with a title that contains the given title part.
     *
     * @param namePart The partial title to search for within product titles.
     * @return A Specification object for filtering products by title.
     */
    public static Specification<Product> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}
