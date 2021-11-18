package ggc.core;

public class AggregateProduct extends Product {

    private Recipe _recipe;
    private double _aggravation;

	AggregateProduct(String idProduct, String idPartner, double price,int stock, double aggravation, Recipe recipe) {
    	super(idProduct, idPartner, price, stock, 3);
        _aggravation = aggravation;
        _recipe = recipe;
    }

    @Override
    public int checkQuantity() {
        return 0;
    }

    Recipe getRecipe(){
        return _recipe;
    }
    
    @Override
    public String toString() {
        return super.toString() + "|" + getRecipe().toString(); 
    }
}
