package org.example.domain;

import java.io.Serializable;

public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cc,nombre,apellido,genero,direccion, telefono;
    private short edad;

    public Paciente(){}

    public Paciente(String cc,String nombre, String apellido, short edad,String genero, String direccion, String telefono){
        this.cc = cc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return  "🆔 ID: " + cc + '\n' +
                "👤 Nombre: " + nombre + '\n' +
                "👥 Apellido: " + apellido + '\n' +
                "⚤ Género: " + genero + '\n' +
                "🏠 Dirección: " + direccion + '\n' +
                "📞 Teléfono: " + telefono + '\n' +
                "🎂 Edad: " + edad ;
    }

}
