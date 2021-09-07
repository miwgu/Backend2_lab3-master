package se.nackademin.java20.lab1.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import se.nackademin.java20.lab1.x_regeringen.RestRegeringClientTest;

import java.io.File;
import java.io.IOException;

public class AccountDeserialized_SerializedTest {
    private final String json = "{\"balance\":0,\"holder\":\"Kalle\"}";

    @Test
    //Deseralize -->Att gå från Json till ett Javaobjekt
    void deserialize() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        AccountDeserialized_Serialized.Account2 account=objectMapper.readValue(json, AccountDeserialized_Serialized.Account2.class);

        Assertions.assertEquals(account.getHolder(),"Kalle");
        Assertions.assertEquals(account.getBalance(),0);
    }

    @Test
        //Deseralize -->Att gå från ett Javaobjekt till Json
    void serialize() throws IOException{
        ObjectMapper objectMapper= new ObjectMapper();

        AccountDeserialized_Serialized.Account2 account= new AccountDeserialized_Serialized.Account2(0,"Kalle");
        //Account account= new Account("Kalle",0); //Account class
        objectMapper.writeValue(new File("account.json"),account);// Save file
        String accountAsString = objectMapper.writeValueAsString(account);

        System.out.println(accountAsString);
        // If you test it with Account class
        //Assertions.assertEquals(accountAsString),"{\"id\":0,\"balance\":0,\"holder\":\"Kalle\"}";
        Assertions.assertEquals(accountAsString,"{\"balance\":0,\"holder\":\"Kalle\"}");
        //"{\"balance\":\"0\",\"holder\":\"Kalle\"}"--> 0  you shouldnt write \"0\"
    }

    /*
    * Serialize
    * ObjektMappar
    * instanser New Object
    * do jasonObject-> objectMapper.writeValueAsString(new object)
    * */
    @Test
    void serialize2() throws IOException{
        ObjectMapper objectMapper= new ObjectMapper();
        AccountDeserialized_Serialized.Account2 account= new AccountDeserialized_Serialized.Account2(0,"Kalle");

        String jsonObject = objectMapper.writeValueAsString(account);
        System.out.println(jsonObject);

        Assertions.assertEquals(jsonObject,json);

    }

    void serializeXXX() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        RestRegeringClientTest.Animal katt = new RestRegeringClientTest.Animal();
        katt.setDjur("katt");

        String jsonObject = objectMapper.writeValueAsString(katt);
        System.out.println(jsonObject);
        Assertions.assertEquals(json, jsonObject);
    }
}
