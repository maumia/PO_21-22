package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnavailableProductException;

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {
  Form _form = new Form();

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    
    _form.addStringField("_partnerId", Message.requestPartnerKey());
    _form.addIntegerField("_deadline", Message.requestPaymentDeadline());
    _form.addStringField("_productId", Message.requestProductKey());
    _form.addIntegerField("_amount", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    String _partnerId = _form.stringField("_partnerId");
    int _deadline = _form.integerField("_deadline");
    String _productId = _form.stringField("_productId");
    int _amount = _form.integerField("_amount");

    if(_receiver.checkPartner(_partnerId) == true) {
      if(_receiver.getProductStock(_productId) >= _amount) {
        _receiver.registerSimpleByCredit(_partnerId, _deadline, _productId, _amount);
      }
      else
        throw new UnavailableProductException(_productId, _amount, _receiver.getProductStock(_productId));
    }
    else 
      throw new UnknownPartnerKeyException(_partnerId);
  }

}
