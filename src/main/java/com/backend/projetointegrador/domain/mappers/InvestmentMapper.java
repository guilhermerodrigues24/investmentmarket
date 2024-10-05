package com.backend.projetointegrador.domain.mappers;

import com.backend.projetointegrador.domain.dtos.InvestmentResponseDTO;
import com.backend.projetointegrador.domain.entities.Investment;

public class InvestmentMapper {
    public static InvestmentResponseDTO toResponseDTO(Investment investment) {
        return new InvestmentResponseDTO(investment.getId(),
                investment.getBuyPrice(),
                investment.getBuyPrice(),
                investment.getBuyTime(),
                investment.getSellPrice(),
                investment.getSellTime(),
                investment.getIsSold()
        );
    }
}
