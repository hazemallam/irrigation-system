package com.banque.misr.irrigationsystem.service;

import com.banque.misr.irrigationsystem.repository.CropRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CropServiceTest {

    @Mock
    private CropRepository cropRepository;
    private AutoCloseable autoCloseable;
    private CropService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CropService(cropRepository);

    }

    @Test
    void getCrops() {
        //when
        underTest.getCrops();
        //then
        verify(cropRepository).findAll();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
}