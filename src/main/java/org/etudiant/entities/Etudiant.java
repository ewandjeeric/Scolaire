package org.etudiant.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Etudiant implements Serializable {

	@Id
	@GeneratedValue
	private Long idEtudiant;
	@NotNull
	@Size(min = 3, max = 20)
	private String nom;
	@NotNull
	@Size(min = 3, max = 20)
	private String prenom;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date dateNaissance;

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public Etudiant(String nom, String prenom, Date dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}
