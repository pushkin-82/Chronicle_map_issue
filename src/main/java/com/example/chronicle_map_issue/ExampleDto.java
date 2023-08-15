package com.example.chronicle_map_issue;

import java.io.Serial;
import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1533692453113579626L;

    private int id;

    private int transactionId;
    
    public ExampleDto(int num) {
        this.id = num;
        this.transactionId = num;
    }
}
