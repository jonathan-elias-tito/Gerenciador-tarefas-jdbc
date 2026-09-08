package model.entities;

import java.util.Date;
import java.util.Objects;

public class Task {
	private Integer id;
	private String titulo;
	private String descricao;
	private Date dataEntrega;
	private String status;
		private User user;
		@Override
		public String toString() {
			return "Task [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataEntrega=" + dataEntrega
					+ ", status=" + status + ", user=" + user + "]";
		}
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Task other = (Task) obj;
			return Objects.equals(id, other.id);
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public Date getDataEntrega() {
			return dataEntrega;
		}
		public void setDataEntrega(Date dataEntrega) {
			this.dataEntrega = dataEntrega;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
}
