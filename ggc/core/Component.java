package ggc.core;

public class Component {

    private String _id;
    private int _quantity;

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
