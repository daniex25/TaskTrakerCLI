package cli;

import modelo.Tarea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdministrarTarea {
    //Se utilizará como memoria temporal antes de guardarlo en el archvio JSON
    private List<Tarea> listaTareas = new ArrayList<Tarea>();

    public void AgregarTarea(int id, String descripcion){
        Tarea nuevaTarea = new Tarea(id, descripcion);
        listaTareas.add(nuevaTarea);
        System.out.println("La tarea fue agregada " + "\n" + nuevaTarea);
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
