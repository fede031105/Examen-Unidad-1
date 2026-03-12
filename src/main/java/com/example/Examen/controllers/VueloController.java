package com.example.Examen.controllers;

import com.example.Examen.Vuelo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vuelos") // Ruta base [cite: 33]
public class VueloController {

    private static List<Vuelo> listaVuelos = new ArrayList<>();

    // Bloque estático para cargar 10 registros de ejemplo [cite: 62]
    static {
        listaVuelos.add(new Vuelo(1, "Zacatecas", "CDMX", LocalDate.now(), 90));
        listaVuelos.add(new Vuelo(2, "CDMX", "Cancún", LocalDate.now(), 130));
        listaVuelos.add(new Vuelo(3, "Guadalajara", "Monterrey", LocalDate.now(), 80));
        listaVuelos.add(new Vuelo(4, "Tijuana", "León", LocalDate.now(), 180));
        listaVuelos.add(new Vuelo(5, "Mérida", "CDMX", LocalDate.now(), 110));
        listaVuelos.add(new Vuelo(6, "Zacatecas", "Tijuana", LocalDate.now(), 150));
        listaVuelos.add(new Vuelo(7, "Puerto Vallarta", "Toluca", LocalDate.now(), 60));
        listaVuelos.add(new Vuelo(8, "Oaxaca", "CDMX", LocalDate.now(), 75));
        listaVuelos.add(new Vuelo(9, "Monterrey", "Querétaro", LocalDate.now(), 65));
        listaVuelos.add(new Vuelo(10, "Zacatecas", "Dallas", LocalDate.now(), 120));
    }

    // 1. Listado [cite: 26]
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("vuelos", listaVuelos);
        return "lista";
    }

    // 2. Nuevo - Mostrar formulario [cite: 34]
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("vuelo", new Vuelo());
        return "crear";
    }

    // Guardar nuevo
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Vuelo vuelo) {
        listaVuelos.add(vuelo);
        return "redirect:/vuelos";
    }

    // 3. Editar - Mostrar formulario [cite: 38]
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable int id, Model model) {
        Vuelo vuelo = listaVuelos.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
        model.addAttribute("vuelo", vuelo);
        return "editar";
    }

    // Actualizar
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Vuelo vuelo) {
        listaVuelos.removeIf(v -> v.getId() == vuelo.getId());
        listaVuelos.add(vuelo);
        return "redirect:/vuelos";
    }

    // 4. Eliminar - Confirmación [cite: 42]
    @GetMapping("/eliminar/{id}")
    public String confirmarEliminar(@PathVariable int id, Model model) {
        Vuelo vuelo = listaVuelos.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
        model.addAttribute("vuelo", vuelo);
        return "eliminar";
    }

    @PostMapping("/borrar")
    public String borrar(@RequestParam int id) {
        listaVuelos.removeIf(v -> v.getId() == id);
        return "redirect:/vuelos";
    }
}