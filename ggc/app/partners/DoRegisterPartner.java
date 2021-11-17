package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import ggc.core.Partner;
import ggc.core.WarehouseManager;
import ggc.app.exception.DuplicatePartnerKeyException;

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {
  private Form _form = new Form();

  public DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    
    _form.addStringField("_partnerId", Message.requestPartnerKey());
    _form.addStringField("_partnerName", Message.requestPartnerName());
    _form.addStringField("_partnerAddress", Message.requestPartnerAddress());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    String _partnerId = _form.stringField("_partnerId");
    String _partnerName = _form.stringField("_partnerName");
    String _partnerAddress = _form.stringField("_partnerAddress");

    if(_receiver.checkPartner(_partnerId) == false) {
      Partner p = new Partner(_partnerId, _partnerName, _partnerAddress);
        _receiver.addPartner(p);
    }
    else 
      throw new DuplicatePartnerKeyException(_partnerId);
  }
}
