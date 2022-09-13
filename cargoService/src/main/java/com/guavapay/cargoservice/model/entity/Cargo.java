package com.guavapay.cargoservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cargo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cargo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="order_id")
    private Long orderId;

    @Column(name="courier_id")
    private Long courierId;

    @Enumerated(EnumType.STRING)
    @Column(name="cargo_status")
    private CargoStatus cargoStatus;

}