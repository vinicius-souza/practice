package br.com.fabricadeprogramador.persistencia.entidade;

public class Estado {
	
	private Integer id;
	private String nome;
	private String UF;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
