package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.LoanDraft;
import cheanxin.domain.User;
import cheanxin.enums.LoanDraftStatus;
import cheanxin.enums.LoanDraftStatusTransfer;
import cheanxin.service.LoanDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jawinton on 17/02/09.
 */
@RestController
@RequestMapping("/loan_drafts")
public class LoanDraftController extends BaseController {
    @Autowired
    LoanDraftService loanDraftService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<LoanDraft> getLoanDrafts(
            @RequestParam(value = "creatorUsername", defaultValue = "") String creatorUsername,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size) {
        if (creatorUsername == null || creatorUsername.isEmpty())
            creatorUsername = null;
        return loanDraftService.list(creatorUsername, page, size);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LoanDraft> add(
            @Valid @RequestBody LoanDraft unsavedLoanDraft,
            Errors errors,
            @AuthenticationPrincipal User user) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);

        LoanDraftStatusTransfer.checkAuthority(user, LoanDraftStatus.FIRST_DRAFT.value(), LoanDraftStatus.FIRST_DRAFT.value());

        unsavedLoanDraft.setCreatorUsername(user.getUsername());
        long now = System.currentTimeMillis() / 1000;
        unsavedLoanDraft.setCreatedTime(now);
        unsavedLoanDraft.setModifiedTime(now);
        unsavedLoanDraft.setStatus(LoanDraftStatus.FIRST_DRAFT.value());
        return new ResponseEntity<>(loanDraftService.save(unsavedLoanDraft), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<LoanDraft> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody LoanDraft unsavedLoanDraft,
            Errors errors,
            @AuthenticationPrincipal User user) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);

        LoanDraft savedLoanDraft = loanDraftService.getOne(id);
        Assert.notNull(savedLoanDraft, "Loan draft not found.");

        LoanDraftStatusTransfer.checkAuthority(user, savedLoanDraft.getStatus(), unsavedLoanDraft.getStatus());

        // if it is transfer to loan or in second draft status, some fields cannot be null
        if (unsavedLoanDraft.getStatus() == LoanDraftStatus.LOAN.value() || unsavedLoanDraft.getStatus() == LoanDraftStatus.SECOND_DRAFT.value()) {
            Assert.notNull(unsavedLoanDraft.getVehicleDealPrice(), "VehicleDealPrice can not be null");
            Assert.notNull(unsavedLoanDraft.getProductType(), "ProductType can not be null");
            Assert.notNull(unsavedLoanDraft.getLoanRate(), "LoanRate can not be null");
            Assert.notNull(unsavedLoanDraft.getLoanTerms(), "LoanTerms can not be null");
            Assert.notNull(unsavedLoanDraft.getSourceApplicationSource(), "SourceApplicationSource can not be null");
            Assert.notNull(unsavedLoanDraft.getApplicantMarriage(), "ApplicantMarriage can not be null");
            Assert.notNull(unsavedLoanDraft.getApplicantCertificateType(), "ApplicantCertificateType can not be null");
            Assert.notNull(unsavedLoanDraft.getStatus(), "Status can not be null");
            Assert.notNull(unsavedLoanDraft.getProductId(), "ProductId can not be null");
            Assert.notNull(unsavedLoanDraft.getSourceCityId(), "SourceCityId can not be null");
            Assert.notNull(unsavedLoanDraft.getProductName(), "ProductName can not be null");
            Assert.notNull(unsavedLoanDraft.getApplicationPicUrl(), "ApplicationPicUrl can not be null");
            Assert.notNull(unsavedLoanDraft.getSourceFinancialCommissioner(), "SourceFinancialCommissioner can not be null");
            Assert.notNull(unsavedLoanDraft.getSourceReceiver(), "SourceReceiver can not be null");
            Assert.notNull(unsavedLoanDraft.getSourceSourcePersonName(), "SourceSourcePersonName can not be null");
            Assert.notNull(unsavedLoanDraft.getSourceSourcePersonTel(), "SourceSourcePersonTel can not be null");
            Assert.notNull(unsavedLoanDraft.getApplicantName(), "ApplicantName can not be null");
            Assert.notNull(unsavedLoanDraft.getApplicantCertificateNumber(), "ApplicantCertificateNumber can not be null");
            Assert.notNull(unsavedLoanDraft.getApplicantMobileNumber(), "ApplicantMobileNumber can not be null");
            Assert.notNull(unsavedLoanDraft.getApplicantCertificateFileIds(), "ApplicantCertificateFileIds can not be null");
            Assert.notNull(unsavedLoanDraft.getVehicleVin(), "VehicleVin can not be null");
            Assert.notNull(unsavedLoanDraft.getVehicleManufacturers(), "VehicleManufacturers can not be null");
            Assert.notNull(unsavedLoanDraft.getVehicleBrand(), "VehicleBrand can not be null");
            Assert.notNull(unsavedLoanDraft.getVehicleSeries(), "VehicleSeries can not be null");
            Assert.notNull(unsavedLoanDraft.getVehicleRegistrationCertificateFileIds(), "VehicleRegistrationCertificateFileIds can not be null");
            Assert.notNull(unsavedLoanDraft.getVehicleLicenseFileIds(), "VehicleLicenseFileIds can not be null");
            Assert.notNull(unsavedLoanDraft.getVehicleVehicleFileIds(), "VehicleVehicleFileIds can not be null");
        }

        // TODO:check fields not null if transfer to loan
        if (unsavedLoanDraft.getStatus() == LoanDraftStatus.SECOND_DRAFT.value()) {
        }

        // attributes below can not be modified.
        unsavedLoanDraft.setId(id);
        unsavedLoanDraft.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedLoanDraft.setCreatorUsername(savedLoanDraft.getCreatorUsername());
        unsavedLoanDraft.setCreatedTime(savedLoanDraft.getCreatedTime());

        if (unsavedLoanDraft.getStatus() == LoanDraftStatus.LOAN.value()) {
            loanDraftService.transferToLoan(user, unsavedLoanDraft);
            return new ResponseEntity<>(unsavedLoanDraft, HttpStatus.OK);
        }

        return new ResponseEntity<>(loanDraftService.save(unsavedLoanDraft), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user) {
        LoanDraft savedLoanDraft = loanDraftService.getOne(id);

        Assert.notNull(savedLoanDraft);
        Assert.isTrue(user.getUsername().equals(savedLoanDraft.getCreatorUsername()), "You are not the owner of this loan draft");

        loanDraftService.remove(id);
    }
}
