package OpenHelper;

public class Usuarios {
    private int idusuario;
    private String usuario,password;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuarios(int idusuario, String usuario, String password){
        setIdusuario(idusuario);
        setUsuario(usuario);
        setPassword(password);

    }
}
