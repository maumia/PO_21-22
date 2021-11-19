package ggc.core;

import java.io.Serializable;

public class Component implements Serializable {

    private String _id;
    private int _quantity;
    /**
    * Serial number for serialization.
    */
    private static final long serialVersionUID = 202109192006L;
    
    public Component(String id, int quantity) {
        _id = id;
        _quantity = quantity;
    }

    String getId() {
        return _id;
    }

    int getQuantity() {
        return _quantity;
    }
}
