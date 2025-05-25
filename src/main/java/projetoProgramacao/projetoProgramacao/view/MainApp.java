package projetoProgramacao.projetoProgramacao.view;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projetoProgramacao.projetoProgramacao.entities.Aluno;
import projetoProgramacao.projetoProgramacao.entities.Disciplina;
import projetoProgramacao.projetoProgramacao.entities.Professor;
import projetoProgramacao.projetoProgramacao.entities.Turma;
import projetoProgramacao.projetoProgramacao.service.AlunoService;
import projetoProgramacao.projetoProgramacao.service.DisciplinaService;
import projetoProgramacao.projetoProgramacao.service.ProfessorService;
import projetoProgramacao.projetoProgramacao.service.TurmaService;

import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class MainApp implements CommandLineRunner {

    private final AlunoService alunoService;
    private final ProfessorService professorService;
    private final TurmaService turmaService;
    private final DisciplinaService disciplinaService;

    public MainApp(AlunoService alunoService, ProfessorService professorService, TurmaService turmaService, DisciplinaService disciplinaService) {
        this.alunoService = alunoService;
        this.professorService = professorService;
        this.turmaService = turmaService;
        this.disciplinaService = disciplinaService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n \uD83C\uDFEB --- MENU --- \uD83C\uDFEB ");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Cadastrar turma");
            System.out.println("4 - Listar turmas");
            System.out.println("5 - Adicionar aluno a turma");
            System.out.println("6 - Cadastrar professor");
            System.out.println("7 - Listar professores");
            System.out.println("8 - Cadastrar disciplina");
            System.out.println("9 - Listar disciplinas");
            System.out.println("10 - Associar disciplina a professor");
            System.out.println("11 - Associar disciplina a aluno");
            System.out.println("12 - Adicionar professor a turma");
            System.out.println("13 - Editar aluno");
            System.out.println("14 - Remover aluno");
            System.out.println("15 - Editar professor");
            System.out.println("16 - Remover professor");
            System.out.println("17 - Editar turma");
            System.out.println("18 - Remover turma");
            System.out.println("19 - Editar disciplina");
            System.out.println("20 - Remover disciplina");



            System.out.println("0 - Sair");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(scanner.nextLine());
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    Aluno aluno = new Aluno();
                    aluno.setNome(nome);
                    aluno.setIdade(idade);
                    aluno.setCpf(cpf);

                    try {
                        alunoService.cadastrarAluno(aluno);
                        System.out.println("\u001B[32mAluno cadastrado com sucesso✅\u001B[0m");
                    } catch (IllegalArgumentException e) {
                        System.out.println("\u001B[31mErro: " + e.getMessage() + "\u001B[0m");
                    }
                }


                case 2 -> alunoService.listarAlunos().forEach(a -> {
                    String turmaNome = (a.getTurma() != null) ? a.getTurma().getNome() : "Sem turma";


                    String disciplinasNomes = a.getDisciplinas().stream()
                            .map(Disciplina::getNome)
                            .collect(Collectors.joining(", "));

                    System.out.println("ID: " + a.getId() +
                            " - " + a.getNome() +
                            " - Turma: " + turmaNome +
                            " - Disciplinas: " + (disciplinasNomes.isEmpty() ? "Sem disciplinas" : disciplinasNomes));
                });


                case 3 -> {
                    System.out.print("Nome da turma: ");
                    String nome = scanner.nextLine();

                    Turma turma = new Turma();
                    turma.setNome(nome);

                    turmaService.cadastrarTurma(turma);
                    System.out.println("\u001B[32mTurma cadastrada com sucesso✅\u001B[0m");
                }

                case 4 -> turmaService.listarTurmas().forEach(t -> {
                    System.out.println("ID: " + t.getId() + " - " + t.getNome());
                    System.out.println("Alunos:");

                    if (t.getAlunos() == null || t.getAlunos().isEmpty()) {
                        System.out.println("  Nenhum aluno nesta turma.");
                    } else {
                        t.getAlunos().forEach(aluno -> System.out.println("  - " + aluno.getNome()));
                    }

                    System.out.println(); // isso aqui eu botei pra deixar uma linha em branco
                });


                case 5 -> {
                    System.out.print("ID do aluno: ");
                    Long idAluno = Long.parseLong(scanner.nextLine());

                    System.out.print("ID da turma: ");
                    Long idTurma = Long.parseLong(scanner.nextLine());

                    boolean sucesso = turmaService.adicionarAlunoNaTurma(idTurma, idAluno);
                    if (sucesso)  System.out.println("\u001B[32mAluno adicionado à turma com sucesso✅\u001B[0m");


                }

                case 6 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(scanner.nextLine());
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    Professor professor = new Professor();
                    professor.setNome(nome);
                    professor.setIdade(idade);
                    professor.setCpf(cpf);

                    professorService.cadastrarProfessor(professor);
                    System.out.println("\u001B[32mProfessor cadastrado com sucesso!\u001B[0m");
                }

                case 7 -> professorService.listarProfessores().forEach(p -> {
                    System.out.print("ID: " + p.getId() + " - " + p.getNome() + " - Disciplinas: ");
                    String disciplinas = p.getDisciplinas().stream()
                            .map(d -> d.getNome())
                            .collect(Collectors.joining(", "));
                    System.out.print(disciplinas);

                    System.out.print(" - Turmas: ");
                    String turmas = p.getTurmas().stream()
                            .map(t -> t.getNome())
                            .collect(Collectors.joining(", "));
                    System.out.println(turmas);
                });



                case 8 -> {
                    System.out.print("Nome da disciplina: ");
                    String nome = scanner.nextLine();

                    Disciplina disciplina = new Disciplina();
                    disciplina.setNome(nome);

                    disciplinaService.cadastrarDisciplina(disciplina);
                    System.out.println("\u001B[32mDisciplina cadastrada com sucesso✅\u001B[0m");
                }

                case 9 -> disciplinaService.listarDisciplinas().forEach(
                        d -> System.out.println("ID: " + d.getId() + " - " + d.getNome())
                );

                case 10 -> {
                    System.out.print("ID do professor: ");
                    Long idProfessor = Long.parseLong(scanner.nextLine());
                    System.out.print("ID da disciplina: ");
                    Long idDisciplina = Long.parseLong(scanner.nextLine());

                    Professor professor = professorService.buscarPorId(idProfessor);
                    Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);

                    if (professor != null && disciplina != null) {
                        professor.getDisciplinas().add(disciplina);
                        professorService.cadastrarProfessor(professor);
                        System.out.println("\u001B[32mDisciplina associada ao professor✅\u001B[0m");
                    } else {
                        System.out.println("Erro ao associar.");
                    }
                }

                case 11 -> {
                    System.out.print("ID do aluno: ");
                    Long idAluno = Long.parseLong(scanner.nextLine());
                    System.out.print("ID da disciplina: ");
                    Long idDisciplina = Long.parseLong(scanner.nextLine());

                    Aluno aluno = alunoService.buscarPorId(idAluno);
                    Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);

                    if (aluno != null && disciplina != null) {
                        aluno.getDisciplinas().add(disciplina);
                        alunoService.cadastrarAluno(aluno);
                        System.out.println("\u001B[32mDisciplina associada ao aluno✅\u001B[0m");
                    } else {
                        System.out.println("Erro ao associar.");
                    }
                }

                // Dentro do switch, após o case 11

                case 12 -> {
                    System.out.print("ID do professor: ");
                    Long idProfessor = Long.parseLong(scanner.nextLine());
                    System.out.print("ID da turma: ");
                    Long idTurma = Long.parseLong(scanner.nextLine());

                    boolean sucesso = turmaService.adicionarProfessorATurma(idTurma, idProfessor);
                    if (sucesso) {
                        System.out.println("\u001B[32mProfessor associado à turma com sucesso✅\u001B[0m");
                    } else {
                        System.out.println("Erro ao associar professor à turma.");
                    }
                }


                case 13 -> {
                    System.out.print("ID do aluno que deseja editar: ");
                    Long idAluno = Long.parseLong(scanner.nextLine());

                    Aluno aluno = alunoService.buscarPorId(idAluno);
                    if (aluno == null) {
                        System.out.println("Aluno não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome (deixe em branco para manter): ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isBlank()) aluno.setNome(novoNome);

                    System.out.print("Nova idade (digite -1 para manter): ");
                    int novaIdade = Integer.parseInt(scanner.nextLine());
                    if (novaIdade >= 0) aluno.setIdade(novaIdade);

                    System.out.print("Novo CPF (deixe em branco para manter): ");
                    String novoCpf = scanner.nextLine();
                    if (!novoCpf.isBlank()) aluno.setCpf(novoCpf);

                    alunoService.cadastrarAluno(aluno);
                    System.out.println("\u001B[32mAluno atualizado com sucesso✅\u001B[0m");
                }

                case 14 -> {
                    System.out.print("ID do aluno que deseja remover: ");
                    Long idAluno = Long.parseLong(scanner.nextLine());

                    Aluno aluno = alunoService.buscarPorId(idAluno);
                    if (aluno == null) {
                        System.out.println("Aluno não encontrado.");
                        break;
                    }

                    alunoService.excluirAluno(idAluno);
                    System.out.println("\u001B[32mAluno removido com sucesso✅\u001B[0m");
                }

                case 15 -> {
                    System.out.print("ID do professor que deseja editar: ");
                    Long idProfessor = Long.parseLong(scanner.nextLine());

                    Professor professor = professorService.buscarPorId(idProfessor);
                    if (professor == null) {
                        System.out.println("Professor não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome (deixe em branco para manter): ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isBlank()) professor.setNome(novoNome);

                    System.out.print("Nova idade (digite -1 para manter): ");
                    int novaIdade = Integer.parseInt(scanner.nextLine());
                    if (novaIdade >= 0) professor.setIdade(novaIdade);

                    System.out.print("Novo CPF (deixe em branco para manter): ");
                    String novoCpf = scanner.nextLine();
                    if (!novoCpf.isBlank()) professor.setCpf(novoCpf);

                    professorService.editarProfessor(professor);
                    System.out.println("\u001B[32mProfessor atualizado com sucesso✅\u001B[0m");
                }

                case 16 -> {
                    System.out.print("ID do professor que deseja remover: ");
                    Long idProfessor = Long.parseLong(scanner.nextLine());

                    Professor professor = professorService.buscarPorId(idProfessor);
                    if (professor == null) {
                        System.out.println("Professor não encontrado.");
                        break;
                    }

                    professorService.removerProfessor(idProfessor);
                    System.out.println("\u001B[32mProfessor removido com sucesso✅\u001B[0m");
                }

                case 17 -> {
                    System.out.print("ID da turma que deseja editar: ");
                    Long idTurma = Long.parseLong(scanner.nextLine());

                    Turma turma = turmaService.buscarPorId(idTurma);
                    if (turma == null) {
                        System.out.println("Turma não encontrada.");
                        break;
                    }

                    System.out.print("Novo nome da turma: ");
                    String novoNome = scanner.nextLine();

                    turmaService.editarTurma(idTurma, novoNome);
                    System.out.println("\u001B[32mTurma atualizada com sucesso✅\u001B[0m");
                }

                case 18 -> {
                    System.out.print("ID da turma que deseja remover: ");
                    Long idTurma = Long.parseLong(scanner.nextLine());

                    boolean removida = turmaService.excluirTurma(idTurma);
                    if (removida) {
                        System.out.println("\u001B[32mTurma removida com sucesso!\u001B[0m");
                    } else {
                        System.out.println("Turma não encontrada.");
                    }
                }


                case 19 -> {
                    System.out.print("ID da disciplina que deseja editar: ");
                    Long idDisciplina = Long.parseLong(scanner.nextLine());

                    Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);
                    if (disciplina == null) {
                        System.out.println("Disciplina não encontrada.");
                        break;
                    }

                    System.out.print("Novo nome da disciplina (deixe em branco para manter): ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isBlank()) {
                        disciplina.setNome(novoNome);
                    }

                    Disciplina atualizada = disciplinaService.editarDisciplina(idDisciplina, disciplina);
                    if (atualizada != null) {
                        System.out.println("\u001B[32mDisciplina atualizada com sucesso✅\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mErro ao atualizar disciplina.\u001B[0m");
                    }
                }

                case 20 -> {
                    System.out.print("ID da disciplina que deseja remover: ");
                    Long idDisciplina = Long.parseLong(scanner.nextLine());

                    boolean removida = disciplinaService.excluirDisciplina(idDisciplina);
                    if (removida) {
                        System.out.println("\u001B[32mDisciplina removida com sucesso✅\u001B[0m");
                    } else {
                        System.out.println("Disciplina não encontrada.");
                    }
                }


                case 0 -> {
                    System.out.println("Encerrando...");
                    return;
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }


}
