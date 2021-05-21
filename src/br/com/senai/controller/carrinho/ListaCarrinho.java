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

			if (!resultSet.next()) {
				System.out.println("Não possui dados cadastrados.");
				return null;
			}

			System.out.println("--- ITENS NO CARRINHO ---");
			System.out.printf("| %2s | %4s\n", "ID", "Qtd");

			resultSet.previous();

			while (resultSet.next()) {
				System.out.printf("| %2s |%4s\n", resultSet.getInt("codigoDoProdutoCarrinho"),
						resultSet.getInt("quantidadeDeProduto"));
			}
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}

	public ResultSet gerarCupom() {
		PreparedStatement preparedStatement;

		try {
			String sql = "select * from cliente";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();

			listarItensNoCarrinho();
			System.out.println(resultSet.getString("nome"));
			System.out.println("Compra finalizada!");
			
			sql = "delete from carrinho";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
