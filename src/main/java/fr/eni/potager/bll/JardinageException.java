package fr.eni.potager.bll;

import java.util.ArrayList;
import java.util.List;

public class JardinageException extends Exception {
  List<String> listError = new ArrayList<>();

  public JardinageException(String message) {
    super(message);
  }
  public JardinageException(List<String> listError) {
    this.listError = listError;
  }

  public List<String> getListError() {
    return listError;
  }
}
