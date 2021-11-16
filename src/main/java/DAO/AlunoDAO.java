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
	 
	 public List<Aluno> selectAluno() throws ClassNotFoundException {
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
	 	
}

