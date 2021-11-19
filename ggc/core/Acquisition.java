package ggc.core;

public class Acquisition extends Transaction {

    Acquisition(int id, int paymentDate, Double baseValue, int quantity, Product product, Partner partner) {
        super(id, paymentDate, baseValue, quantity, product, partner);
    }

    @Override
    public String toString(){
        return "COMPRA|" + super.toString() + "|" + getPaymentDate();
    }
}