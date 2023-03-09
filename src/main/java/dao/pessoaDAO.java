package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Pessoa;

public class pessoaDAO {
	
	public boolean inserir(Pessoa pessoa) {
		try {
			Connection conn = new conexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("Insert into usuario (nome, senha, email) values (?,?,?)");
			
			statement.setString(1, pessoa.getNome());
			statement.setString(2, pessoa.getEmail());
			statement.setString(3, pessoa.getSenha());
			
			statement.execute();
			
			conn.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
