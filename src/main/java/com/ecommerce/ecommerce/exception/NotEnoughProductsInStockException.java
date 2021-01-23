package com.ecommerce.ecommerce.exception;

import com.ecommerce.ecommerce.domaine.ProduitVo;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(ProduitVo product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getDesignation(), product.getQuantite()));
    }
}