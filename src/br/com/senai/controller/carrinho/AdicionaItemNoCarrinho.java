package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;

public class AdicionaItemNoCarrinho {

	Scanner entrada = new Scanner(System.in);
	CarrinhoModel carrinhoModel;
	ListaProduto listaProduto;
	EditaProduto editaProduto;
	private Connection connection;

	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public boolean verificaSeExisteProduto(int idDoProduto) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return false;

			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean verificaSeExisteQuantidade(int quantidadeDeProduto, int idDoProduto) {
		PreparedStatement preparedStatement;

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if (resultSet.getInt("quantidadeDeProduto") < quantidadeDeProduto) {
				System.out.println("Este produto não existe nessa quantidade.");
				return false;

			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("all")
	public void cadastrarItemNoCarrinho() {
		carrinhoModel = new CarrinhoModel();
		listaProduto = new ListaProduto();

		System.out.println("\n--- ADICIONAR ITEM NO CARRINHO ---\n");

		if (listaProduto.listarProdutos() == null) {
			return;
		}

		System.out.print("Informe o ID do produto: ");
		int idDoProduto = entrada.nextInt();

		System.out.print("Informe a quantidade de itens: ");
		int quantidadeDeProduto = entrada.nextInt();

		try {
			if (!verificaSeExisteProduto(idDoProduto)) {
				return;
			}
			if (!verificaSeExisteQuantidade(quantidadeDeProduto, idDoProduto)) {
				return;
			}
			String sql = "SELECT * FROM carrinho where codigoDoProdutoCarrinho = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);

			ResultSet resultSet = preparedStatement.executeQuery();
			preparedStatement.execute();

			if (!resultSet.next()) {
				sql = "INSERT INTO carrinho (codigoDoProdutoCarrinho, quantidadeDeProduto) VALUES (?, ?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, idDoProduto);
				preparedStatement.setInt(2, quantidadeDeProduto);
			} else {
				sql = "update carrinho set quantidadeDeProduto = ? where codigoDoProdutoCarrinho = ? ";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, resultSet.getInt("quantidadeDeProduto") + quantidadeDeProduto);
				preparedStatement.setInt(2, idDoProduto);
			}
			preparedStatement.execute();

			sql = "select * from produto where codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			double preco = resultSet.getDouble("precoDoProduto");
			preparedStatement.execute();

			sql = "update produto set quantidadeDeProduto = ?, saldoEmEstoque = ? where codigoDoProduto = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, resultSet.getInt("quantidadeDeProduto") - quantidadeDeProduto);
			preparedStatement.setDouble(2, (resultSet.getInt("quantidadeDeProduto") - quantidadeDeProduto) * preco);
			preparedStatement.setInt(3, idDoProduto);
			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao adicionar item ao carrinho");
		}
	}

}
