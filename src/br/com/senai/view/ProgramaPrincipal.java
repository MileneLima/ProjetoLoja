package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;
import br.com.senai.controller.ProdutoController;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<ProdutoModel> produtosCarrinho = new ArrayList<ProdutoModel>();

		// OBJETO CONTROLLER DO SISTEMA
		ProdutoController lojaController = new ProdutoController();

		// CONTROLE DO LOOP DE SAIDA
		boolean sair = false;

		do {
			lojaController.menu();
			int opcao = lojaController.opcao();

			switch (opcao) {
			case 1:
				produtos.add(lojaController.cadastrarProduto());
				break;
			case 2:
				lojaController.listarProdutos(produtos);
				break;
			case 3:
				lojaController.editarProduto(produtos);
				break;
			case 4:
				lojaController.removerProduto(produtos);
				break;
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opção inválida!");
			}

		} while (!sair);
		System.out.println("Sistema encerrado!!!");

	}

}
