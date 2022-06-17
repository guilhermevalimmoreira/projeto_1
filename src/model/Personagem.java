
package model;

import model.Arma;
import model.Acao;


public class Personagem implements Acao{
    
    private String nome;
    private String nivel;
    private String familia;
    private String classe;
    public Arma arma = new Arma();
    
    @Override
    public void Atacar(){
        System.out.println("O " + this.getClasse() + " " + this.getNome() + " atacou com " + this.arma.getTipo());
    }
    
    @Override
    public void Defender(){
        System.out.println("O " + this.getClasse()+ " " + this.getNome() + " defendeu-se do ataque");
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    
}
