package com.devsuperior.movieflix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public List<GenreDTO> AllGenres() {
		List<GenreDTO> listDTO = new ArrayList<GenreDTO>();
		List<Genre> genre = genreRepository.findAll();

		for (Genre listG : genre) {
			GenreDTO g = new GenreDTO(listG.getId(), listG.getName());
			listDTO.add(g);
		}
		return listDTO;
	}
}
