package com.techShop.tienda.service;

import com.techShop.tienda.domain.Producto;
import java.util.List;

public interface ProductoService {

    List<Producto> getProductos();

    Producto getProducto(Producto producto);

    void save(Producto producto);

    void delete(Producto producto);

    List<Producto> consultaDerivada(double precioInf, double precioSup);

    List<Producto> consultaJPQL(double precioInf, double precioSup);

    List<Producto> consultaSQL(double precioInf, double precioSup);
}