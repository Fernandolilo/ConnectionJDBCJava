package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import domain.Aluno;

public class AlunoDAO {	
	 
	 public List<Aluno> findAll() throws ClassNotFoundException {
		 Connection conn = ConnectionFactory.getConnection();
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     List<Aluno> alunos = new ArrayList<>();
		 
		 try {
			 stmt = conn.prepareStatement("SELECT * FROM aluno");
			 rs = stmt.executeQuery();
			 
			 while(rs.next()) {				 
				 Aluno aluno = new Aluno();
				 aluno.setId(rs.getInt("id"));
				 aluno.setNome(rs.getString("nome"));
				 aluno.setIdade(rs.getInt("idade"));
				 aluno.setEstado(rs.getString("estado"));				
				 alunos.add(aluno);
				 
			 }
		 } catch (SQLException ex) {
	         Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
	     }
	     finally{
	      ConnectionFactory.closeConnection(conn, stmt, rs);
	     }
		 return alunos;
	 }
	 
	 public Aluno findById(Integer id) throws ClassNotFoundException {
		 Connection conn = ConnectionFactory.getConnection();
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     Aluno aluno = new Aluno();
		 
		 try {
			 stmt = conn.prepareStatement("SELECT * FROM aluno WHERE id = ?");
			 stmt.setInt(1, id);
			 rs = stmt.executeQuery();
			 
			if(rs.next()) {				 
				 aluno.setId(rs.getInt("id"));
				 aluno.setNome(rs.getString("nome"));
				 aluno.setIdade(rs.getInt("idade"));
				 aluno.setEstado(rs.getString("estado"));				
				 
			 }
		 } catch (SQLException ex) {
	         Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
	     }
	     finally{
	      ConnectionFactory.closeConnection(conn, stmt, rs);
	     }
		 return aluno;
	 }
	
	 public void delete(Integer id) throws ClassNotFoundException {
		 
		  Connection conn = ConnectionFactory.getConnection();
          PreparedStatement stmt = null;
        
		 try {
			 stmt = conn.prepareStatement("DELETE FROM aluno  WHERE id = ?");
			 stmt.setInt(1, id);			 
			 stmt.executeUpdate();
			 System.out.println("Deletado com sucesso");
		 
			 			
		 }catch(SQLException e) {
			 System.out.println("Erro ao deletar" + e.getErrorCode());
		 }
		 finally {
			 ConnectionFactory.closeConnection(conn, stmt);
		}
	 }
	 
	 public void create (Aluno aluno) throws ClassNotFoundException {
		 Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         try {
        	 stmt = conn.prepareStatement("INSERT INTO aluno (nome, idade, estado) VALUES (?, ? ,?)");
        	 stmt.setString(1, aluno.getNome());
        	 stmt.setInt(2, aluno.getIdade());
        	 stmt.setString(3, aluno.getEstado());
        	 stmt.executeUpdate();
        	 System.out.println("Inserido com sucesso! ");
         }catch(SQLException e) {
        	 System.out.println("Faha na inserção");
         }
         finally {
        	 ConnectionFactory.closeConnection(conn, stmt);
         }
	 }

	 public void update(Aluno aluno) throws ClassNotFoundException {
		 Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = null;
         try {
        	 stmt = conn.prepareStatement("UPDATE `aluno` SET `nome` = ?, `idade` = ?, `estado` = ? WHERE `aluno`.`id` = ?");
        	 stmt.setString(1, aluno.getNome());
        	 stmt.setInt(2, aluno.getIdade());
        	 stmt.setString(3, aluno.getEstado());
        	 stmt.setInt(4, aluno.getId());
        	 
        	 stmt.executeUpdate();
        	 
        	 System.out.println("Atualizado com sucesso! ");
         }catch(SQLException e) {
        	 System.out.println("Faha na atualização");
         }
         finally {
        	 ConnectionFactory.closeConnection(conn, stmt);
         }
	 }
		 	
}

