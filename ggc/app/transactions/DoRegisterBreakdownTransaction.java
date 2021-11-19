package ggc.app.transactions;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownPartnerKeyException;


/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {
  private Form _form = new Form();
  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    _form.addStringField("_partnerId", Message.requestPartnerKey());
    _form.addStringField("_productId", Message.requestProductKey());
    _form.addIntegerField("_amount", Message.requestAmount());
  }
  @Override
  public final void execute() throws CommandException {
    _form.parse();
    String _partnerId = _form.stringField("_partnerId");
    String _productId = _form.stringField("_productId");
    int _amount = _form.integerField("_amount");
    if(_receiver.checkPartner(_partnerId) == true) {

    }
    else
      throw new UnknownPartnerKeyException(_partnerId);
  }

}
