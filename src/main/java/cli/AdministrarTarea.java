package cli;

import modelo.Tarea;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AdministrarTarea {
    //Se utilizará como memoria temporal antes de guardarlo en el archvio JSON
    private List<Tarea> listaTareas = new ArrayList<Tarea>();
    private static final String NOMBRE_ARCHIVO = "tareas.json";

    public AdministrarTarea(){
        CargarTareaDeJSON();
    }

    public void AgregarTarea(String descripcion){
        int nuevoId = listaTareas.isEmpty() ? 1 : listaTareas.get(listaTareas.size()-1).getId()+1;
        Tarea nuevaTarea = new Tarea(nuevoId, descripcion);
        listaTareas.add(nuevaTarea);
        GuardarTareaAJSON();
        System.out.println("La tarea fue agregada " + "\n" + nuevaTarea);
    }

    public void GuardarTareaAJSON(){
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

    public void CargarTareaDeJSON(){
        try(BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))){
            //leer la documentación sobre Stream en Java
            //lines: Métod de BufferedReader que lee todas las líneas del archivo y las devuelve como un Stream<String>.
            //collect: toma todos los elementos del stream y los reúne en algo
            //Collectors.joining: Concatena todos los elementos del Stream en una sola cadena sin separadores
            String json = lector.lines().collect(Collectors.joining());
            //por lo tanto, ahora el archivo tendra tod0 unido, sin espacios

            if (!json.isEmpty() && !json.equals("{}")){
                json = json.substring(1, json.length()-1); //devuelve sin los corchetes que pusimos en GuardarTareaAJSON
                String[] arregloTarea = json.split("},\\{"); //Separamos nuestros objetos en JSON
                for (String tarea : arregloTarea){
                    //reemplazamos los {} por vacio
                    tarea = tarea.replace("{", "").replace("}","");
                    String[] SplitArreglo = tarea.split(","); //Separamos donde haya una coma
                    Map<String,String> tareaMap = new HashMap<>();
                    //TODO: Comprender esto
                    for (String splitTareas : SplitArreglo){
                        int indicePrimeraColumna = splitTareas.indexOf(":"); //Solo se divide en el primer ":"
                        if (indicePrimeraColumna != -1){
                            String llave = splitTareas.substring(0, indicePrimeraColumna).replace("\"","").trim();
                            String valor = splitTareas.substring(indicePrimeraColumna+1).replace("\"","" ).trim();
                            tareaMap.put(llave,valor);
                        }
                    }

                    Tarea objetoTarea = new Tarea(
                            Integer.parseInt(tareaMap.get("id")),
                            tareaMap.get("descripcion")
                    );
                    objetoTarea.setEstado(tareaMap.get("estado"));
                    objetoTarea.setCreadoEn(LocalDateTime.parse(tareaMap.get("creadoEn")));
                    objetoTarea.setActualizadoEn(LocalDateTime.parse(tareaMap.get("actualizadoEn")));

                    listaTareas.add(objetoTarea);
                }
            }
        }catch (IOException e){
            System.out.println("Error cargando tareas desde el archivo: " + e.getMessage());
        }
    }


    public void ListarTarea(String estadoFiltro){
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas agregadas");
            return;
        }

        //filtrar por estados de las tareas
        List<Tarea> tareaFiltro = listaTareas;

        if (estadoFiltro != null && !estadoFiltro.isEmpty()){
            tareaFiltro = listaTareas.stream().filter(tarea -> tarea.getEstado().equals(estadoFiltro)).collect(Collectors.toList());
        }

        if (tareaFiltro.isEmpty()){
            System.out.println("No hay tareas para: " + tareaFiltro);
        }else{
            for (Tarea tarea:listaTareas){
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
