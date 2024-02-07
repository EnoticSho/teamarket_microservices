package com.example.teamarket.cart.model;

import com.example.teamarket.cart.dto.response.InfoProductDto;
import com.example.teamarket.cart.exceptions.IncorrectProductWeight;
import com.example.teamarket.cart.exceptions.ResourceNotFoundException;
import com.example.teamarket.cart.utils.CartUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a shopping cart in an e-commerce platform, allowing for the management of cart items,
 * including adding, editing, and removing items, as well as calculating the total cost.
 * This class is designed to be used as a Spring Component, indicating it should be automatically
 * detected and registered as a bean in the Spring application context.
 */
@Getter
@Component
@NoArgsConstructor
public class Cart implements Serializable {

    private final Map<Long, CartItem> itemsMap = new HashMap<>();
    private BigDecimal totalCost = BigDecimal.ZERO;

    /**
     * Adds a product to the cart or updates its quantity if it already exists. The product and its quantity
     * are specified by the {@link InfoProductDto} and weight parameters, respectively. After adding or updating
     * an item, the total cost of the cart is recalculated.
     *
     * @param infoProductDto the product information Data Transfer Object (DTO) containing details like product ID, name, price, and image links.
     * @param weight the quantity of the product to be added to the cart, measured in weight units.
     */
    public void addItem(InfoProductDto infoProductDto, int weight) {
        CartItem cartItem = itemsMap.get(infoProductDto.productId());
        if (cartItem != null) {
            cartItem.changeQuantity(weight);
        }
        else {
            CartItem build = CartItem.builder()
                    .id(infoProductDto.productId())
                    .name(infoProductDto.name())
                    .weight(weight)
                    .costByHundredGrams(infoProductDto.price())
                    .sum(CartUtils.calculateAmount(infoProductDto.price(), weight))
                    .imagesLink(infoProductDto.imagesLinks().get(0))
                    .build();
            itemsMap.put(infoProductDto.productId(), build);
        }
        recalculateTotalCost();
    }

    /**
     * Edits the quantity of an existing cart item identified by its product ID. The method allows for
     * increasing or decreasing the item's quantity based on the provided weight parameter. If the resulting
     * quantity is invalid (e.g., less than zero), an {@link IncorrectProductWeight} exception is thrown.
     * The cart's total cost is recalculated after the item is edited.
     *
     * @param id the product ID of the cart item to be edited.
     * @param weight the amount by which the item's quantity is to be adjusted. Can be negative for a decrease.
     * @throws ResourceNotFoundException if the item with the specified ID does not exist in the cart.
     * @throws IncorrectProductWeight if the resulting quantity after adjustment is invalid.
     */
    public void editCartItem(Long id, int weight) {
        CartItem cartItem = itemsMap.get(id);
        if (cartItem == null) {
            throw ResourceNotFoundException.of(id, CartItem.class);
        }

        if (cartItem.getWeight() + weight < 0) {
            throw IncorrectProductWeight.of(cartItem.getId(), cartItem.getWeight(), weight);
        }

        cartItem.changeQuantity(weight);
        removeItemIfNecessary(id, cartItem);
        recalculateTotalCost();
    }

    /**
     * Removes an item from the cart based on its product ID. If the item is successfully removed,
     * the method returns the weight of the removed item and recalculates the total cost of the cart.
     *
     * @param id the product ID of the item to be removed from the cart.
     * @return the weight of the removed item.
     * @throws ResourceNotFoundException if the item with the specified ID does not exist in the cart.
     */
    public Integer removeItemById(Long id) {
        CartItem removedItem = itemsMap.remove(id);
        if (removedItem == null) {
            throw ResourceNotFoundException.of(id, CartItem.class);
        }

        recalculateTotalCost();
        return removedItem.getWeight();
    }

    /**
     * Removes all items from the cart, clearing the items map and resetting the total cost to zero.
     * Returns a map of product IDs to their respective quantities for all items that were removed.
     *
     * @return a map containing the IDs and quantities of all items removed from the cart.
     */
    public Map<Long, Integer> removeAllItems() {
        Map<Long, Integer> removedItemsWeights = new HashMap<>();
        itemsMap.forEach((id, item) -> removedItemsWeights.put(id, item.getWeight()));
        itemsMap.clear();
        totalCost = BigDecimal.ZERO;
        return removedItemsWeights;
    }

    /**
     * Recalculates the total cost of the cart based on the sum of the costs of all items in the cart.
     * This method is called internally after any operation that modifies the contents of the cart.
     */
    private void recalculateTotalCost() {
        totalCost = itemsMap.values().stream()
                .map(CartItem::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void removeItemIfNecessary(Long id, CartItem cartItem) {
        if (cartItem.getWeight() <= 0) {
            itemsMap.remove(id);
        }
    }
}
