package ggc.core;

import java.io.Serializable;

public class Batch implements Serializable {

    private Product _product;
    private double _price;
    private int _stock;
    private Partner _partner;
    
    /**
    * Serial number for serialization.
    */
    private static final long serialVersionUID = 202109192006L;

     /**
    * @param product
    * @param price 
    * @param stock
    * @param partner 
    */
    Batch(Product product, double price, int stock, Partner partner) {
        _product = product;
        _price = price;
        _stock = stock;
        _partner = partner;
    }

    String getBatchProductId() {
        return _product.getProductId();
    }
    
    double getBatchPrice() {
        return _price;
    }

    int getBatchStock() {
        return _stock;
    }

    String getBatchPartnerId() {
        return _partner.getPartnerId();
    }

    @Override
    public String toString() {
        return getBatchProductId() + "|" + getBatchPartnerId() + "|" + Math.round(getBatchPrice()) + "|" + getBatchStock();
    }
}
