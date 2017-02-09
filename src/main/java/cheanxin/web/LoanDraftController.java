package cheanxin.web;

import cheanxin.domain.LoanDraft;
import cheanxin.domain.Product;
import cheanxin.domain.User;
import cheanxin.enums.*;
import cheanxin.global.Constants;
import cheanxin.service.LoanDraftService;
import cheanxin.service.ProductService;
import cheanxin.util.StringUtil;
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
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_SIZE) int size) {
        if (creatorUsername == null || creatorUsername.isEmpty())
            creatorUsername = null;
        return loanDraftService.getLoanDrafts(creatorUsername, page, size);
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

        LoanDraft savedLoanDraft = loanDraftService.findOne(id);
        Assert.notNull(savedLoanDraft, "Loan draft not found.");

        LoanDraftStatusTransfer.checkAuthority(user, savedLoanDraft.getStatus(), unsavedLoanDraft.getStatus());

        // if it is transfered to loan, some fields cannot be null
        if (unsavedLoanDraft.getStatus() == LoanDraftStatus.LOAN.value()) {

        }

        // if it is second draft, some fields should not be null
        if (unsavedLoanDraft.getStatus() == LoanDraftStatus.SECOND_DRAFT.value()) {

        }

        // attributes below can not be modified.
        unsavedLoanDraft.setId(id);
        unsavedLoanDraft.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedLoanDraft.setCreatorUsername(savedLoanDraft.getCreatorUsername());
        unsavedLoanDraft.setCreatedTime(savedLoanDraft.getCreatedTime());

        return new ResponseEntity<>(loanDraftService.save(unsavedLoanDraft), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user) {
        LoanDraft savedLoanDraft = loanDraftService.findOne(id);

        Assert.notNull(savedLoanDraft);
        Assert.isTrue(user.getUsername().equals(savedLoanDraft.getCreatorUsername()), "You are not the owner of this loan draft");

        loanDraftService.delete(id);
    }
}
