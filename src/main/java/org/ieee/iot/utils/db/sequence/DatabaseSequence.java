package org.ieee.iot.utils.db.sequence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*************************************************
 * Copyright (c) 2023-2-22 Abdullah Sayed Sallam.
 ************************************************/

@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

}