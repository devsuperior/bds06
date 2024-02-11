package com.devsuperior.movieflix.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String subTitle;

  private Integer year;

  private String imgUrl;

  @Column(columnDefinition = "TEXT")
  private String synopsis;

  @ManyToOne
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @OneToMany(mappedBy = "movie")
  private List<Review> reviews = new ArrayList<>();
}
