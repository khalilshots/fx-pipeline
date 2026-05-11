package com.fx.dealsimporter.service;

import com.fx.dealsimporter.dto.DealRequestDto;
import com.fx.dealsimporter.dto.ImportResult;
import com.fx.dealsimporter.model.Deal;
import com.fx.dealsimporter.repository.DealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {

    private final DealRepository dealRepository;

    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public ImportResult importDeals(List<DealRequestDto> requests) {
        int imported = 0;
        List<String> errors = new ArrayList<>();

        for (DealRequestDto dto : requests) {
            String error = validate(dto);
            if (error != null) {
                errors.add("Deal " + dto.getDealUniqueId() + ": " + error);
                continue;
            }

            if (dealRepository.findByDealUniqueId(dto.getDealUniqueId()).isPresent()) {
                errors.add("Deal " + dto.getDealUniqueId() + ": duplicate");
                continue;
            }

            Deal deal = new Deal(
                dto.getDealUniqueId(),
                dto.getFromCurrency(),
                dto.getToCurrency(),
                LocalDateTime.parse(dto.getDealTimestamp()),
                dto.getAmount()
            );

            dealRepository.save(deal);
            imported++;
        }

        return new ImportResult(imported, errors.size(), errors);
    }

    private String validate(DealRequestDto dto) {
        if (dto.getDealUniqueId() == null || dto.getDealUniqueId().isEmpty())
            return "Missing dealUniqueId";

        if (dto.getFromCurrency() == null || dto.getFromCurrency().length() != 3)
            return "Invalid fromCurrency";

        if (dto.getToCurrency() == null || dto.getToCurrency().length() != 3)
            return "Invalid toCurrency";

        if (dto.getDealTimestamp() == null)
            return "Missing timestamp";

        if (dto.getAmount() == null || dto.getAmount() <= 0)
            return "Invalid amount";

        return null;
    }
}
