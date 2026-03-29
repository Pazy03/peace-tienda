package com.techShop.tienda.controller;

import com.techShop.tienda.domain.Categoria;
import com.techShop.tienda.domain.Producto;
import com.techShop.tienda.service.CategoriaService;
import com.techShop.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = productoService.getProductos();
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());
        return "/producto/listado";
    }

    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto, Model model) {
        producto.setCategoria(new Categoria());
        var categorias = categoriaService.getCategorias();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";
    }

    @PostMapping("/guardar")
    public String productoGuardar(Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getIdCategoria() != null) {
            Categoria categoria = categoriaService.getCategoria(producto.getCategoria());
            producto.setCategoria(categoria);
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);

        if (producto == null) {
            return "redirect:/producto/listado";
        }

        var categorias = categoriaService.getCategorias();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";
    }
}