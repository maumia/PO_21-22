package ggc.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.exception.BadEntryException;
import java.util.*;

public class Parser {

  private Warehouse _store;
  
  public Parser(Warehouse w) {
    _store = w;
  }

  void parseFile(String filename) throws IOException, BadEntryException, UnknownProductKeyException, UnknownPartnerKeyException   {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
  }

  private void parseLine(String line) throws BadEntryException,UnknownProductKeyException, UnknownPartnerKeyException  {
    String[] components = line.split("\\|");

    switch (components[0]) {
      case "PARTNER":
        parsePartner(components, line);
        break;
      case "BATCH_S":
        parseSimpleProduct(components, line);
        break;
      case "BATCH_M":
        parseAggregateProduct(components, line);
        break;
      default:
        throw new BadEntryException("Invalid type element: " + components[0]);
    }
  }

  private void parsePartner(String[] components, String line) throws BadEntryException {
    if (components.length != 4)
      throw new BadEntryException("Invalid partner with wrong number of fields (4): " + line);
    Partner partner = new Partner(components[1],components[2],components[3]);
    _store.addPartner(partner);
  }

  private void parseSimpleProduct(String[] components, String line) throws BadEntryException, UnknownProductKeyException, UnknownPartnerKeyException {
    if (components.length != 5)
      throw new BadEntryException("Invalid number of fields (5) in simple batch description: " + line);
    
    String idProduct = components[1];
    String idPartner = components[2];
    double price = Double.parseDouble(components[3]);
    int stock = Integer.parseInt(components[4]);

    if (_store.checkProduct(idProduct) == false) {
      Product product = new SimpleProduct(idProduct, idPartner, price);
      _store.addSimpleProduct(product);
    }

    Product product2 = _store.getProduct(idProduct);
    Partner partner = _store.getPartner(idPartner);

    Batch batch = new Batch(product2, price, stock, partner);
    _store.addBatch(product2, batch);    
  }

  private void parseAggregateProduct(String[] components, String line) throws BadEntryException,UnknownProductKeyException, UnknownPartnerKeyException  {
    if (components.length != 7)
      throw new BadEntryException("Invalid number of fields (7) in aggregate batch description: " + line);
    
    String idProduct = components[1];
    String idPartner = components[2];
    double price = Double.parseDouble(components[3]);
    int stock = Integer.parseInt(components[4]);
    double aggravation = Double.parseDouble(components[5]);

    if (_store.checkProduct(idProduct) == false) {
      List<Product> _products = new ArrayList<>();
      List<Integer> _quantities = new ArrayList<>();
      Recipe _newRecipe = new Recipe();
            
      for (String component : components[6].split("#")) {
        String[] recipeComponent = component.split(":");
        _products.add(_store.getProduct(recipeComponent[0]));
        _quantities.add(Integer.parseInt(recipeComponent[1]));
        Component newToRecipe = new Component(recipeComponent[0], Integer.parseInt(recipeComponent[1]));
        _newRecipe.addComponent(newToRecipe);
      }
      
      Product product = new AggregateProduct(idProduct, idPartner, price, aggravation, _newRecipe);
      _store.addAggregateProduct(product);
      _store.addRecipe(_newRecipe);
    }
    
    Product product = _store.getProduct(idProduct);
    Partner partner = _store.getPartner(idPartner);
    
    Batch batch = new Batch(product, price, stock, partner);
    _store.addBatch(product, batch);
  }
}

  