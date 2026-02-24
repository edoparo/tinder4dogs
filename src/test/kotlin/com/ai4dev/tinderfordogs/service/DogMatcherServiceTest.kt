package com.ai4dev.tinderfordogs.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DogMatcherServiceTest {

    private lateinit var dogMatcherService: DogMatcherService

    @BeforeEach
    fun setUp() {
        dogMatcherService = DogMatcherService()
    }

    @Test
    fun `should return 1_0 when two identical dogs`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male")

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog1)

        // Then
        assertEquals(1.0, result)
    }

    @Test
    fun `should calculate correct compatibility score based on different criteria`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male")
        val dog2 = Dog(2, "Bella", "Labrador", 3, "Female")

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog2)

        // Then
        assertEquals(0.75, result)
    }

    @Test
    fun `should return lower compatibility score for different breeds and more age difference`() {
        // Given
        val dog1 = Dog(1, "Rex", "Labrador", 5, "Male")
        val dog2 = Dog(2, "Bella", "Beagle", 10, "Female")

        // When
        val result = dogMatcherService.calculateCompatibility(dog1, dog2)

        // Then
        assertEquals(0.25, result)
    }

    @Test
    fun `should return null when no candidates are available`() {
        // Given
        val dog = Dog(1, "Rex", "Labrador", 5, "Male")
        val candidates = emptyList<Dog>()

        // When
        val result = dogMatcherService.findBestMatch(dog, candidates)

        // Then
        assertNull(result)
    }

    @Test
    fun `should find best match from a list of candidates`() {
        // Given
        val dog = Dog(1, "Rex", "Labrador", 5, "Male")
        val candidates = listOf(
            Dog(2, "Bella", "Labrador", 3, "Female"),
            Dog(3, "Max", "Beagle", 7, "Male")
        )

        // When
        val result = dogMatcherService.findBestMatch(dog, candidates)

        // Then
        assertEquals(2, result?.id)
    }

    @Test
    fun `should ignore the same dog id in candidates when finding best match`() {
        // Given
        val dog = Dog(1, "Rex", "Labrador", 5, "Male")
        val candidates = listOf(
            Dog(1, "Rex", "Labrador", 5, "Male"),
            Dog(2, "Bella", "Labrador", 3, "Female")
        )

        // When
        val result = dogMatcherService.findBestMatch(dog, candidates)

        // Then
        assertEquals(2, result?.id)
    }
}
