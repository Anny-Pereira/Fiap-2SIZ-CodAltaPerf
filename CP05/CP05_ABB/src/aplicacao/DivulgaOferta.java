package aplicacao;

import java.util.Scanner;

import abb.AbbCliente;
import filasInt.FilaCliente;

public class DivulgaOferta {

	/*
	 * Anny Isabelle Gomes Pereira 	553793 
	 * Giovanna Makida 				552852 
	 * Katharine Fernandes Viana 	552673 
	 * Larissa Alves de Souza 		552664
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner le = new Scanner(System.in);

		AbbCliente cadastro = new AbbCliente();
		AbbCliente oferta = new AbbCliente();

		FilaCliente filaOferta = new FilaCliente();
		filaOferta.init();

		int opcao, op;
		String nome, whatsapp, cpf;
		double totalGasto;
		boolean apto;

		do {
			System.out.println(" 0 - Encerrar o programa");
			System.out.println(" 1 - Inscricao um cliente");
			System.out.println(" 2 - Oferta de novo produto/promocacao");
			System.out.println(" 3 – Entrar no Submenu ");
			System.out.println(" 4 - Remove um cliente do cadastro");
			opcao = le.nextInt();

			switch (opcao) {

			case 0:
				System.out.println("\n\nClientes que não aceitaram ou não estavam adequados para a oferta");
				/*
				 * Apresenta todos os clientes que nao aceitaram nenhuma oferta
				 */
				cadastro.exibirClientesAptos(cadastro.root);
				break;

			case 1:
				System.out.print("Digite nome: ");
				le.nextLine();
				nome = le.nextLine();
				System.out.print("Digite CPF (somente numeros): ");
				cpf = le.next();
				System.out.print("Whatsapp: ");
				whatsapp = le.next();
				System.out.print("Informe total gasto do cliente R$: ");
				totalGasto = le.nextDouble();
				apto = true;
				/*
				 * Intancia um objeto da classe Cliente e insere na ABB cadastro organizada pelo
				 * CPF
				 */
				Cliente cliente = new Cliente(nome, whatsapp, cpf, totalGasto, apto);
				cadastro.root = cadastro.inserirCliente(cadastro.root, cliente);
				break;

			case 2:
				System.out.print("Qual o valor de saldo minimo exigido: R$ ");
				totalGasto = le.nextDouble();
				/*
				 * Percorrendo a ABB de cadastro gera ABB oferta usando como criterio de
				 * organizacao o total de gasto do cliente.
				 *
				 * Usando um método de percurso gerar uma fila de clientes para contactar via
				 * whatsapp, em ordem decrescente de gastos (o primeiro cliente deve ser o com
				 * maior valor de gasto.
				 *
				 * Esvazia ABB oferta.
				 */
				/*
				 * Nesse trecho de programa que é simulada a tentativa de fazer o contato com
				 * cada um dos clientes presentes na fila. Até nao ter mais clientes para
				 * contactar.
				 *
				 * Cada cliente que aceita a oferta tem o atributo apto para oferta alterado
				 * para false no seu cadastro
				 */
					
//				Cliente cli = cadastro.consultaValorOferta(cadastro.root, totalGasto);
//				if(cli != null) {
//					oferta.root = oferta.inserirClienteOferta(oferta.root, cli);
//				}
			
				cadastro.percorrerEInserirAptos( cadastro.root, oferta, totalGasto);
				oferta.enfileirarCliente(oferta.root, filaOferta);
				oferta.root = null;
				
				while(!filaOferta.isEmpty()) {
					Cliente cli = filaOferta.dequeue();
					
					System.out.println("\nCliente: "+ cli.getNome()+"\tValor gasto: "+cli.getTotalGasto());
					System.out.print("Voce aceita essa oferta (S / N)?");
					String resposta = le.next();
					
					if(resposta.equalsIgnoreCase("S")) {
						cadastro.root = cadastro.alterarApto(cadastro.root, cli.getCpf());
						System.out.println("Uau, voce aceitou a oferta!");
					} else {
						System.out.println("Poxa, quem sabe da proxima...");
					}
					
				}
				
				break;

			case 3:
				do {
					System.out.println("\t 1) Consulta cliente buscando pelo CPF");
					System.out.println("\t 2) Apresenta o total de gasto de todos os clientes");
					System.out.println(
							"\t 3) Apresenta a quantidade de clientes com saldo acima de um valor a ser consultado");
					System.out.println("\t 4) Volta menu principal");
					op = le.nextInt();

					switch (op) {

					case 1:
						System.out.print("Informe CPF para consulta");
						cpf = le.next();
						/*
						 * Apresenta todos os atributos do cliente consultado ou avisa se CPF nao foi
						 * encontrado.
						 */
						 
						Cliente cli = cadastro.exibirClienteporCPF(cadastro.root, cpf);
						if (cli == null) {
							System.out.println("Cliente nao encontrado!");
						} else {
							System.out.println(cli);
						}
						
						break;
					case 2:
						/*
						 * Apresenta soma de todos os gastos de todos os clientes
						 */
						double somaGastos =0;
						somaGastos = cadastro.percorreSomandoGastos(cadastro.root, somaGastos);
						System.out.println("Soma de todos os gastos de todos os clientes: "+ somaGastos);
						
						break;
					case 3:
						System.out.print("Qual valor minimo de gastos para consulta? R$ ");
						double minimo = le.nextDouble();
						/*
						 * Apresenta a quantidade de clientes com gastos a partir do minimo consultado
						 */
						int cont =0;
						cont = cadastro.percorreSomando(cadastro.root, minimo, cont);
						System.out.println("Qtd de clientes acima do minimo: "+ cont);
						
						break;
					case 4:
						break;
					default:
						System.out.println("Opcao invalida");
					}
				} while (op != 4);
				break;
			case 4:
				System.out.print("Informe CPF do cliente que deseja ser retirado do cadastro");
				cpf = le.next();
				/*
				 * Retira da ABB de cadastro o cliente escolhido pelo CPF
				 */
				cadastro.root = cadastro.removeValor(cadastro.root, cpf);
				
				
				break;
			default:
				System.out.println("Opcao invalida");
			}
		} while (opcao != 0);
		le.close();
	}

}
