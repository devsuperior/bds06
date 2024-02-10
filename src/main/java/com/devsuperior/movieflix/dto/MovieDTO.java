package com.devsuperior.movieflix.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MovieDTO {

  private Long id;
  private String title;
  private String subTitle;
  private Integer year;
  private String imgUrl;
  private String synopsis;
  private GenreDTO genre;
  private List<ReviewDTO> reviews = new ArrayList<>();
}
