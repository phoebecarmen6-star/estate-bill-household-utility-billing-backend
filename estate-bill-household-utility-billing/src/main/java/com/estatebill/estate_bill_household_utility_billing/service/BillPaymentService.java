package com.estatebill.estate_bill_household_utility_billing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.BillPayment;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;
import com.estatebill.estate_bill_household_utility_billing.model.ReadingStatus;
import com.estatebill.estate_bill_household_utility_billing.repository.BillPaymentRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.MeterReadingRepository;

@Service
public class BillPaymentService {
    @Autowired 
    private BillPaymentRepository repository;

    @Autowired 
    private MeterReadingRepository meterReadingRepository;

    

    public String save(UUID meterReadingId, BillPayment payment) {
        Optional<MeterReading> found = meterReadingRepository.findById(meterReadingId);
        if (found.isEmpty()) {
            return "The meter reading with that id does not exist";

        }
        MeterReading reading = found.get();
        if (reading.getStatus()  != ReadingStatus.BILLED) {
            return "Bill payments are only for BILLED meter readings";

        }
        payment.setPaidDate(LocalDate.now());
        payment.setMeterReading(reading);
        repository.save(payment);
        return "Bill payment saved successfully";
    }
    public List<BillPayment> getByHousehold(String unitCode) {
        return repository.findByHouseholdUnitCode(unitCode);

    }
    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            return "The bill payment with that id does not exist";
        }
        repository.deleteById(id);
        return "Bill payment deleted successfully";
    }
}
