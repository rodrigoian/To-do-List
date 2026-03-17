package model.entities;

import java.time.LocalDateTime;

public class Tarefa {
	private Long id;
	private String nome;
	private String descricao;
	private LocalDateTime data;
	private String usuarioId;
	private String statusId;
	
	public Tarefa() {}
	
	public Tarefa(Long id, String nome, String descricao,LocalDateTime data,String statusId, String usuarioId) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.statusId = statusId;
		this.usuarioId = usuarioId;
		this.data = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}
	

}
