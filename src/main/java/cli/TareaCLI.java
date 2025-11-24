package cli;

import java.io.IOException;
import java.util.Scanner;

public class TareaCLI {
    private final AdministrarTarea admin;
    private Scanner escaner;

    public TareaCLI(){
        this.admin = new AdministrarTarea();
        this.escaner = new Scanner(System.in);
    }

    //Métod0 para inicializar el CLI e interactuará con el usuario
    public void Inicar(){
        while (true){
            System.out.println("task-cli ");
            String comado = escaner.nextLine().trim();
            String[] comandArgs = comado.split(" ", 2); //separa el comando y el argumento
            String accion = comandArgs[0].toLowerCase(); //convierte a minúscula;

            switch (accion){
                case "agregar" :
                    ManejoDeAgregarTareas(comandArgs);
                    break;
                case "listar" :
                    ManejoDeListarTareas(comandArgs);
                    break;
                case "actualizar" :
                    ManejoDeActualizarTarea(comandArgs);
                    break;
                case "eliminar" :
                    ManejoDeEliminarTarea(comandArgs);
                    break;
                case "marcar-done" :
                    ManejoDeMarcarTarea(comandArgs, true);
                    break;
                case "marcar-in-progress":
                    ManejoDeMarcarTarea(comandArgs, false);
                    break;
                case "ayuda" :
                    ayuda();
                    break;
                case "salir":
                    admin.GuardarTareaAJSON();
                    System.out.println("Saliendo de task-cli");
                    return;
                default:
                    System.out.println("Ingrese un comando válido");
            }
        }
    }

    public void ayuda(){
        System.out.println("\nPara administrar tus tareas inicia los siguientes comandos: ");
        System.out.println("  agregar <descripción>            - Agrega una nueva tarea.");
        System.out.println("  listar [estado]                  - Listas las tareas (opcionalmente puedes filtrarlo con: todo, in-progress, -done");
        System.out.println("  actualizar <id> <descripción>    - Edita una tarea.");
        System.out.println("  eliminar <id>                    - Elimina una tarea por el ID.");
        System.out.println("  marcar-done <id>                 - Actualiza por ID una tarea a 'done'.");
        System.out.println("  marcar-in-progress <id>          - Actualiza por ID una tarea a 'in-progress'");
        System.out.println("  salir                            - Guarda las tareas y sale del programa.");
        System.out.println("  ayuda                            - Muestra este mensaje.");
    }


    private void ManejoDeAgregarTareas(String[] comandArgs){
        if (comandArgs.length > 1){
            admin.AgregarTarea(comandArgs[1]);
        }else{
            System.out.println("Tienes que dejar una descripción de la tarea");
        }
    }

    private void ManejoDeListarTareas(String[] comandArgs){
        if (comandArgs.length > 1){
            admin.ListarTarea(comandArgs[1]);
        }else {
            admin.ListarTarea(null);
        }
    }

    private void ManejoDeActualizarTarea(String[] comandArgs){
        if (comandArgs.length > 1){
            String[] updateArgs = comandArgs[1].split(" ", 2);
            if (updateArgs.length > 1){
                try{
                    int id = Integer.parseInt(updateArgs[0]);
                    admin.ActualizarTarea(id,comandArgs[1]);
                }catch(NumberFormatException e){
                    System.out.println("Ingrese un ID Válido!: " + e.getMessage());
                }
            }else{
                System.out.println("Ingrese una descripción de la tarea");
            }
        }else{
            System.out.println("Ingrese un ID válido y una descripción!");
        }
    }

    private void ManejoDeEliminarTarea(String[] comandArgs){
        if (comandArgs.length > 1){
            try {
                int id = Integer.parseInt(comandArgs[1]);
                admin.EliminarTarea(id);
            }catch (NumberFormatException e){
                System.out.println("Ingrese un ID válido: " + e.getMessage());
            }
        }else{
            System.out.println("Ingrese un ID válido!");
        }
    }

    private void ManejoDeMarcarTarea(String[] comandArgs, boolean estado){
        if (comandArgs.length > 1){
            try {
                int id = Integer.parseInt(comandArgs[1]);
                //true: Done, false: in-progress
                if (estado){
                    admin.MarcarTareaLista(id);
                }else{
                    admin.MarcarTareaEnProgreso(id);
                }
            }catch (NumberFormatException e){
                System.out.println("Ingrese un ID válido!");
            }
        }else {
            System.out.println("Ingrese un ID válido");
        }
    }
}
