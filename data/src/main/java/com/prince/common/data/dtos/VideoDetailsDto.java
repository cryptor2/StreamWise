package com.prince.data.commons.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoDetailsDto {
    private Date uploadDate;
    private Long videoId;
    private String videoTitle;
    private String videoDescription;
    private String videoFormat;
    private String videoPath;
    private Long fileSize;

}
