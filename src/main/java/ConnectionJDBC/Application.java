/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ConnectionJDBC;

import DAO.AlunoDAO;

public class Application {
  
 



public static void main(String [] args) throws ClassNotFoundException{
	 
	
	AlunoDAO dao = new AlunoDAO();	
	System.out.println(dao.selectAluno().toString());	
  }

}
