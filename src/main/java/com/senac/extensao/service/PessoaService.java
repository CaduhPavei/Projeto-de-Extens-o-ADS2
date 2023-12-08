package com.senac.extensao.service;

import com.senac.extensao.models.Pessoa;
import com.senac.extensao.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa salvar(Pessoa entity) {
        if(entity.validarCPF(entity.getCpf())) {
            return repository.save(entity);
        }else {
            throw new RuntimeException("CPF inválido!");
        }
    }


    public List<Pessoa> buscaTodos(){
        return repository.findAll();}

    public Pessoa buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pessoa alterar(Long id,Pessoa alterado){
        Optional<Pessoa> encontrado = repository.findById(id);
        if(encontrado.isPresent()){
            Pessoa pessoa = encontrado.get();

            pessoa.setNome(alterado.getNome());
            pessoa.setContato(alterado.getContato());
            pessoa.setCpf(alterado.getCpf());
            pessoa.setEmail(alterado.getEmail());

            return repository.save(pessoa);
        }
        return null;
    }
    public void remover(Long id) {repository.deleteById(id);}
}
