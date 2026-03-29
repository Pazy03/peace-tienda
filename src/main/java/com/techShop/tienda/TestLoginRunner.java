package com.techShop.tienda;

import com.techShop.tienda.domain.Usuario;
import com.techShop.tienda.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestLoginRunner implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public TestLoginRunner(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========== PRUEBA LOGIN ==========");

        Usuario usuario = usuarioRepository.findByUsername("admin");

        if (usuario == null) {
            System.out.println("No se encontró el usuario admin en la base de datos.");
        } else {
            System.out.println("Usuario encontrado: " + usuario.getUsername());
            System.out.println("Activo: " + usuario.isActivo());
            System.out.println("Password en BD: " + usuario.getPassword());
            System.out.println("¿123 coincide con el hash?: " +
                    passwordEncoder.matches("123", usuario.getPassword()));
        }

        System.out.println("==================================");
    }
}