package aplicacao;

import java.util.Scanner;

import arvores.AVLInt;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner le = new Scanner(System.in);
		int opcao;
		AVLInt avl = new AVLInt();
		
		
		do {
			System.out.println("0 - Encerrar");
			System.out.println("1 - Insere 1 valor na ABB");
			System.out.println("2 - Apresenta pos-ordem os nos da ABB apresentada");
			opcao = le.nextInt();
			
			switch (opcao) {
			case 0: 
				System.out.println("Encerrando...");
				break;
				
			case 1:
				System.out.println("Informe valor: ");
				int valor = le.nextInt();
				avl.root = avl.inserirH(avl.root, valor);
				break;
				
			case 2:
				System.out.println("****** Apresentacao da AVL ******");
				avl.mostraFB(avl.root);
				break;
			
			default:
				System.out.println("Opcao Invalida!");
			}
		} while(opcao != 0);
		
		
		
		le.close();
			
			
			

	}

}
