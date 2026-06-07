package persistencia;

import java.io.File;

public class Persistencia {

    public static boolean inicializarPasta(String caminho) {
        File pasta = new File(caminho);

        if (!pasta.exists()) {
            return pasta.mkdirs();
        }

        return true;
    }
}
