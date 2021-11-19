package ggc.core;

import java.util.ArrayList;
import java.util.Collection;

public class Breakdown extends Transaction{
    private ArrayList<Component> _components = new ArrayList<Component>();
    Breakdown(int id, Date paymentDate, Double baseValue, int quantity, Product product, Partner partner,ArrayList<Component> components){
        super(id, paymentDate, baseValue, quantity, product, partner);
        _components = components;
    }
    @Override
    public String toString(){
        return "DESAGREGAÇÃO|" + super.toString();
    }




}