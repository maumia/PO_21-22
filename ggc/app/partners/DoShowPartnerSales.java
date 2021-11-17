package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.app.exception.UnknownPartnerKeyException;

/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerSales extends Command<WarehouseManager> {
  Form _form = new Form();

  DoShowPartnerSales(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_SALES, receiver);
    _form.addStringField("_partnerId", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    _form.parse();
    String _partnerId = _form.stringField("_partnerId");
    if(_receiver.checkPartner(_partnerId) == true) {
      for(String p: _receiver.ShowSalesByPartner(_partnerId))
        _display.addLine(p);
      _display.display();
    }
    else 
      throw new UnknownPartnerKeyException(_partnerId);
  }
}

