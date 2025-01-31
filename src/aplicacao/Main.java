package aplicacao;

import dao.TransacaoDAOImpl;
import modelo.Transacao;
import servico.ServicoTransacao;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ServicoTransacao servico = new ServicoTransacao(new TransacaoDAOImpl());
        Scanner entrada = new Scanner(System.in);

        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        NumberFormat formatarMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        while (true) {
            System.out.println("\n=== Controle Financeiro ===");
            System.out.println("1. Adicionar Transação");
            System.out.println("2. Listar Transações");
            System.out.println("3. Ver Saldo Atual");
            System.out.println("4. Atualizar Transação");
            System.out.println("5. Deletar Transação");
            System.out.println("6. Sair");
            System.out.println("Escolha uma opção:");
            int opcao = entrada.nextInt();
            entrada.nextLine();

            if (opcao == 1) {
                System.out.println("\n=== Adicionar Transação ===");
                System.out.print("Descrição: ");
                String descricao = entrada.nextLine();

                System.out.print("Valor: ");
                double valor = entrada.nextDouble();
                entrada.nextLine();

                LocalDate data = null;
                while (data == null) {
                    System.out.print("Data (dd-MM-yyyy): ");
                    String dataStr = entrada.nextLine();
                    try {
                        data = LocalDate.parse(dataStr, formatarData);
                    } catch (DateTimeParseException e) {
                        System.out.println("Data inválida. Tente novamente.");
                    }
                }

                String tipo;
                while (true) {
                    System.out.print("Tipo (receita/despesa): ");
                    tipo = entrada.nextLine().toLowerCase();
                    if (tipo.equals("receita") || tipo.equals("despesa")) {
                        break;
                    } else {
                        System.out.println("Tipo inválido. Digite 'receita' ou 'despesa'.");
                    }
                }

                Transacao transacao = new Transacao(valor, descricao, data, tipo);
                servico.adicionarTransacao(transacao);
                System.out.println("\nTransação adicionada!");
                System.out.println("Saldo Atual: " + formatarMoeda.format(servico.calcularTotal()));

            } else if (opcao == 2) {
                System.out.println("\n=== Lista de Transações ===");
                System.out.println("---------------------------");
                for (Transacao t : servico.listarTransacoes()) {
                    System.out.println(t);
                }

            } else if (opcao == 3) {
                System.out.println("\nSaldo Atual: R$ " + formatarMoeda.format(servico.calcularTotal()));

            } else if (opcao == 4) {
                System.out.println("\n=== Atualizar Transação ===");
                System.out.println("Informe o ID da transação a ser atualizada: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                Transacao transacao = servico.buscarPorId(id);
                if (transacao == null) {
                    System.out.println("Transação nao encontrada!");
                } else {
                    System.out.println("Transação atual: " + transacao);

                    System.out.println("Nova descrição (ou deixe vazio para manter): ");
                    String novaDescricao = entrada.nextLine();
                    if (!novaDescricao.isEmpty()) {
                        transacao.setDescricao(novaDescricao);
                    }

                    System.out.println("Novo valor (ou digite -1 para manter): ");
                    double novoValor = entrada.nextDouble();
                    entrada.nextLine();
                    if (novoValor != -1) {
                        transacao.setValor(novoValor);
                    }

                    System.out.print("Nova data (dd-MM-yyyy) ou deixe vazio para manter: ");
                    String novaData = entrada.nextLine();
                    if (!novaData.isEmpty()) {
                        try {
                            LocalDate dataAtualizada = LocalDate.parse(novaData, formatarData);
                            transacao.setData(dataAtualizada);
                        } catch (DateTimeParseException e) {
                            System.out.println("Data inválida. Mantendo a anterior.");
                        }
                    }

                    System.out.print("Novo tipo (receita/despesa (ou deixe vazio para manter): ");
                    String novoTipo = entrada.nextLine().toLowerCase();
                    if (novoTipo.equals("receita") || novoTipo.equals("despesa")) {
                        transacao.setTipo(novoTipo);
                    }

                    servico.atualizarTransacao(transacao);
                    System.out.println("Transação atualizada!");
                    }


            } else if (opcao == 5) {
                System.out.println("\n=== Deletar transação===");
                System.out.println("Informe o Id da transação a ser deletada: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                boolean sucesso = servico.deletarTransacao(id);
                if (sucesso) {
                    System.out.println("Transação deletada com sucesso!");
                } else {
                    System.out.println("Transação não encontrada!");
                }

            }  else if (opcao == 6) {
                System.out.println("Saindo...");
                break;

            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }


        }
        entrada.close();
    }
}





