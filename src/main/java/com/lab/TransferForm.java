package com.lab;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TransferForm
{
    @NotNull
    @Size(min=2, max=50)
    private String name;

    @NotNull
    @Size(min=2, max=50)
    private String surname;

    @NotNull
    @Size(min=5, max=50)
    private String transferTitle;

    @NotNull
    @Pattern(regexp="^[0-9]{16}$", message="Account number must contain 16 integer values")
    private String accountNumber;

    public TransferForm(){}

    public TransferForm(String name, String surname, String transferTitle, String accountNumber) {
        this.name = name;
        this.surname = surname;
        this.transferTitle = transferTitle;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTransferTitle() {
        return transferTitle;
    }

    public void setTransferTitle(String transferTitle) {
        this.transferTitle = transferTitle;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return String.format(
                "Transfer[name='%s', surname='%s', transferTitle='%s', accountNumber='%s']",
                name, surname, transferTitle, accountNumber);
    }
}
