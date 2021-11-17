package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductBatchesUnderGivenPrice extends Command<WarehouseManager> {
  Form _form = new Form();

  public DoLookupProductBatchesUnderGivenPrice(WarehouseManager receiver) {
    super(Label.PRODUCTS_UNDER_PRICE, receiver);
    _form.addIntegerField("_price", Message.requestPriceLimit());
  }

  @Override
  public void execute() throws CommandException {
    _form.parse();
    int _price = _form.integerField("_price");
    for(String b: _receiver.LookupProductBatchesUnderGivenPrice(_price))
    _display.addLine(b);
  _display.display();
  }

}
