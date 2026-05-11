package com.fx.dealsimporter.controller;

import com.fx.dealsimporter.dto.DealRequestDto;
import com.fx.dealsimporter.dto.ImportResult;
import com.fx.dealsimporter.service.DealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/import")
    public ImportResult importDeals(@RequestBody List<DealRequestDto> deals) {
        return dealService.importDeals(deals);
    }
}
