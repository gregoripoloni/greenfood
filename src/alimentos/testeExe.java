package alimentos;

public class testeExe {

	public static void main(String[] args) {
			Usuario user = new Usuario("Leonardo");
			Alimento alimento = new Alimento("feijao", 20);
			
			user.getEstoque().adicionaAoEstoque(alimento);
			
			System.out.println(user.getEstoque());
	}

}
