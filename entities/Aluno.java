
package entities;

import java.time.LocalDate;

public class Aluno {

	private int id;
	private String nome;
	private String sexo;
	private LocalDate dt_nasc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	
	public void setDt_nasc(LocalDate localDate) {
		this.dt_nasc = localDate;
	}
	public void setSexo(String nextLine) {
		
	}
	public void setDt_nasc(LocalDate parse) {
		
	}
	
}
