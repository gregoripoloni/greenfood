package alimentos;

import java.util.List;

public class testeExe {
	

	public static void main(String[] args) {
			List<UsuarioSoTesteNaoEhOPrincipal> todosUsers = EstoqueDAO.carregarUsuariosComEstoques("src/alimentos/data/estoque.txt");
			//Alimento alimento = new Alimento("feijao", 20);
			//EstoqueDAO estoqueDAO = new EstoqueDAO();
			
			
			//user.getEstoque().adicionaAoEstoque(alimento);
			
			for (UsuarioSoTesteNaoEhOPrincipal user : todosUsers) {
			    System.out.println("Usuário: " + user.getNome());
			    System.out.println(user.getEstoque());
			}
			
			//estoqueDAO.salvarEstoque(user);
	}

}
