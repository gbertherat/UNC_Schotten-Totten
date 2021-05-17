package unc.gl.st.exception;

public class EmptyStockException extends Exception{
    
    public EmptyStockException(){
        super("Stock is empty.");
    }
}
