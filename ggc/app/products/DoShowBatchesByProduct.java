package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.app.exception.UnknownProductKeyException;

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {
  Form _form = new Form();

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    _form.addStringField("_productId", Message.requestProductKey());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    String _productId = _form.stringField("_productId");
  
  if (_receiver.checkProduct(_productId) == false)
    throw new UnknownProductKeyException(_productId);
  else
    for(String b: _receiver.showBatchesByProduct(_productId))
    _display.addLine(b);
  _display.display();
  }
}



