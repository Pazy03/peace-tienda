package com.techShop.tienda.service;

import com.techShop.tienda.domain.Rol;
import com.techShop.tienda.domain.Usuario;
import com.techShop.tienda.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null || !usuario.isActivo()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> permisos = new ArrayList<>();

        for (Rol rol : usuario.getRoles()) {
            permisos.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        }

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                permisos
        );
    }
}