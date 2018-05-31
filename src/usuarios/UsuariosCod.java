/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import java.io.FileInputStream;
import java.util.Date;

/**
 *
 * @author Rojeru San CL
 */
public class UsuariosCod {
    
    public static String LISTAR_US = "SELECT * FROM usuarios ORDER BY nombre";
    
    public static String REGISTRAR = "INSERT INTO usuarios(id_usuario, nombre, sexo, tipo, contraseña) "
            + "VALUES(?,?,?,?,?)";
    
    public static String ACTUALIZAR = "UPDATE usuarios SET "
                + "nombre=?, "
                + "sexo=?, "
                + "tipo=?, "
                + "contraseña=? WHERE id_usuario=?";
    
    public static String ELIMINAR = "DELETE FROM usuarios WHERE id_usuario = ?";
    
    public static String ELIMINAR_TODO = "DELETE FROM usuarios";
    
    private String primaryKey;
    private String nombre;
    private String sexo;
    private String tipouser;
    private String contraseña;

    public UsuariosCod(){
        
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipouser() {
        return tipouser;
    }

    public void setTipouser(String tipouser) {
        this.tipouser = tipouser;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
