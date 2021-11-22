package com.cabanas.pagos.services.interfaces;

import java.util.List;
import java.util.Optional;

public interface ICrud<G> {

	List<G> getAll();
    Optional<G> findById(int id);
    G save(G std);
    G update(G std);
    void deleteById(int id);
}
