package ggc.core;

import java.util.ArrayList;
import java.util.Collection;

public class Breakdown extends Transaction{
    private ArrayList<Component> _components = new ArrayList<Component>();
    Breakdown(int id, Date paymentDate, Double baseValue, int quantity, Product product, Partner partner,ArrayList<Component> components){
        super(id, paymentDate, baseValue, quantity, product, partner);
        _components = components;
    }
    public Collection compToString(Recipe recipe){
        Collection<String> _description = new ArrayList<String>();
        for(Component component : _components){
           _description.add(component.toString());
        }
        return _description;

    }
    
    @Override
    public String toString(){
        return "DESAGREGAÇÃO|" + super.toString();
    }




}