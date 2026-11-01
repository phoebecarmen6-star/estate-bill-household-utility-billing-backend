package com.estatebill.estate_bill_household_utility_billing.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.ReadingStatus;
import com.estatebill.estate_bill_household_utility_billing.repository.BillPaymentRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.BlockRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.HouseholdRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.MeterReaderRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.MeterReadingRepository;


@Service 
public class DashboardService {
    @Autowired 
    private HouseholdRepository householdRepository;
    @Autowired 
    private MeterReaderRepository meterReaderRepository;
    @Autowired 
    private BlockRepository blockRepository;
    @Autowired 
    private MeterReadingRepository meterReadingRepository;
    @Autowired 
    private BillPaymentRepository billPaymentRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("households", householdRepository.count());
        summary.put("meterReaders", meterReaderRepository.count());
        summary.put("freeBlocks", (long) blockRepository.findFreeBlocks().size());
        summary.put("readingsToday", meterReadingRepository.countByReadingDate(LocalDate.now()));
        summary.put("recorded", meterReadingRepository.countByStatus(ReadingStatus.RECORDED));
        summary.put("billPayments", billPaymentRepository.count());
        return summary;


    }

}