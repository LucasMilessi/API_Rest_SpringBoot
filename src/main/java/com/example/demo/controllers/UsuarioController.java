package com.example.demo.controllers;

import com.example.demo.models.UsuarioModel;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping( path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuaioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if(ok){
            return "Se eliminio el usuario con id " + id;
        }else{
            return "No hay un usuario con id " + id;
        }
    }

    //Muestra la cantidad de usuarios que hay en la BD en números.
    @GetMapping("/total")
    public String cantidadDeUsuarios(){
        return "Hay " + usuarioService.obtenerUsuarios().size() + " usuarios registrado en la BD.";
    }

    //Elimina todos los usuarios de la BD.
    @DeleteMapping("/eliminarTodos")
    public void eliminarTodosLosUsuarios(){
        usuarioService.obtenerUsuarios().forEach(usuarioService::eliminarTodosLosUsuarios);
    }

    @GetMapping("/consulta")
    public ArrayList<UsuarioModel> obtenerUsuarioPorApellido(@RequestParam("apellido") String apellido){
        return usuarioService.obtenerPorApellido(apellido);
    }
}
