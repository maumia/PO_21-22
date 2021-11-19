package ggc.core;

public class SaleByCredit extends Sale {

    private Date _deadline;
    private double _valueToPay;
    private double _discount;
    private double _fine;
    
    SaleByCredit(int id, Date paymentDate, Double baseValue, int quantity, Product product, Partner partner, Date deadline) {
        super(id, paymentDate, baseValue, quantity, product, partner);
        _valueToPay = baseValue* 0.9;
        _deadline = deadline;
    }

    int getValueToPay() {
        return (int)_valueToPay;
    }

    Date getDeadline() {
        return _deadline;
    }

    void setValueToPay(double valueToPay) {
        _valueToPay = valueToPay;
    }

    double getDiscount(){
        return _discount;
    }

    double getFine(){
        return _fine;
    }

    void setDiscountFine(int period){
        String status = getPartner().getPartnerStatus();
        if (period == 1){
           this._discount = .1;
           this._fine = 0;
          }
          if (period == 2){
            if (status.equals("NORMAL")){
                this._discount = 0;
                this._fine = 0; 
            }
            if (status.equals("SELECTION")){
                this._discount = .05;
                this._fine = 0; 
            }
            if (status.equals("ELITE")){
                this._discount = .1;
                this._fine = 0; 
            }
          }
          if (period == 3){
            if (status.equals("NORMAL")){
                this._discount = 0;
                this._fine = .05; 
            }
            if (status.equals("SELECTION")){
                this._discount = 0;
                this._fine = 0; 
            }
            if (status.equals("ELITE")){
                this._discount = .05;
                this._fine = 0; 
            }
          }
          if (period == 4){
            if (status.equals("NORMAL")){
                this._discount = 0;
                this._fine = .1; 
            }
            if (status.equals("SELECTION")){
                this._discount = 0;
                this._fine = .05; 
            }
            if (status.equals("ELITE")){
                this._discount = 0;
                this._fine = 0; 
            }
          }
    }

    @Override
    public String toString(){
        return super.toString() + "|" + getValueToPay() + "|" + getDeadline().getCurrentDate() + "|" + getPaymentDate().getCurrentDate();
    }
}