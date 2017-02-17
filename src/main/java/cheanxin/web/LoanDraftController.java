package cheanxin.web;

import cheanxin.constant.LogicConstants;
import cheanxin.domain.LoanDraft;
import cheanxin.domain.User;
import cheanxin.enums.LoanDraftStatus;
import cheanxin.enums.LoanDraftStatusTransfer;
import cheanxin.service.LoanDraftService;
import cheanxin.service.LoanListService;
import cheanxin.util.ReflectUtil;
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
    public Page<LoanDraft> list(
            @RequestParam(value = "isFilterSelf", defaultValue = "0") boolean isFilterSelf,
            @RequestParam(value = "sourceFinancialCommissioner", defaultValue = "") String sourceFinancialCommissioner,
            @RequestParam(value = "applicantName", defaultValue = "") String applicantName,
            @RequestParam(value = "applicantMobileNumber", defaultValue = "") String applicantMobileNumber,
            @RequestParam(value = "createdTimeFrom", defaultValue = "0") long createdTimeFrom,
            @RequestParam(value = "createdTimeTo", defaultValue = "0") long createdTimeTo,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = LogicConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = LogicConstants.DEFAULT_SIZE) int size,
            @AuthenticationPrincipal User user) {
        String username = isFilterSelf ? user.getUsername() : null;
        return loanDraftService.list(username, sourceFinancialCommissioner, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, status, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LoanDraft> get(@PathVariable(value = "id") long id) {
        LoanDraft loanDraft = loanDraftService.getOne(id);
        Assert.notNull(loanDraft, "Loan draft not found.");
        return new ResponseEntity<>(loanDraft, HttpStatus.OK);
    }

    /**
     * 添加贷款草稿箱
     * @param unsavedLoanDraft
     * @param errors
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<LoanDraft> save(
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
        return new ResponseEntity<>(loanDraftService.save(LoanDraftStatus.FIRST_DRAFT.value(), unsavedLoanDraft), HttpStatus.CREATED);
    }

    /**
     * 编辑草稿箱操作
     * @param id
     * @param unsavedLoanDraft
     * @param user
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<LoanDraft> patch(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody LoanDraft unsavedLoanDraft,
            Errors errors,
            @AuthenticationPrincipal User user) throws IllegalAccessException {
        LoanDraft savedLoanDraft = loanDraftService.getOne(id);
        Assert.notNull(savedLoanDraft, "Loan draft not found.");

        if (unsavedLoanDraft.getStatus() == null) {
            unsavedLoanDraft.setStatus(savedLoanDraft.getStatus());
        }
        int fromStatus = savedLoanDraft.getStatus();
        int toStatus = unsavedLoanDraft.getStatus();
        LoanDraftStatusTransfer.checkAuthority(user, fromStatus, unsavedLoanDraft.getStatus());

        // attributes below can not be modified.
        unsavedLoanDraft.setId(null);
        unsavedLoanDraft.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedLoanDraft.setCreatorUsername(null);
        unsavedLoanDraft.setCreatedTime(null);
        ReflectUtil.mergeObject(unsavedLoanDraft, savedLoanDraft);

        // if it is transfer from first draft status to second draft status, some fields cannot be null
        if (LoanDraftStatus.FIRST_DRAFT.value().equals(fromStatus) && LoanDraftStatus.SECOND_DRAFT.value().equals(toStatus)) {
            String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
            Assert.isNull(errorMessage, errorMessage);
            Assert.notNull(savedLoanDraft.getVehicleDealPrice(), "VehicleDealPrice can not be null");
            Assert.notNull(savedLoanDraft.getLoanRate(), "LoanRate can not be null");
            Assert.notNull(savedLoanDraft.getLoanTerms(), "LoanTerms can not be null");
            Assert.notNull(savedLoanDraft.getSourceChannel(), "SourceChannel can not be null");
            Assert.notNull(savedLoanDraft.getApplicantMarriage(), "ApplicantMarriage can not be null");
            Assert.notNull(savedLoanDraft.getApplicantCertificateType(), "ApplicantCertificateType can not be null");
            Assert.notNull(savedLoanDraft.getStatus(), "Status can not be null");
            Assert.notNull(savedLoanDraft.getProductId(), "ProductId can not be null");
            Assert.notNull(savedLoanDraft.getSourceCityId(), "SourceCityId can not be null");
            Assert.notNull(savedLoanDraft.getApplicationPicUrl(), "ApplicationPicUrl can not be null");
            Assert.notNull(savedLoanDraft.getSourceFinancialCommissioner(), "SourceFinancialCommissioner can not be null");
            Assert.notNull(savedLoanDraft.getSourceReceiver(), "SourceReceiver can not be null");
            Assert.notNull(savedLoanDraft.getSourcePersonName(), "SourcePersonName can not be null");
            Assert.notNull(savedLoanDraft.getSourcePersonTel(), "SourcePersonTel can not be null");
            Assert.notNull(savedLoanDraft.getApplicantName(), "ApplicantName can not be null");
            Assert.notNull(savedLoanDraft.getApplicantCertificateNumber(), "ApplicantCertificateNumber can not be null");
            Assert.notNull(savedLoanDraft.getApplicantMobileNumber(), "ApplicantMobileNumber can not be null");
            Assert.notNull(savedLoanDraft.getApplicantCertificateFileIds(), "ApplicantCertificateFileIds can not be null");
            Assert.notNull(savedLoanDraft.getVehicleVin(), "VehicleVin can not be null");
            Assert.notNull(savedLoanDraft.getVehicleManufacturers(), "VehicleManufacturers can not be null");
            Assert.notNull(savedLoanDraft.getVehicleBrand(), "VehicleBrand can not be null");
            Assert.notNull(savedLoanDraft.getVehicleSeries(), "VehicleSeries can not be null");
            Assert.notNull(savedLoanDraft.getVehicleRegistrationCertificateFileIds(), "VehicleRegistrationCertificateFileIds can not be null");
            Assert.notNull(savedLoanDraft.getVehicleLicenseFileIds(), "VehicleLicenseFileIds can not be null");
            Assert.notNull(savedLoanDraft.getVehicleFileIds(), "VehicleFileIds can not be null");
        }

        return new ResponseEntity<>(loanDraftService.save(fromStatus, savedLoanDraft), HttpStatus.OK);
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
