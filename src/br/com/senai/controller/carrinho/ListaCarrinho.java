package br.com.senai.controller.carrinho;

import java.util.List;

import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {

	public List<CarrinhoModel> listarItensNoCarrinho(List<CarrinhoModel> itensNoCarrinho) {
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Pre�o", "Qtd", "R$ Total");

		if (itensNoCarrinho.size() <= 0) {
			return null;
		}

		itensNoCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", item.getIdDoProduto(),
					item.getProdutoModel().getNomeDoProduto(), item.getProdutoModel().getPrecoDoProduto(),
					item.getQuantidadeDeItensNoCarrinho(), item.getValorTotalDoItem());
		});

		double valorTotalDoCarrinho = itensNoCarrinho.stream().mapToDouble(CarrinhoModel::getValorTotalDoItem).sum();
		System.out.println("Valor total: R$" + valorTotalDoCarrinho);

		return itensNoCarrinho;
	}

	public void gerarCupom(List<CarrinhoModel> itensNoCarrinho, String Cliente) {
		ListaCarrinho listaCarrinho = new ListaCarrinho();

		if (itensNoCarrinho.size() <= 0) {
			System.out.println("Lista vazia.");
			return;
		}

		listaCarrinho.listarItensNoCarrinho(itensNoCarrinho);
		System.out.println("Cliente: " + Cliente);
	}

}
