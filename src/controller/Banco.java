package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Arma;
import model.Artesao;
import model.Ferreiro;
import model.Personagem;

public class Banco 
{
    
    /***************** CONEXÃO COM O BANCO DE DADOS ************************/
    
    // objeto responsável pela conexão com o servidor do banco de dados
    Connection con;
    // objeto responsável por preparar as consultas dinâmicas
    PreparedStatement pst;
    // objeto responsável por executar as consultas estáticas
    Statement st;
    // objeto responsável por referenciar a tabela resultante da busca
    ResultSet rs;

    // NOME DO BANCO DE DADOS
    String database = "RPG";
    // URL: VERIFICAR QUAL A PORTA
    String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    // USUÁRIO
    String user = "root";
    // SENHA
    String password = "Blodmaster123";

    boolean sucesso = false;
    
    // Conectar ao banco de dados
    public void connectToDb() 
    {
        try 
        {  
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão feita com sucesso!");
        } 
        catch (SQLException ex) 
        {
             System.out.println("Erro: " + ex.getMessage());
        }
                
    }
    
     
    /************************ INSERIR DADOS *********************************/
    public boolean inserirArma(Arma novaArma) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO Arma (tipo) values (?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novaArma.getTipo()); //1- refere-se à primeira interrogação
                                                       //e assim por diante....
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {   //Encerra a conexão
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
    
    public boolean inserirPersonagem(Personagem novoPersonagem) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO Personagem (nome, nivel, familia, classe , Arma_tipo) values (?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoPersonagem.getNome()); //1- refere-se à primeira interrogação
            pst.setString(2, novoPersonagem.getNivel());  //2- refere-se à segunda interrogação
            pst.setString(3, novoPersonagem.getFamilia());
            pst.setString(4, novoPersonagem.getClasse());
            pst.setString(5, novoPersonagem.arma.getTipo());
                                                       //e assim por diante....
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {   //Encerra a conexão
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
    
    public boolean inserirFerreiro(Ferreiro novoFerreiro) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO Ferreiro (nome, familia) values (?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoFerreiro.getNome()); //1- refere-se à primeira interrogação
            pst.setString(2, novoFerreiro.getFamilia());
                                                       //e assim por diante....
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {   //Encerra a conexão
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
    
    public boolean inserirArtesao(Artesao novoArtesao) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO Artesao (nome, ferreiro_nome) values (?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoArtesao.getNome()); //1- refere-se à primeira interrogação
            pst.setString(2, novoArtesao.getFerreiro_nome());
                                                       //e assim por diante....
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {   //Encerra a conexão
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
    /************************ BUSCAR DADOS *********************************/
    public ArrayList<Personagem> buscarPersonagem() 
    {
        ArrayList<Personagem> listaDePersonagens = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM Personagem";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de personagens: ");
            while(rs.next())
            {
                //System.out.println(rs.getString("nome"));
                Personagem personagemTemp = new Personagem();
                personagemTemp.setNome(rs.getString("nome"));
                personagemTemp.setNivel(rs.getString("nivel"));
                personagemTemp.setFamilia(rs.getString("familia"));
                personagemTemp.setClasse(rs.getString("classe"));
                personagemTemp.arma.setTipo(rs.getString("Arma_tipo"));
                System.out.println("Nome = "+personagemTemp.getNome());
                System.out.println("Nivel = "+personagemTemp.getNivel());
                System.out.println("Familia = "+personagemTemp.getFamilia());
                System.out.println("Classe = "+personagemTemp.getClasse());
                System.out.println("Arma = "+personagemTemp.arma.getTipo());
                System.out.println("---------------------------------");
                listaDePersonagens.add(personagemTemp);
            }
            sucesso = true;
            return listaDePersonagens;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDePersonagens;
    }
    
    public ArrayList<Artesao> buscarArtesao() 
    {
        ArrayList<Artesao> listaDeArtesaos = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM Artesao";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de artesãos: ");
            while(rs.next())
            {
                //System.out.println(rs.getString("nome"));
                Artesao artesaoTemp = new Artesao();
                artesaoTemp.setNome(rs.getString("nome"));
                artesaoTemp.setFerreiro_nome(rs.getString("ferreiro_nome"));
                System.out.println("Nome = "+artesaoTemp.getNome());
                System.out.println("Ferreiro mestre = "+artesaoTemp.getFerreiro_nome());
                System.out.println("---------------------------------");
                listaDeArtesaos.add(artesaoTemp);
            }
            sucesso = true;
            return listaDeArtesaos;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDeArtesaos;
    }
    
    public ArrayList<Ferreiro> buscarFerreiro() 
    {
        ArrayList<Ferreiro> listaDeFerreiros = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM Ferreiro";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de ferreiros: ");
            while(rs.next())
            {
                //System.out.println(rs.getString("nome"));
                Ferreiro ferreiroTemp = new Ferreiro();
                ferreiroTemp.setNome(rs.getString("nome"));
                ferreiroTemp.setFamilia(rs.getString("familia"));
                System.out.println("Nome = "+ferreiroTemp.getNome());
                System.out.println("Familia = "+ferreiroTemp.getFamilia());
                System.out.println("---------------------------------");
                listaDeFerreiros.add(ferreiroTemp);
            }
            sucesso = true;
            return listaDeFerreiros;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDeFerreiros;
    }
    
    /************************ ATUALIZAR DADOS *********************************/
    public boolean atualizarPersonagem(String nome, String nivel, String familia, String classe, String arma) 
    {
        connectToDb();
        //Comando em SQL
        String sql = "UPDATE Personagem SET nivel=?, familia=?, classe=?, Arma_tipo=? WHERE nome=?";
        
         //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, nivel);
            pst.setString(2, familia);
            pst.setString(3, classe);
            pst.setString(4, arma);
            pst.setString(5, nome);
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }

        }
        return sucesso;
    }
    
    /************************ DELETAR REGISTROS *******************************/
    public boolean deletarPersonagem(String nome) 
    {
        connectToDb();
        //Comando em SQL:
        String sql = "DELETE FROM Personagem WHERE nome=?";
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
}
