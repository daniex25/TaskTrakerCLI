package cli;

import modelo.Tarea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdministrarTarea {
    //Se utilizará como memoria temporal antes de guardarlo en el archvio JSON
    private List<Tarea> listaTareas = new ArrayList<Tarea>();
    private static final String NOMBRE_ARCHIVO = "tareas.json";

    public void AgregarTarea(int id, String descripcion){
        Tarea nuevaTarea = new Tarea(id, descripcion);
        listaTareas.add(nuevaTarea);
        System.out.println("La tarea fue agregada " + "\n" + nuevaTarea);
    }

    public void GuardarAJSON(){
        //BufferWriter para poder escribir en el archivo tareas.json
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            //Abrimos llaves Json
            escritor.write("{");
            //Iteramos todas las tareas para guardarlas
            for (int i = 0; i<listaTareas.size(); i++){
                Tarea tarea = listaTareas.get(i);
                //Cambiamos el formato de String para que se guarde en el formato JSON
                escritor.write(String.format(
                        "{\"id\":%d,\"descripcion\":\"%s\",\"estado\":\"%s\",\"creadoEn\":\"%s\",\"actualizadoEn\":\"%s\"}",
                        tarea.getId(),tarea.getDescripcion(),tarea.getEstado(),tarea.getCreadoEn(),tarea.getActualizadoEn()
                ));
                //Si aún hay mas tareas, poner la coma
                if (i < listaTareas.size()-1){
                    escritor.write(",");
                }
            }
            //Cerramos llaves JSON
            escritor.write("}");
        }catch (IOException e){
            System.out.println("Error al guardar el archivo, " + e.getMessage());
        }
    }

    public void ListarTarea(){
        if (listaTareas.isEmpty()){
            System.out.println("No hay tareas agregadas");
        }else{
            for (Tarea tarea : listaTareas){
                System.out.println(tarea);
            }
        }
    }

    public void ActualizarTarea(int id, String descripcion){
        boolean encontrado = false;
        if (listaTareas.isEmpty()){
            System.out.println("No hay tareas agregadas");
        }else{
            for (Tarea tarea : listaTareas){
                if (tarea.getId() == id){
                    tarea.setDescripcion(descripcion);
                    System.out.println("Actualizado con éxito");
                    encontrado = true;
                    //Ya no es necesario actualizar la fecha ya que lo agregamos
                    //en el modelo
                    break;
                }
            }
        }
        if (!encontrado){
            System.out.println("No existe ese ID!");
        }
    }

    public void EliminarTarea(int id){
        Iterator<Tarea> iterador = listaTareas.iterator();
        boolean encontrado = false;
        while (iterador.hasNext()){
            Tarea tarea = iterador.next();
            if (tarea.getId() == id) {
                iterador.remove();
                System.out.println("Se eliminó la tarea");
                encontrado = true;
                break;
            }
        }
        if (!encontrado){
            System.out.println("No existe ese ID!");
        }
    }

    public void MarcarTareaLista(int id){
        boolean encontrado = false;
        if (listaTareas.isEmpty()){
            System.out.println("No hay tareas agregadas");
        }else {
            for (Tarea tarea:listaTareas){
                if (tarea.getId() == id){
                    tarea.setEstado("done");
                    System.out.println("La tarea fué marcada correctamente");
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado){
            System.out.println("No existe ese ID!");
        }

    }

    public void MarcarTareaEnProgreso(int id){
        boolean encontrado = false;
        if (listaTareas.isEmpty()){
            System.out.println("No tareas agregadas");
        }else{
            for (Tarea tarea: listaTareas){
                if (tarea.getId() == id){
                    tarea.setEstado("in-progress");
                    encontrado = true;
                    System.out.println("La tarea fue marcada correctamente");
                    break;
                }
            }
        }
        if (!encontrado){
            System.out.println("No existe ese ID!");
        }
    }
}
