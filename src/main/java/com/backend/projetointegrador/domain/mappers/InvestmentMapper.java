package com.backend.projetointegrador.domain.mappers;

import com.backend.projetointegrador.domain.dtos.InvestmentResponseDTO;
import com.backend.projetointegrador.domain.entities.Investment;

public class InvestmentMapper {
    public static InvestmentResponseDTO toResponseDTO(Investment investment) {
        return new InvestmentResponseDTO(investment.getId(),
                investment.getBuyPrice(),
                investment.getSellPrice(),
                investment.getSellTime(),
                AccountMapper.toResponseDTO(investment.getAccount()),
                ProductMapper.toResponseDTO(investment.getProduct())
        );
    }
}
