package br.compasso.desafio.controle;

import br.compasso.desafio.modelo.Aluno;
import br.compasso.desafio.repositorio.AlunoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoControle {

    @Autowired
    AlunoRepositorio alunoRepositorio;

    @GetMapping("/todos")
    public List<Aluno> getAlunoList() {
        return this.alunoRepositorio.findAll();
    }

    @GetMapping("/alunoId/{id}")
    public Aluno getAlunoId(@PathVariable(name = "id")Long id) {
        return this.alunoRepositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado")
        );
    }

    @GetMapping("/alunoNome/{nome}")
    public Aluno getAlunoNomeCompleto(@PathVariable(name = "nome") String nome) {
        return this.alunoRepositorio.findAlunoByNomeCompleto(nome).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não encontrado")
        );
    }

    @GetMapping("/alunoNomeUsuario/{nome}")
    public Aluno getAlunoNomeUsuario(@PathVariable(name = "nome") String nome) {
        return this.alunoRepositorio.findAlunoByNomeUsuario(nome).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Aluno não encontrado")
        );
    }

    @PostMapping("/addAluno")
    public Aluno addAluno(@Validated @RequestBody Aluno aluno) {
        return this.alunoRepositorio.save(aluno);
    }

    @DeleteMapping("/dellAluno/{id}")
    public ResponseEntity<?> deletarAluno(@PathVariable(name = "id") Long id){
        Aluno aluno = getAlunoId(id);
        this.alunoRepositorio.delete(aluno);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alterar/{id}")
    public Aluno alterarAluno(@PathVariable(name = "id")Long id, @Validated @RequestBody Aluno novoAluno) {
        Aluno aluno = getAlunoId(id);
        novoAluno.setId(id);
        return this.alunoRepositorio.save(novoAluno);
    }
}
