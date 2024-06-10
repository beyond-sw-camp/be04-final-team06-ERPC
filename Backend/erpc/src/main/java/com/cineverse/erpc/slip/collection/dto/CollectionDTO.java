package com.cineverse.erpc.slip.collection.dto;

import com.cineverse.erpc.slip.collection.aggregate.DepositCategory;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CollectionDTO {
    private long collectionId;
    private String depositAccount;
    private long depositPrice;
    private String depositDate;
    private String depositPic;
    private DepositCategory depositCategory;
    private String depositCode;
}
