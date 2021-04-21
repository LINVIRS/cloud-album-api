package com.ssy.api.SQLservice.dto.face;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FaceStoreDto {
    Integer faceStoreId;
    String description;
}
