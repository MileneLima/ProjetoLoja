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

	public CarrinhoModel cadastrarItemNoCarrinho() {
		carrinhoModel = new CarrinhoModel();
		listaProduto = new ListaProduto();

		listaProduto.listarProdutos();

		System.out.println("\n--- ADICIONAR ITEM NO CARRINHO ---\n");
		System.out.print("Informe o ID do produto: ");
		carrinhoModel.setIdDoProduto(entrada.nextInt());
		System.out.print("Informe a quantidade de itens: ");
        carrinhoModel.setQuantidadeDeItensNoCarrinho(entrada.nextInt());

		try {
			String sql = "INSERT INTO carrinho (codigoDoProdutoCarrinho, quantidadeDeProduto)" + 
						" VALUES (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carrinhoModel.getIdDoProduto());
			preparedStatement.setInt(2, carrinhoModel.getQuantidadeDeItensNoCarrinho());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Erro ao adicionar item ao carrinho");
		}
		return carrinhoModel;
	}
}
