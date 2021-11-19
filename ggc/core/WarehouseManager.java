package ggc.core;

import java.io.*;
import java.util.*;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;
import ggc.app.exception.UnknownTransactionKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.app.exception.UnknownPartnerKeyException;

/** Façade for access. */
public class WarehouseManager {

  private String _filename;
  private Warehouse _warehouse = new Warehouse();

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    ObjectOutputStream save = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
    try {
      save.writeObject(_warehouse);
    } catch (FileNotFoundException e ) {
      throw new MissingFileAssociationException();
    } catch (IOException e) {
      e.printStackTrace(); 
    } finally {
      save.close();
    }
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    if (filename == null){
      throw new MissingFileAssociationException();
    }
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws ClassNotFoundException, FileNotFoundException, IOException, BadEntryException {
    ObjectInputStream textfile = new ObjectInputStream(new FileInputStream(filename));

    if (textfile == null)
      throw new FileNotFoundException();

    Warehouse w = (Warehouse)textfile.readObject();
    textfile.close();
    _warehouse = w;
    _filename = filename;
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException, UnknownProductKeyException, UnknownPartnerKeyException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(textfile, e);
    }
  }

  public int getCurrentDate() {
    return _warehouse.getCurrentDate();
  }
  
  public int addDate(int days) {
    return _warehouse.addDate(days);
  }

  public void addPartner(Partner partner) {
    _warehouse.addPartner(partner);
  }

  public void addSimpleProduct(Product product){
    _warehouse.addSimpleProduct(product);
  }

  public Product getProduct(String id) throws UnknownProductKeyException{
    return _warehouse.getProduct(id);
  }

  public void addAggregateProduct(Product product){
    _warehouse.addAggregateProduct(product);
  }

  public String showPartner(String id){
    return _warehouse.showPartner(id);
  }

  public Collection<String> showAllPartners() {
    return _warehouse.showAllPartners();
  }

  public Collection<String> showAllProducts() {
    return _warehouse.showAllProducts();
  }

  public String getFileName() {
    return _filename;
  }

  public Collection<String> showAvailableBatches() {
    return _warehouse.showAvailableBatches();
  }

  public boolean checkPartner(String id) {
    return _warehouse.checkPartner(id);
  }

  public boolean checkProduct(String id) {
    return _warehouse.checkProduct(id);
  }

  public Collection<String> showBatchesByPartner(String id) {
    return _warehouse.showBatchesByPartner(id);
  }

  public Collection<String> showBatchesByProduct(String id) {
    return _warehouse.showBatchesByProduct(id);
  }

  public Collection<String> LookupProductBatchesUnderGivenPrice(int price) {
    return _warehouse.LookupProductBatchesUnderGivenPrice(price);
  }

  public void addTransaction(Transaction transaction){
    _warehouse.addTransaction(transaction);
  }

  public void registerSimpleAcquisition(String partnerId, String productId, Double price, int amount) throws UnknownProductKeyException, UnknownPartnerKeyException  {
    _warehouse.registerSimpleAcquisition(partnerId, productId, price, amount);
  }

  public void registerAggregateAcquisition(String partnerId, String productId, Double price, int amount, String[] components, int[] quantities, Double aggravation) throws UnknownProductKeyException, UnknownPartnerKeyException {
    _warehouse.registerAggregateAcquisition(partnerId, productId, price, amount, components, quantities, aggravation);
  }
  /*
  public void registerBatchAcquisition(String productId, double price, int stock, String partnerId) {
    _warehouse.registerBatchAcquisition(productId, price, stock, partnerId);
  }
  */

  public Collection<String> showPaidTransactions(String id){
    return _warehouse.ShowPaidTransactions(id);
  }

  public double getAvailableBalance(){
    return _warehouse.getAvailableBalance();
  }

  public double getContabilisticBalance(){
    return _warehouse.getContabilisticBalance();
  }

  public Collection<String> showAcquisitionsByPartner(String id){
    return _warehouse.ShowAcquisitionsByPartner(id);
  }

  public int getProductStock(String id) throws UnknownProductKeyException, UnknownPartnerKeyException {
    return _warehouse.getStockProduct(id);
  }

  public void registerSimpleByCredit(String partnerId, int deadline, String productId, int quantity) throws UnknownProductKeyException, UnknownPartnerKeyException {
    _warehouse.registerSimpleByCredit(partnerId, deadline, productId, quantity);
  }

  public void paySale(int transactionId) throws UnknownTransactionKeyException {
    _warehouse.paySale(transactionId);
  }

  public Collection<String> ShowSalesByPartner(String id) {
    return _warehouse.ShowSalesByPartner(id);
  }

  public String showTransaction(int transactionId) throws UnknownTransactionKeyException{
    return _warehouse.showTransaction(transactionId);
  }
}
