package br.com.cadastro.main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import br.com.cadastro.model.Jogador;
import br.com.cadastro.negocio.JogadorImpl;

public class Main {

	public static void main(String[] args) throws IOException {
		
		JogadorImpl jogadorImpl = new JogadorImpl();
		String diretorioArquivo = "C:\\Users\\Robinson\\Desktop\\stream";
		String nomeArquivo = "jogadores.txt";
		List<Jogador> listaDeJogadores = jogadorImpl.getListaDeJogadores(Paths.get(diretorioArquivo + "\\" + nomeArquivo));
		
		if (!jogadorImpl.verificarArquivoExiste(Paths.get(diretorioArquivo))) {
			System.out.println("Arquivo não encontrado");
		} else {
			jogadorImpl.imprimirJogadorArtilheiro(listaDeJogadores);
			jogadorImpl.imprimirJogadorMaisVelho(listaDeJogadores);
			jogadorImpl.imprimirJogadorMaisNovo(listaDeJogadores);
		}
	}
}
