package br.com.cadastro.negocio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.cadastro.model.Jogador;

public class JogadorImpl {
	
	/**
	 * 
	 * @param caminho
	 * @return
	 */
	public boolean verificarArquivoExiste(Path caminho) {
		boolean ret = false;
		try {
			Stream<Path> stream = Files.list(caminho);
			Optional<Path> arq = stream.filter(p -> p.toString().endsWith("jogadores.txt")).findAny();
			ret = arq.isPresent();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 
	 * @param caminho
	 * @return
	 * @throws IOException
	 */
	public List<Jogador> getListaDeJogadores(Path caminho) throws IOException {
		Stream<String> linhas = Files.lines(caminho, StandardCharsets.ISO_8859_1);
		List<String> listaDeLinhas = linhas.collect(Collectors.toList());
		List<Jogador> listaDeJogadores = new ArrayList<>();
		Jogador jogador;
		Iterator it = listaDeLinhas.listIterator();
		String str = null;
		while (it.hasNext()) {
			str = (String) it.next();
			String info[] = str.split(",");
			jogador = new Jogador();
			jogador.setNome(info[0]);
			jogador.setPosicao(info[1]);
			jogador.setIdade(Integer.parseInt(info[2]));
			jogador.setTimeAtual(info[3]);
			jogador.setGolsMarcados(Integer.parseInt(info[4]));
			listaDeJogadores.add(jogador);
		}
		
		return listaDeJogadores;
	}
	
	/**
	 * 
	 * @param jogadores
	 */
	public void imprimirJogadores(List<Jogador> jogadores) {
		jogadores.stream().forEach(System.out::println);
	}
	
	/**
	 * 
	 * @param jogadores
	 * @param time
	 */
	public void imprimirJogadoresTime(List<Jogador> jogadores, String time) {
		jogadores.stream().filter(jogador -> jogador.getTimeAtual().equals(time))
			.forEach(System.out::println);
	}
	
	/**
	 * 
	 * @param jogadores
	 * @param time
	 */
	public void imprimirJogadoresTimeGols(List<Jogador> jogadores, String time) {
		jogadores.stream().filter(jogador -> jogador.getTimeAtual().equals(time) && jogador.getGolsMarcados() > 10)
			.forEach(System.out::println);
	}
	
	/**
	 * 
	 * @param jogadores
	 */
	public void agruparJogadoresPorTime(List<Jogador> jogadores) {
		jogadores.stream().sorted(Comparator.comparing(Jogador::getTimeAtual))
			.forEach(System.out::println);
	}
	
	/**
	 * 
	 * @param jogadores
	 * @return
	 */
	public double calcularMediaIdade(List<Jogador> jogadores) {
		return jogadores.stream().mapToInt(Jogador::getIdade).average().getAsDouble();
	}

	/**
	 * 	
	 * @param jogadores
	 */
	public void imprimirJogadorMaisVelho(List<Jogador> jogadores) {
		Jogador jogador = jogadores.stream().max(Comparator.comparingInt(Jogador::getIdade)).get();
		System.out.println("Jogador mais velho : " + jogador.getNome());
	}
	
	/**
	 * 
	 * @param jogadores
	 */
	public void imprimirJogadorMaisNovo(List<Jogador> jogadores) {
		Jogador jogador = jogadores.stream().min(Comparator.comparingInt(Jogador::getIdade)).get();
		System.out.println("Jogador mais novo : " + jogador.getNome());
	}
	
	/**
	 * 
	 * @param jogadores
	 */
	public void imprimirJogadorArtilheiro(List<Jogador> jogadores) {
		Jogador jogador = jogadores.stream().max(Comparator.comparingInt(Jogador::getGolsMarcados)).get();
		System.out.println("Jogador artilheiro : " + jogador.getNome());
	}
	
	/**
	 * 
	 * @param jogadores
	 * @return
	 */
	public int imprimirSomatorioGols(List<Jogador> jogadores) {
		int soma = jogadores.stream().mapToInt(jogador -> jogador.getGolsMarcados()).sum();
		return soma;
	}
	
	/**
	 * 
	 * @param jogadores
	 */
	public void agruparJogadoresPeloTime(List<Jogador> jogadores) {
		Map<String, List<Jogador>> groupByTime = jogadores.stream().collect(Collectors.groupingBy(Jogador::getTimeAtual));
		System.out.println(groupByTime);
	}
	
	/**
	 * 
	 * @param jogadores
	 */
	public void ordenarJogadoresGols(List<Jogador> jogadores) {
		jogadores.stream().sorted(Comparator.comparingInt(Jogador::getGolsMarcados).reversed()).forEach(System.out::println);
	}

}
 