package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Assicurato {

    private Long id;

    private String nome;

    private String cognome;

    private Date dataNascita;

    private Integer numeroSinistri;

    private String codiceFiscale;

}
