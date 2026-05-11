package com.fx.dealsimporter.service;

import com.fx.dealsimporter.dto.DealRequestDto;
import com.fx.dealsimporter.dto.ImportResult;
import com.fx.dealsimporter.model.Deal;
import com.fx.dealsimporter.repository.DealRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DealServiceTest {

    private final DealRepository dealRepository = Mockito.mock(DealRepository.class);
    private final DealService dealService = new DealService(dealRepository);

    @Test
    void importDeals_shouldImportValidDeal() {
        DealRequestDto dto = new DealRequestDto();
        dto.setDealUniqueId("D1");
        dto.setFromCurrency("USD");
        dto.setToCurrency("EUR");
        dto.setDealTimestamp("2023-01-01T12:00:00");
        dto.setAmount(100.0);

        when(dealRepository.findByDealUniqueId("D1")).thenReturn(Optional.empty());
        when(dealRepository.save(any(Deal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ImportResult result = dealService.importDeals(Collections.singletonList(dto));

        assertEquals(1, result.getImportedCount());
        assertEquals(0, result.getFailedCount());
        assertTrue(result.getErrors().isEmpty());
        verify(dealRepository, times(1)).save(any(Deal.class));
    }

    @Test
    void importDeals_shouldRejectDuplicateDeal() {
        DealRequestDto dto = new DealRequestDto();
        dto.setDealUniqueId("D2");
        dto.setFromCurrency("USD");
        dto.setToCurrency("EUR");
        dto.setDealTimestamp("2023-01-01T12:00:00");
        dto.setAmount(100.0);

        when(dealRepository.findByDealUniqueId("D2"))
                .thenReturn(Optional.of(new Deal("D2", "USD", "EUR",
                        LocalDateTime.now(), 100.0)));

        ImportResult result = dealService.importDeals(Collections.singletonList(dto));

        assertEquals(0, result.getImportedCount());
        assertEquals(1, result.getFailedCount());
        assertFalse(result.getErrors().isEmpty());
        verify(dealRepository, never()).save(any(Deal.class));
    }
}
