package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {
  Form _form = new Form();

  public DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    _form.addStringField("_partnerId", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
    _form.parse();
    String _partnerId = _form.stringField("_partnerId");
    
    String result = _receiver.showPartner(_partnerId);

    if (result == null)
      throw new UnknownPartnerKeyException(_partnerId);
    else
      _display.addLine(result);
      _display.display();
  }
}
