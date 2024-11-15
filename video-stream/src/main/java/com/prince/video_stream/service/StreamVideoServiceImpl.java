package com.prince.video_stream.service;

import com.prince.video_stream.feign.VideoServiceFeignClient;
import org.springframework.core.io.FileSystemResource;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class StreamVideoServiceImpl implements StreamVideoService{
    VideoServiceFeignClient videoServiceFeignClient;

    public StreamVideoServiceImpl(VideoServiceFeignClient videoServiceFeignClient) {
        this.videoServiceFeignClient = videoServiceFeignClient;
    }
    public Resource getMasterFile(Long videoID, String quality){
        String path = videoServiceFeignClient.getVideoPath(videoID).getBody();
        System.out.println(path);
        Path p = Paths.get(path, quality+".m3u8");
        System.out.println(p.toString());

        Resource resource = new FileSystemResource(p);
        return resource;
    }
    public Resource getMasterFile(Long videoID){

        String path = videoServiceFeignClient.getVideoPath(videoID).getBody();
        Path p = Paths.get(path.toString(), "master.m3u8");
        System.out.println(p.toString());

        Resource resource = new FileSystemResource(p);
        return resource;
    }
    public Resource getSegment(Long videoId,String quality, String segment){
        String path = videoServiceFeignClient.getVideoPath(videoId).getBody();
        Path p = Paths.get(path.toString()+"/"+quality+"/"+segment+".ts");
        Resource resource = new FileSystemResource(p);
        return resource;
    }
}
