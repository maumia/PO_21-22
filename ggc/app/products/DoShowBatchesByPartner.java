package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.app.exception.UnknownPartnerKeyException;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {
  Form _form = new Form();

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    _form.addStringField("_partnerId", Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    String _partnerId = _form.stringField("_partnerId");
  
  if (_receiver.checkPartner(_partnerId) == false)
    throw new UnknownPartnerKeyException(_partnerId);
  else
    for(String b: _receiver.showBatchesByPartner(_partnerId))
    _display.addLine(b);
  _display.display();
  }

}
