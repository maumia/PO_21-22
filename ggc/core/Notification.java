package ggc.core;

import java.io.Serializable;
import java.util.*;

public class Notification implements Serializable {

    /**
    * Serial number for serialization.
    */
    private static final long serialVersionUID = 202109192006L;

    private List<NotificationObserver> observers = new ArrayList<>();
    private List<String> userNotificationAuxList = new ArrayList<>();
    private String _type;
    private Product _product;

    public void SetNotification(Product product) {
        _product = product;
    }

    public void attach(NotificationObserver observer) {
        observers.add(observer);
        userNotificationAuxList.add(observer.getMessage());
    }

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
        userNotificationAuxList.add(observer.getMessage());
    }

    public List<String> returnAllNotifications() {
        return userNotificationAuxList;
    }


abstract class NotificationObserver implements Serializable {
    
    private static final long serialVersionUID = 201810051538L;
    Notification _notifications;

    public abstract String getMessage();
}

class NewProductObserver extends NotificationObserver {

    public NewProductObserver(Notification notifications) {
        _notifications = notifications;
        this._notifications.attach(this);
    }

    @Override
    public String getMessage() {
        return _product.getProductId() + "NEW"; 
    }
}

class BargainProductObserver extends NotificationObserver {

    public BargainProductObserver(Notification notifications) {
        _notifications = notifications;
        this._notifications.attach(this);
    }

    @Override
    public String getMessage() {
       return _product.getProductId() + "BARGAIN"; 
    }
}
}