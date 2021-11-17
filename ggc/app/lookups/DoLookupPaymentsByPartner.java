package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.app.exception.UnknownPartnerKeyException;


/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {
  Form _form = new Form();
  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    _form.addStringField("_partnerId", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    _form.parse();
    String _partnerId = _form.stringField("_partnerId");
    if(_receiver.checkPartner(_partnerId) == true){
      for(String p: _receiver.showPaidTransactions(_partnerId))
      _display.addLine(p);
    _display.display();

    }
    else
      throw new UnknownPartnerKeyException(_partnerId);
  }

}
