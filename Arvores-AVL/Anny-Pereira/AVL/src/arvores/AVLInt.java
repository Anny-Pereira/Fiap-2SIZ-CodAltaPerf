package arvores;

public class AVLInt {
	
	private class NO{
		int dado;
		NO dir;
		NO esq;
		int hEsq;
		int hDir;
	}
	
	public NO root = null;
	
	public void mostraEmOrdem(NO p) {
		if(p!=null) {
			mostraEmOrdem(p.esq);
			System.out.println(p.dado + "\t");
			mostraEmOrdem(p.dir);
		}
	}
	
	
	public void mostraFB(NO p) {
		if(p !=null) {
			mostraFB(p.esq);
			mostraFB(p.dir);
			System.out.println("Dado: "+p.dado+"\t FB= "+(p.hDir-p.hEsq));
		}
	}
	
	
	
	public NO balanceamento (NO p) {
		// analisa FB e realiza rotações necessárias para balancear árvore
		int FB = p.hDir - p.hEsq;
		if (FB > 1) {
		int fbFilhoDir = p.dir.hDir - p.dir.hEsq;
		if (fbFilhoDir >= 0)
			p = rotacaoEsquerda(p);
		else {
		p.dir = rotacaoDireita(p.dir);
		p = rotacaoEsquerda(p);
		}
		}
		else {
		if (FB < -1) {
		int fbFilhoEsq = p.esq.hDir - p.esq.hEsq;
		if (fbFilhoEsq <= 0)
		p = rotacaoDireita(p);
		else {
		p.esq = rotacaoEsquerda(p.esq);
		p = rotacaoDireita(p);
		}
		}
		}
		return p;
	}
	
	public NO rotacaoDireita (NO p){
		// faz rotação para direita em relação ao nó apontado por p	
			NO q,temp;
			q = p.esq;
			temp = q.dir;
			q.dir = p;
			p.esq = temp;
			return q;
	}
	
	
	public NO rotacaoEsquerda(NO p) {
		// faz rotação para esquerda em relação ao nó apontado por p
			NO q,temp;
			q = p.dir;
			temp = q.esq;
			q.esq = p;
			p.dir = temp;
			return q;
	}

	
	public void atualizaAlturas(NO p) {
		/*atualiza informação da altura de cada nó depois da remoção percorre a árvore usando percurso pós-ordem para ajustar primeiro os nós folhas (profundidade maior) e depois os níveis acima */ 
		  if( p != null) {
			atualizaAlturas(p.esq);
			if (p.esq == null)
				p.hEsq = 0;
			else  if (p.esq.hEsq > p.esq.hDir)
				  p.hEsq = p.esq.hEsq+1;
			     else
				  p.hEsq = p.esq.hDir+1;
			atualizaAlturas(p.dir);
			if (p.dir == null)
				p.hDir = 0;
			else if (p.dir.hEsq > p.dir.hDir)
				 p.hDir = p.dir.hEsq+1;
			     else
				 p.hDir = p.dir.hDir+1;
			}
	}
	
	public NO inserirAVL(NO p, int info) {
		if (p == null) { //nó inserido sempre será nó folha
			p=new NO();
			p.dado = info;
			p.esq = null;
			p.dir = null;
			p.hDir=0;
			p.hEsq=0;
		    }
		else if (info < p.dado){
			p.esq= inserirAVL (p.esq, info);
			if (p.esq.hDir > p.esq.hEsq) //Altura do nó será a maior
			    p.hEsq = p.esq.hDir + 1; //altura dos seus filhos
			else
			    p.hEsq = p.esq.hEsq + 1;
			p= balanceamento(p);
			}
		     else {
			p.dir=inserirAVL(p.dir, info);
			if (p.dir.hDir > p.dir.hEsq)
			    p.hDir = p.dir.hDir + 1;
			else
			    p.hDir = p.dir.hEsq + 1;
			p= balanceamento(p);
		        }
		return p;

	}
	
	
	 

}
