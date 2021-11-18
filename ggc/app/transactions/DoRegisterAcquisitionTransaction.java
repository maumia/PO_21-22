package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import ggc.core.WarehouseManager;
import ggc.app.exception.UnknownPartnerKeyException;

/**
 * Register order.
 */
class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {
  private Form _form = new Form();

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    
    _form.addStringField("_partnerId", Message.requestPartnerKey());
    _form.addStringField("_productId", Message.requestProductKey());
    _form.addRealField("_price", Message.requestPrice());
    _form.addIntegerField("_amount", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    Form _fform = new Form();
    String _partnerId = _form.stringField("_partnerId");
    String _productId = _form.stringField("_productId");
    Double _price = _form.realField("_price");
    int _amount = _form.integerField("_amount");
    if(_receiver.checkPartner(_partnerId) == true) {
      if(_receiver.checkProduct(_productId) == false) {
        /*
        _fform.addStringField("_answear", Message.requestAddRecipe());
        _fform.parse();
        String _answear = _fform.stringField("_answear");
        if (_answear.equals("n")){
          _receiver.registerSimpleAcquisition(_partnerId, _productId, _price, _amount);
        }
        else
          _display.display();
        */
        _receiver.registerSimpleAcquisition(_partnerId, _productId, _price, _amount);
      }
      else
        /*
        _fform.addStringField("_answear", Message.requestAddRecipe());
        _fform.parse();
        String _answear = _fform.stringField("_answear");
        if (_answear.equals("n")){
          _receiver.registerSimpleAcquisition(_partnerId, _productId, _price, _amount);
        }
        else
          _display.display();
        */
          _receiver.addSimpleProduct(_receiver.getProduct(_productId));
          _receiver.registerSimpleAcquisition(_partnerId, _productId, _price, _amount);
        
      }
  else 
    throw new UnknownPartnerKeyException(_partnerId);
  }
}
