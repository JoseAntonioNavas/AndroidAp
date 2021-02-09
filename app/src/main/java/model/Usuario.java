package model;

public class Usuario {

    private Integer id_user;
    private String email;
    private String passwd;


    public Usuario(Integer id_user, String email, String passwd) {
        this.id_user = id_user;
        this.email = email;
        this.passwd = passwd;
    }

    public Usuario(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_user=" + id_user +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
