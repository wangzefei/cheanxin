package cheanxin.service.impl;

import cheanxin.data.LoanVehicleRepository;
import cheanxin.domain.LoanVehicle;
import cheanxin.service.LoanVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanVehicleServiceImpl implements LoanVehicleService {
    @Autowired
    LoanVehicleRepository loanVehicleRepository;

    @Override
    public LoanVehicle save(LoanVehicle unsavedLoanVehicle) {
        return loanVehicleRepository.save(unsavedLoanVehicle);
    }

    @Override
    public LoanVehicle findOne(long id) {
        return loanVehicleRepository.getOne(id);
    }
}
