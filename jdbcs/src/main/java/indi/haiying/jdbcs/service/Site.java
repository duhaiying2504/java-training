package indi.haiying.jdbcs.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Site {

    private Integer siteID;

    private String siteName;

    private String creator;

    private LocalDateTime createTime;

}
