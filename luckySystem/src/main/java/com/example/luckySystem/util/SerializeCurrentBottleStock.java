package com.example.luckySystem.util;
import com.example.luckySystem.dto.bottles.CurrentBottleStatusDTO;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SerializeCurrentBottleStock {

    /*
    * This class Created For Manage The Current Bottle Stock
    * Add The New Bottles For The Bottle Stock
    * Serialize and get DeSerialize Bottle
    * Manage Stock Easily without Database Transaction
    * */


    public synchronized  void serializebottleStock(CurrentBottleStatusDTO  currentBottleStatusDTO) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("currentBottleStock.ser"))) {
            oos.writeObject(currentBottleStatusDTO);
            System.out.println("User currentBottleStatusDTO serialized successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred during serialization: " + e.getMessage());
        }
    }

    synchronized public CurrentBottleStatusDTO deserializebottleStock() {

        CurrentBottleStatusDTO decurrentBottleStatusDTO = new CurrentBottleStatusDTO();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("currentBottleStock.ser"))) {

            decurrentBottleStatusDTO = (CurrentBottleStatusDTO) ois.readObject();


            System.out.println("User currentBottleStatusDTO deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error occurred during deserialization: " + e.getMessage());
        }
        return decurrentBottleStatusDTO;
    }

}
