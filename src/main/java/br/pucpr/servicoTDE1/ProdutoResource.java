package br.pucpr.servicoTDE1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Consulta de produto por ID
    @GetMapping("/{id}")
    public Produto consultaProduto(@PathVariable int id) {
        return produtoRepository.getById(id);
    }

    // Listagem de produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    //Cadastro de produto
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualização de produto
    @PutMapping("/{id}")
    public ResponseEntity editarProduto(@PathVariable("id") int id, @RequestBody Produto produto) {
        if (!produtoRepository.existsById(id)) return ResponseEntity.notFound().build();

        produto.setId(id);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    // Exclusão de produto
    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable("ID") int id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
