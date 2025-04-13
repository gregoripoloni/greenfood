import java.util.List;

import usuario.Doador;
import usuario.PersistenciaDoador;
import usuario.PersistenciaReceptor;
import usuario.Receptor;

public class Main {
    public static void main(String[] args) {
        // Doador doador = new Doador("Douglas", "douglas@gmail.com", "douglas123", "54998765432");
        // doador.exibirInformacoes();

        // Receptor receptor = new Receptor("Rodrigo", "rodrigo@gmail.com", "rodrigo123", "54998765431");
        // receptor.exibirInformacoes();

        PersistenciaDoador persistenciaDoador = new PersistenciaDoador();
        PersistenciaReceptor persistenciaReceptor = new PersistenciaReceptor();

        // persistenciaDoador.salvaDoador(doador);
        // persistenciaReceptor.salvaReceptor(receptor);

        List<Doador> doadores = persistenciaDoador.obterDoadores();

        for (Doador doador : doadores) {
            doador.exibirInformacoes();
        }

        List<Receptor> receptores = persistenciaReceptor.obterReceptores();

        for (Receptor receptor : receptores) {
            receptor.exibirInformacoes();
        }
    }
}