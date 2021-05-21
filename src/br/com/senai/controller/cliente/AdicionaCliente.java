package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ClienteModel;

public class AdicionaCliente {

	Scanner entrada = new Scanner(System.in);

	ClienteModel clienteModel;
	private Connection connection;

	public AdicionaCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ClienteModel definirCliente() {
		clienteModel = new ClienteModel();

		System.out.print("\n--- CADASTRAR CLIENTE ---\n ");
		System.out.print("Informe o nome do cliente: ");
		clienteModel.setNome(entrada.next());

		try {
			String sql = "INSERT INTO cliente (nome)" + " VALUES (?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, clienteModel.getNome());

			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados.");
		}

		return clienteModel;
	}
}