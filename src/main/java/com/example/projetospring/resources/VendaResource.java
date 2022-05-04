package com.example.projetospring.resources;

import com.example.projetospring.entities.Venda;
import com.example.projetospring.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venda")
public class VendaResource {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> inserir(@RequestBody Venda venda) {
        return new ResponseEntity<Venda>(vendaService.inserir(venda), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Venda>> buscarTodos() {
        List<Venda> vendas = vendaService.findAll();
        if(vendas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(vendaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable(value="id") Long id, @RequestBody Venda venda) {
        venda.setIdVenda(id);
        vendaService.alterar(venda);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable(value= "id") Long id) {
        Venda venda = vendaService.buscarPorId(id);
        vendaService.deletar(venda);
        return ResponseEntity.noContent().build();
    }

}

