package gestgym.com.gestgym.models;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suscription")
public class Suscription {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer is mandatory")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pack_id", nullable = false)
    @NotNull(message = "Pack is mandatory")
    private Pack pack;

    @NotNull(message = "Start date is mandatory")
    private LocalDateTime start_date;

    @Transient
    private Long pack_id;

    @Transient
    private Long customer_id;


}
