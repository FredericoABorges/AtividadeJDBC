package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import entities.Aluno;
import jdbc.AlunoJDBC;

public class Program {

    public static void main(String[] args) {
        try {
            int opcao = 0;
            Scanner console = new Scanner(System.in);

            do {
                System.out.println("####### Menu #######"
                        + "\n1 - Cadastrar"
                        + "\n2 - Listar"
                        + "\n3 - Alterar"
                        + "\n4 - Excluir"
                        + "\n5 - Sair");
                System.out.println("\n\tOpção:");
                opcao = Integer.parseInt(console.nextLine());

                if (opcao == 1) {
                    Aluno a = new Aluno();
                    AlunoJDBC acao = new AlunoJDBC();

                    System.out.println("\n ### Cadastrar Aluno ### \n\r");

                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());

                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());

                    System.out.print("Data de Nascimento (dd-mm-aaaa): ");
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    a.setDt_nasc(LocalDate.parse(console.nextLine(), formato));

                    acao.salvar(a);
                    console.nextLine();
                    System.out.println("\n\n\n\n");
                } else if (opcao == 2) {
                    AlunoJDBC acao = new AlunoJDBC();
                    List<Aluno> alunos = acao.listar();

                    System.out.println("\n ### Lista de Alunos ### \n");
                    for (Aluno aluno : alunos) {
                        System.out.println("ID: " + aluno.getId());
                        System.out.println("Nome: " + aluno.getNome());
                        System.out.println("Sexo: " + aluno.getSexo());
                        System.out.println("Data de Nascimento: " + aluno.getDt_nasc());
                        System.out.println("---------------------------");
                    }
                    console.nextLine();
                } else if (opcao == 3) {
                    AlunoJDBC acao = new AlunoJDBC();
                    Scanner input = new Scanner(System.in);

                    System.out.print("Digite o ID do aluno a ser alterado: ");
                    int id = Integer.parseInt(console.nextLine());

                    Aluno aluno = new Aluno();
                    aluno.setId(id);

                    System.out.print("Novo Nome: ");
                    aluno.setNome(console.nextLine());

                    System.out.print("Novo Sexo: ");
                    aluno.setSexo(console.nextLine());

                    System.out.print("Nova Data de Nascimento (dd-mm-aaaa): ");
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    aluno.setDt_nasc(LocalDate.parse(console.nextLine(), formato));

                    acao.alterar(aluno);
                    console.nextLine();
                } else if (opcao == 4) {
                    AlunoJDBC acao = new AlunoJDBC();

                    System.out.print("Digite o ID do aluno a ser excluído: ");
                    int id = Integer.parseInt(console.nextLine());

                    acao.apagar(id);
                    console.nextLine();
                }

            } while (opcao != 5);

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
