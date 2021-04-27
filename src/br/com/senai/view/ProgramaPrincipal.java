package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.ProdutoController;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

		// objeto controller do sistema
		ProdutoController produtoController = new ProdutoController();

		boolean sair = false;

		// controle do loop de saida
		do {
			produtoController.menu();
			int opcao = produtoController.opcao();

			switch (opcao) {
			case 1:
				produtos.add(produtoController.cadastrarProduto());
				break;

			case 2:
				produtoController.consultarProdutos(produtos);
				break;
				
			case 9:
				sair = true;
				break;
			default:
				System.out.print("Opção inválida!!!");
			}
		} while (!sair);
		
		System.out.print("Sistema encerrado!!!");
	}

}
