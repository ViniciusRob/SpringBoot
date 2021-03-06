package org.generation.lojadegames.repository;

import java.util.List;
import org.generation.lojadegames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findAllByTituloContainingIgnoreCase(String titulo);
}
