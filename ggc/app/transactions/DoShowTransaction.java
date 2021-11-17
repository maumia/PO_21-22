package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.app.exception.UnknownTransactionKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {
  Form _form = new Form();

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    _form.addIntegerField("_transactionId", Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    int _transactionId = _form.integerField("_transactionId");
    String result = _receiver.showTransaction(_transactionId);

    if (result == null)
      throw new UnknownTransactionKeyException(_transactionId);
    else
      _display.addLine(result);
      _display.display();
  }
}

