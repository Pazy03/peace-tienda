package com.techShop.tienda.service;

import com.techShop.tienda.domain.Producto;
import java.util.List;

public interface ProductoService {

    public List<Producto> getProductos();

    public Producto getProducto(Producto producto);

    public void save(Producto producto);

    public void delete(Producto producto);

    public List<Producto> consultaDerivada(double precioInf, double precioSup);

    public List<Producto> consultaJPQL(double precioInf, double precioSup);

    public List<Producto> consultaSQL(double precioInf, double precioSup);
}