package abb;

import aplicacao.Cliente;
import filasInt.FilaCliente;

public class AbbCliente {

	private class NO {
		Cliente dado;
		NO esq, dir;
	}

	public NO root = null;

	public NO inserirCliente(NO p, Cliente info) {
		// insere elemento em uma ABB
		if (p == null) {
			p = new NO();
			p.dado = info;
			p.esq = null;
			p.dir = null;
		} else if (Long.parseLong(info.getCpf()) < Long.parseLong(p.dado.getCpf()))
			p.esq = inserirCliente(p.esq, info);
		else
			p.dir = inserirCliente(p.dir, info);
		return p;
	}
	
	public NO inserirClienteOferta(NO p, Cliente info) {
		// insere elemento em uma ABB
		if (p == null) {
			p = new NO();
			p.dado = info;
			p.esq = null;
			p.dir = null;
		} else if (info.getTotalGasto() < p.dado.getTotalGasto())
			p.esq = inserirClienteOferta(p.esq, info);
		else
			p.dir = inserirClienteOferta(p.dir, info);
		return p;
	}

	public void mostraEmOrdem(NO p) {
		if (p != null) {
			mostraEmOrdem(p.esq);
			System.out.print(p.dado + "\t");
			mostraEmOrdem(p.dir);
		}
	}

	public Cliente consultaValorOferta(NO p, double valor) {
		if (p == null) {
			return null;
		} else {

			if (valor >= p.dado.getTotalGasto()) {
				return p.dado;
			} else if (valor <p.dado.getTotalGasto()) {
				return consultaValorOferta(p.esq, valor);
			} else {
				return consultaValorOferta(p.dir, valor);
			}
		}
	}
	
	
	public NO alterarApto(NO p, String cpf) {
		if (p != null) {
			if (cpf.equals(p.dado.getCpf())) {
				p.dado.setApto(false);
			} else if (Long.parseLong(cpf) < Long.parseLong(p.dado.getCpf())) {
				alterarApto(p.dir, cpf);
			} else {
				alterarApto(p.esq, cpf);
			}
		} 
		return p;
	}
	
	
	public Cliente exibirClienteporCPF(NO p, String cpf) {
		if (p != null) {
			if (cpf.equals(p.dado.getCpf())) {
				//System.out.println(p.dado);
				return p.dado;
			} else if (Long.parseLong(cpf) < Long.parseLong(p.dado.getCpf())) {
				return exibirClienteporCPF(p.dir, cpf);
			} else {
				return exibirClienteporCPF(p.esq, cpf);
			}
		}  
		
		return null;
	}
	
	
	public void exibirClientesAptos(NO p) {
		if (p != null) {
			if (p.dado.isApto()) {
				System.out.println(p.dado);
			} 
			
			exibirClientesAptos(p.esq);
			exibirClientesAptos(p.dir);
			
		} 
	}
	
	public void percorrerEInserirAptos(NO root, AbbCliente oferta, double minimo) {
	    if (root != null) {
	        percorrerEInserirAptos(root.esq, oferta, minimo);
	        if (root.dado.getTotalGasto() >= minimo) {
	            oferta.root = oferta.inserirClienteOferta(oferta.root, root.dado);
	        }
	        percorrerEInserirAptos(root.dir, oferta, minimo);
	    }
	}
	
	public int percorreSomando(NO root, double minimo, int cont) {
		
	    if (root != null) {
	        if (root.dado.getTotalGasto() >= minimo) {
	            cont++;
	        }
	        cont = percorreSomando(root.esq, minimo, cont);
	        cont = percorreSomando(root.dir, minimo, cont);
	    }
	    return cont;
	}
	
	
	public double percorreSomandoGastos(NO root, double somaGastos) {
		
	    if (root != null) {
	    	somaGastos += root.dado.getTotalGasto();
	        somaGastos = percorreSomandoGastos(root.esq, somaGastos);
	        somaGastos = percorreSomandoGastos(root.dir, somaGastos);
	    }
	    return somaGastos;
	}
	
	public void enfileirarCliente(NO p, FilaCliente filaOferta) {
		if (p != null) {
			enfileirarCliente(p.dir, filaOferta);
			filaOferta.enqueue(p.dado);
			enfileirarCliente(p.esq, filaOferta);
			//System.out.print(p.dado + "\t");
		}
	}
	

	public int contaConsulta(NO p, String valor, int cont) {

		if (p != null) {
			cont++;
			if (valor.equalsIgnoreCase(p.dado.getCpf())) {
				return cont;
			} else if (Integer.parseInt(valor) < Integer.parseInt(p.dado.getCpf())) {
				return contaConsulta(p.esq, valor, cont);

			} else {
				return contaConsulta(p.dir, valor, cont);
			}
		}

		return cont;
	}

	public int contaNos(NO p, int cont) {
		if (p != null) {
			cont++;
			cont = contaNos(p.esq, cont);
			cont = contaNos(p.dir, cont);
		}
		return cont;
	}

	public NO removeValor(NO p, String info) {
		if (p != null) {
			if (info.equals(p.dado.getCpf())) {
				if (p.esq == null && p.dir == null) // nó a ser removido é nó folha
					return null;
				if (p.esq == null) { // se não há sub-árvore esquerda o ponteiro passa apontar para a sub-árvore
										// direita
					return p.dir;
				} else {
					if (p.dir == null) { // se não há sub-árvore direita o ponteiro passa apontar para a sub-árvore
											// esquerda
						return p.esq;
					} else { /*
								 * o nó a ser retirado possui sub-arvore esquerda e direita, então o nó que será
								 * retirado deve-se encontrar o menor valor na sub-árvore á direita
								 */
						NO aux, ref;
						ref = p.dir;
						aux = p.dir;
						while (aux.esq != null)
							aux = aux.esq;
						aux.esq = p.esq;
						return ref;
					}
				}
			} else { // procura dado a ser removido na ABB
				if (Long.parseLong(info) < Long.parseLong(p.dado.getCpf()))
					p.esq = removeValor(p.esq, info);
				else
					p.dir = removeValor(p.dir, info);
			}
		}
		
		return p;
	}

	public Cliente maximo(NO p) {
		NO aux = p;

		while (aux.dir != null) {
			aux = aux.dir;
		}
		return aux.dado;

	}

}
