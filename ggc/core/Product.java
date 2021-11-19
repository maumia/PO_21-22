package ggc.core;

import java.io.Serializable;
import java.util.*;

public abstract class Product implements Serializable{

    private String _idProduct;
    private String _idPartner;
    private double _price;
    private int _stock;
    private List<Batch> _batches;
    private int _n;
    private double _lowPrice;

    /**
    * Serial number for serialization.
    */
    private static final long serialVersionUID = 202109192006L;

    /**
   * @param idProduct
   * @param idPartner
   * @param price
   * @param n
   */
    Product(String idProduct, String idPartner, double price, int n) { 
        _idProduct = idProduct;
        _idPartner = idPartner;
        _price = price;
        _stock = 0;
        _batches = new ArrayList<>();
        _n = n;
        _lowPrice = price;
    }

    String getProductId() {
        return _idProduct;
    }

    String getPartnerId() {
        return _idPartner;
    }

    double getPrice() {
        return _price;
    }

    double getLowPrice() {
        return _lowPrice;
    }

    /**
    * @param price
    */
    void setPrice(Double price) {
        _price = price;
    }
    void setLowPrice(Double price) {
        _lowPrice = price;
    }

    int getStock() {
        return _stock;
    }

     /**
    * @param stock
    */
    void setStock(int stock) {
        _stock = stock;
    }
    void addStock(int amount){
        _stock += amount;
    }

    Collection<Batch> getBatches() {
        return _batches;
    }

     /**
    * @param batch
    */
    void checkPrice(Batch batch) {
        if(batch.getBatchPrice() > _price)
            setPrice(batch.getBatchPrice());
    }

    /**
    * @param batch
    */
    void checkLowPrice(Batch batch) {
        if(batch.getBatchPrice() < _lowPrice)
            _lowPrice = batch.getBatchPrice();
    }

    /**
    * @param batch
    */
    void addBatch(Batch batch) {
        _batches.add(batch);
        addStock(batch.getBatchStock());
        checkPrice(batch);
        checkLowPrice(batch);
    }

    /**
    * @param list
    */
    void addBatches(List<Batch> list){
        for(Batch batch: _batches)
            list.add(batch);
    }

    int getN() {
        return _n;
    }

    public abstract int checkQuantity();
    
    @Override
    public String toString() {
        return getProductId() + "|" + Math.round(getPrice()) + "|" + getStock();
    }    
}