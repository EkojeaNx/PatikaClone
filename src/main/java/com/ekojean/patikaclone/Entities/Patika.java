package com.ekojean.patikaclone.Entities;

import com.ekojean.patikaclone.Interfaces.IEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patika")
public class Patika implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patikarefno;
    private String adi;
    private String aciklama;
    private int puan;
    private int saat;
    private String aktif;
}
