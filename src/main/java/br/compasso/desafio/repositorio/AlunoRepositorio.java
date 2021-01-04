package br.compasso.desafio.repositorio;

import br.compasso.desafio.modelo.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findAlunoByNomeCompleto(String nome);
    Optional<Aluno> findAlunoByNomeUsuario(String nomeUsuario);
    Optional<Aluno> findAlunoByEmail(String email);
}
