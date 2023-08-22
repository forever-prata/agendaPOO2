package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import classe.Contato;

public class ContatoDAO {
    final String NOMEDATABELA = "contato";
    public boolean inserir(Contato contato) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (Nome,Nsc,Email,Telefone) VALUES (?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setDate(2, new java.sql.Date(contato.getNasc().getTime()));
            ps.setString(3, contato.getEmail());
            ps.setString(4, contato.getTelefone());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Contato> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Contato> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            //System.err.println("Erro: " + e.toString());
            //e.printStackTrace();
            return null;
        }
    }
    
    public List<Contato> montarLista(ResultSet rs) {
        List<Contato> listObj = new ArrayList<Contato>();
        try {
            while (rs.next()) {
                Contato obj = new Contato();
                obj.setNome(rs.getString(2));
                obj.setNasc(rs.getDate(3));
                obj.setTelefone(rs.getString(4));
                obj.setEmail(rs.getString(5));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            //System.err.println("Erro: " + e.toString());
            //e.printStackTrace();
            return null;
        }
    }
}
