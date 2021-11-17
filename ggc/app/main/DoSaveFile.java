package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
import pt.tecnico.uilib.forms.Form;
import ggc.core.exception.MissingFileAssociationException;
import java.io.IOException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {
  private Form _form = new Form();

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
    _form.addStringField("filename", Message.newSaveAs());
  }

  @Override
  public final void execute() throws CommandException {
    try {
      if (_receiver.getFileName() != null) {
        _receiver.save();
      }
      else {
        _form.parse();
        String filename = _form.stringField("filename");
        _receiver.saveAs(filename);
      }
    } catch (MissingFileAssociationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

