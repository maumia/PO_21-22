package ggc.app.transactions;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;

/**
 * Receive payment for sale transaction.
 */

class DoReceivePayment extends Command<WarehouseManager> {
  Form _form = new Form();

  public DoReceivePayment(WarehouseManager receiver) {
    super(Label.RECEIVE_PAYMENT, receiver);
    _form.addIntegerField("_transactionId", Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    int _transactionId = _form.integerField("_transactionId");
    _receiver.paySale(_transactionId);
  }
}
