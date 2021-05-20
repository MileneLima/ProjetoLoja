package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.dao.DataBaseConnection;

public class ListaCarrinho {
	
	private Connection connection;
	
	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet listarItensNoCarrinho() {
		PreparedStatement preparedStatement;
		
		try {
			String sql = "select * from carrinho";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return null;
			}
			
			System.out.println("--- ITENS NO CARRINHO ---");
			System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
		
			resultSet.previous();
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", 
						resultSet.getInt("idDoProduto"),
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeItensNoCarrinho"),
						resultSet.getDouble("valorTotalPorItem"));	
			}
			
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}

