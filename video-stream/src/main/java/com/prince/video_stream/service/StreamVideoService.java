package com.prince.video_stream.service;

import org.springframework.core.io.Resource;

public interface StreamVideoService {
    Resource getMasterFile(Long videoID, String quality);
    Resource getMasterFile(Long videoID);
    Resource getSegment(Long videoId,String quality, String segment);
}
