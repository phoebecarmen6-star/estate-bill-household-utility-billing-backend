package com.estatebill.estate_bill_household_utility_billing.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;
import com.estatebill.estate_bill_household_utility_billing.model.ReadingStatus;
import com.estatebill.estate_bill_household_utility_billing.repository.MeterReadingRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.BillPaymentRepository;


@Service
public class MeterReadingService {
    
    @Autowired
    private MeterReadingRepository repository;

    @Autowired 
    private BillPaymentRepository billPaymentRepository;

   

    public String save(MeterReading reading) {
        if (reading.getReadingDate().isBefore(LocalDate.now())) {
            return "Meter reading date cannot be in the past";
        }
        if (repository.existsByMeterReaderAndReadingDateAndStatusNot(
            reading.getMeterReader(), reading.getReadingDate(), ReadingStatus.REJECTED)) {
          return "Meter reader already has a reading on that date";    
            }
            reading.setStatus(ReadingStatus.RECORDED);
            repository.save(reading);
        return "Meter reading saved successfully";
    }

    public List<MeterReading> getAll() {
        return repository.findAll();
    }

    public Optional<MeterReading> getById(UUID id) {
        return repository.findById(id);
    }

    public Optional<MeterReading> update(UUID id, MeterReading newData) {
        Optional<MeterReading> found = repository.findById(id);
        if (found.isEmpty()) {
            return found;
        }
        MeterReading existing = found.get();
        existing.setReadingDate(newData.getReadingDate());
        existing.setMeterType(newData.getMeterType());
        existing.setUnitsUsed(newData.getUnitsUsed());
        existing.setHousehold(newData.getHousehold());
        existing.setMeterReader(newData.getMeterReader());
        return Optional.of(repository.save(existing));
    }

    public String delete(UUID id) {
        Optional<MeterReading> found = repository.findById(id);
        if (found.isEmpty()) {
            return "The meter reading with that id does not exist";
        }
        if (billPaymentRepository.existsByMeterReading(found.get())) {
            return "The meter reading has bill payments, delete them first";
        }
        repository.deleteById(id);
        return "Meter reading deleted successfully";
    }

    public List<MeterReading> getByStatus(ReadingStatus status) {
        return repository.findByStatusOrderByReadingDateAsc(status);
    }

    public List<MeterReading> getBetween(LocalDate start, LocalDate end) {
        return repository.findByReadingDateBetweenOrderByReadingDateAsc(start, end);
    }

    public String updateStatus(UUID id, ReadingStatus newStatus) {
        Optional<MeterReading> found = repository.findById(id);
        if  (found.isEmpty()) {
            return "The meter reading with that id does not exist";
        }
        MeterReading existing = found.get();
        ReadingStatus current = existing.getStatus();
        boolean allowed =
                ((current == ReadingStatus.RECORDED)
                        && (newStatus == ReadingStatus.VERIFIED || newStatus == ReadingStatus.REJECTED))
                || (current == ReadingStatus.VERIFIED
                    && (newStatus == ReadingStatus.BILLED || newStatus == ReadingStatus.REJECTED));

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        existing.setStatus(newStatus);
        repository.save(existing);
        return "Meter reading status updated successfully";

    }
}