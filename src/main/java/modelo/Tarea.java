package modelo;

import java.time.LocalDateTime;

public class Tarea {
    private int id;
    private String descripcion;
    private String estado;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    //Constructor
    //Al agregar una Tarea, solamente necesitaremos que el usuario ingrese la descripción
    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = "todo";
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }


    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //Actualizamos la hora al mismo tiempo en el que se agrega una descripción
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        this.actualizadoEn = LocalDateTime.now();
    }

    public String getEstado() {
        return estado;
    }

    //Actualizamos la hora al mismo tiempo en el que se asigna un estado
    public void setEstado(String estado) {
        this.estado = estado;
        this.actualizadoEn = LocalDateTime.now();
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }


    //ToString personalizado:
    @Override
    public String toString() {
        return "Tareas: " +
                "\n || ID: " + id + "\n" +
                "\n || Descripción: " + descripcion + "\n" +
                "\n || Estado: " + estado + "\n" +
                "\n || Fecha de Creación: " + creadoEn + "\n" +
                "\n || Última Actualización: " + actualizadoEn;
    }
}
