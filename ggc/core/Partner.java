package ggc.core;

import java.io.Serializable;

public class Partner implements Serializable {
    private String _name;
    private String _address;
    private String _id;
    private String _status;
    private double _points;
    private double _shoppingValue;
    private double _sellsValueMade;
    private double _sellsValuePaid;

    /**
    * Serial number for serialization.
    */
    private static final long serialVersionUID = 202109192006L;
    
    public Partner(String id, String name, String address) {
        _id = id;  
        _name = name;
        _address = address;
        _status = "NORMAL";
        _points = 0;
        _shoppingValue = 0;
        _sellsValueMade = 0;
        _sellsValuePaid = 0;
    }

    void statusPartner() {
        if (getPartnerPoints() > 25000)
            _status = "ELITE";
        else if(getPartnerPoints() < 25000 && getPartnerPoints() > 2000)
            _status = "SELECTION";
        else 
            _status = "NORMAL";
    }

    String getPartnerName() {
        return _name;
    }

    String getPartnerAdress() {
        return _address;
    }

    String getPartnerId() {
        return _id;
    }

    String getPartnerStatus() {
        return _status;
    }

    double getPartnerPoints() {
        return _points;
    }

    void addPartnerPoints(double newPoints) {
        _points += newPoints;
    }

    double getPartnerShoppingValue() {
    	return _shoppingValue;
    }

    double getPartnerSellsValueMade() {
    	return _sellsValueMade;
    }

    void addPartnerShoppingValue(Double value){
        _shoppingValue += value;
    }

    double getPartnerSellsValuePaid() {
   	 return _sellsValuePaid;
    }

    @Override
    public String toString() {
        return getPartnerId() + "|" + getPartnerName() + "|" + getPartnerAdress() + "|" + getPartnerStatus() + "|" + Math.round(getPartnerPoints()) + "|" +  Math.round(getPartnerShoppingValue()) + "|" +  Math.round(getPartnerSellsValueMade()) + "|" +  Math.round(getPartnerSellsValuePaid());
    }
}
