package ggc.core;

import java.io.Serializable;
import java.io.IOException;
import ggc.core.exception.BadEntryException;
import java.util.*;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /**
  * Serial number for serialization.
  */
  private static final long serialVersionUID = 202109192006L;

  private Date _date;
  private Double _availableBalance = 0.0;
  private Double _contabilisticBalance = 0.0;
  private List<Partner> _partners;
  private List<Product> _products;
  private List<Recipe> _recipes;
  private List<Transaction> _transactions;
  private List<Acquisition> _acquisitions;
  private List<SaleByCredit> _salesByCredict;
  private int _transactionId = 0;

  public Warehouse() {
    _partners = new ArrayList<>();
    _products = new ArrayList<>();
    _recipes = new ArrayList<>();
    _date = new Date();
    _transactions = new ArrayList<>();
    _acquisitions =  new ArrayList<>();
    _salesByCredict = new ArrayList<>();
  }

  void importFile(String filename) throws IOException, BadEntryException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }
  
  protected int getCurrentDate() {
    return _date.getCurrentDate();
  }
  
  protected int addDate(int days) {
    _date.addDate(days);
    return _date.getCurrentDate();
  }

  protected void addPartner(Partner partner) {
    _partners.add(partner);
  }

  protected Partner getPartner(String id){
    for(Partner partner: _partners){
      if(id.toUpperCase().equals(partner.getPartnerId().toUpperCase()))
        return partner;
    }
    return null;
  }

  protected String showPartner(String id) {
    for(Partner partner: _partners){
      if (id.toUpperCase().equals(partner.getPartnerId().toUpperCase()))
        return partner.toString();
    }
    return null;
  }

  protected List<String> showAllPartners() {
    List<Partner> partnersCopy = new ArrayList<>();
    List<String> description = new ArrayList<>();

    for(Partner partner: _partners)
      partnersCopy.add(partner);
    
    partnersCopy.sort(partnerComparator);
    for(Partner partner: partnersCopy)
      description.add(partner.toString());

    return description;
  }
  
  protected void addSimpleProduct(Product product) {
       _products.add(product);
  }

  protected void addAggregateProduct(Product product) {
    _products.add(product);
  } 

  protected boolean checkPartner(String id) {
    for(Partner partner: _partners){
      if(id.toUpperCase().equals(partner.getPartnerId().toUpperCase()))
        return true;
    }
    return false;
  }

  protected boolean checkProduct(String id) {
    for(Product product: _products){
      if(id.equals(product.getProductId()))
        return true;
    }
    return false;
  }
 
  public Product getProduct(String id){
    for(Product product: _products){
      if(id.equals(product.getProductId()))
        return product;
    }
    return null;
  }

  public static Comparator<Product> productComparator = new Comparator<Product>() {
    @Override
    public int compare(Product p1, Product p2){
        return p1.getProductId().toUpperCase().compareTo(p2.getProductId().toUpperCase());
    }
  };

  public static Comparator<Partner> partnerComparator = new Comparator<Partner>() {
    @Override
    public int compare(Partner p1, Partner p2){
        return p1.getPartnerId().toUpperCase().compareTo(p2.getPartnerId().toUpperCase());
    }
  };

  public static Comparator<Batch> batchComparator = new Comparator<Batch>() {
   @Override
    public int compare(Batch b1, Batch b2){
      if(b1.getBatchProductId().equals(b2.getBatchProductId())) {
        if(b1.getBatchPartnerId().equals(b2.getBatchPartnerId())) {     
         if(b1.getBatchPrice() == b2.getBatchPrice()) {
           return b1.getBatchStock() - b2.getBatchStock();
          }
          return (int)(b1.getBatchPrice() - b2.getBatchPrice());
        }   
        return b1.getBatchPartnerId().compareTo(b2.getBatchPartnerId());
     }
      return b1.getBatchProductId().compareTo(b2.getBatchProductId());
   }
  };

  protected List<String> showAllProducts(){
    List<Product> productsCopy = new ArrayList<>();
    List<String> description = new ArrayList<>();

    for(Product product: _products)
      productsCopy.add(product);
    
    productsCopy.sort(productComparator);
    for(Product product: productsCopy)
      description.add(product.toString());

    return description;
  }

  protected void addBatch(Product product, Batch batch) {
    product.addBatch(batch);
  }

  protected List<String> showAvailableBatches() {
    List<Batch> batchesCopy = new ArrayList<>();
    List<String> description = new ArrayList<>();

    for(Product product: _products)
      product.addBatches(batchesCopy);

    batchesCopy.sort(batchComparator);
    for(Batch batch: batchesCopy)
      description.add(batch.toString());
    return description;
  }

  protected void addRecipe(Recipe recipe) {
    _recipes.add(recipe);
  }

  protected List<String> showBatchesByPartner(String id) {
    List<Batch> batchesCopy = new ArrayList<>();
    List<String> description = new ArrayList<>();

    for(Product product: _products)
      product.addBatches(batchesCopy);

    batchesCopy.sort(batchComparator);
    for(Batch batch: batchesCopy) {
      if(id.equals(batch.getBatchPartnerId())) 
        description.add(batch.toString());
    }
    return description;
  }

  protected List<String> showBatchesByProduct(String id) {
    List<String> description = new ArrayList<>();
    List<Batch> batchesCopy = new ArrayList<>();

    for(Product product: _products)
      product.addBatches(batchesCopy);

    batchesCopy.sort(batchComparator);
    for(Batch batch: batchesCopy) {
      if(id.equals(batch.getBatchProductId())) 
        description.add(batch.toString());
    }
    return description;
  }

  protected List<String> LookupProductBatchesUnderGivenPrice(int price) {
    List<String> description = new ArrayList<>();
    List<Batch> batchesCopy = new ArrayList<>();

    for(Product product: _products)
      product.addBatches(batchesCopy);

    batchesCopy.sort(batchComparator);
    for(Batch batch: batchesCopy) {
      if(price > (int)(batch.getBatchPrice())) 
        description.add(batch.toString());
    }
    return description;
  }
  
  protected void addTransaction(Transaction transaction){
    _transactions.add(transaction);
  }

  protected Collection<String> ShowAcquisitionsByPartner(String id){
    List<String> description = new ArrayList<>();
    for(Acquisition acquisition: _acquisitions){
      if(id.toUpperCase().equals(acquisition.getPartner().getPartnerId().toUpperCase()))
        description.add(acquisition.toString());
    }
    return description;
  }

  protected void registerSimpleAcquisition(String partnerId, String productId, Double price, int amount) {
    Product p = new SimpleProduct(productId, partnerId, price);
    Batch b = new Batch(p, price, amount, getPartner(partnerId));
      if(checkProduct(p.getProductId()) == true){
        if((getProduct(productId) instanceof SimpleProduct) == true){
          Product var = getProduct(p.getProductId());
          var.addBatch(b);
          if (var.getPrice() < p.getPrice())
                  var.setPrice(p.getPrice());
        }
        else{
          Product var = getProduct(p.getProductId());
          var.addBatch(b);
          if (var.getPrice() < p.getPrice())
                  var.setPrice(p.getPrice());
        
          if (var.getLowPrice() > p.getPrice())
            var.setLowPrice(p.getPrice());
        }

      }
      else{
        addSimpleProduct(p);
        p.addBatch(b);
      }
    
    Acquisition acquisition = new Acquisition(_transactionId, _date, price, amount, getProduct(productId), getPartner(partnerId));
    getPartner(partnerId).addPartnerShoppingValue(price * amount);
    _transactions.add(acquisition);
    _acquisitions.add(acquisition);
    _transactionId++;
    _availableBalance -= price * amount;
    _contabilisticBalance -= price * amount;
  }
  
  protected void registerAggregateAcquisition(String partnerId, String productId, Double price, int amount, String[] components, int[] quantities, Double aggravation) {
   int i = 0;
   Recipe _recipe = new Recipe();

    while(i < components.length) {
      Component component = new Component(components[i], quantities[i]);
      _recipe.addComponent(component);
      i++;
    }

    Product p = new AggregateProduct(productId, partnerId, price, aggravation, _recipe);
    Batch b = new Batch(p, price, amount, getPartner(partnerId));

    addAggregateProduct(p);
    p.addBatch(b);
    

    Acquisition acquisition = new Acquisition(_transactionId, _date, price, amount, getProduct(productId), getPartner(partnerId));
    getPartner(partnerId).addPartnerShoppingValue(price * amount);
    _transactions.add(acquisition);
    _acquisitions.add(acquisition);
    _transactionId++;
    _availableBalance -= price * amount;
    _contabilisticBalance -= price * amount;
  }
  protected double getAvailableBalance() {
    return _availableBalance;
  }

  protected double getContabilisticBalance() {
    return _contabilisticBalance;
  }

  protected void registerSimpleByCredit(String partnerId, int deadline, String productId, int quantity){ 
    Product product = getProduct(productId);
    Double baseValue = product.getLowPrice();      
    Date date = new Date();
    date.setDays(deadline);
    date.addDate(getCurrentDate());

    SaleByCredit saleByCredit = new SaleByCredit(_transactionId, new Date(), baseValue, quantity, product, getPartner(partnerId), date);  
    
    product.setStock(product.getStock() - quantity);
    _transactions.add(saleByCredit);
    _salesByCredict.add(saleByCredit);
    _transactionId++;
    _contabilisticBalance += baseValue;
  }

  protected int getStockProduct(String id) {
    Product product = getProduct(id);
    return product.getStock();
  }

  protected SaleByCredit getSaleByCredit(int transactionId) {
    for(SaleByCredit saleByCredit: _salesByCredict){
      if(transactionId == saleByCredit.getTransactionID())
        return saleByCredit;
    }
    return null;
  }

  protected int period(SaleByCredit saleByCredit) {
    int deadline = saleByCredit.getDeadline().getCurrentDate();
    Product product = saleByCredit.getProduct();

    if(deadline - getCurrentDate() >= product.getN()) {
      return 1;
    }
    else if(0 <= deadline - getCurrentDate() && deadline - getCurrentDate() < product.getN()) {
      return 2;
    }
    else if(0 < getCurrentDate() - deadline && getCurrentDate() - deadline <= product.getN()) {
      return 3;
    }
    else 
      return 4;
  }

  protected void setValueToPay(int transactionId) {
    SaleByCredit saleByCredit = getSaleByCredit(transactionId);
    int period = period(saleByCredit);
    saleByCredit.setDiscountFine(period);
    double fine = saleByCredit.getFine();
    double discount = saleByCredit.getDiscount();
    double baseValue = saleByCredit.getBaseValue();
    saleByCredit.setValueToPay(baseValue - (baseValue * discount) + (baseValue * fine));
  }
   
  protected void paySale(int transactionId) {
    setValueToPay(transactionId);
    SaleByCredit saleByCredit = getSaleByCredit(transactionId);
    Partner partner = saleByCredit.getPartner();
    if(saleByCredit.getValueToPay() != 0){
      _availableBalance += saleByCredit.getValueToPay();
      partner.addPartnerPoints(saleByCredit.getValueToPay() * 10);
    } 
  }

  protected Collection<String> ShowSalesByPartner(String id) {
    List<String> description = new ArrayList<>();
    for(SaleByCredit saleByCredit: _salesByCredict){
      if(id.equals(saleByCredit.getPartner().toString()))
        description.add(saleByCredit.toString());
    }
    return description;
  }

  protected String showTransaction(int transactionId){
    for(Acquisition acquisition: _acquisitions){
      if(acquisition.getTransactionID() == transactionId){
        return acquisition.toString();
      }
    }

    for(SaleByCredit saleByCredit: _salesByCredict)
      if(saleByCredit.getTransactionID() == transactionId){
        return saleByCredit.toString();
      }
    return null;
  }

  protected Collection<String> ShowPaidTransactions(String id){
    List<String> description = new ArrayList<>();
   
    for(Acquisition acquisition: _acquisitions){
      if(id.equals(acquisition.getPartner().getPartnerId()))
          description.add(acquisition.toString());
    }

    for(SaleByCredit saleByCredit: _salesByCredict) {
      if(id.equals(saleByCredit.getPartner().getPartnerId()))
        description.add(saleByCredit.toString());
    }
    return description;
  }
}






