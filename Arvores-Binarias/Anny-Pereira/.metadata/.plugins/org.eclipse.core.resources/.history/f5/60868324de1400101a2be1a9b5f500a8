package aplicacao;

import java.util.Scanner;

import arvores.ABBint;

public class MainTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner le = new Scanner(System.in);
		
		
		//criando objeto
		ABBint abb = new ABBint();
		
		
		int opcao;
		
		do {
			System.out.println("0 - sair");
			System.out.println("1 - inserir 1 valor na ABB");
			System.out.println("2 - apresentar nos da ABB");
			System.out.println("3 - apresenta qtd de nos na ABB");
			System.out.println("4 - verifica a existencia de valor na ABB");
			System.out.println("5 - verifica qtd de comparacoes para pesquisar um valor na ABB");
			
			opcao = le.nextInt();
			
			switch (opcao) {
			case 0: {
				System.out.println("Encerrando o programa");
				break;
			}
			case 1:
				System.out.println("Informe o valor do dado:");
				int valor = le.nextInt();
				//inserir na ABB   // root -> raiz da arvore
				abb.root = abb.inserir(abb.root, valor);
				
				break;
			case 2:
				//mostra ABB
				abb.mostraEmOrdem(abb.root);
				System.out.println();
				break;
				
			case 3:
				//conta nos presentes na ABB
				System.out.println("Quantidade de nos= "+abb.contaNos(abb.root, 0));
				break;
				
			case 4:
				System.out.println("Informe o valor a ser procurado: ");
				valor = le.nextInt();
				//metodo consulta
				if(abb.consulta(abb.root, valor)) {
					System.out.println("Valor esta na ABB!");
				} else {
					System.out.println("Valor nao encontrado na ABB");
				}
				break;
			
			case 5:
				System.out.println("Informe o valor a ser procurado: ");
				valor = le.nextInt();
				//metodo conta consulta
				int comparacoes = abb.contaConsulta(abb.root, valor, 0);
				System.out.println("Numero de comparacoes realizadas na busca: "+ comparacoes);
				break;
				
			default:
				System.out.println("Opcao invalida");
			}
		} while(opcao !=0);
		
		
		le.close();

	}

}
