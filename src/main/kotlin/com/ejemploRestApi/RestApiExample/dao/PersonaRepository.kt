package com.ejemploRestApi.RestApiExample.dao

import com.ejemploRestApi.RestApiExample.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface PersonaRepository: JpaRepository<Persona, Long> {
}