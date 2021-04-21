package com.ssy.api.SQLservice.dto.face;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddFaceDto {
    Integer faceId;
    String faceName;
    Integer faceSetId;
    String faceSetName;

}
