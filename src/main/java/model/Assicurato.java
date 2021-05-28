package model;

import lombok.*;

import java.util.Date;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class Assicurato {

    private Long id;

    private String nome;

    private String cognome;

    private Date dataNascita;

    private Integer numeroSinistri;

    private String codiceFiscale;

}
