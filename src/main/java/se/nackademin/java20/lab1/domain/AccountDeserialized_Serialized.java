package se.nackademin.java20.lab1.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/*
*Deserialization:
* @JsonCreator -> på konstruktor
* @JsonProperty(“propertyName”) -> framför variabel
*
* Serialization
* @JsonProperty(“propertyName”); -> framför
* variabel om du vill byta namn på den!
*
* */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDeserialized_Serialized {
    //private final String json = "{\"balance\": \"0\",\"holder\": \"Kalle\"}"; Testdata
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Account2{

        private final long balance;
        private final String holder;

        @JsonCreator
        public Account2(@JsonProperty("balance") long balance, @JsonProperty("holder") String holder) {
            this.balance = balance;
            this.holder = holder;
        }

        public long getBalance() {
            return balance;
        }

        public String getHolder() {
            return holder;
        }
    }

}
