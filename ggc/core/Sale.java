package ggc.core;

public abstract class Sale extends Transaction {
  
    Sale(int id, int paymentDate, Double baseValue, int quantity, Product product, Partner partner) {
        super(id, paymentDate, baseValue, quantity, product, partner);
    }
        
    @Override
    public String toString(){
        return "VENDA|" + super.toString();
    }
}

