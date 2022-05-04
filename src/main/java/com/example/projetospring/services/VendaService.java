package com.example.projetospring.services;

import com.example.projetospring.entities.Venda;
import com.example.projetospring.repositories.VendaRepository;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda inserir(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Venda alterar(Venda venda) {
        Venda vendaDataBase = this.buscarPorId(venda.getIdVenda());
        BeanUtils.copyProperties(venda, vendaDataBase);
        return vendaRepository.save(vendaDataBase);
    }

    public boolean existsByNome(String nome) {
            return vendaRepository.existsByNome(nome);
    }

    public boolean existsByCidade(String cidade) {
            return vendaRepository.existsByCidade(cidade);
    }

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        Optional<Venda> obj = vendaRepository.findById(id);
        return obj.orElseThrow(()-> new RuntimeException("Venda não encontrada pelo id"));
    }

    public void deletar(Venda venda) {
        Venda obj = this.buscarPorId(venda.getIdVenda());
        vendaRepository.delete(obj);
    }
}
