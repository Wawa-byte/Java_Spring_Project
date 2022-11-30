package org.Waleed.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    private int directorId;
    private String fName;
    private String lName;
    private boolean stillActive;
}
