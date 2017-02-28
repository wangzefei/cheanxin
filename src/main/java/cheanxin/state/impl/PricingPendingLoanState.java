package cheanxin.state.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.User;
import cheanxin.enums.Authority;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.LoanService;
import cheanxin.state.LoanState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

/**
 * Created by Jawinton on 2017/2/22.
 */
public class PricingPendingLoanState extends LoanState {
    private static GrantedAuthority neededAuthority = new SimpleGrantedAuthority(Authority.ROLE_LOAN_PRICING.name());

    public PricingPendingLoanState(LoanService loanService) {
        super(neededAuthority, loanService);
    }

    @Override
    public Integer getValue() {
        return 3;
    }

    @Override
    public Loan update(User user, Loan unsavedLoan, Loan savedLoan) throws IllegalAccessException {
        super.checkAuthority(user);

        Assert.notNull(unsavedLoan.getVehicleDealPrice(), "Vehicle deal price is empty.");
        Assert.notNull(unsavedLoan.getVehicleBrand(), "Vehicle brand is empty");
        Assert.notNull(unsavedLoan.getVehicleSeries(), "Vehicle series is empty");
        Assert.notNull(unsavedLoan.getVehicleType(), "Vehicle type is empty");
        Assert.notNull(unsavedLoan.getVehicleVin(), "Vehicle vin is empty");
        Assert.notNull(unsavedLoan.getVehicleProductionYearMonth(), "Vehicle production year month is empty");
        Assert.notNull(unsavedLoan.getVehicleKilometers(), "Vehicle kilometers is empty");
        Assert.notNull(unsavedLoan.getVehicleRegistrationYearMonth(), "Vehicle registration year month is empty");
        Assert.notNull(unsavedLoan.getVehicleEmission(), "Vehicle emission is empty.");
        Assert.notNull(unsavedLoan.getVehicleUtilityType(), "Vehicle utility type is empty.");


        savedLoan.setVehicleDealPrice(unsavedLoan.getVehicleDealPrice());
        savedLoan.setVehicleBrand(unsavedLoan.getVehicleBrand());
        savedLoan.setVehicleSeries(unsavedLoan.getVehicleSeries());
        savedLoan.setVehicleType(unsavedLoan.getVehicleType());
        savedLoan.setVehicleVin(unsavedLoan.getVehicleVin());
        savedLoan.setVehicleProductionYearMonth(unsavedLoan.getVehicleProductionYearMonth());
        savedLoan.setVehicleKilometers(unsavedLoan.getVehicleKilometers());
        savedLoan.setVehicleRegistrationYearMonth(unsavedLoan.getVehicleRegistrationYearMonth());
        savedLoan.setVehicleEmission(unsavedLoan.getVehicleEmission());
        savedLoan.setVehicleUtilityType(unsavedLoan.getVehicleUtilityType());
        return super.updateLoan(unsavedLoan, savedLoan, false);
    }

    @Override
    public Loan submit(User user, Loan unsavedLoan, Loan savedLoan) {
        super.checkAuthority(user);

        Assert.notNull(savedLoan.getVehicleDealPrice(), "Vehicle deal price is empty.");

        LoanState targetState = super.getLoanService().getSecondReviewPendingLoanState();
        return super.updateLoanState(user, unsavedLoan, savedLoan, targetState);
    }

    @Override
    public Loan accept(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    @Override
    public Loan reject(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }

    @Override
    public Loan abort(User user, Loan unsavedLoan, Loan savedLoan) {
        throw new UnauthorizedException("Undefined loan operation.");
    }
}
