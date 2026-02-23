package com.ai4dev.tinderfordogs.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DogMatcherServiceTest {

    private lateinit var dogMatcherService: DogMatcherService

    @BeforeEach
    fun setup() {
        dogMatcherService = DogMatcherService()
    }

    @Test
    fun `should return 1 when both dogs are identical`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones", "running"))
        val dog2 = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones", "running"))

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog2)

        // Then
        assertEquals(1.0, result)
    }

    @Test
    fun `should calculate lower compatibility when dogs have different breeds`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones", "running"))
        val dog2 = Dog(2, "Buddy", "Beagle", 5, "Male", setOf("bones", "running"))

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog2)

        // Then
        assertEquals(0.75, result)
    }

    @Test
    fun `should calculate lower compatibility when dogs have different gender`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones", "running"))
        val dog2 = Dog(2, "Bella", "Labrador", 5, "Female", setOf("bones", "running"))

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog2)

        // Then
        assertEquals(0.85, result)
    }

    @Test
    fun `should calculate lower compatibility when dogs have different age groups`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones", "running"))
        val dog2 = Dog(2, "Buddy", "Labrador", 10, "Male", setOf("bones", "running"))

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog2)

        // Then
        assertEquals(0.75, result)
    }

    @Test
    fun `should calculate lower compatibility when dogs have no common preferences`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones"))
        val dog2 = Dog(2, "Buddy", "Labrador", 5, "Male", setOf("swimming"))

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog2)

        // Then
        assertEquals(0.7, result)
    }

    @Test
    fun `should return null when no candidates are available`() {
        // Given
        val dog = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones"))
        val candidates = emptyList<Dog>()

        // When
        val result = dogMatcherService.findBestMatch(dog, candidates)

        // Then
        assertNull(result)
    }

    @Test
    fun `should find best match among multiple candidates`() {
        // Given
        val dog = Dog(1, "Rex", "Labrador", 5, "Male", setOf("bones", "running"))
        val candidates = listOf(
            Dog(2, "Buddy", "Beagle", 5, "Male", setOf("bones", "running")),
            Dog(3, "Bella", "Labrador", 3, "Female", setOf("bones", "running")),
            Dog(4, "Charlie", "Labrador", 5, "Male", setOf("bones", "running"))
        )

        // When
        val result = dogMatcherService.findBestMatch(dog, candidates)

        // Then
        assertEquals(Dog(4, "Charlie", "Labrador", 5, "Male", setOf("bones", "running")), result)
    }
}
