package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Citas {

    private final Scanner scanner = new Scanner(System.in);

    public String getTeclado() {
        return this.scanner.nextLine();
    }


    public void run() {

        String funcion = getTeclado();
        String[] datos = getTeclado().split(" ");
        Cita cita = new Cita(Integer.parseInt(datos[0]), datos[1], datos[2], Double.parseDouble(datos[3]), datos[4]);


        switch (funcion) {
            case "ACTUALIZAR":
                actualizar(cita);

                break;
            case "CANCELAR":
                cancelar(cita);

                break;
            case "BUSQUEDA":
                System.out.println(buscar(cita));
                break;
            case "AGENDAR":
                agendar(cita);

                break;
            case "ORDENAR":
                ordenar(cita);

                break;


        }
    }


    public void generarInforme() {

        Cita mayor = Cita.getCitas().stream().max(Comparator.comparingDouble(Cita::getPrecio)).orElse(null);
        Cita menor = Cita.getCitas().stream().min(Comparator.comparingDouble(Cita::getPrecio)).orElse(null);
        Double promedio = Cita.getCitas().stream().mapToDouble(Cita::getPrecio).average().orElseGet(null);
        // Double promedio = Cita.getCitas().stream().mapToDouble(Cita::getPrecio).sum() / lista.size();
        System.out.println(mayor.getPersona() + " " + menor.getPersona() + " " + Math.floor(promedio * 10.0d) / 10.0);
    }


    public void actualizar(Cita cita) {

        Cita busqueda = buscar(cita.getId());
        if (busqueda == null) {
            System.out.println("ERROR");
        } else {
            Cita.getCitas().set(Cita.getCitas().indexOf(busqueda), cita);
            System.out.println(Cita.getCitas().get(cita.id));
            generarInforme();
        }


    }

    public void cancelar(Cita cita) {
        Cita busqueda = buscar(cita);
        if (busqueda == null) {
            System.out.println("ERROR");
        } else {
            Cita.getCitas().removeIf(c -> c.equals(busqueda));
            generarInforme();
        }

    }

    public void agendar(Cita cita) {
        Cita busqueda = buscar(cita);

        if (busqueda == null) {
            Cita.getCitas().add(cita);
            generarInforme();

        } else {
            System.out.println("ERROR");
        }


    }

    public Cita buscar(Cita cita) {

        return Cita.getCitas().stream().filter(c -> c.getId() == cita.getId() && c.getPrecio() == cita.getPrecio() && c.getHora().equals(c.getHora())).findFirst().orElse(null);
    }

    public Cita buscar(int id) {

        return Cita.getCitas().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }


    public void ordenar(Cita cita) {
        Cita busqueda = buscar(cita);
        List<Cita> orden = null;

        if (busqueda == null) {
            System.out.println("ERROR");
        } else {

            orden = Cita.getCitas().
                    stream().
                    filter(c -> c.getPrecio() <= cita.getPrecio()).collect(Collectors.toList());

            orden.sort(Comparator.comparingDouble(Cita::getPrecio).reversed().thenComparing(Cita::getId));

            orden.forEach(c -> {
                System.out.println(c.getId() + " " + c.getPersona() + " " + c.getTipoServicio() + " " + c.getPrecio() + " " + c.getHora());
            });


        }


    }


}

public class Main {


    public static void main(String[] args) {

        Citas run = new Citas();
        run.run();
    }


}

class Cita {

    private static List<Cita> citas = new ArrayList<>();

    static {
        citas.add(new Cita(1, "Maria", "General", 100000.0, "10:00"));
        citas.add(new Cita(2, "Juan", "Odontologia", 280000.0, "11:00"));
        citas.add(new Cita(3, "Andres", "Psicologia", 120000.0, "12:00"));
        citas.add(new Cita(4, "Valentina", "Nutricion", 78000.0, "13:00"));
        citas.add(new Cita(5, "Sergio", "General", 100000.0, "14:00"));
        citas.add(new Cita(6, "Laura", "General", 100000.0, "15:00"));
        citas.add(new Cita(7, "Carlos", "Odontologia", 200000.0, "16:00"));
        citas.add(new Cita(8, "Sofia", "Psicologia", 120000.0, "7:00"));
        citas.add(new Cita(9, "Fernando", "Nutricion", 80000.0, "18:00"));
        citas.add(new Cita(10, "Pedro", "Odontologia", 200000.0, "19:00"));
    }


    public int id;
    public String persona;
    public String tipoServicio;
    public double precio;
    public String hora;

    public Cita() {

    }


    public Cita(int id, String persona, String tipoServicio, double precio, String hora) {
        this.id = id;
        this.persona = persona;
        this.tipoServicio = tipoServicio;
        this.precio = precio;
        this.hora = hora;

    }


    public static List<Cita> getCitas() {
        return citas;
    }

    public static void setCitas(List<Cita> citas) {
        Cita.citas = citas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return this.id + " " + this.persona + " " + this.tipoServicio + " " + this.getHora();
    }
}