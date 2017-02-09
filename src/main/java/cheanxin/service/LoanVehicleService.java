package cheanxin.service;

import cheanxin.domain.LoanVehicle;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanVehicleService {
    LoanVehicle save(LoanVehicle unsavedLoanVehicle);
    LoanVehicle findOne(long id);
}
