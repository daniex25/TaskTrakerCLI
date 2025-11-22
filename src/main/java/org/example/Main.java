package org.example;

import cli.AdministrarTarea;
import modelo.Tarea;

public class Main {
    public static void main(String[] args) {

        AdministrarTarea admin = new AdministrarTarea();
        /*
        admin.AgregarTarea("Practicar Java");
        admin.AgregarTarea("Segunda Tarea");
        admin.AgregarTarea("Tercera Tarea");
        admin.AgregarTarea("Agregar método CLI a este proyecto");
         */

        System.out.println("Todas las tareas: ");
        admin.ListarTarea();

        /*
        System.out.println("Actualizar Tarea 1");
        admin.ActualizarTarea(1, "Practicar lógica de Java");

        System.out.println("Actualizar Tarea 4 (NO EXISTE)");
        admin.ActualizarTarea(4, "Practicar lógica de Java");

        System.out.println("Cambiar estado de TODO a in-progress");
        admin.MarcarTareaEnProgreso(1);

        System.out.println("Cambiar estado de TODO a in-progress en ID 4 (NO EXISTE)");
        admin.MarcarTareaEnProgreso(5);

        System.out.println("Cambiar estado de in-progress a done");
        admin.MarcarTareaLista(1);

        System.out.println("Eliminar tarea 3");
        admin.EliminarTarea(3);

        System.out.println("Eliminar tarea 4 (NO EXISTE)");
        admin.EliminarTarea(4);

        System.out.println("Listar todas las tareas: ");
        admin.ListarTarea();

         */
    }
}