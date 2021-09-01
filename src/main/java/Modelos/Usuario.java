package Modelos;

public class Usuario {
    
    private String ID;
    private String clave;

    public Usuario() {}
    
    public Usuario(String ID, String clave) {
        this.ID = ID;
        this.clave = clave;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}