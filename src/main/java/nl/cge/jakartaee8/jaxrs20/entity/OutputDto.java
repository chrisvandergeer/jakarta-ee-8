package nl.cge.jakartaee8.jaxrs20.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputDto {

    @JsonbDateFormat("dd-MM-yyyy")
    private LocalDate datum;
    private BigDecimal bedrag;

}
