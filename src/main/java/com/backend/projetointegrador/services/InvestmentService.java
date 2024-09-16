package com.backend.projetointegrador.services;

import com.backend.projetointegrador.domain.dtos.InvestmentRequestDTO;
import com.backend.projetointegrador.domain.dtos.InvestmentResponseDTO;
import com.backend.projetointegrador.domain.entities.Account;
import com.backend.projetointegrador.domain.entities.Investment;
import com.backend.projetointegrador.domain.entities.Product;
import com.backend.projetointegrador.domain.mappers.InvestmentMapper;
import com.backend.projetointegrador.repositories.AccountRepository;
import com.backend.projetointegrador.repositories.InvestmentRepository;
import com.backend.projetointegrador.services.exceptions.InvalidArgsException;
import com.backend.projetointegrador.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final AccountRepository accountRepository;  //TODO this may disappear after adding balance entity

    private final UserService userService;
    private final ProductService productService;

    public List<InvestmentResponseDTO> findAll() {
        return investmentRepository.findAll().stream()
                .map(entity -> InvestmentMapper.toResponseDTO(entity)).toList();
    }

    public InvestmentResponseDTO findById(Long id) {
        Investment investment = findEntityById(id);
        return InvestmentMapper.toResponseDTO(investment);
    }

    public InvestmentResponseDTO create(InvestmentRequestDTO requestDTO, Authentication authentication) {
        Account account = userService.findEntityByEmail(authentication.getName()).getAccount();
        if (account == null) {
            throw new InvalidArgsException("User does not have an account");
        }

        Product product = productService.findEntityById(requestDTO.productId());

        Investment investment = new Investment(null, requestDTO.buyPrice(), account, product);
        account.subtractBalance(requestDTO.buyPrice());

        investment = investmentRepository.save(investment);
        accountRepository.save(account);

        return InvestmentMapper.toResponseDTO(investment);
    }

    public void delete(Long id) {
        try {
            investmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(Investment.class, id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public InvestmentResponseDTO redeem(Long id) {
        Investment investment = findEntityById(id);
        Product product = investment.getProduct();

        float variationInsideRange = 1 + productService.getYieldPercentageInDateRange(product, investment.getBuyTime(), Instant.now());
        float sellPrice = Math.max(investment.getBuyPrice() * variationInsideRange, 1f);
        float administrationTaxes = 0.1f;
        float companyYieldCut = sellPrice * administrationTaxes; //TODO add this to the company account

        sellPrice -= companyYieldCut;

        investment.setSellPrice(sellPrice);
        investment.setSellTime(Instant.now());
        investment.getAccount().addBalance(investment.getSellPrice());
        investmentRepository.save(investment);
        accountRepository.save(investment.getAccount());

        return InvestmentMapper.toResponseDTO(investment);
    }

    Investment findEntityById(Long id) {
        return investmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Investment.class, id));
    }

}
