package ggc.core;

public class SimpleProduct extends Product {

    SimpleProduct(String idProduct, String idPartner, double price, int stock) {
        super(idProduct, idPartner, price,stock, 5);
    }

    @Override
    public int checkQuantity() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
