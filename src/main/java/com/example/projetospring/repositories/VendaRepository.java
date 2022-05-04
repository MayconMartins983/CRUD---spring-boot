package com.example.projetospring.repositories;

import com.example.projetospring.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    public boolean existsByNome(String nome);

    public boolean existsByCidade(String cidade);
}
