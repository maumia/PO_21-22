package ggc.core;

import java.io.Serializable;

public class Date implements Serializable {

    private int _days;
    
    /**
    * Serial number for serialization.
    */
    private static final long serialVersionUID = 202109192006L;

    Date() {
        _days = 0;
    }

    int getCurrentDate() {
        return this._days;
    }

    void addDate(int days) {
        if (days > 0) {
            _days += days;
        }   
    }

    void setDays(int _days) {
        this._days = _days;
    }

    int difference(Date other) {
        return other._days - _days;
    }  
} 
