package com.cabanas.pagos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabanas.pagos.models.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

}
