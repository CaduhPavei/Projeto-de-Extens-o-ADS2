package com.senac.extensao.service;

import com.senac.extensao.models.Agricultor;
import com.senac.extensao.models.Pessoa;
import com.senac.extensao.repository.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgricultorService {

    @Autowired
    private AgricultorRepository repository;

    public Agricultor salvar(Agricultor entity) {
        if(entity.validarCPF(entity.getCpf())) {
            return repository.save(entity);
        }else {
            throw new RuntimeException("CPF inválido!");
        }
    }


    public List<Agricultor> buscaTodos(){
        return repository.findAll();}

    public Agricultor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Agricultor alterar(Long id,Agricultor alterado){
        Optional<Agricultor> encontrado = repository.findById(id);
        if(encontrado.isPresent()){
            Agricultor agricultor = encontrado.get();

            agricultor.setNome(alterado.getNome());
            agricultor.setContato(alterado.getContato());
            agricultor.setCpf(alterado.getCpf());
            agricultor.setEmail(alterado.getEmail());

            return repository.save(agricultor);
        }
        return null;
    }
    public void remover(Long id) {repository.deleteById(id);}
}
