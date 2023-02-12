package james.developer.restaurante.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Reserva  implements Serializable {
	
	
	 private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;

	 private String nome;
	
	 private String  email;
	
	 private String pessoas;
	
	


	public String getPessoas() {
		return pessoas;
	}



	public void setPessoas(String pessoas) {
		this.pessoas = pessoas;
	}



	@JsonFormat(pattern = "dd/MM/yyyy") /*Passo pro front um jason neste formato de data*/
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
		private Date dataDaCadastro;
	 
	 
	     @DateTimeFormat(iso = ISO.TIME, pattern = "HH:mm:ss")
	     private String hora;



		public String getNome() {
			return nome;
		}



		public void setNome(String nome) {
			this.nome = nome;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}






		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}



		public String getHora() {
			return hora;
		}



		public void setHora(String hora) {
			this.hora = hora;
		}



		public Date getDataDaCadastro() {
			return dataDaCadastro;
		}



		public void setDataDaCadastro(Date dataDaCadastro) {
			this.dataDaCadastro = dataDaCadastro;
		}




}
