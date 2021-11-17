package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.InvalidDateException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<WarehouseManager> {
  private Form _form = new Form();

  public DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _form.addIntegerField("_dateToAdvance", Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws CommandException {
    _form.parse();
    int _dateToAdvance = _form.integerField("_dateToAdvance");
    if(_dateToAdvance > 0)
      _receiver.addDate(_dateToAdvance);
    else 
      throw new InvalidDateException(_dateToAdvance);
  } 

}
