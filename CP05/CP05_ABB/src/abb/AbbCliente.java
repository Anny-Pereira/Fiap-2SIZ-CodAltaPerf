package abb;

import aplicacao.Cliente;
import filas.FilaCliente;

public class AbbCliente {

    private class NO {
        Cliente dado;
        NO esq, dir;
    }

    public NO root = null;

    public NO inserirClientePorCpf(NO p, Cliente info) {
        // insere elemento em uma ABB
        if (p == null) {
            p = new NO();
            p.dado = info;
            p.esq = null;
            p.dir = null;
        } else if (Long.parseLong(info.getCpf()) < Long.parseLong(p.dado.getCpf()))
            p.esq = inserirClientePorCpf(p.esq, info);
        else
            p.dir = inserirClientePorCpf(p.dir, info);
        return p;
    }

    public NO inserirClientePorGasto(NO p, Cliente info) {
        // insere elemento em uma ABB
        if (p == null) {
            p = new NO();
            p.dado = info;
            p.esq = null;
            p.dir = null;
        } else if (info.getTotalGasto() < p.dado.getTotalGasto())
            p.esq = inserirClientePorGasto(p.esq, info);
        else
            p.dir = inserirClientePorGasto(p.dir, info);
        return p;
    }

    public void mostraEmOrdem(NO p) {
        if (p != null) {
            mostraEmOrdem(p.esq);
            System.out.println(p.dado + "\t");
            mostraEmOrdem(p.dir);
        }
    }

    public Cliente consultaValorOferta(NO p, double valor) {
        if (p == null) {
            return null;
        } else {

            if (valor >= p.dado.getTotalGasto()) {
                return p.dado;
            } else if (valor < p.dado.getTotalGasto()) {
                return consultaValorOferta(p.esq, valor);
            } else {
                return consultaValorOferta(p.dir, valor);
            }
        }
    }

    public NO atualizarAptoParaOferta(NO p, String cpf) {
        if (p != null) {
            if (cpf.equals(p.dado.getCpf())) {
                p.dado.setApto(false);
            } else if (Long.parseLong(cpf) < Long.parseLong(p.dado.getCpf())) {
                p.esq = atualizarAptoParaOferta(p.esq, cpf); // Atribuir o resultado
            } else {
                p.dir = atualizarAptoParaOferta(p.dir, cpf); // Atribuir o resultado
            }
        }
        return p;
    }

    public Cliente consultarClientePorCpf(NO p, String cpf) {
        if (p != null) {
            if (cpf.equals(p.dado.getCpf())) {
                return p.dado;
            } else if (Long.parseLong(cpf) < Long.parseLong(p.dado.getCpf())) {
                return consultarClientePorCpf(p.esq, cpf);
            } else {
                return consultarClientePorCpf(p.dir, cpf);
            }
        }
        return null;
    }

    public void exibirClientesAptosParaOferta(NO p) {
        if (p != null) {
            if (p.dado.isApto()) {
                System.out.println(p.dado);
            }
            exibirClientesAptosParaOferta(p.esq);
            exibirClientesAptosParaOferta(p.dir);
        }
    }

    public void popularAbbComClientesAptos(NO p, AbbCliente oferta, double minimo) {
        if (p != null) {
            popularAbbComClientesAptos(p.esq, oferta, minimo);
            if (p.dado.getTotalGasto() >= minimo) {
                oferta.root = oferta.inserirClientePorGasto(oferta.root, p.dado);
            }
            popularAbbComClientesAptos(p.dir, oferta, minimo);
        }
    }

    public int contaClientesComGastoMinimo(NO p, double minimo, int cont) {
        if (p != null) {
            if (p.dado.getTotalGasto() >= minimo) {
                cont++;
            }
            cont = contaClientesComGastoMinimo(p.esq, minimo, cont);
            cont = contaClientesComGastoMinimo(p.dir, minimo, cont);
        }
        return cont;
    }

    public double somaGastosDeTodosClientes(NO p, double somaGastos) {
        if (p != null) {
            somaGastos += p.dado.getTotalGasto();
            somaGastos = somaGastosDeTodosClientes(p.esq, somaGastos);
            somaGastos = somaGastosDeTodosClientes(p.dir, somaGastos);
        }
        return somaGastos;
    }

    public void enfileirarClientes(NO p, FilaCliente filaOferta) {
        if (p != null) {
            enfileirarClientes(p.dir, filaOferta);
            filaOferta.enqueue(p.dado);
            enfileirarClientes(p.esq, filaOferta);
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

    public NO removeValor(NO p, String cpf) {
        if (p != null) {
            if (cpf.equals(p.dado.getCpf())) {
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
                if (Long.parseLong(cpf) < Long.parseLong(p.dado.getCpf()))
                    p.esq = removeValor(p.esq, cpf);
                else
                    p.dir = removeValor(p.dir, cpf);
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
