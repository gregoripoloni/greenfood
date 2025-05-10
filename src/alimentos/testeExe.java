package alimentos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class testeExe {
	

	public static void main(String[] args) {
			EstoqueDAO estoqueDAO = new EstoqueDAO();
			List<UsuarioSoTesteNaoEhOPrincipal> todosUsers = estoqueDAO.recuperarEstoqueUsers("alimentos/data/estoque.json");
			Alimento alimento = new Alimento("pao", 5, LocalDate.of(2025, 6, 22), "frutas");
			UsuarioSoTesteNaoEhOPrincipal user = new UsuarioSoTesteNaoEhOPrincipal("Maisa");
			
			if(todosUsers == null) {
				todosUsers = new ArrayList<>();
			}
			
			user.getEstoque().adicionaAoEstoque(alimento);
			
			todosUsers.add(user);
			
			for (UsuarioSoTesteNaoEhOPrincipal user1 : todosUsers) {
			    System.out.println("Usuário: " + user1.getNome());
			    System.out.println(user1.getEstoque());
			}
			
			estoqueDAO.salvarEstoque(todosUsers);
	}

}
