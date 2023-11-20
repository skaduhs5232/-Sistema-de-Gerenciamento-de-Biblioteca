import java.util.LinkedList;

class Livro {
    int numeroCatalogo;
    String titulo;
    String autor;
    boolean emprestado;

    public Livro(int numeroCatalogo, String titulo, String autor) {
        this.numeroCatalogo = numeroCatalogo;
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
    }
}

class GerenciadorBiblioteca {
    private static final int TAMANHO_TABELA = 100;
    private LinkedList<Livro>[] tabelaHash;

    public GerenciadorBiblioteca() {
        this.tabelaHash = new LinkedList[TAMANHO_TABELA];
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            tabelaHash[i] = new LinkedList<>();
        }
    }

    private int calcularIndice(int numeroCatalogo) {
        return numeroCatalogo % TAMANHO_TABELA;
    }

    public void adicionarLivro(Livro livro) {
        int indice = calcularIndice(livro.numeroCatalogo);
        tabelaHash[indice].add(livro);
    }

    public void removerLivro(int numeroCatalogo) {
        int indice = calcularIndice(numeroCatalogo);
        tabelaHash[indice].removeIf(livro -> livro.numeroCatalogo == numeroCatalogo);
    }

    public Livro consultarLivro(int numeroCatalogo) {
        int indice = calcularIndice(numeroCatalogo);
        for (Livro livro : tabelaHash[indice]) {
            if (livro.numeroCatalogo == numeroCatalogo) {
                return livro;
            }
        }
        return null;
    }

    public void emprestarLivro(int numeroCatalogo) {
        Livro livro = consultarLivro(numeroCatalogo);
        if (livro != null) {
            livro.emprestado = true;
        }
    }

    public void devolverLivro(int numeroCatalogo) {
        Livro livro = consultarLivro(numeroCatalogo);
        if (livro != null) {
            livro.emprestado = false;
        }
    }

    public void listarLivros() {
        System.out.println("Livros disponíveis na biblioteca:");
        for (LinkedList<Livro> listaLivros : tabelaHash) {
            for (Livro livro : listaLivros) {
                System.out.println("Número de Catálogo: " + livro.numeroCatalogo +
                        ", Título: " + livro.titulo +
                        ", Autor: " + livro.autor +
                        ", Emprestado: " + (livro.emprestado ? "Sim" : "Não"));
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GerenciadorBiblioteca gerenciador = new GerenciadorBiblioteca();

        // Adicionando alguns livros
        Livro livro1 = new Livro(101, "Java Programming", "John Doe");
        Livro livro2 = new Livro(202, "Data Structures", "Jane Smith");
        Livro livro3 = new Livro(303, "Algorithms", "Bob Johnson");

        gerenciador.adicionarLivro(livro1);
        gerenciador.adicionarLivro(livro2);
        gerenciador.adicionarLivro(livro3);

        // Listando os livros disponíveis
        gerenciador.listarLivros();

        // Emprestando o livro1
        gerenciador.emprestarLivro(101);

        // Consultando o livro1
        Livro livroConsultado = gerenciador.consultarLivro(101);
        System.out.println("Detalhes do livro consultado:");
        System.out.println("Número de Catálogo: " + livroConsultado.numeroCatalogo +
                ", Título: " + livroConsultado.titulo +
                ", Autor: " + livroConsultado.autor +
                ", Emprestado: " + (livroConsultado.emprestado ? "Sim" : "Não"));

        // Devolvendo o livro1
        gerenciador.devolverLivro(101);

       
        gerenciador.listarLivros();
    }
}
