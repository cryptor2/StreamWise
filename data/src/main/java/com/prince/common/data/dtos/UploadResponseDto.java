package com.prince.common.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UploadResponseDto {
    private String videoTitle;
    private String videoDescription;
    private String message;
    private Boolean isUploaded;
}
