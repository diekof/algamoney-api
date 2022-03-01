package com.algaworks.algamoney.algamoneyapi.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long codigo;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @NotNull
    private Boolean ativo;

    @Embedded
    private Endereco endereco;
    
    @JsonIgnore
    @Transient
    public boolean isInativo(){
        return !this.ativo;
    }

    
}
