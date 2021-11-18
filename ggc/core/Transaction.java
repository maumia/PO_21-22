package ggc.core;
import java.io.Serializable;

public abstract class Transaction implements Serializable {
        private int _id;
        private Date _paymentDate;
        private Double _baseValue;
        private int _quantity;
        private Product _product;
        private Partner _partner;

        /**
        * Serial number for serialization.
        */
        private static final long serialVersionUID = 202109192006L;

        Transaction(int id, Date paymentDate, Double baseValue, int quantity, Product product, Partner partner) {
            _id = id;
            _paymentDate = paymentDate;
            _baseValue = baseValue;
            _quantity = quantity;
            _product = product;
            _partner = partner;
        }

        int getTransactionID(){
                return _id;
        }

        void setPaymentDate(Date paymentDate) {
                _paymentDate = paymentDate;
        }

        Date getPaymentDate(){
                return _paymentDate;
        }

        Double getBaseValue(){
                return _baseValue;
        }

        int getQuantity(){
                return _quantity;
        }

        Product getProduct() {
                return _product;
            }

        Partner getPartner() {
                return _partner;
        }
        @Override
        public String toString(){
                return getTransactionID() + "|" + getPartner().getPartnerId() + "|" + getProduct().getProductId() + "|" + getQuantity() + "|" + Math.round(getBaseValue()*_quantity);
        }
}
