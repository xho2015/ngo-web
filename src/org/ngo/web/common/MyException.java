package org.ngo.web.common;

public class MyException extends Exception {  
	  
    private static final long serialVersionUID = 1L;  
  
    private int idNumber;  
  
    public int getIdNumber() {  
        return idNumber;  
  
    }  
  
    public MyException(String exception, int id) {  
        super(exception);  
        this.idNumber = id;  
    }  
}
