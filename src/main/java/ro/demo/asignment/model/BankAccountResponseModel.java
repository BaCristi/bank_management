package ro.demo.asignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BankAccountResponseModel {
    private String accountName;
    private Integer balance;
}
