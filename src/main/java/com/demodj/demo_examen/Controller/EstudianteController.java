package com.demodj.demo_examen.Controller;

import com.demodj.demo_examen.Entity.Estudiante;
import com.demodj.demo_examen.Services.Impl.EstudianteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    private final EstudianteServiceImpl estudianteService;

    public EstudianteController(EstudianteServiceImpl estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/listar")
    public String listAllEstudiantes(Model model) {
        model.addAttribute("title", "Lista de Estudiantes");
        model.addAttribute("listaEstudiante", estudianteService.getAllEstudiantes());
        return "pages/listar-estudiante";
    }

    @GetMapping("/nuevo")
    public String addEstudiante(Model model) {
        Estudiante estudiante = new Estudiante();
        model.addAttribute("title", "Agregar Estudiante");
        model.addAttribute("estudiante", estudiante);
        return "pages/formulario-estudiante";
    }

    @PostMapping("/save")
    public String saveEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
        estudianteService.addEstudiante(estudiante);
        return "redirect:/estudiante/listar";
    }

    @GetMapping("/showUpdateEstudiante/{id}")
    public String updateEstudiante(@PathVariable(value = "id") Long id, Model model) {
        Estudiante estudiante = estudianteService.getEstudianteById(id);
        model.addAttribute("estudiante", estudiante);
        return "pages/formulario-estudianteActualizar";
    }

    @PostMapping("/update")
    public String updateEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
        estudianteService.updateEstudiante(estudiante);
        return "redirect:/estudiante/listar";
    }

    @GetMapping("/delete/{id}")
    public String deleteEstudiante(@PathVariable(value = "id") Long id) {
        estudianteService.deleteEstudiante(id);
        return "redirect:/estudiante/listar";
    }
}
