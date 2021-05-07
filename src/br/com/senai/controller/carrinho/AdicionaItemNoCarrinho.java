package br.com.senai.controller.carrinho;

import java.util.Scanner;
import java.util.List;

import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {
	Scanner entrada = new Scanner(System.in);
	ListaProduto listaProduto = new ListaProduto(); 
	EditaProduto editaProduto = new EditaProduto();
	
	public CarrinhoModel cadastrarItemNoCarrinho(List<ProdutoModel> produtos) {
		CarrinhoModel carrinhoModel = new CarrinhoModel();
		ListaProduto listaProduto = new ListaProduto();
		
		if (produtos.size() <= 0) {
			System.out.println("As estantes estão todas vazias, quer comprar o que retardado?");
			return null;
		}

		listaProduto.listarProdutos(produtos);

		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		carrinhoModel.setIdDoProduto(entrada.nextInt());
		int idDoProduto = carrinhoModel.getIdDoProduto() - 1;
		if (carrinhoModel.getIdDoProduto() > produtos.size()) {
			System.out.println("Este produto não está cadastrado.");
			return null;
		}

		System.out.print("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(entrada.nextInt());

		if (carrinhoModel.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQuantidadeDeProduto()) {
			System.out.println("Tá em falta cara, dá uma segurada.");
			return null;
		}

		carrinhoModel.setProdutoModel(produtos.get(idDoProduto));

		carrinhoModel.setValorTotalDoItem(
				carrinhoModel.getQuantidadeDeItensNoCarrinho() * produtos.get(idDoProduto).getPrecoDoProduto());

		editaProduto.atualizarQuantidadeEValorTotal(produtos, carrinhoModel.getQuantidadeDeItensNoCarrinho(),
				idDoProduto);

		return carrinhoModel;
	}
}
