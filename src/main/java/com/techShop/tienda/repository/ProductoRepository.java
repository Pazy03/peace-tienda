package com.techShop.tienda.repository;

import com.techShop.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByPrecioBetweenOrderByPrecioAsc(double precioInf, double precioSup);

    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    List<Producto> consultaJPQL(double precioInf, double precioSup);

    @Query(value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC", nativeQuery = true)
    List<Producto> consultaSQL(double precioInf, double precioSup);
}
