package com.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.dto.PetDTO;
import com.pet.entities.Pet;
import com.pet.service.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class PetController {
	
	private final PetService petService;
	
	@Autowired
	public PetController(PetService petService) {
		this.petService = petService;
	}
	
	@PostMapping
	public ResponseEntity<PetDTO> criar(@RequestBody @Valid PetDTO petDTO){
		PetDTO salvarPet = petService.salvar(petDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarPet);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PetDTO> alterar(@PathVariable Long id, @RequestBody @Valid PetDTO petDTO){
		PetDTO alterarPetDTO = petService.atualizar(id, petDTO);
		if(alterarPetDTO !=null) {
			return ResponseEntity.ok(alterarPetDTO);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pet> apagar(@PathVariable Long id){
		boolean apagar = petService.deletar(id);
		
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pet> buscarPorId(@PathVariable Long id){
		Pet pet = petService.buscarPorId(id);
		if (pet != null) {
			return ResponseEntity.ok(pet);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Pet>> buscaTodos(){
		List<Pet> pet = petService.BuscarTodos();
		return ResponseEntity.ok(pet);
	}

}
