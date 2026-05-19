package service;

import model.Pessoa;
import repository.PessoaRepository;

public class PessoaService {
    private PessoaRepository repository;


    public PessoaService(PessoaRepository repository){
        this.repository = repository;
    }

    public void salvarPessoa(Pessoa pessoa){
        if(pessoa.getNome() == null || pessoa.getNome().isBlank()){
            throw new IllegalArgumentException("Nome invalido!");
        }

        String telefone = pessoa.getTelefone();
        if(telefone == null || telefone.length() != 11){
            throw new IllegalArgumentException("Telefone invalido");
        }

        Pessoa pessoaExistente = repository.buscaPessoa(pessoa.getNome(), pessoa.getTelefone());

        if(pessoaExistente != null){
            System.out.println("\nCliente ja possui cadastro\n");

            pessoa.setIdPessoa(pessoaExistente.getIdPessoa());
            return;
        }

        repository.salvarPessoa(pessoa);
    }
}
